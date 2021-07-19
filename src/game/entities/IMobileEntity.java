/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */
package game.entities;

import utilities.Point;

/**
 * Interface for IMobileEntity
 */
public interface IMobileEntity {
    void move();
    Point getLocation ();
}
