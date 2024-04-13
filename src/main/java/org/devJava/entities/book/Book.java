package org.devJava.entities.book;

public class Book {
    private String title;
    private String author;
    private Gender gender;
    private int year;
    private Status status;

    public enum Gender {
        ACTION, THRILLER, ADVENTURE, ROMANCE, COMEDY;
    }

    public enum Status {
        AVAILABLE, LOANED;
    }

    public Book(String title, String author, Gender gender, int year, Status status) {
        this.title = title;
        this.author = author;
        this.gender = gender;
        this.year = year;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}