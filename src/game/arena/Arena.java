/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */

package game.arena;

import game.entities.IMobileEntity;
import game.enums.SnowSurface;
import game.enums.WeatherCondition;
import utilities.ValidationUtils;

public abstract class Arena implements IArena {
    private double length;
    private SnowSurface surface;

    /**
     * Ctor for a arena
     * @param length length of the arena
     * @param surface surface of the arena
     */
    public Arena(double length, SnowSurface surface) {
        try{
            this.setLength(length);
            this.setSurface(surface);
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace(System.out);
        }
    }

    //region Getters

    /**
     * @return the length value of the arena
     */
    public double getLength() {
        return length;
    }

    /**
     * @return the surface type of the arena
     */
    public SnowSurface getSurface() {
        return surface;
    }

    /**
     * @return the friction value of the arena
     */
    public double getFriction(){
        return this.getSurface().getFriction();
    }

    public abstract WeatherCondition getCondition();

    //endregion

    //region Setters

    /**
     * @param length the length value of the arena
     * @throws IllegalArgumentException if length is negative
     */
    public void setLength(double length) throws IllegalArgumentException {
        ValidationUtils.assertNotNegative(length);
        this.length = length;
    }

    /**
     * @param surface the surface type of the arena
     * @throws IllegalArgumentException if surface is null
     */
    public void setSurface(SnowSurface surface) throws IllegalArgumentException{
        ValidationUtils.assertNotNull(surface);
        this.surface = surface;
    }

    //endregion

    /**
     * @param me IMobileEntity object
     * @return true if the object crossed the finish line
     */
    public boolean isFinished(IMobileEntity me){
        return me.getLocation().getX() >= getLength();
    }

    /**
     * @return the name of the arena
     */
    public String toString() {
        return ("Arena ");
    }
}


