package org.devJava.entity.loan;

import org.devJava.exception.LibraryException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.Objects;

public final class Loan {
    private final int id;
    private final int userId;
    private final int bookId;
    private final @NotNull Instant loanDate;
    private final @Nullable Instant returnDate;

    private Loan(int id, int userId, int bookId, @NotNull Instant loanDate, @Nullable Instant returnDate) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public static @NotNull Loan create(int id, int userId, int bookId,@NotNull Instant loanDate,@Nullable Instant returnDate) throws LibraryException {
        if (loanDate.isAfter(returnDate)) {
            throw new LibraryException("Loan date must be less than return date");
        }
        return new Loan(id, userId, bookId, loanDate, returnDate);
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getBookId() {
        return bookId;
    }

    public @NotNull Instant getLoanDate() {
        return loanDate;
    }

    public @Nullable Instant getReturnDate() {
        return returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return id == loan.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
