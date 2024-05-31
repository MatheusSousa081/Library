package org.devJava.entities.user;

import org.devJava.entities.book.Book;
import org.devJava.exceptions.LibraryException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private final @NotNull int id;
    private final @NotNull String name;
    private final @NotNull String email;
    private final @Nullable int limitBorrowedBooks = 5;
    private List<Book> borrowedBooksFromClient;

    public Client(int id, String name, String email) throws LibraryException {
        if (id < 0) {
            throw new LibraryException("Id invalid!");
        }
        this.id = id;
        this.name = name;
        this.email = email;
        borrowedBooksFromClient = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getLimitBorrowedBooks() {
        return limitBorrowedBooks;
    }

    public List<Book> getBorrowedBooksFromClient() {
        return borrowedBooksFromClient;
    }
}
