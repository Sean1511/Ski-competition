/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */

package game.entities.sportsman.Decorator;

import game.entities.sportsman.IWinterSportsman;

/**
 * Class of decorated colored sports man
 */
public class ColoredSportsman extends WSDecorator {
    private String color;

    /**
     * Ctor for decorated colored sports man
     * @param WsDecorator
     * @param color the new color to set
     */
    public ColoredSportsman(IWinterSportsman WsDecorator, String color){
        super(WsDecorator);
        this.color = null;
        this.setColor(color);
    }

    /**
     * @param color the new color to set
     */
    @Override
    public void setColor(String color) {
        this.color = color;
        WsDecorator.setColor(this.color);
    }
}
