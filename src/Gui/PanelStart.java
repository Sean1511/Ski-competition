/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */
package Gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * Class of start competition panel
 */
public class PanelStart extends JPanel {
	
	private JButton startBtn;
	private JButton infoBtn;

	/**
	 * Ctor for start competition panel
	 */
	public PanelStart() {
		setPreferredSize(new Dimension(160,60));
		this.setStartBtn();
		this.setInfoBtn();
		this.makeLayout();
	}

	//region Getters

	/**
	 * @return the button start
	 */
	public JButton getStartBtn() {
		return startBtn;
	}

	/**
	 * @return the button info
	 */
	public JButton getInfoBtn() {
		return infoBtn;
	}

	//endregion

	//region Setters

	/**
	 * setter for start button
	 */
	public void setStartBtn() {
		this.startBtn = new JButton("Start competition");
		this.startBtn.setPreferredSize(new Dimension(140,25));
	}

	/**
	 * setter for info button
	 */
	public void setInfoBtn() {
		this.infoBtn = new JButton("Show info");
		this.infoBtn.setPreferredSize(new Dimension(140,25));
	}

	//endregion

	/**
	 * set layout for the panel
	 */
	public void makeLayout() {
		setLayout(new GridBagLayout());
        Border a = BorderFactory.createTitledBorder("");
        Border b = BorderFactory.createEmptyBorder(10,5,0,5);
        setBorder(BorderFactory.createCompoundBorder(a,b));
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 1;
        gc.weighty = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridx = 0;
        gc.gridy = 0;
        add(startBtn,gc);
        gc.gridx = 1;
        gc.gridy = 0;
        add(infoBtn,gc);
	}
}

