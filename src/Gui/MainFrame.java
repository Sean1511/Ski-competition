/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */
package Gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import State.Injured;
import game.arena.Arena;
import game.arena.FactoryArena;
import game.arena.IArena;
import game.arena.WinterArena;
import game.competition.Competition;
import game.competition.Competitor;
import game.entities.sportsman.IWinterSportsman;
import game.entities.sportsman.WinterSportsman;
import game.enums.*;
import utilities.ValidationUtils;

/**
 * Class for main frame
 */
public class MainFrame extends JFrame implements Observer {

    private PanelArena panelArena;
    private PanelCompetition panelCompetition;
    protected PanelCompetitor panelCompetitor;
    private PanelStart panelStart;
    protected JPanel panelSide;
    protected JPanel canvas;
    private Object competition;
    private Arena arena;
    private BufferedImage background = null;
    private BufferedImage red = null;
    private BufferedImage blue = null;
    private BufferedImage green = null;
    private BufferedImage pink = null;
    private BufferedImage injured = null;
    private BufferedImage disabled = null;
    private BufferedImage completed = null;
    private JButton competitorBtn;
    private JButton editBtn;
    private boolean gameOver = false;

    /**
     * Ctor for main frame
     */
    public MainFrame(){
        super("Competition");

        // initialization
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1160,730));
        panelArena = new PanelArena();
        panelCompetition = new PanelCompetition();
        panelCompetitor = new PanelCompetitor();
        panelStart = new PanelStart();
        panelSide = new JPanel();
        panelSide.setPreferredSize(new Dimension(500,730));

        try {
            injured = ImageIO.read(new File("icons/injured.png"));
            disabled = ImageIO.read(new File("icons/disabled.png"));
            completed = ImageIO.read(new File("icons/completed.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        // main panel
        canvas = new JPanel(){
          public void paintComponent(Graphics g){
              super.paintComponent(g);
              if (background != null){
                  g.drawImage(background,0,0,canvas.getWidth(),canvas.getHeight(),null);
              }
              if (competition!=null) {
                  synchronized (competition) {
                      Competition comp = (Competition) competition;
                      for (Competitor c : comp.getActiveCompetitors()) {
                          if (c.getLocation().getX() > arena.getLength() - 65) {
                              g.drawImage(getImage(c), (int) c.getLocation().getY(), (int) arena.getLength() - 65, 60, 60, null);
                              if (((WinterSportsman) c).getCurrentState() instanceof Injured) {
                                  g.drawImage(injured, (int) c.getLocation().getY() + 15, (int) arena.getLength() - 70, 20, 20, null);
                              }
                          }
                          else {
                              g.drawImage(getImage(c), (int) c.getLocation().getY(), (int) c.getLocation().getX(), 60, 60, null);
                              if (((WinterSportsman) c).getCurrentState() instanceof Injured) {
                                  g.drawImage(injured, (int) c.getLocation().getY() + 15, (int) c.getLocation().getX() - 5, 20, 20, null);
                              }
                          }
                      }
                      for (Competitor c : comp.getFinishedCompetitors()) {
                          g.drawImage(getImage(c), (int) c.getLocation().getY(), (int) c.getLocation().getX() - 65, 60, 60, null);
                          g.drawImage(completed, (int) c.getLocation().getY()+40, (int) c.getLocation().getX()-90, 30, 30, null);
                          if (((WinterSportsman)c).getInjuredLocation() != -1){
                              g.drawImage(injured, (int) c.getLocation().getY()+15, (int) c.getLocation().getX() - 70, 20, 20, null);
                          }
                      }
                      for (Competitor c : comp.getDisabledCompetitors()) {
                          g.drawImage(getImage(c), (int) c.getLocation().getY(), (int) c.getLocation().getX() - 65, 60, 60, null);
                          g.drawImage(disabled, (int) c.getLocation().getY()+15, (int) c.getLocation().getX() - 70, 20, 20, null);
                      }
                  }
              }
          }
        };


        // create main panel and side panel
        canvas.setPreferredSize(new Dimension(840,730));
        canvas.setLayout(null);
        panelSide.add(panelArena);
        panelSide.add(panelCompetition);
        panelSide.add(panelCompetitor);
        panelSide.add(panelStart);
        panelSide.setLayout(new BoxLayout(panelSide, BoxLayout.Y_AXIS));
        this.getContentPane().setLayout(null);
        this.setBounds(0,0,1000,700);
        canvas.setBounds(0,0,840,700);
        panelSide.setBounds(840,0,305,700);
        add(canvas);
        add(panelSide);


        // Create arena button
        JButton arenaBtn = panelArena.getBuildBtn();
        arenaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WinterSportsman.setID(0);
                editBtn.setEnabled(true);
                competitorBtn.setEnabled(true);
                gameOver = false;
                int length = 0;
                try {
                    length = panelArena.getLen();
                }
                catch (IllegalArgumentException k){
                    JOptionPane.showMessageDialog(null, "invalid input values! Please try again","Message",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                arena = FactoryArena.buildArena(panelArena.getArenaType(),length,panelArena.getSur(),panelArena.getCondition());
                competition = null;
                setPreferredSize(new Dimension(1160,(int)arena.getLength()+30));
                canvas.setBounds(0,0,840,(int)arena.getLength());
                panelSide.setBounds(840,0,305,700);
                setBackground();
                canvas.repaint();
                pack();
                JOptionPane.showMessageDialog(null, arena+"was successfully built","Message",JOptionPane.INFORMATION_MESSAGE);
            }
        });


        // Create competition button
        String compType = panelCompetition.getComp();
        JButton competitionBtn = panelCompetition.getCreatebtn();
        competitionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameOver = false;
                panelCompetitor.setNum(1);
                WinterSportsman.setID(0);
                editBtn.setEnabled(true);
                competitorBtn.setEnabled(true);
                int maxComp = 0;
                Class cls = null;
                Constructor con = null;
                try{
                    ValidationUtils.assertNotNull(arena);
                }
                catch (IllegalArgumentException k){
                    JOptionPane.showMessageDialog(null, "Please build arena","Message",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                try{
                    maxComp = panelCompetition.getCompNum();
                }
                catch (IllegalArgumentException k) {
                    JOptionPane.showMessageDialog(  null, "invalid input values! Please try again", "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                ClassLoader loader = ClassLoader.getSystemClassLoader();
                try {
                    cls = loader.loadClass("game.competition."+compType+"Competition");
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                    return;
                }
                try {
                    con = cls.getConstructor(WinterArena.class ,int.class ,Discipline.class ,League.class ,Gender.class );
                } catch (NoSuchMethodException e1) {
                    e1.printStackTrace();
                    return;
                }
                try {
                    competition = con.newInstance ((IArena)arena,maxComp,panelCompetition.getDiscp(),panelCompetition.getLeag(),panelCompetition.getGen());
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (InvocationTargetException e1) {
                    e1.printStackTrace();
                }
                canvas.setBounds(0,0,840,(int)arena.getLength()+30);
                panelSide.setBounds(840,0,305,700);
                if (panelCompetition.getCompNum()*65 > canvas.getWidth()){
                    panelSide.setBounds(panelCompetition.getCompNum()*65,0,308,700);
                    canvas.setBounds(0,0,panelCompetition.getCompNum()*65,(int)arena.getLength()+30);
                }
                setBounds(0,0,panelSide.getWidth()+canvas.getWidth()+10,(int)arena.getLength()+30);
                JOptionPane.showMessageDialog(null, "Competition was successfully built","Message",JOptionPane.INFORMATION_MESSAGE);
                canvas.repaint();
            }
        });


        // Create competitor button
        competitorBtn = panelCompetitor.getAddBtn();
        competitorBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameOver) {
                    JOptionPane.showMessageDialog(null, "Competition is over, please start again", "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                try {
                    ValidationUtils.assertNotNull(arena);
                    ValidationUtils.assertNotNull(competition);
                } catch (IllegalArgumentException k) {
                    String msg = "Please ";
                    if (arena == null)
                        msg += "build arena ";
                    if (competition == null)
                        if (msg != "Please ")
                            msg += "and ";
                    msg += "create competition";
                    JOptionPane.showMessageDialog(null, msg, "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Class cls = null;
                Constructor con = null;
                ClassLoader loader = ClassLoader.getSystemClassLoader();
                try {
                    cls = loader.loadClass("game.entities.sportsman." + compType + "er");
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                    return;
                }
                try {
                    con = cls.getConstructor(String.class, double.class, Gender.class, double.class, double.class, Discipline.class, String.class);
                } catch (NoSuchMethodException e1) {
                    e1.printStackTrace();
                    return;
                }
                try {
                    Competitor c = (Competitor) con.newInstance(panelCompetitor.getNameField(), panelCompetitor.getAgeField(), panelCompetition.getGen(), panelCompetitor.getAccelerationField(), panelCompetitor.getSpeedField(), panelCompetition.getDiscp(),panelCompetitor.getColorSelect());
                    ((Competition) competition).addCompetitor(c);
                    ((WinterSportsman)c).registerObserver(MainFrame.this);
                } catch (IllegalStateException e1){
                    JOptionPane.showMessageDialog(  null, "Competition is full!", "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (InvocationTargetException e1) {
                    e1.printStackTrace();
                } catch (IllegalArgumentException e1) {
                    JOptionPane.showMessageDialog(null, "Competitor does not fit to competition! Choose another competitor.", "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (((Competition)competition).getActiveCompetitors().size()*65 > canvas.getWidth()){
                    panelSide.setBounds(((Competition)competition).getActiveCompetitors().size()*65,0,308,700);
                    canvas.setBounds(0,0,((Competition)competition).getActiveCompetitors().size()*65,(int)arena.getLength()+30);
                }
                setBounds(0,0,panelSide.getWidth()+canvas.getWidth()+10,(int)arena.getLength()+30);
                setImage(panelCompetitor.getColorSelect());
                panelCompetitor.addItem();
                canvas.repaint();
            }
        });


        // Create start competition button
        JButton Start = panelStart.getStartBtn();
        Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameOver) {
                    JOptionPane.showMessageDialog(null, "Competition in progress or over, please wait or start again", "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                try {
                    ValidationUtils.assertNotNull(arena);
                    ValidationUtils.assertNotNull(competition);
                } catch (IllegalArgumentException k) {
                    String msg = "Please ";
                    if (arena == null)
                        msg += "build arena ";
                    if (competition == null) {
                        if (msg != "Please ")
                            msg += "and ";
                        msg += "create competition ";
                    }
                    if( competition!=null) {
                        if (!((Competition) competition).hasActiveCompetitors()) {
                            if (msg != "Please ")
                                msg += "and ";
                            msg += "add competitors";
                        }
                    }
                    JOptionPane.showMessageDialog(null, msg, "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (!((Competition) competition).hasActiveCompetitors()) {
                    JOptionPane.showMessageDialog(null, "Please add a competitor", "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                if (((Competition) competition).hasActiveCompetitors()) {
                    ((Competition) competition).startRace();
                }
                gameOver = true;
                editBtn.setEnabled(false);
                competitorBtn.setEnabled(false);
            }
        });


        // Create competition information button
        JButton infoBtn = panelStart.getInfoBtn();
        infoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ValidationUtils.assertNotNull(arena);
                    ValidationUtils.assertNotNull(competition);
                } catch (IllegalArgumentException k) {
                    String msg = "Please ";
                    if (arena == null)
                        msg += "build arena ";
                    if (competition == null) {
                        if (msg != "Please ")
                            msg += ", ";
                        msg += "create competition ";
                    }
                    if( competition!=null) {
                        if (!((Competition) competition).hasActiveCompetitors()) {
                            if (msg != "Please ")
                                msg += "and ";
                            msg += "add competitors";
                        }
                    }
                    JOptionPane.showMessageDialog(null, msg, "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                String [][] data = buildData();
                FrameInfo info = new FrameInfo(data);
            }
        });


        // Create Copy competitor button
        editBtn = panelCompetitor.getEditBtn();
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ValidationUtils.assertNotNull(arena);
                    ValidationUtils.assertNotNull(competition);
                } catch (IllegalArgumentException k) {
                    String msg = "Please ";
                    if (arena == null)
                        msg += "build arena ";
                    if (competition == null) {
                        if (msg != "Please ")
                            msg += "and ";
                        msg += "create competition ";
                    }
                    if( competition!=null) {
                        if (!((Competition) competition).hasActiveCompetitors()) {
                            if (msg != "Please ")
                                msg += "and ";
                            msg += "add competitors";
                        }
                    }
                    JOptionPane.showMessageDialog(null, msg, "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                editFrame editFrame = new editFrame((Competition)competition,MainFrame.this);
            }
        });

        // Create default competition button
        JButton Create = panelCompetition.getDefComp();
        Create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editBtn.setEnabled(true);
                competitorBtn.setEnabled(true);
                WinterSportsman.setID(0);
                gameOver = false;
                defaultCompetition defaultCompetition = new defaultCompetition((Competition)competition,MainFrame.this);
            }
        });
        pack();
        setVisible(true);
    }

    /**
     * set the arena
     * @param arena the arena of the competition
     */
    public void setArena (Arena arena){
        this.arena = arena;
    }

    /**
     * set the competition
     * @param competition
     */
    public void setCompetition (Competition competition){
        this.competition = competition;
    }


    /**
     * Build the competition information table
     */
    public String[][] buildData(){
        int row = ((Competition)competition).getActiveCompetitors().size()+((Competition)competition).getFinishedCompetitors().size()+((Competition)competition).getDisabledCompetitors().size();
        int col = 6;
        String data[][] = new String[row][col];
        int i = 0;
        String buffer[];
        for (Competitor c : ((Competition)competition).getFinishedCompetitors()){
            buffer = ((WinterSportsman)c).getData();
            buffer[0] = String.valueOf(i+1);
            data[i++]=buffer;
        }
        sortArray(((Competition)competition).getActiveCompetitors());
        for (Competitor c : ((Competition)competition).getActiveCompetitors()) {
            buffer = ((WinterSportsman) c).getData();
            buffer[0] = String.valueOf(i+1);
            data[i++] = buffer;
        }
        for (int j = ((Competition)competition).getDisabledCompetitors().size()-1; j >= 0; j--){
            Competitor c = ((Competition)competition).getDisabledCompetitors().get(j);
            buffer = ((WinterSportsman) c).getData();
            buffer[0] = String.valueOf(i+1);
            data[i++] = buffer;
        }
        return data;
    }

    /**
     * update method for gui
     */
    @Override
    public void update(Observable o, Object arg) {
        canvas.repaint();
    }

    /**
     * @param c active competitor
     * @return the color for the competitor
     */
    public BufferedImage getImage(Competitor c){
        switch((c).getColor()){
            case "Red":
                return red;
            case "Blue":
                return blue;
            case "Pink":
                return pink;
            case "Green":
                return green;
            default:
                return null;
        }
    }

    /**
     * set image color of the competitor
     * @param color color of the competitor
     */
    public void setImage(String color){
        switch(color) {
            case "Red":
                try {
                    red = ImageIO.read(new File("icons/" + "Red" + competition.toString() + ".png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case "Blue":
                try {
                    blue = ImageIO.read(new File("icons/" + "Blue" + competition.toString() + ".png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case "Pink":
                try {
                    pink = ImageIO.read(new File("icons/" + "Pink" + competition.toString() + ".png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case "Green":
                try {
                    green = ImageIO.read(new File("icons/" + "Green" + competition.toString() + ".png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * set the background of the arena
     */
    public void setBackground(){
        if(arena != null) {
            try {
                background = ImageIO.read(new File("icons/"+arena.getCondition()+".jpg"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * sort the active competitors array
     * @param arrayList active competitors array
     */
    public void sortArray(ArrayList<Competitor> arrayList){
        Collections.sort(arrayList, new Comparator<Competitor>() {
            @Override
            public int compare(Competitor o1, Competitor o2) {
                return (int)o2.getLocation().getX()-(int)o1.getLocation().getX();
            }
        });
    }
}