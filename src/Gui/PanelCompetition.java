/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */
package Gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

import game.enums.*;
import utilities.ValidationUtils;

/**
 * Class for competition panel
 */
public class PanelCompetition extends JPanel {
	
	private JLabel title;
	private JLabel choose;
	private JLabel maxNumber;
	private JLabel discipline;
	private JLabel league;
	private JLabel gender;
	private JComboBox comp;
	private JComboBox discp;
	private JComboBox leag;
	private JComboBox gen;
	private JTextField compNum;
	private JButton createbtn;
	private JButton defComp;

	/**
	 * Ctor for competition panel
	 */
    public PanelCompetition() {
        setPreferredSize(new Dimension(160,320));
        this.setTitle();
        this.setChoose();
        this.setMaxNumber();
        this.setDiscipline();
        this.setLeague();
        this.setGender();
        this.setComp();
        this.setDiscp();
        this.setLeag();
        this.setGen();
        this.setCompNum();
        this.setCreateBtn();
        this.setDefComp();
        this.makeLayout();
	}

	//region Setters

	public void setTitle() {
		this.title = new JLabel("<html><u>CREATE COMPETITION</u></html>");
		this.title.setForeground(Color.BLUE);
	}
	
	public void setChoose() {
		this.choose = new JLabel("choose competition:");
	}
	
	public void setMaxNumber() {
		this.maxNumber = new JLabel("Max competitors number:");
	}
		
	public void setDiscipline() {
		this.discipline = new JLabel("Discipline:");
	}
	
	public void setLeague() {
		this.league = new JLabel("League:");
	}
	
	public void setGender() {
		this.gender = new JLabel("Gender:");
	}

	public void setComp() {
		this.comp = new JComboBox();
		this.comp.setPreferredSize(new Dimension(140,20));
		comp.addItem("Ski");
		comp.addItem("Snowboard");
	}

	public void setDiscp() {
		this.discp = new JComboBox();
		this.discp.setPreferredSize(new Dimension(140,20));
		discp.addItem(Discipline.SLALOM);
		discp.addItem(Discipline.DOWNHILL);
		discp.addItem(Discipline.FREESTYLE);
		discp.addItem(Discipline.GIANT_SLALOM);
	}

	public void setLeag() {
		this.leag = new JComboBox();
		this.leag.setPreferredSize(new Dimension(143,20));
		leag.addItem(League.JUNIOR);
		leag.addItem(League.ADULT);
		leag.addItem(League.SENIOR);
	}

	public void setCompNum() {
		this.compNum = new JTextField("10",14);
	}

	public void setGen() {
		this.gen = new JComboBox();
		this.gen.setPreferredSize(new Dimension(143,20));
		gen.addItem(Gender.MALE);
		gen.addItem(Gender.FEMALE);
	}

	public void setCreateBtn() {
		this.createbtn = new JButton("Create competition");
	}

	public void setDefComp() {
		this.defComp = new JButton("Default competition");
	}

	//endregion

	//region Getters

	public String getComp() {
		return (String)comp.getSelectedItem();
	}

	public Discipline getDiscp() {
		return (Discipline)discp.getSelectedItem();
	}

	public League getLeag() {
		return (League)leag.getSelectedItem();
	}

	public Gender getGen() {
		return (Gender)gen.getSelectedItem();
	}

	public int getCompNum() throws IllegalArgumentException {
		int n = Integer.parseInt(compNum.getText());
        ValidationUtils.assertInRange(n,1,20);
        return n;
	}

    public JButton getCreatebtn() {
        return createbtn;
    }

	public JButton getDefComp() {
		return defComp;
	}

	//endregion

	/**
	 * set layout for the panel
	 */
	public void makeLayout() {
		setLayout(new GridBagLayout());
        Border a = BorderFactory.createTitledBorder("");
        Border b = BorderFactory.createEmptyBorder(0,5,0,5);
        setBorder(BorderFactory.createCompoundBorder(a,b));
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 1;
        gc.weighty = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridx = 0;
        gc.gridy = 0;
        add(title,gc);
        gc.gridy = 1;
        add(choose,gc);
        gc.gridy = 2;
        add(comp,gc);
        gc.gridy = 5;
        add(maxNumber,gc);
        gc.gridy = 6;
        add(compNum,gc);
        gc.gridy = 3;
        add(discipline,gc);
        gc.gridy = 4;
        add(discp,gc);
        gc.gridx = 1;
        gc.gridy = 1;
        add(league,gc);
        gc.gridy = 2;
        add(leag,gc);
        gc.gridy = 3;
        add(gender,gc);
        gc.gridy = 4;
        add(gen,gc);
        gc.gridx = 0;
        gc.gridy = 7;
        add(createbtn,gc);
        gc.gridx = 1;
        gc.gridy = 7;
        add(defComp,gc);
	}
}
