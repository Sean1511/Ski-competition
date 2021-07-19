/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */

package game.entities.sportsman.Decorator;

import game.entities.sportsman.IWinterSportsman;
import game.entities.sportsman.WinterSportsman;

/**
 * Class of decorated speedy sports man
 */
public class SpeedySportsman extends WSDecorator {
    private double acceleration;

    /**
     * Ctor for decorated speedy sports man
     * @param WsDecorator
     * @param acceleration the new acceleration to set
     */
    public SpeedySportsman (IWinterSportsman WsDecorator, double acceleration){
        super(WsDecorator);
        this.setAcceleration(acceleration);
    }

    /**
     * @param acceleration the new acceleration to set
     */
    @Override
    public void setAcceleration(double acceleration) {
        this.acceleration += acceleration +((WinterSportsman)WsDecorator).getAcceleration();
        WsDecorator.setAcceleration(this.acceleration);
    }
}
