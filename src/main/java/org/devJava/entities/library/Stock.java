package org.devJava.entities.library;

import org.devJava.entities.book.Book;
import org.devJava.exceptions.LibraryException;

import java.time.Year;
import java.util.HashMap;
import java.util.Map;

public class Stock {
    private final Map<String, Book> books;
    private int quantityBookInStock;

    public Stock() {
        books = new HashMap<String, Book>();
        quantityBookInStock = books.size();
    }

    public int getQuantityBooksInStock() {
        return quantityBookInStock;
    }

    public Map<String, Book> getBooks() {
        return books;
    }

    public void addBook(int id, String title, String author, Year year, Book.Gender gender) throws LibraryException {
        books.put(title, Book.create(id, title, author, year, gender));
    }

    public Book removeBook(String title) {
        return books.remove(title);
    }

    public Book searchBook(String title) {
        return books.get(title);
    }

    public boolean hasBook(String title) {
        return books.containsKey(title);
    }
}
