/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */

package game.entities.sportsman.Decorator;

import game.competition.Competition;
import game.competition.Competitor;
import game.entities.sportsman.IWinterSportsman;
import game.entities.sportsman.WinterSportsman;
import utilities.Point;

/**
 * abstract class for winter sportsman decorator
 */
public abstract class WSDecorator implements IWinterSportsman {
    protected IWinterSportsman WsDecorator;

    /**
     * Ctor for Ws decorator
     * @param WsDecorator
     */
    public WSDecorator (IWinterSportsman WsDecorator){
        this.WsDecorator = WsDecorator.getWintersportsman();
    }

    /**
     * @param acceleration to set
     */
    public void setAcceleration(double acceleration){
        this.WsDecorator.setAcceleration(acceleration);
    }

    /**
     * @param color to set
     */
    public void setColor (String color){
        this.WsDecorator.setColor(color);
    }

    /**
     * @return casting for winter sports man
     */
    public WinterSportsman getWintersportsman (){
        return (WinterSportsman)this.WsDecorator;
    }

    // Implement Override methods

    @Override
    public void initRace(Point p, Competition competition) {
        WsDecorator.initRace(WsDecorator.getLocation(),competition);
    }

    @Override
    public void run() {
        WsDecorator.run();
    }

    @Override
    public Competitor Clone() {
        return WsDecorator.Clone();
    }

    @Override
    public void move() {
        WsDecorator.move();
    }

    @Override
    public Point getLocation() {
        return WsDecorator.getLocation();
    }

    public String getColor(){
        return WsDecorator.getColor();
    }
}
