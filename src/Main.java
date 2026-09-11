public class Main {

    public static void main(String[] args) {

        System.out.println("Library Management System");

        Book book1 = new Book(
                "The Hobbit",
                "J.R.R. Tolkien",
                "9780261103303"
        );

        Student student1 = new Student(
                1,
                "Fouad",
                "fouad@example.com"
        );

        EBook ebook1 = new EBook(
        "Effective Java",
        "Joshua Bloch",
        "9780134685991",
        12.5,
        "PDF"
);

System.out.println(ebook1);

        System.out.println(book1);
        System.out.println(student1);

        student1.borrowBook(book1);
        System.out.println(book1);
        System.out.println(student1);

        student1.returnBook(book1);
        System.out.println(book1);
        System.out.println(student1);
    }
}