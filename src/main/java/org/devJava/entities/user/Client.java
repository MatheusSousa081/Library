package org.devJava.entities.user;

import org.devJava.entities.book.Book;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private final int id;
    private String name;
    private String email;
    private final int limitBorrowedBooks = 5;
    private List<Book> borrowedBooksFromClient;

    public Client(int id, String name, String email) {
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

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLimitBorrowedBooks() {
        return limitBorrowedBooks;
    }

    public List<Book> getBorrowedBooksFromClient() {
        return borrowedBooksFromClient;
    }
}
