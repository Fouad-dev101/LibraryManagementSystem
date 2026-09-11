public class Book {

    private String title;    // The title of the book
    private String author;   // The author's name
    private String isbn;     // The ISBN number of the book/ebook
    private boolean available; // Availability status of the book/ebook


    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = true; // By default, the book is available
    }

    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;  
    }
    public String getIsbn() {
        return isbn;
    }
    public boolean isAvailable() {
        return available;
    }



    public void borrow() {
        if (available) {
            available = false; // Mark the book as borrowed
            System.out.println("You have borrowed: " + title);
        } else {
            System.out.println("Sorry, this book is currently unavailable.");
        }
    }

    public void returnBook() {
        if (!available) {
            available = true; // Mark the book as returned
            System.out.println("You have returned: " + title);
        } else {
            System.out.println("This book was not borrowed.");
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s by %s | Available: %s",
            isbn, title, author, available ? "Yes" : "No");
    }

}