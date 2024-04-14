package org.devJava.entities.library;

import org.devJava.Service.ListOrganizer;
import org.devJava.entities.book.Book;
import org.devJava.entities.user.User;
import org.devJava.exceptions.LibraryException;
import org.devJava.exceptions.UserException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LibrarySystem implements Library{
    private List<Book> listOfBooks = new ArrayList<>();
    private List<Book> borrowedBooks = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public LibrarySystem() {
    }

    @Override
    public void addBook(@NotNull Book book) {
        listOfBooks.add(book);
        System.out.println("Book added successfully");
    }

    @Override
    public void addBook(@NotNull String title, @NotNull String author, Book.@NotNull Gender gender, @NotNull int year) {
        listOfBooks.add(new Book(title, author, gender, year));
        System.out.println("Book added successfully");
    }

    @Override
    public void removeBook(@NotNull Book book) {
        listOfBooks.remove(book);
        System.out.println("Book removed successfully");
    }

    @Override
    public void removeBook(@NotNull String title) {
        for (int i = 0; i <= listOfBooks.size(); i++) {
            if (listOfBooks.get(i).getTitle().equalsIgnoreCase(title)) {
                listOfBooks.remove(i);
                System.out.println("Book removed successfully");
                return;
            }
        }
    }


    @Override
    public void listBook() {
        listOfBooks.sort(Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));
        for (Book book : listOfBooks) {
            System.out.println(book);
        }
    }

    @Override
    public void listBorrowedBooks() {
        borrowedBooks.sort(Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));
        for (Book book : borrowedBooks) {
            System.out.println(book);
        }
    }

    @Override
    public void loanBook(@NotNull User user,@NotNull String title) throws LibraryException {
        if (!(users.contains(user))) {
            throw new LibraryException("User is not registered");
        }

        for (Book book : listOfBooks) {
            if (book.getTitle().equalsIgnoreCase(title)) {;
                if (book.getStatus() == Book.Status.LOANED) {
                    throw new LibraryException("The book is already on loan");
                }
                book.setStatus(Book.Status.LOANED);
                borrowedBooks.add(book);
                return;
            }
        }

        throw new LibraryException("Book not found");
    }

    @Override
    public void returnBook(@NotNull User user,@NotNull String title) throws LibraryException{
        if (!(users.contains(user))) {
            throw new LibraryException("User is not registered");
        }

        for (Book book : listOfBooks) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.setStatus(Book.Status.AVAILABLE);
                borrowedBooks.remove(book);
                return;
            }
        }
    }

    @Override
    public void registerUser(@NotNull User user, @NotNull Long id) throws UserException {
        if (user.getId() != id) {
            throw new UserException("Registration failed");
        }

        if (user.getId() == id) {
            System.out.println("User registered successfully!");
            users.add(user);
        }
    }
}