import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Library library = new Library();

    public static void main(String[] args) {
        System.out.println("================================");
        System.out.println("   Library Management System");
        System.out.println("================================");

        // Load saved data on startup
        library.loadFromFile();

        boolean running = true;
        while (running) {
            System.out.println();
            printMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> addBook();
                case 2 -> addEBook();
                case 3 -> addStudent();
                case 4 -> library.listBooks();
                case 5 -> library.listStudents();
                case 6 -> borrowBook();
                case 7 -> returnBook();
                case 8 -> findBook();
                case 9 -> library.saveToFile();
                case 10 -> library.loadFromFile();
                case 11 -> {
                    System.out.print("Save before exit? (y/n): ");
                    String ans = scanner.nextLine().trim().toLowerCase();
                    if (ans.equals("y") || ans.equals("yes")) {
                        library.saveToFile();
                    }
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }

    // ---------- Menu ----------

    private static void printMenu() {
        System.out.println("--- Menu ---");
        System.out.println("1. Add Book");
        System.out.println("2. Add EBook");
        System.out.println("3. Add Student");
        System.out.println("4. List Books");
        System.out.println("5. List Students");
        System.out.println("6. Borrow Book");
        System.out.println("7. Return Book");
        System.out.println("8. Find Book by ISBN");
        System.out.println("9. Save Library");
        System.out.println("10. Load Library");
        System.out.println("11. Exit");
    }

    // ---------- Actions ----------

    private static void addBook() {
        System.out.println("--- Add Book ---");
        String title = readString("Title: ");
        String author = readString("Author: ");
        String isbn = readString("ISBN: ");

        Book book = new Book(title, author, isbn);
        library.addBook(book);
    }

    private static void addEBook() {
        System.out.println("--- Add EBook ---");
        String title = readString("Title: ");
        String author = readString("Author: ");
        String isbn = readString("ISBN: ");
        double size = readDouble("File size (MB): ");
        String format = readString("Format (e.g. PDF, EPUB): ");

        EBook ebook = new EBook(title, author, isbn, size, format);
        library.addBook(ebook);
    }

    private static void addStudent() {
        System.out.println("--- Add Student ---");
        int id = readInt("Student ID: ");
        String name = readString("Name: ");
        String email = readString("Email: ");

        Student student = new Student(id, name, email);
        library.addStudent(student);
    }

    private static void borrowBook() {
        System.out.println("--- Borrow Book ---");
        int studentId = readInt("Student ID: ");
        String isbn = readString("Book ISBN: ");

        library.borrowBook(studentId, isbn);
    }

    private static void returnBook() {
        System.out.println("--- Return Book ---");
        int studentId = readInt("Student ID: ");
        String isbn = readString("Book ISBN: ");

        Student student = library.findStudentById(studentId);
        Book book = library.findBookByIsbn(isbn);

        if (student == null) {
            System.out.println("No student with that ID.");
        } else if (book == null) {
            System.out.println("No book with that ISBN.");
        } else {
            student.returnBook(book);
        }
    }

    private static void findBook() {
        System.out.println("--- Find Book ---");
        String isbn = readString("ISBN: ");
        Book book = library.findBookByIsbn(isbn);

        if (book == null) {
            System.out.println("No book found with that ISBN.");
        } else {
            System.out.println(book);
        }
    }

    // ---------- Input helpers ----------

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid decimal number.");
            }
        }
    }
}