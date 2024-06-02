package org.devJava.entitie.user;

import org.devJava.entitie.book.Book;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private final int id;
    private final @NotNull String name;
    private final @NotNull String email;
    private final int LIMIT_BORROWED_BOOKS = 5;
    private final @NotNull List<Book> borrowedBooksFromClient;

    public Client(int id,@NotNull String name,@NotNull String email) {
        if (id < 0) {
            throw new IllegalArgumentException("Id invalid!");
        }
        this.id = id;
        this.name = name;
        this.email = email;
        borrowedBooksFromClient = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public @NotNull String getName() {
        return name;
    }

    public @NotNull String getEmail() {
        return email;
    }

    public int getLIMIT_BORROWED_BOOKS() {
        return LIMIT_BORROWED_BOOKS;
    }

    public @NotNull List<Book> getBorrowedBooksFromClient() {
        return borrowedBooksFromClient;
    }
}
