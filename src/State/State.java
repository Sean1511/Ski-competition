/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */
package State;

import game.entities.sportsman.WinterSportsman;

/**
 * Interface for State
 */
public interface State {
    void doAction(WinterSportsman competitor);
}
