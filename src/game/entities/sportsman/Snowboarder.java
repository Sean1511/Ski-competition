/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */
package game.entities.sportsman;

import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;

/**
 * Class of the Snowboarder
 */
public class Snowboarder extends WinterSportsman {

    /**
     * Default Ctor for snowboarder competitor
     */
    public Snowboarder(){
        super("Snowboarder",12.0,Gender.MALE,5.0,40.0,Discipline.SLALOM,"Blue");
    }

    /**
     * Non default ctor for Snowboarder
     * @param name the name of the competitor
     * @param age the age of the competitor
     * @param gender the gender of the competitor
     * @param acceleration the acceleration of the competitor
     * @param maxSpeed the max speed of the competitor
     * @param discipline the discipline of the arena
     */
    public Snowboarder(String name, double age, Gender gender, double acceleration, double maxSpeed, Discipline discipline, String color) throws IllegalArgumentException {
        super(name, age, gender, acceleration , maxSpeed, discipline, color);
    }

    /**
     * Override method for toString
     */
    public String toString() {
        return ("Snowboarder "+this.getName());
    }

    /**
     * @return clone of snowboarder competitor
     */
    public Snowboarder Clone (){
        String str = this.getName();
        if (str.charAt(str.length()-1)=='1')
            str = str.replace(str.substring(str.length()-1), "");
        return new Snowboarder(str+(WinterSportsman.ID+1),this.getAge(),this.getGender(),this.getAcceleration()- League.calcAccelerationBonus(this.getAge()),this.getMaxSpeed(),this.getDiscipline(), this.getColor());
    }
}
