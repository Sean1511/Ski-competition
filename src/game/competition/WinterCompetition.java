/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */
package game.competition;

import game.arena.Arena;
import game.arena.WinterArena;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;
import utilities.ValidationUtils;

/**
 * abstract class of winter competition
 */
public abstract class WinterCompetition extends Competition {
    private Discipline discipline;
    private League league;
    private Gender gender;

    /**
     * Ctor for WinterCompetition
     * @param arena length of the arena
     * @param maxCompetitors the max value of competitors that can race
     * @param discipline the discipline of the arena
     * @param league the league that the competitor can enter to
     * @param gender the gender of the competitor
     */
    protected WinterCompetition(Arena arena, int maxCompetitors, Discipline discipline, League league, Gender gender) throws IllegalArgumentException{
        super(arena, maxCompetitors);
        this.setDiscipline(discipline);
        this.setLeague(league);
        this.setGender(gender);
    }

    //region Getters

    /**
     * @return the discipline of the arena
     */
    public Discipline getDiscipline() {
        return discipline;
    }

    /**
     * @return the league name
     */
    public League getLeague() {
        return league;
    }

    /**
     * @return the gender of the competitor
     */
    public Gender getGender() { return gender; }

    //endregion

    //region Setters

    /**
     * @param discipline the discipline of the arena
     * @throws IllegalArgumentException if discipline is null
     */
    public void setDiscipline(Discipline discipline) throws IllegalArgumentException {
        ValidationUtils.assertNotNull(discipline);
        this.discipline = discipline;
    }

    /**
     * @param league the league name
     * @throws IllegalArgumentException if league is null
     */
    public void setLeague(League league) throws IllegalArgumentException{
        ValidationUtils.assertNotNull(league);
        this.league = league;
    }

    /**
     * @param gender the gender of the competitor
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    //endregion

    /**
     * @param competitor the competitor object
     * @return true if the competitor is vaild for the race
     */
    protected abstract boolean isValidCompetitor(Competitor competitor);
}
