/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */
package State;

import game.entities.sportsman.WinterSportsman;

import java.util.Random;

/**
 * Disabled competitor class
 */
public class Injured implements State {

    @Override
    public void doAction(WinterSportsman competitor) {
        competitor.setState(this);
        Random rand = new Random();
        int n = rand.nextInt(3);
        if (n == 0){
            competitor.getDisabledState().doAction(competitor);
        }
    }

    @Override
    public String toString() {
        return ("Injured");
    }
}
