/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */
package game.entities;

import game.arena.WinterArena;
import game.competition.Competition;
import game.entities.sportsman.WinterSportsman;
import utilities.Point;
import utilities.ValidationUtils;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * abstract class of mobile entity
 */
public abstract class MobileEntity extends Entity implements IMobileEntity {
    private double maxSpeed;
    private double acceleration;
    private double speed;
    private Competition competition;
    private ArrayList<Observer> observers;

    /**
     * Ctor for MobileEntity
     * @param maxSpeed the max speed value for entity
     * @param acceleration the acceleration value for entity
     */
    protected MobileEntity (double maxSpeed, double acceleration) throws IllegalArgumentException{
        super(new Point());
        this.setSpeed(0);
        this.setAcceleration(acceleration);
        this.setMaxSpeed(maxSpeed);
        this.setObservers();
    }

    //region Getters
    /**
     * @return the max speed of the competitor
     */
    public double getMaxSpeed() { return maxSpeed; }

    /**
     * @return the acceleration value of the competitor
     */
    public double getAcceleration() { return acceleration; }

    /**
     * @return the current speed of the competitor
     */
    public double getSpeed() { return speed; }

    public Competition getCompetition () { return competition; }
    //endregion

    //region Setters

    /**
     * @param maxSpeed the max speed of the competitor
     * @throws IllegalArgumentException if maxSpeed is negative
     */
    public void setMaxSpeed(double maxSpeed) throws IllegalArgumentException {
        ValidationUtils.assertPositive(maxSpeed);
        this.maxSpeed = maxSpeed;
    }

    /**
     * @param acceleration the acceleration value of the competitor
     * @throws IllegalArgumentException if acceleration is negative
     */
    public void setAcceleration(double acceleration) throws IllegalArgumentException {
        ValidationUtils.assertPositive(acceleration);
        this.acceleration = acceleration;
    }

    /**
     * @param speed the speed of the competitor
     * @throws IllegalArgumentException if speed is negative
     */
    public void setSpeed(double speed) throws IllegalArgumentException{
        ValidationUtils.assertNotNegative(speed);
        if (speed <= this.getMaxSpeed())
            this.speed = speed;
        else
            this.speed = getMaxSpeed();
    }

    /**
     * initialize array list for observers
     */
    public void setObservers() {
        this.observers = new ArrayList<>();
    }

    /**
     * @param competition the speed of the competitor
     * set competition for mobile entity object
     */
    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    //endregion

    /**
     * Move the competitor in the race
     * @throws IllegalArgumentException if speed is negative
     */
    public void move()throws IllegalArgumentException {
        double length = competition.getArenaLength();
        Point p;
        this.setSpeed(this.speed + (this.getAcceleration()*(1-competition.getArena().getFriction())));
        for (int i = 0; i<this.getSpeed();i++) {
            if (this.getLocation().getX() + 1 > length) {
                p = new Point(length, this.getLocation().getY());
                this.setLocation(p);
                ((WinterSportsman)this).getCompletedState().doAction((WinterSportsman)this);
                break;
            }
            else if (this.getLocation().getX() == ((WinterSportsman)this).getInjuredLocation()){
                ((WinterSportsman)this).getInjuredState().doAction((WinterSportsman)this);
                p = new Point(this.getLocation().offset(1, 0));
                this.setLocation(p);
                break;
            }
            else {
                p = new Point(this.getLocation().offset(1, 0));
            }
            this.setLocation(p);
            notifyObservers();
        }
    }

    /**
     * add the observer to tha observers array list
     */
    public void registerObserver(Observer o){
        observers.add(o);
    }

    /**
     * runs update function for competition observer
     */
    public void notifyCompetition(){
        competition.update(this,null);
    }

    /**
     * runs update function for other observers
     */
    public void notifyObservers(){
        for (Observer o : observers){
            o.update(this,null);
        }
    }
}
