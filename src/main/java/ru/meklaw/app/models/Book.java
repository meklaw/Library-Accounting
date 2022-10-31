package ru.meklaw.app.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Название не должно быть пустым")
    @Size(min = 1, max = 120, message = "Название должно быть от 1 до 120 символов")
    @Column(name = "name")
    private String name;
    @NotEmpty(message = "Автор не должен быть пустым")
    @Size(min = 3, max = 120, message = "Автор должен быть от 3 до 120 символов")
    @Column(name = "author")
    private String author;
    @Min(value = 0, message = "Год издания не должен быть меньше 0")
    @Column(name = "year")
    private int year;
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    public Book() {
    }

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
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

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
