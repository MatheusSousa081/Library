package org.devJava.entity.library;

import org.devJava.entity.book.Book;
import org.devJava.entity.loan.Loan;
import org.devJava.entity.user.Client;
import org.devJava.exception.LibraryException;
import org.devJava.repository.BookRepository;
import org.devJava.repository.ClientRepository;
import org.devJava.repository.LoanRepository;
import org.devJava.repository.StockRepository;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.time.Instant;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LibrarySystem implements Library{
    private final @NotNull Stock stock;
    private final @NotNull Set<Client> clients;
    private final @NotNull Set<Book> allBorrowedBooks;
    private final @NotNull List<Loan> loans;
    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;
    private final StockRepository stockRepository;
    private final LoanRepository loanRepository;

    public LibrarySystem(BookRepository bookRepository, ClientRepository clientRepository, StockRepository stockRepository, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
        this.stockRepository = stockRepository;
        this.loanRepository = loanRepository;
        clients = new HashSet<>();
        allBorrowedBooks = new HashSet<>();
        loans = new ArrayList<>();
        stock = new Stock();
    }

    public @NotNull Stock getStock() {
        return stock;
    }

    @Override
    public void addBook(int id, String title, String author, Year year, Book.Gender gender) throws LibraryException {
        bookRepository.create(Book.create(id, title, author, year, gender));
        stock.add(id, title, author, year, gender, 10);
        stockRepository.create(new StockItem(Book.create(id, title, author, year, gender), 10));
    }

    @Override
    public void addBook(Book book) throws LibraryException{
        bookRepository.create(Book.create(book.getId(), book.getTitle(), book.getAuthor(), book.getYear(), book.getGender()));
        stock.add(book.getId(), book.getTitle(), book.getAuthor(), book.getYear(), book.getGender(), 10);
        stockRepository.create(new StockItem(Book.create(book.getId(), book.getTitle(), book.getAuthor(), book.getYear(), book.getGender()), 10));
    }

    @Override
    public void removeBook(int id) throws SQLException{
        stock.remove(id);
        stockRepository.delete(id);
        bookRepository.delete(id);
    }

    @Override
    public void removeBook(Book book) throws SQLException {
        stock.remove(book);
        stockRepository.delete(book.getId());
        bookRepository.delete(book.getId());
    }

    @Override
    public void getAllBooks() {
        bookRepository.findAll();
    }

    public @NotNull Set<Book> getAllBorrowedBooks() {
        return allBorrowedBooks;
    }

    public @NotNull String getLoans() {
        return loans.toString();
    }

    @Override
    public String getBook(int id) {
        return bookRepository.read(id);
    }

    public @NotNull Set<Client> getClients() {
        return clients;
    }

    @Override
    public void loanBook(Book book, Client client) throws LibraryException, SQLException {
        if (book.getStatus() == Book.Status.LOANED) {
            throw new IllegalArgumentException("Book is already on loan!");
        } else if (client.getBorrowedBooksFromClient().size() >= client.getLimitBorrowedBooks()) {
            throw new LibraryException("Customer has already reached the limit of books borrowed");
        } else if (!(stock.contains(book))) {
            throw new LibraryException("Book not found in stock");
        } else {
            Loan loan = Loan.create(client.getId(), book.getId(), Instant.now(), null);
            allBorrowedBooks.add(book);
            StockItem item = stock.get(book.getTitle()).orElseThrow(() -> new LibraryException("Book not found in stock"));
            client.getBorrowedBooksFromClient().add(item.getBook());
            item.setQuantity(item.getQuantity() - 1);
            stockRepository.update(item);
            book.setStatus(Book.Status.LOANED);
            loans.add(loan);
            bookRepository.update(book);
            loanRepository.create(loan);
            clientRepository.update(client);
            System.out.println("Loan realized successfully");
        }
    }

    @Override
    public void returnBook(Book book, Client client) throws LibraryException {
        if (book.getStatus() == Book.Status.AVAILABLE) {
            throw new IllegalArgumentException("Books is already on available");
        } if (!(client.getBorrowedBooksFromClient().contains(book))) {
            throw new RuntimeException("Book not found");
        }
        allBorrowedBooks.remove(book);
        client.getBorrowedBooksFromClient().remove(book);
        StockItem item = stock.get(book.getTitle()).orElseThrow(() -> new LibraryException("Book not found in stock"));
        item.setQuantity(item.getQuantity() + 1);
        stockRepository.update(item);
        book.setStatus(Book.Status.AVAILABLE);
        bookRepository.update(book);
        Loan loan = loans.stream().filter(l -> l.getBookId() == book.getId() && l.getUserId() == client.getId() && l.getReturnDate() == null)
                .findFirst().orElseThrow(() -> new LibraryException("Loan not exists"));
        loans.get(loan.getId()).setReturnDate(Instant.now());
        clientRepository.update(client);
        loanRepository.update(loan);
        System.out.println("Return realizes successfully");
    }

    @Override
    public void registerClient(Client client, String name, String email) throws SQLException {
        if (client.getEmail().equalsIgnoreCase(email) && client.getName().equalsIgnoreCase(name)) {
            clients.add(client);
            clientRepository.create(client);
            System.out.println("Client registered successfully");
        } else {
            System.out.println("Error in registered the client");
        }
    }
}