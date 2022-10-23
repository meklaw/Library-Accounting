package ru.meklaw.app.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;
    @NotEmpty(message = "Название не должно быть пустым")
    @Size(min = 1, max = 120, message = "Название должно быть от 1 до 120 символов")
    private String name;
    @NotEmpty(message = "Автор не должен быть пустым")
    @Size(min = 3, max = 120, message = "Автор должен быть от 3 до 120 символов")
    private String author;
    @Min(value = 0, message = "Год издания не должен быть меньше 0")
    private int year;
    private int personId;

    public Book() {
    }

    public Book(int id, String name, String author, int year, int personId) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.personId = personId;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
