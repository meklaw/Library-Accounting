package ru.meklaw.app.models;

import java.time.Year;

public class Book {
    private String name;
    private String author;
    private Year year;

    public Book() {
    }

    public Book(String name, String author, Year year) {
        this.name = name;
        this.author = author;
        this.year = year;
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

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }
}
