/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */

package game.arena;

import game.enums.SnowSurface;
import game.enums.WeatherCondition;


/**
 * A Factory builder class
 */
public class FactoryArena {

    /**
     * Default factory builder
     */
    public static Arena buildDefaultArena(){
        return new WinterArena();
    }

    /**
     * Non default factory builder
     * @param name of the arena
     * @param length of the arena
     * @param surface of the arena
     * @param condition of the arena
     */
    public static Arena buildArena(String name, double length, SnowSurface surface, WeatherCondition condition){
        if (name == "Winter")
            return new WinterArena(length,surface,condition);
        else if (name == "Summer")
            return new SummerArena(length,surface,condition);
        else return null;
    }
}
