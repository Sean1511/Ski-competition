/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */
package game.competition;

import game.arena.FactoryArena;
import game.arena.WinterArena;
import game.entities.sportsman.Skier;
import game.entities.sportsman.WinterSportsman;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;
import utilities.ValidationUtils;

import java.util.Observable;
import java.util.Observer;

/**
 * Class of ski competition
 */
public class SkiCompetition extends WinterCompetition {

    /**
     * Default Ctor for ski competition
     */
    public SkiCompetition(){
        super(FactoryArena.buildDefaultArena(),20,Discipline.SLALOM,League.JUNIOR,Gender.MALE);
    }

    /**
     * Non default ctor for SkiCompetition
     * @param arena length of the arena
     * @param maxCompetitors the max value of competitors that can race
     * @param discipline the discipline of the arena
     * @param league the league that the competitor can enter to
     * @param gender the gender of the competitor
     */
    public SkiCompetition(WinterArena arena, int maxCompetitors, Discipline discipline, League league, Gender gender) throws IllegalArgumentException {
        super(arena,maxCompetitors,discipline,league,gender);
    }

    /**
     * @param competitor object of competitor
     * @return true if the competitor is vaild for the race
     */
    protected boolean isValidCompetitor(Competitor competitor) throws IllegalArgumentException{
        ValidationUtils.assertNotNull(competitor);
        if (competitor instanceof Skier && getLeague().isInLeague(((WinterSportsman) competitor).getAge()) && this.getGender() == ((WinterSportsman) competitor).getGender() && this.getDiscipline() == ((WinterSportsman) competitor).getDiscipline())
            return true;
        return false;
    }

    /**
     * @return name of the competition
     */
    public String toString(){
        return ("Ski");
    }
}
