/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */
package game.competition;

import game.arena.WinterArena;
import game.entities.sportsman.Snowboarder;
import game.entities.sportsman.WinterSportsman;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;

import java.util.Observable;

/**
 * Class of snowboard competition
 */
public class SnowboardCompetition extends WinterCompetition {

    /**
     * Ctor for SnowboardCompetition
     * @param arena length of the arena
     * @param maxCompetitors the max value of competitors that can race
     * @param discipline the discipline of the arena
     * @param league the league that the competitor can enter to
     * @param gender the gender of the competitor
     */
    public SnowboardCompetition (WinterArena arena, int maxCompetitors, Discipline discipline, League league, Gender gender) throws IllegalArgumentException {
        super(arena,maxCompetitors,discipline,league,gender);
    }

    /**
     * @param competitor object of competitor
     * @return true if the competitor is vaild for the race
     */
    public boolean isValidCompetitor(Competitor competitor) {
        if (competitor instanceof Snowboarder && getLeague().isInLeague(((WinterSportsman) competitor).getAge()) && this.getGender() == ((WinterSportsman) competitor).getGender() && this.getDiscipline() == ((WinterSportsman) competitor).getDiscipline())
            return true;
        return false;
    }

    /**
     * @return name of the competition
     */
    public String toString(){
        return ("Snowboard");
    }
}
