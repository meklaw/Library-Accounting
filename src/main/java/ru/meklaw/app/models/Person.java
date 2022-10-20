package ru.meklaw.app.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class Person {
    private int id;
    @NotEmpty(message = "ФИО не должно быть пустым")
    @Size(min = 3, max = 120, message = "ФИО должно быть от 3 до 120 символов")
    private String fullName;
    @Min(value = 0, message = "Возраст должен быть больше 0")
    private int age;


    public Person() {
    }

    public Person(int id, String fullName, int age) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
