package mylab.library.control;

import java.util.List;

import mylab.library.entity.Book;
import mylab.library.entity.Library;

public class LibraryManagementSystem {

    public static void main(String[] args) {
        Library library = new Library("중앙 도서관");

        addSampleBooks(library);

        System.out.println("\n===== " + library.getName() + " =====");
        System.out.println("[전체 도서 수]: " + library.getTotalBooks());
        System.out.println("[대출 가능 도서 수]: " + library.getAvailableBooksCount());
        System.out.println("[대출 중인 도서 수]: " + library.getBorrowedBooksCount());

        testFindBook(library);
        testCheckOut(library);

        System.out.println("\n도서관 상태 업데이트:");
        System.out.println("[전체 도서 수]: " + library.getTotalBooks());
        System.out.println("[대출 가능 도서 수]: " + library.getAvailableBooksCount());
        System.out.println("[대출 중인 도서 수]: " + library.getBorrowedBooksCount());

        testReturn(library);

        System.out.println("\n도서관 최종 상태:");
        System.out.println("[전체 도서 수]: " + library.getTotalBooks());
        System.out.println("[대출 가능 도서 수]: " + library.getAvailableBooksCount());
        System.out.println("[대출 중인 도서 수]: " + library.getBorrowedBooksCount());

        displayAvailableBooks(library);
    }

    private static void addSampleBooks(Library library) {
        library.addBook(new Book("자바 프로그래밍", "김자바", "978-89-01-12345-6", 2022));
        library.addBook(new Book("객체지향의 사실과 오해", "조영호", "978-89-01-67890-1", 2015));
        library.addBook(new Book("Clean Code", "Robert C. Martin", "978-0-13-235088-4", 2008));
        library.addBook(new Book("Effective Java", "Joshua Bloch", "978-0-13-468599-1", 2018));
        library.addBook(new Book("Head First Java", "Kathy Sierra", "978-0-596-00920-5", 2005));
        library.addBook(new Book("자바의 정석", "남궁성", "978-89-01-14077-4", 2019));
    }

    private static void testFindBook(Library library) {
        System.out.println("\n===== 도서 검색 테스트 =====");

        Book byTitle = library.findBookByTitle("자바의 정석");
        System.out.println("제목으로 검색 결과:");
        if (byTitle != null) System.out.println(byTitle);
        else System.out.println("검색 결과 없음");

        List<Book> byAuthor = library.findBooksByAuthor("Robert C. Martin");
        System.out.println("\n저자로 검색 결과:");
        if (byAuthor.isEmpty()) System.out.println("검색 결과 없음");
        else {
            for (Book b : byAuthor) System.out.println(b);
        }
    }

    private static void testCheckOut(Library library) {
        System.out.println("\n===== 도서 대출 테스트 =====");

        String isbn = "978-89-01-14077-4";
        boolean ok = library.checkOutBook(isbn);

        System.out.println("도서 대출 성공!");
        if (!ok) {
            System.out.println("대출 실패(ISBN 없음 또는 이미 대출 중)");
            return;
        }

        Book book = library.findBookByISBN(isbn);
        System.out.println("대출된 도서 정보:");
        System.out.println(book);
    }

    private static void testReturn(Library library) {
        System.out.println("\n===== 도서 반납 테스트 =====");

        String isbn = "978-89-01-14077-4";
        boolean ok = library.returnBook(isbn);

        System.out.println("도서 반납 성공!");
        if (!ok) {
            System.out.println("반납 실패(ISBN 없음)");
            return;
        }

        Book book = library.findBookByISBN(isbn);
        System.out.println("반납된 도서 정보:");
        System.out.println(book);
    }

    private static void displayAvailableBooks(Library library) {
        System.out.println("\n===== 대출 가능한 도서 목록 =====");
        List<Book> available = library.getAvailableBooks();
        for (Book b : available) {
            System.out.println(b);
            System.out.println("------------------------");
        }
    }
}