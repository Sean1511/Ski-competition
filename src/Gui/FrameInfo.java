/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */
package Gui;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Class for information table
 */
public class FrameInfo extends JFrame {
    private JPanel panel;
    private JTable table;
    private final String[] titles = {"Place","Name", "Speed", "Max speed", "Location", "Status"};

    /**
     * Ctor for information table
     */
    public FrameInfo(String[][] data){
        super ("Competitors information");
        panel = new JPanel();
        setTable(data);
        add(panel);
        setBounds(450,150,600,panel.getHeight());
        pack();
        setVisible(true);
    }

    /**
     * @param data 2d array that contains the information for the table
     * sets the information table
     */
    public void setTable(String [][]data){
        this.table = new JTable(data,titles){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            public Component prepareRenderer(TableCellRenderer r,int data,int titles) {
                Component c = super.prepareRenderer(r, data, titles);
                if (data % 2 == 0)
                    c.setBackground(Color.WHITE);
                else
                    c.setBackground(Color.lightGray);
                if(isCellSelected(data,titles))
                    c.setBackground(Color.cyan);
                return  c;
            }
        };
        this.table.setPreferredScrollableViewportSize(new Dimension(580, 16*data.length));
        this.table.setFillsViewportHeight(true);
        JScrollPane jps = new JScrollPane(table);
        panel.add(jps);
    }
}
