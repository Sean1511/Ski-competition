/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */

package game.arena;

import game.enums.SnowSurface;
import game.enums.WeatherCondition;
import utilities.ValidationUtils;

public class SummerArena extends Arena {
    private WeatherCondition condition;

    /**
     * Ctor for a winter arena
     * @param length length of the arena
     * @param surface surface of the arena
     * @param condition condition of the arena
     */
    public SummerArena(double length, SnowSurface surface, WeatherCondition condition) {
        super(length,surface);
        try{
            this.setCondition(condition);
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace(System.out);
        }
    }

    //region Getters

    /**
     * @return the condition of the arena
     */
    public WeatherCondition getCondition() {
        return condition;
    }


    //endregion

    //region Setters

    /**
     * @param condition the condition of the arena
     * @throws IllegalArgumentException if condition is null
     */
    public void setCondition(WeatherCondition condition) throws IllegalArgumentException {
        ValidationUtils.assertNotNull(condition);
        if (condition != WeatherCondition.SUNNY)
            throw new IllegalArgumentException ("Invalid weather condition");
        this.condition = condition;
    }

    //endregion

    /**
     * @return the name of the arena
     */
    public String toString() {
        return ("Summer Arena ");
    }
}


