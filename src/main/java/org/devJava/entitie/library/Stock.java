package org.devJava.entitie.library;

import org.devJava.entitie.book.Book;
import org.devJava.exception.LibraryException;
import org.jetbrains.annotations.NotNull;

import java.time.Year;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class Stock implements Iterable<@NotNull Book> {
    private final @NotNull Set<Book> books;

    public Stock() {
        books = new HashSet<>();
    }

    public void add (int id,@NotNull String title,@NotNull String author,@NotNull Year year,@NotNull Book.Gender gender) throws LibraryException {
        books.add(Book.create(id, title, author, year, gender));
    }

    public boolean remove(@NotNull String title) {
        return books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
    }

    public boolean remove(@NotNull Book book) {
        return books.remove(book);
    }

    public Optional<Book> get(@NotNull String title) {
        return books.stream().filter(book -> book.getTitle().equalsIgnoreCase(title)).findFirst();
    }

    public boolean contains(@NotNull String title) {
        return stream().anyMatch(book -> book.getTitle().equalsIgnoreCase(title));
    }

    public boolean contains(@NotNull Book book) {
        return books.contains(book);
    }

    public int size() {
        return books.size();
    }

    public @NotNull Stream<Book> stream() {
        return books.stream();
    }

    @NotNull
    @Override
    public Iterator<@NotNull Book> iterator() {
        return books.iterator();
    }
}
