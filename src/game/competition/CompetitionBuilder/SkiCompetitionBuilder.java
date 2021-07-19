/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */

package game.competition.CompetitionBuilder;

import Gui.MainFrame;
import game.arena.FactoryArena;
import game.competition.Competition;
import game.competition.Competitor;
import game.competition.SkiCompetition;
import game.entities.sportsman.Skier;

/**
 * Class for ski competition builder
 */
public class SkiCompetitionBuilder implements CompetitionBuilder {
    private Competition competition;
    private MainFrame gui;
    private int N;

    /**
     * Ctor for ski competition builder
     * @param N number of competitors
     * @param gui observer
     */
    public SkiCompetitionBuilder(int N, MainFrame gui) {
        this.competition = new SkiCompetition();
        this.N = N;
        this.gui = gui;
        BuildCompetitors();
    }

    /**
     * Implement of method build arena
     */
    @Override
    public void BuildArena() {
        competition.setArena(FactoryArena.buildDefaultArena());
    }

    /**
     * Implement of build competitors
     */
    @Override
    public void BuildCompetitors() {
        Competitor c = new Skier();
        competition.addCompetitor(c);
        ((Skier) c).registerObserver(gui);
        for (int i = 0; i < N-1;i++){
            competition.addCompetitor(c.Clone());
            ((Skier) c).registerObserver(gui);
        }
    }

    /**
     * @return the competition
     */
    public Competition getCompetition(){
        return competition;
    }
}
