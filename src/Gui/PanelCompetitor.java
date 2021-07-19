/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */
package Gui;

import utilities.ValidationUtils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * Class for competitor panel
 */
public class PanelCompetitor extends JPanel {
	
	private JLabel title; 
	private JLabel name;
	private JLabel age;
	private JLabel maxSpeed;
	private JLabel acceleration;
	private JTextField nameField;
	private JTextField ageField;
	private JTextField speedField;
	private JTextField accelerationField;
	private JButton addBtn;
	private JButton editBtn;
	private JLabel color;
	private JComboBox colorSelect;
	private JLabel number;
	private JComboBox numberSelect;
	private int num = 1;

	/**
	 * Ctor for competitor panel
	 */
	public PanelCompetitor() {
		setPreferredSize(new Dimension(160,280));
		this.setTitle();
		this.setName();
		this.setAge();
		this.setMaxSpeed();
		this.setAcceleration();
		this.setNameField();
		this.setAgeField();
		this.setSpeedField();
		this.setAccelerationField();
		this.setColor();
		this.setColorSelect();
		this.setNumber();
		this.setNumberSelect();
		this.setEditBtn();
		this.setAddBtn();
		this.makeLayout();
	}

	//region Setters

	public void setTitle() {
		this.title = new JLabel("<html><u>ADD COMPETITOR</u></html>");
		this.title.setForeground(Color.BLUE);
	}

	public void setName() {
		this.name =new JLabel("Name:");
	}
	
	public void setAge() {
		this.age = new JLabel("Age:");
	}
	
	public void setMaxSpeed() {
		this.maxSpeed = new JLabel("Max speed:");
	}
	
	public void setAcceleration() {
		this.acceleration = new JLabel("Acceleration:");
	}

	public void setNameField() {
		this.nameField = new JTextField(14);
	}

	public void setAgeField() {
		this.ageField =  new JTextField(14);
	}

	public void setSpeedField() {
		this.speedField =  new JTextField(14);
	}

	public void setAccelerationField() {
		this.accelerationField =  new JTextField(14);
	}

	public void setAddBtn() {
		this.addBtn = new JButton("Add competitor");
		this.addBtn.setPreferredSize(new Dimension(142,25));
	}

	public void setEditBtn() {
		this.editBtn = new JButton("Edit competitors");
		this.editBtn.setPreferredSize(new Dimension(142,25));
	}

	public void setColor() {
		this.color = new JLabel("Color:");
	}

	public void setColorSelect() {
		this.colorSelect = new JComboBox();
		this.colorSelect.setPreferredSize(new Dimension(140,20));
		this.colorSelect.addItem("Blue");
		this.colorSelect.addItem("Pink");
		this.colorSelect.addItem("Red");
		this.colorSelect.addItem("Green");
	}

	public void setNumber() {
		this.number = new JLabel("Number:");
	}

	public void setNumberSelect() {
		this.numberSelect = new JComboBox();
		this.numberSelect.setPreferredSize(new Dimension(140,20));
		addItem();
	}

    public void setNum(int num) {
	    this.numberSelect.removeAllItems();
        this.num = num;
        addItem();
    }

	//endregion

	//region Getters

	public String getNameField() throws IllegalArgumentException{
		String name = nameField.getText();
		ValidationUtils.assertNotNullOrEmptyString(name);
		return name;
	}

	public Double getAgeField() throws IllegalArgumentException {
		return Double.parseDouble(ageField.getText());
	}

	public Double getSpeedField() throws IllegalArgumentException{
		return Double.parseDouble(speedField.getText());
	}

	public Double getAccelerationField() throws IllegalArgumentException {
		return Double.parseDouble(accelerationField.getText());
	}

	public String getColorSelect() {
		return (String) colorSelect.getSelectedItem();
	}

	public String getNumberSelect() {
		return (String) numberSelect.getSelectedItem();
	}

	public JButton getAddBtn() {
		return addBtn;
	}

	public JButton getEditBtn() {
		return editBtn;
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
        add(name,gc);
        gc.gridy = 2;
        add(nameField,gc);
        gc.gridy = 3;
        add(age,gc);
        gc.gridy = 4;
        add(ageField,gc);
        gc.gridx = 1;
        gc.gridy = 1;
        add(maxSpeed,gc);
        gc.gridy = 2;
        add(speedField,gc);
        gc.gridy = 3;
        add(acceleration,gc);
        gc.gridy = 4;
        add(accelerationField,gc);
        gc.gridx = 0;
        gc.gridy = 5;
        add(color,gc);
        gc.gridy = 6;
        add(colorSelect,gc);
        gc.gridx = 1;
        gc.gridy = 5;
        add(number,gc);
        gc.gridy = 6;
        add(numberSelect,gc);
        gc.gridx = 0;
        gc.gridy = 7;
        add(addBtn,gc);
		gc.gridx = 1;
		gc.gridy = 7;
		add(editBtn,gc);
	}

	public void addItem (){
		if (num>1)
			this.numberSelect.removeAllItems();
		this.numberSelect.addItem(Integer.toString(num++));
	}
}
