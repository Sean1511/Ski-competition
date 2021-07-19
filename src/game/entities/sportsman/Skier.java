/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */
package game.entities.sportsman;

import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;

/**
 * Class of the Skier
 */
public class Skier extends WinterSportsman {

    /**
     * Default Ctor for skier
     */
    public Skier(){
        super("Skier"+(getID()+1),12.0,Gender.MALE,5.0,40.0,Discipline.SLALOM,"Blue");
    }

    /**
     * Non default ctor for Skier
     * @param name the name of the competitor
     * @param age the age of the competitor
     * @param gender the gender of the competitor
     * @param acceleration the acceleration of the competitor
     * @param maxSpeed the max speed of the competitor
     * @param discipline the discipline of the arena
     */
    public Skier(String name, double age, Gender gender, double acceleration, double maxSpeed, Discipline discipline, String color) throws IllegalArgumentException {
        super(name, age, gender, acceleration , maxSpeed, discipline, color);
    }

    /**
     * Override method for toString
     */
    public String toString() {
        return ("Skier "+this.getName());
    }

    /**
     * @return clone of skier
     */
    public Skier Clone (){
        String str = this.getName();
        if (str.charAt(str.length()-1)=='1')
            str = str.replace(str.substring(str.length()-1), "");
        return new Skier(str+(WinterSportsman.ID+1),this.getAge(),this.getGender(),this.getAcceleration()- League.calcAccelerationBonus(this.getAge()),this.getMaxSpeed(),this.getDiscipline(), this.getColor());
    }
}
