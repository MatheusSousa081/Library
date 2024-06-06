package org.devJava.entity.book;

import org.devJava.exception.LibraryException;
import org.jetbrains.annotations.NotNull;

import java.time.Year;
import java.util.Objects;

public final class Book {
    private final int id;
    private final @NotNull String title;
    private final @NotNull String author;
    private final @NotNull Year year;
    private final @NotNull Gender gender;
    private @NotNull Status status;

    public Book(int id, @NotNull String title, @NotNull String author, @NotNull Year year, @NotNull Gender gender) {
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

    public @NotNull String getTitle() {
        return title;
    }

    public @NotNull String getAuthor() {
        return author;
    }

    public @NotNull Year getYear() {
        return year;
    }

    public @NotNull Gender getGender() {
        return gender;
    }

    public @NotNull Status getStatus() {
        return status;
    }

    public void setStatus(@NotNull Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return title + "by " + author + ", published in " + year +
                "\nGender: " + gender.name() + ", " + status.name();
    }

    public enum Gender {
        ACTION, ADVENTURE, ROMANCE, COMEDY
    }

    public enum Status {
        AVAILABLE, LOANED
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }
}
