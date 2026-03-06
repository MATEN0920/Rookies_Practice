package mylab.library.entity;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private String name;

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public String getName() { return name; }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("도서가 추가되었습니다: " + book.getTitle());
    }

    public Book findBookByTitle(String title) {
        if (title == null) return null;
        for (Book b : books) {
            if (title.equals(b.getTitle())) return b;
        }
        return null;
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        if (author == null) return result;

        for (Book b : books) {
            if (author.equals(b.getAuthor())) {
                result.add(b);
            }
        }
        return result;
    }

    public Book findBookByISBN(String isbn) {
        if (isbn == null) return null;
        for (Book b : books) {
            if (isbn.equals(b.getIsbn())) return b;
        }
        return null;
    }

    public boolean checkOutBook(String isbn) {
        Book book = findBookByISBN(isbn);
        if (book == null) return false;
        return book.checkOut();
    }

    public boolean returnBook(String isbn) {
        Book book = findBookByISBN(isbn);
        if (book == null) return false;

        book.returnBook();
        return true;
    }

    public List<Book> getAvailableBooks() {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.isAvailable()) result.add(b);
        }
        return result;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public int getTotalBooks() {
        return books.size();
    }

    public int getAvailableBooksCount() {
        int count = 0;
        for (Book b : books) {
            if (b.isAvailable()) count++;
        }
        return count;
    }

    public int getBorrowedBooksCount() {
        return getTotalBooks() - getAvailableBooksCount();
    }
}