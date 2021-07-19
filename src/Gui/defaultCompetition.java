/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */

package Gui;

import game.arena.Arena;
import game.competition.Competition;
import game.competition.CompetitionBuilder.SkiCompetitionBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class for default competition gui initialization
 */
public class defaultCompetition extends JFrame {
    private JPanel panel;
    private JComboBox selectNumber;
    private JButton create;
    private Competition competition;
    private MainFrame gui;

    /**
     * Ctor for default competition
     * @param competition reference for competition
     * @param gui observer
     */
    public defaultCompetition(Competition competition, MainFrame gui){
        super ("Create default competition");
        this.gui = gui;
        this.competition = competition;
        setLayout(null);
        panel = new JPanel();
        setSelectNumber();
        setCreate();
        panel.setBounds(0,22,330,150);
        add(panel);
        setBounds(450,250,330,150);
        setVisible(true);
    }

    /**
     * set for select number of default competitors combo box
     */
    public void setSelectNumber() {
        this.selectNumber = new JComboBox();
        this.selectNumber.setPreferredSize(new Dimension(200,25));
        selectNumber.addItem("Select number of competitors");
        for (int i = 1; i<=20; i++){
            selectNumber.addItem(i);
        }
        panel.add(selectNumber);
    }

    /**
     * set for create default competition button
     */
    public void setCreate() {
        this.create = new JButton("Build competition");
        panel.add(create);
        this.create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectNumber.getSelectedIndex() == 0){
                    JOptionPane.showMessageDialog(null, "Please select number of competitors","Message",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                SkiCompetitionBuilder builder = new SkiCompetitionBuilder((int)selectNumber.getSelectedItem(),gui);
                competition = builder.getCompetition();
                gui.setCompetition(builder.getCompetition());
                gui.setArena((Arena)competition.getArena());
                gui.setBackground();
                gui.setImage("Blue");
                int w = competition.getActiveCompetitors().size()*65;
                if (w>840 || w >= gui.canvas.getWidth()){
                    gui.panelSide.setBounds(competition.getActiveCompetitors().size()*65,0,308,700);
                    gui.canvas.setBounds(0,0,competition.getActiveCompetitors().size()*65,(int)competition.getArenaLength()+30);
                }
                else{
                    gui.canvas.setBounds(0,0,840,(int)competition.getArenaLength());
                    gui.panelSide.setBounds(840,0,305,700);
                }
                gui.panelCompetitor.setNum(competition.getActiveCompetitors().size()+1);
                gui.setBounds(0,0,gui.panelSide.getWidth()+gui.canvas.getWidth()+10,730);
                gui.repaint();
                defaultCompetition.this.dispose();
            }
        });
    }
}
