/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */
package Gui;

import game.competition.Competition;
import game.competition.Competitor;
import game.entities.sportsman.Decorator.ColoredSportsman;
import game.entities.sportsman.Decorator.SpeedySportsman;
import game.entities.sportsman.IWinterSportsman;
import game.entities.sportsman.WinterSportsman;
import utilities.ValidationUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * class for gui edit frame
 */
public class editFrame extends JFrame {
    private JPanel panel;
    private JPanel copyPanel;
    private JPanel editPanel;
    private JLabel copy;
    private JLabel edit;
    private JComboBox editColor;
    private JTextField editAcceleration;
    private JButton editBtn;
    private JTable table;
    private JComboBox colors;
    private JButton copyBtn;
    private final String[] titles = {"Number", "Name", "Age", "Max speed", "Acceleration", "Gender" ,"Color"};
    private Competition competition;
    private MainFrame gui;
    private DefaultTableModel model;


    /**
     * Ctor for edit frame
     */
    public editFrame(Competition competition, MainFrame gui){
        super ("Edit competitor");
        this.gui = gui;
        this.competition = competition;
        setLayout(null);
        panel = new JPanel();
        setTable();
        panel.setBounds(5,0,620,16*competition.getActiveCompetitors().size()+50);
        copyPanel = new JPanel();
        copyPanel.setBounds(5,16*competition.getActiveCompetitors().size()+50,615,50);
        Border a = BorderFactory.createTitledBorder("");
        Border b = BorderFactory.createEmptyBorder(5,0,0,0);
        copyPanel.setBorder(BorderFactory.createCompoundBorder(a,b));
        editPanel = new JPanel();
        editPanel.setBounds(5,16*competition.getActiveCompetitors().size()+105,615,50);
        editPanel.setBorder(BorderFactory.createCompoundBorder(a,b));
        add(panel);
        add(copyPanel);
        add(editPanel);
        setCopy();
        setColors();
        setCopyBtn();
        setEdit();
        setEditColor();
        setEditAcceleration();
        setEditBtn();
        setBounds(450,150,640,panel.getHeight()+copyPanel.getHeight()+editPanel.getHeight()+50);
        setVisible(true);
    }

    //region Setters

    /**
     * sets the information table
     */
    public void setTable(){
        this.table = new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            public Component prepareRenderer(TableCellRenderer r, int data, int titles) {
                Component c = super.prepareRenderer(r, data, titles);
                if (data % 2 == 0)
                    c.setBackground(Color.WHITE);
                else
                    c.setBackground(Color.lightGray);
                if(isCellSelected(data,titles))
                    c.setBackground(Color.cyan);
                return c;
            }
        };
        model = new DefaultTableModel();
        model.setColumnIdentifiers(titles);
        table.setModel(model);
        buildCopyData();
        this.table.setPreferredScrollableViewportSize(new Dimension(590, 16*competition.getActiveCompetitors().size()));
        this.table.setFillsViewportHeight(true);
        JScrollPane jps = new JScrollPane(table);
        panel.add(jps);
    }

    /**
     * set the combo box colors
     */
    public void setColors() {
        colors = new JComboBox();
        this.colors.setPreferredSize(new Dimension(142,25));
        this.colors.addItem("Select color");
        this.colors.addItem("Blue");
        this.colors.addItem("Pink");
        this.colors.addItem("Red");
        this.colors.addItem("Green");
        copyPanel.add(colors);
    }

    /**
     * set for copy competitor label
     */
    public void setCopy() {
        this.copy = new JLabel("<html><u>Copy competitor:</u></html>");
        this.copy.setForeground(Color.BLUE);
        copyPanel.add(copy);
    }

    /**
     * set for edit competitor label
     */
    public void setEdit() {
        this.edit = new JLabel("<html><u>Edit competitor:</u></html>");
        this.edit.setForeground(Color.BLUE);
        editPanel.add(edit);
    }

    /**
     * set for color choose combo box
     */
    public void setEditColor() {
        this.editColor = new JComboBox();
        this.editColor.setPreferredSize(new Dimension(142,25));
        this.editColor.addItem("Select color");
        this.editColor.addItem("Blue");
        this.editColor.addItem("Pink");
        this.editColor.addItem("Red");
        this.editColor.addItem("Green");
        editPanel.add(editColor);
    }

    /**
     * set for acceleration Jtextfield
     */
    public void setEditAcceleration() {
        this.editAcceleration = new JTextField("Set acceleration",13);
        editPanel.add(editAcceleration);
    }

    /**
     * set for copy competitor button
     */
    public void setCopyBtn() {
        this.copyBtn = new JButton("Copy competitor");
        copyPanel.add(copyBtn);
        this.copyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row < 0){
                    JOptionPane.showMessageDialog(null, "Please select a competitor","Message",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                else if (colors.getSelectedIndex()==0){
                    JOptionPane.showMessageDialog(null, "Please select a color","Message",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Competitor c = competition.getActiveCompetitors().get(row).Clone();
                ((WinterSportsman)c).setColor((String)colors.getSelectedItem());
                try {
                    competition.addCompetitor(c);
                    ((WinterSportsman) c).registerObserver(gui);
                    gui.setImage(((WinterSportsman) c).getColor());
                }
                catch (IllegalStateException e1){
                    JOptionPane.showMessageDialog(  null, "Competition is full!", "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                String data[]={((WinterSportsman) c).getId(),((WinterSportsman) c).getName(),String.valueOf(((WinterSportsman) c).getAge()),String.valueOf(((WinterSportsman) c).getMaxSpeed()),String.valueOf(((WinterSportsman) c).getAcceleration()),((WinterSportsman) c).getGender().toString(),(((WinterSportsman) c).getColor())};
                model.addRow(data);
                table.setPreferredScrollableViewportSize(new Dimension(590, 16*competition.getActiveCompetitors().size()));
                panel.setBounds(5,0,620,16*competition.getActiveCompetitors().size()+50);
                copyPanel.setBounds(5,16*competition.getActiveCompetitors().size()+50,615,50);
                editPanel.setBounds(5,16*competition.getActiveCompetitors().size()+105,615,50);
                setBounds(450,150,640,panel.getHeight()+copyPanel.getHeight()+editPanel.getHeight()+50);
                gui.repaint();
            }
        });
    }

    /**
     * set for edit competitor button
     */
    public void setEditBtn() {
        this.editBtn = new JButton("Edit competitor");
        editPanel.add(editBtn);
        this.editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(null, "Please select a competitor", "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else if (editColor.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Please select a color", "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                try {
                    ValidationUtils.assertNotNullOrEmptyString(editAcceleration.getText());
                    ValidationUtils.assertPositive(Double.parseDouble(editAcceleration.getText()));
                } catch (IllegalArgumentException e1){
                    JOptionPane.showMessageDialog(null, "Please enter acceleration number", "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                Competitor c = competition.getActiveCompetitors().get(row);
                Competitor sportsman = new ColoredSportsman(new SpeedySportsman((IWinterSportsman)c,Double.parseDouble(editAcceleration.getText())),editColor.getSelectedItem().toString());
                competition.setCompetitor(sportsman,row);
                gui.setImage(c.getColor());
                String data[]={((WinterSportsman) c).getId(),((WinterSportsman) c).getName(),String.valueOf(((WinterSportsman) c).getAge()),String.valueOf(((WinterSportsman) c).getMaxSpeed()),String.valueOf(((WinterSportsman) c).getAcceleration()),((WinterSportsman) c).getGender().toString(),(((WinterSportsman) c).getColor())};
                model.removeRow(row);
                model.insertRow(row,data);
                gui.repaint();
            }
        });
        gui.repaint();
    }


    //endregion

    //region Getters

    /**
     * @return reference for copy competitor button
     */
    public JButton getCopyBtn() {
        return copyBtn;
    }

    /**
     * @return reference for edit competitor button
     */
    public JButton getEditBtn() {
        return editBtn;
    }


    //endregion

    /**
     * Build the competition information table
     */
    public void buildCopyData(){
        for (Competitor c : competition.getActiveCompetitors()) {
            String [] buffer = new String[7];
            buffer[0] = String.valueOf(((WinterSportsman) c).getId());
            buffer[1] = ((WinterSportsman) c).getName();
            buffer[2] = String.valueOf(((WinterSportsman) c).getAge());
            buffer[3] = String.valueOf(((WinterSportsman) c).getMaxSpeed());
            buffer[4] = String.valueOf(((WinterSportsman) c).getAcceleration());
            buffer[5] = ((WinterSportsman) c).getGender().toString();
            buffer[6] = (c.getColor());
            model.addRow(buffer);
        }
    }
}
