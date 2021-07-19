/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */
package game.competition;
import State.Completed;
import State.Disabled;
import game.arena.IArena;
import game.arena.WinterArena;
import game.entities.sportsman.IWinterSportsman;
import game.entities.sportsman.WinterSportsman;
import utilities.ValidationUtils;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import utilities.Point;

/**
 * Abstract class of the competition
 */
public abstract class Competition implements Observer {
    private IArena arena;
    private int maxCompetitors;
    private ArrayList<Competitor>activeCompetitors;
    private ArrayList<Competitor>finishedCompetitors;
    private ArrayList<Competitor>disabledCompetitors;
    private double y=0;

    /**
     * Ctor for Competition
     * @param arena length of the arena
     * @param maxCompetitors the max value of competitors that can race
     */
    protected Competition(IArena arena, int maxCompetitors) {
        try{
            this.setArena(arena);
            this.setMaxCompetitors(maxCompetitors);
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace(System.out);
        }
        setActiveCompetitors();
        setFinishedCompetitors();
        setDisabledCompetitors();
    }

    //region Getters

    /**
     * @return the arena name
     */
    public IArena getArena() {
        return arena;
    }

    /**
     * @return the max competitors value
     */
    public int getMaxCompetitors() {
        return maxCompetitors;
    }

    /**
     * @return the active competitors array
     */
    public ArrayList<Competitor> getActiveCompetitors() {
        synchronized (activeCompetitors){
            return activeCompetitors;
        }
    }

    /**
     * @return the finished competitors array
     */
    public ArrayList<Competitor> getFinishedCompetitors() {
        synchronized (finishedCompetitors){
            return finishedCompetitors;
        }
    }


    /**
     * @return the disabled competitors array
     */
    public ArrayList<Competitor> getDisabledCompetitors() {
        synchronized (disabledCompetitors) {
            return disabledCompetitors;
        }
    }


    /**
     * @return the arena length
     */
    public double getArenaLength(){
        return ((WinterArena)arena).getLength();
    }


    //endregion

    //region Setters

    /**
     * @param arena the arena type
     * @throws IllegalArgumentException if arena is null
     */
    public void setArena(IArena arena) throws IllegalArgumentException {
        ValidationUtils.assertNotNull(arena);
        this.arena = arena;
    }

    /**
     * @param maxCompetitors the max value of competitors that can race
     * @throws IllegalArgumentException if maxCompetitors value is negative
     */
    public void setMaxCompetitors(int maxCompetitors) throws IllegalArgumentException {
        ValidationUtils.assertPositive(maxCompetitors);
        this.maxCompetitors = maxCompetitors;
    }

    /**
     * Create new array for active competitors
     */
    public void setActiveCompetitors() {
        this.activeCompetitors = new ArrayList<>();
    }

    /**
     * Create new array for finished competitors
     */
    public void setFinishedCompetitors() {
        this.finishedCompetitors = new ArrayList<>();
    }

    /**
     * Create new array for finished competitors
     */
    public void setDisabledCompetitors() {
        this.disabledCompetitors = new ArrayList<>();
    }

    //endregion

    /**
     * @param competitor the competitor object
     * @return true if the competitor is vaild for the race
     */
    protected abstract boolean isValidCompetitor(Competitor competitor);

    /**
     * @param competitor the competitor object
     * add competitor to the race
     */
    public void addCompetitor(Competitor competitor){
        if (isFull())
            throw new IllegalStateException (arena+ "is full max = "+getMaxCompetitors());
        if (!isValidCompetitor(competitor))
            throw new IllegalArgumentException ("Invalid competitor "+competitor.toString());
        competitor.initRace(new Point(0, y),this);
        y += 65;
        this.activeCompetitors.add(competitor);
        ((WinterSportsman)competitor).destiny();
    }

    /**
     * Move the competitor in his turn
     */
    public void startRace() {
        for (Competitor comp : activeCompetitors) {
            Thread t = new Thread(comp);
            t.start();
        }
    }

    /**
     * @return true if the active competitors array is not empty
     */
    public boolean hasActiveCompetitors(){
        synchronized (activeCompetitors) {
            if (!activeCompetitors.isEmpty())
                return true;
            return false;
        }
    }

    /**
     * @throws IllegalArgumentException if the arena is full
     */
    public boolean isFull() throws IllegalStateException {
        return activeCompetitors.size() >= getMaxCompetitors();
    }

    /**
     * @update for observer
     */
    @Override
    public void update(Observable o, Object arg) {
        if (((WinterSportsman) o).getCurrentState() instanceof Completed) {
            synchronized (this) {
                activeCompetitors.remove((Competitor) o);
                finishedCompetitors.add((Competitor) o);
            }
        } else if (((WinterSportsman) o).getCurrentState() instanceof Disabled) {
            synchronized (this) {
                activeCompetitors.remove((Competitor) o);
                disabledCompetitors.add((Competitor) o);
            }
        }
    }

    /**
     * @param i active competitor
     * @param index The position in the array to replace
     */
    public void setCompetitor(Competitor i, int index){
        i = ((IWinterSportsman)i).getWintersportsman();
        activeCompetitors.remove(index);
        activeCompetitors.add(index,(i));
    }

    /**
     * Abstract method toSting
     */
    public abstract String toString();
}
