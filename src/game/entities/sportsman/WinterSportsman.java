/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */
package game.entities.sportsman;
import game.competition.Competition;
import State.*;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;
import utilities.ValidationUtils;
import utilities.Point;
import java.util.Random;

/**
 * abstract class of winter sportsman
 */
public abstract class WinterSportsman extends sportsman implements IWinterSportsman {
    private Discipline discipline;
    private String color;
    private String id;
    public static int ID = 0;
    private State currentState = new Active();
    private final State activeState = new Active();
    private final State injuredState = new Injured();
    private final State disabledState = new Disabled();
    private final State completedState = new Completed();
    private int injuredLocation = -1;

    /**
     * Ctor for WinterSportsman
     * @param name the name of the competitor
     * @param age the age of the competitor
     * @param gender the gender of the competitor
     * @param acceleration the acceleration of the competitor
     * @param maxSpeed the max speed of the competitor
     * @param discipline the discipline of the arena
     */
    public WinterSportsman (String name, double age, Gender gender, double acceleration, double maxSpeed, Discipline discipline, String color) throws IllegalArgumentException  {
        super(name, age, gender, acceleration + League.calcAccelerationBonus(age), maxSpeed);
        this.setDiscipline(discipline);
        this.color = color;
        this.id = Integer.toString(++ID);
        currentState = activeState;
    }

    //region Getters

    /**
     * @return the discipline of the arena
     */
    public Discipline getDiscipline() {
        return discipline;
    }

    /**
     * @return the color of the competitor
     */
    public String getColor() {
        return color;
    }

    /**
     * @return the id of the competitor
     */
    public static int getID() {
        return ID;
    }

    /**
     * @return the id of the competitor
     */
    public String getId() {
        return id;
    }

    /**
     * @return active state
     */
    public State getActiveState() {
        return activeState;
    }

    /**
     * @return injured state
     */
    public State getInjuredState() {
        return injuredState;
    }

    /**
     * @return disabled state
     */
    public State getDisabledState() {
        return disabledState;
    }

    /**
     * @return completed state
     */
    public State getCompletedState() {
        return completedState;
    }

    /**
     * @return current state of the competitor
     */
    public State getCurrentState (){
        return this.currentState;
    }

    /**
     * @return the position that the player is assigned to be injured
     */
    public int getInjuredLocation (){ return injuredLocation; }


    //endregion

    //region Setters

    /**
     * @param discipline the discipline of the arena
     * @throws IllegalArgumentException if discipline is null
     */
    public void setDiscipline(Discipline discipline) throws IllegalArgumentException {
        ValidationUtils.assertNotNull(discipline);
        this.discipline = discipline;
    }

    /**
     * Set color for competitor
     * @param color for the competitor
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @param i unique id for competitor
     */
    public static void setID(int i) {
        ID = i;
    }

    /**
     * set the new state for the competitor and update the competition
     * @param newState
     */
    public void setState (State newState){
        this.currentState = newState;
        this.notifyCompetition();
    }


    //endregion

    /**
     * @param p point of the competitor
     * @param competition the competition
     * Puts the competitor in the given point
     */
    public void initRace(Point p, Competition competition){
        this.setLocation(new Point(p));
        this.setCompetition(competition);
    }

    /**

     * @returns data for sportsman
     */
    public String[] getData(){
        String [] data = {null,getName(), Double.toString(getSpeed()), Double.toString(getMaxSpeed()), Double.toString(getLocation().getX()),getCurrentState().toString()};
        return data;
    }

    /**
     * Override method run of the threads
     */
    @Override
    public void run() {
        while (!getCompetition().getArena().isFinished(this) && !isDisabled()){
            this.move();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @return clone for winter sportsman
     */
    public WinterSportsman Clone (){
        Object clone = null;

        try {
            clone = super.clone();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return (WinterSportsman)clone;
    }

    /**
     * Set acceleration for competitor
     * @param acceleration the acceleration value of the competitor
     */
    @Override
    public void setAcceleration(double acceleration) {
        super.setAcceleration(acceleration);
    }

    /**
     * @return winter competitor reference
     */
    public WinterSportsman getWintersportsman(){
        return this;
    }

    /**
     * Determines whether the player will be injured
     */
    public void destiny (){
        Random rand = new Random();
        int n = rand.nextInt(6);
        if (n == 0){
            n = rand.nextInt((int)getCompetition().getArenaLength()-50);
            this.injuredLocation = n + 50;
        }
    }

    /**
     *
     * @return true if the competitor is in disabled state
     */
    public Boolean isDisabled(){
        return currentState == disabledState;
    }
}
