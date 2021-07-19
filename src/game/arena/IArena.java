/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */
package game.arena;

import game.entities.IMobileEntity;

/**
 * Interface for arena
 */
public interface IArena {
    double getFriction();
    boolean isFinished(IMobileEntity me);
}
