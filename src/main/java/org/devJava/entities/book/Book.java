package org.devJava.entities.book;

import java.time.Year;

public class Book {
    private final int id;
    private final String title;
    private final String author;
    private final Year year;
    private final Gender gender;
    private Status status;

    public enum Gender {
        ACTION, ADVENTURE, ROMANCE, COMEDY;
    }

    public enum Status {
        AVAILABLE, LOANED;
    }

    public Book(String title, String author, Year year, int id, Gender gender) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.id = id;
        this.gender = gender;
        status = Status.AVAILABLE;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Year getYear() {
        return year;
    }

    public Gender getGender() {
        return gender;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return title + "by " + author + ", published in " + year +
                "\nGender: " + gender.name() + ", " + status.name();
    }
}
