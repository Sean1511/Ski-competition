/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */
package Gui;

import game.enums.*;
import utilities.ValidationUtils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * Class for arena panel
 */
public class PanelArena extends JPanel {

    private JLabel buildArena;
    private JLabel chooseArena;
    private JLabel length;
    private JLabel surface;
    private JLabel weather;
    private JTextField len;
    private JComboBox sur;
    private JComboBox condition;
    private JComboBox arenaType;
    private JButton buildBtn;

    /**
     * Ctor for arena panel
     */
    public PanelArena(){
        setPreferredSize(new Dimension(270,200));
        setBuildArena();
        setChooseArena();
        setArenaType();
        setLength();
        setSurface();
        setWeather();
        setLen();
        setSur();
        setCondition();
        setBuildBtn();
        makeLayout();
    }

    //region Setters

    public void setBuildArena() {
        this.buildArena = new JLabel("<html><u>BUILD ARENA</u></html>");
        this.buildArena.setForeground(Color.BLUE);
    }

    public void setLength() {
        this.length = new JLabel("Arena length:");
    }

    public void setSurface() {
        this.surface = new JLabel("Snow surface:");
    }

    public void setWeather() {
        this.weather = new JLabel("Weather condition:");
    }

    public void setLen(){
        this.len = new JTextField(14);
        this.len.setText("700");
    }

    public void setSur() {
        this.sur = new JComboBox();
        this.sur.setPreferredSize(new Dimension(140,20));
        this.sur.addItem(SnowSurface.POWDER);
        this.sur.addItem(SnowSurface.CRUD);
        this.sur.addItem(SnowSurface.ICE);
    }

    public void setCondition() {
        this.condition = new JComboBox();
        this.condition.setPreferredSize(new Dimension(145,20));
        this.condition.addItem(WeatherCondition.STORMY);
        this.condition.addItem(WeatherCondition.CLOUDY);
    }

    public void setBuildBtn() {
        this.buildBtn = new JButton("Build arena");
        this.buildBtn.setPreferredSize(new Dimension(140,25));
    }

    public void setChooseArena() {
        this.chooseArena = new JLabel("Arena Type:");
    }

    public void setArenaType() {
        this.arenaType = new JComboBox();
        this.arenaType.setPreferredSize(new Dimension(145,20));
        this.arenaType.addItem("Winter");
        this.arenaType.addItem("Summer");
        this.arenaType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (arenaType.getSelectedItem() == "Winter"){
                    condition.removeAllItems();
                    condition.addItem(WeatherCondition.STORMY);
                    condition.addItem(WeatherCondition.CLOUDY);
                }
                else if (arenaType.getSelectedItem() == "Summer"){
                    condition.removeAllItems();
                    condition.addItem(WeatherCondition.SUNNY);
                }
            }
        });
    }

    //endregion

    //region Getters

    public int getLen() throws NumberFormatException, IllegalArgumentException{
        int n = Integer.parseInt(len.getText());
        ValidationUtils.assertInRange(n,700,900);
        return n;
    }

    public SnowSurface getSur() {
        return (SnowSurface)sur.getSelectedItem();
    }

    public WeatherCondition getCondition() {
        return (WeatherCondition) condition.getSelectedItem();
    }

    public JButton getBuildBtn() {
        return buildBtn;
    }

    public String getArenaType() {
        return (String) arenaType.getSelectedItem();
    }

    //endregion

    /**
     * set layout for the panel
     */
    public void makeLayout() {
        setLayout(new GridBagLayout());
        Border a = BorderFactory.createTitledBorder("");
        Border b = BorderFactory.createEmptyBorder(2,2,0,5);
        setBorder(BorderFactory.createCompoundBorder(a,b));
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx =0.1;
        gc.weighty = 0.5;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridx = 0;
        gc.gridy = 0;
        add(buildArena,gc);
        gc.gridy = 1;
        add(length,gc);
        gc.gridy = 2;
        add(len,gc);
        gc.gridy = 3;
        add(surface,gc);
        gc.gridy = 4;
        add(sur,gc);
        gc.gridx = 1;
        gc.gridy = 1;
        add(chooseArena,gc);
        gc.gridy = 2;
        add(arenaType,gc);
        gc.gridy = 3;
        add(weather,gc);
        gc.gridy = 4;
        add(condition,gc);
        gc.gridx = 0;
        gc.gridy = 5;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(buildBtn,gc);
    }
}


