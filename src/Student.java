import java.util.ArrayList;


public class Student {
    private int id;
    private String name;
    private String email;
    private ArrayList<Book> borrowedBooks;


    public Student(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
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
    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }


    public void borrowBook(Book book) {

    if (book.isAvailable()) {
        book.borrow(); // borrow the book
        borrowedBooks.add(book); // add book to borrowedBooks
    } else {
        System.out.println("Sorry, this book is currently unavailable."); // print message
    }
    }


    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            book.returnBook();
            borrowedBooks.remove(book); // remove book from borrowedBooks
        } else {
            System.out.println("This book was not borrowed by you."); // print message
        }   



    }

    @Override
    public String toString() {
        return String.format("Student ID: %d | Name: %s | Email: %s | Borrowed Books: %d",
            id, name, email, borrowedBooks.size());
    }
}




