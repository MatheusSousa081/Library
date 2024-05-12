package org.devJava.entities.book;

import org.jetbrains.annotations.NotNull;

import java.time.Year;

public class Book {
    private final String title;
    private final String author;
    private final Gender gender;
    private final Year year;
    private Status status;

    public enum Gender {
        ACTION, THRILLER, ADVENTURE, ROMANCE, COMEDY;
    }

    public enum Status {
        AVAILABLE, LOANED;
    }

    public Book(String title, String author, Gender gender, Year year) {
        this.title = title;
        this.author = author;
        this.gender = gender;
        this.year = year;
        this.status = Status.AVAILABLE;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Gender getGender() {
        return gender;
    }

    public Year getYear() {
        return year;
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
