package ru.meklaw.app.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;


public class Person {
    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 30, message = "Name should to be between 3 and 30 characters")
    private String name;
    @Min(value = 0, message = "Age should be greater than 0")
    private Date dateOfBirthday;


    public Person() {
    }

    public Person(int id, String name, Date dateOfBirthday) {
        this.id = id;
        this.name = name;
        this.dateOfBirthday = dateOfBirthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(Date dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }
}
