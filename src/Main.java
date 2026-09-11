public class Main {

    public static void main(String[] args) {

        System.out.println("Library Management System");

        Library library = new Library();

        Book book1 = new Book(
                "The Hobbit",
                "J.R.R. Tolkien",
                "9780261103303"
        );

        EBook ebook1 = new EBook(
                "Effective Java",
                "Joshua Bloch",
                "9780134685991",
                12.5,
                "PDF"
        );

        Student student1 = new Student(
                1,
                "Fouad",
                "fouad@example.com"
        );

        library.addBook(book1);
        library.addBook(ebook1);
        library.addStudent(student1);

        library.listBooks();
        library.listStudents();

    Book found = library.findBookByIsbn("9780261103303");
    System.out.println("Found: " + found);

    Book notFound = library.findBookByIsbn("0000000000000");
    System.out.println("Not found: " + notFound);


    }

    

}