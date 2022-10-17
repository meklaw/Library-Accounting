package ru.meklaw.app.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;


public class Person {
    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 30, message = "Name should to be between 3 and 30 characters")
    private String fullName;
    @Min(value = 0, message = "Age should be greater than 0")
    private Date dateOfBirthday;


    public Person() {
    }

    public Person(int id, String fullName, Date dateOfBirthday) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirthday = dateOfBirthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(Date dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }
}
