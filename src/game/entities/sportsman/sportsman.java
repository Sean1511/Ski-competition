/**
 * @author Sean Assolin 311426118
 * @author Ori Tabibi 205508971
 */
package game.entities.sportsman;

import game.entities.MobileEntity;
import game.enums.Gender;
import utilities.ValidationUtils;

/**
 * abstract class of sportsman
 */
public abstract class sportsman extends MobileEntity {
    private String name;
    private double age;
    private Gender gender;

    /**
     * Ctor for sportsman
     * @param name the name of the competitor
     * @param age the age of the competitor
     * @param gender the gender of the competitor
     * @param acceleration the acceleration of the competitor
     * @param maxSpeed the max speed of the competitor
     */
    protected sportsman (String name, double age, Gender gender, double acceleration, double maxSpeed) throws IllegalArgumentException {
        super(maxSpeed,acceleration);
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
    }

    //region Getters

    /**
     * @return the competitor name
     */
    public String getName() { return name; }

    /**
     * @return the competitor age
     */
    public double getAge() {
        return age;
    }

    /**
     * @return the competitor gender
     */
    public Gender getGender() {
        return gender;
    }

    //endregion

    //region Setters

    /**
     * @param name the competitor name
     * @throws IllegalArgumentException if name is empty
     */
    public void setName (String name) throws IllegalArgumentException {
        ValidationUtils.assertNotNullOrEmptyString(name);
        this.name = name;
    }

    /**
     * @param age the competitor age
     * @throws IllegalArgumentException if age is negative
     */
    public void setAge(double age) throws IllegalArgumentException  {
        ValidationUtils.assertPositive(age);
        this.age = age;
    }

    /**
     * @param gender the competitor gender
     * @throws IllegalArgumentException if gender is null
     */
    public void setGender(Gender gender) throws IllegalArgumentException{
        ValidationUtils.assertNotNull(gender);
        this.gender = gender;
    }
}
