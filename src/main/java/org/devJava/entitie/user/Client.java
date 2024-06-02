package org.devJava.entitie.user;

import org.devJava.entitie.book.Book;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client {
    private final int id;
    private final @NotNull String name;
    private final @NotNull String email;
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

    public int getLimitBorrowedBooks() {
        return 5;
    }

    public @NotNull List<Book> getBorrowedBooksFromClient() {
        return borrowedBooksFromClient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && Objects.equals(name, client.name) && Objects.equals(email, client.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }
}
