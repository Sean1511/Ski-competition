/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */
package game.entities;

import utilities.Point;

import java.util.Observable;

/**
 * abstract class of Entity
 */
public abstract class Entity extends Observable {
    private Point location;

    /**
     * Non-Default Ctor for Entity
     * @param location point (x,y) for the entity
     */
    protected Entity (Point location) throws IllegalArgumentException{
        this.setLocation(location);
    }

    /**
     * Default Ctor for Entity
     */
    protected Entity (){
        this(new Point(0,0));
    }

    /**
     * @return the location of the entity
     */
    public Point getLocation() {
        return location;
    }

    /**
     * @param location point (x,y) for the entity
     * @throws IllegalArgumentException if point value(x,y) out of range
     */
    public void setLocation(Point location) throws IllegalArgumentException{
        this.location = new Point (location);
    }
}
