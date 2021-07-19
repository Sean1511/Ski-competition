/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */
package game.competition;

import game.entities.IMobileEntity;
import utilities.Point;

/**
 * Interface for Competitor
 */
public interface Competitor extends IMobileEntity, Runnable, Cloneable {
    void initRace(Point p, Competition competition);
    void run();
    Competitor Clone();
    String getColor();
}
