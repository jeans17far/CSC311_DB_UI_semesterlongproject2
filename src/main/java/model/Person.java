package model;

import java.util.Arrays;

/**
 * Represents a person in the Student cookie Management System.
 * This class encapsulates all the information related to an Student.
 */
public class Person {
    private Integer id;
    private String firstName;
    private String lastName;
    private String grade;
    private Double studentCookiehold;
    private String email;
    private byte[] profilePicture;

    /**
     * Default constructor for Person.
     */
    public Person() {
    }

    /**
     * Constructor for creating a new Person without an ID.
     *
     * @param firstName         The first name of the person
     * @param lastName          The last name of the person
     * @param grade        The grade the person belongs to
     * @param studentCookiehold The performance rating of the person
     * @param email             The email address of the person
     */
    public Person(String firstName, String lastName, String grade, Double studentCookiehold, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.studentCookiehold = studentCookiehold;
        this.email = email;
    }

    /**
     * Constructor for creating a Person with an ID.
     *
     * @param id                The unique identifier for the person
     * @param firstName         The first name of the person
     * @param lastName          The last name of the person
     * @param grade        The grade the person belongs to
     * @param studentCookiehold The performance rating of the person
     * @param email             The email address of the person
     */
    public Person(Integer id, String firstName, String lastName, String grade, Double studentCookiehold, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.studentCookiehold = studentCookiehold;
        this.email = email;
    }

    /**
     * Gets the email address of the person.
     *
     * @return The email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the person.
     *
     * @param email The email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the ID of the person.
     *
     * @return The ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the person.
     *
     * @param id The ID to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the first name of the person.
     *
     * @return The first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the person.
     *
     * @param firstName The first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the person.
     *
     * @return The last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the person.
     *
     * @param lastName The last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the performance rating of the person.
     *
     * @param studentCookiehold The performance rating to set
     */
    public void setstudentCookiehold(Double studentCookiehold) {
        this.studentCookiehold = studentCookiehold;
    }

    /**
     * Gets the performance rating of the person.
     *
     * @return The performance rating
     */
    public Double getstudentCookiehold() {
        return studentCookiehold;
    }

    /**
     * Gets the grade of the person.
     *
     * @return The grade
     */
    public String getgrade() {
        return grade;
    }

    /**
     * Sets the grade of the person.
     *
     * @param grade The grade to set
     */
    public void setgrade(String grade) {
        this.grade = grade;
    }

    /**
     * Gets the profile picture of the person.
     *
     * @return The profile picture as a byte array
     */
    public byte[] getProfilePicture() {
        return profilePicture;
    }

    /**
     * Sets the profile picture of the person.
     *
     * @param profilePicture The profile picture to set as a byte array
     */
    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    /**
     * Returns a string representation of the Person object.
     *
     * @return A string containing the person's details
     */
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", grade='" + grade + '\'' +
                ", studentCookiehold=" + studentCookiehold +
                ", email='" + email + '\'' +
                ", hasProfilePicture=" + (profilePicture != null && profilePicture.length > 0) +
                '}';
    }
}