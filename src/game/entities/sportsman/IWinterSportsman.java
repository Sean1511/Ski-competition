/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */

package game.entities.sportsman;

import game.competition.Competitor;

/**
 * Interface for IWinter Sportsman
 */
public interface IWinterSportsman extends Competitor {
    void setColor (String color);
    void setAcceleration(double acceleration);
    WinterSportsman getWintersportsman();
}
