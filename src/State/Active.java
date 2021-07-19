/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */
package State;

import game.entities.sportsman.WinterSportsman;

/**
 * Active competitor class
 */
public class Active implements State {

    @Override
    public void doAction(WinterSportsman competitor) {
        competitor.setState(this);
    }


    @Override
    public String toString() {
        return ("Active");
    }
}
