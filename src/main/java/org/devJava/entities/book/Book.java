package org.devJava.entities.book;

import org.devJava.exceptions.LibraryException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Year;

public final class Book {
    private final @NotNull int id;
    private final @NotNull String title;
    private final @NotNull String author;
    private final @NotNull Year year;
    private final @NotNull Gender gender;
    private @Nullable Status status;

    public enum Gender {
        ACTION, ADVENTURE, ROMANCE, COMEDY;
    }

    public enum Status {
        AVAILABLE, LOANED;
    }

    public Book(@NotNull int id, @NotNull String title, @NotNull String author, @NotNull Year year, @NotNull Gender gender) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.gender = gender;
        status = Status.AVAILABLE;
    }

    public static Book create(int id, String title, String author, Year year, Gender gender) throws LibraryException {
        if (id < 0) {
            throw new LibraryException("Id invalid!");
        }
        return new Book(id, title, author, year, gender);
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
