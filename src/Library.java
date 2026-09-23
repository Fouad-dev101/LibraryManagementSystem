import java.io.*;
import java.util.ArrayList;

public class Library {

    private ArrayList<Book> books;
    private ArrayList<Student> students;

    private static final String DATA_FILE = "library.txt";
    private static final String SEP = "|";

    public Library() {
        this.books = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    // ---------- Add ----------

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle() + " successfully");
    }

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added: " + student.getName() + " successfully");
    }

    // ---------- List ----------

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        System.out.println("--- Books ---");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void listStudents() {
        if (students.isEmpty()) {
            System.out.println("No students registered.");
            return;
        }
        System.out.println("--- Students ---");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    // ---------- Find ----------

    public Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    // ---------- Borrow ----------

    public void borrowBook(int studentId, String isbn) {
        Student student = findStudentById(studentId);
        Book book = findBookByIsbn(isbn);

        if (student == null) {
            System.out.println("No student with that ID.");
        } else if (book == null) {
            System.out.println("No book with that ISBN.");
        } else {
            student.borrowBook(book);
        }
    }

    // ---------- File I/O ----------

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {

            for (Book book : books) {
                if (book instanceof EBook) {
                    EBook eb = (EBook) book;
                    writer.write(String.join(SEP,
                            "EBOOK",
                            eb.getIsbn(),
                            eb.getTitle(),
                            eb.getAuthor(),
                            String.valueOf(eb.isAvailable()),
                            String.valueOf(eb.getFileSizeMB()),
                            eb.getFormat()
                    ));
                } else {
                    writer.write(String.join(SEP,
                            "BOOK",
                            book.getIsbn(),
                            book.getTitle(),
                            book.getAuthor(),
                            String.valueOf(book.isAvailable())
                    ));
                }
                writer.newLine();
            }

            for (Student student : students) {
                StringBuilder isbns = new StringBuilder();
                for (int i = 0; i < student.getBorrowedBooks().size(); i++) {
                    if (i > 0) isbns.append(";");
                    isbns.append(student.getBorrowedBooks().get(i).getIsbn());
                }
                writer.write(String.join(SEP,
                        "STUDENT",
                        String.valueOf(student.getId()),
                        student.getName(),
                        student.getEmail(),
                        isbns.toString()
                ));
                writer.newLine();
            }

            System.out.println("Library saved to " + DATA_FILE + " ("
                    + books.size() + " books, " + students.size() + " students)");

        } catch (IOException e) {
            System.out.println("Error saving library: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            System.out.println("No saved library found (" + DATA_FILE + "). Starting fresh.");
            return;
        }

        books.clear();
        students.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNum = 0;
            int loadedBooks = 0;
            int loadedStudents = 0;

            while ((line = reader.readLine()) != null) {
                lineNum++;
                if (line.isBlank()) continue;

                String[] parts = line.split("\\" + SEP, -1);
                String type = parts[0];

                try {
                    switch (type) {
                        case "BOOK" -> {
                            Book b = new Book(parts[2], parts[3], parts[1]);
                            b.setAvailable(Boolean.parseBoolean(parts[4]));
                            books.add(b);
                            loadedBooks++;
                        }
                        case "EBOOK" -> {
                            EBook eb = new EBook(parts[2], parts[3], parts[1],
                                    Double.parseDouble(parts[5]), parts[6]);
                            eb.setAvailable(Boolean.parseBoolean(parts[4]));
                            books.add(eb);
                            loadedBooks++;
                        }
                        case "STUDENT" -> {
                            Student s = new Student(
                                    Integer.parseInt(parts[1]),
                                    parts[2],
                                    parts[3]
                            );
                            if (parts.length > 4 && !parts[4].isEmpty()) {
                                String[] isbns = parts[4].split(";");
                                for (String isbn : isbns) {
                                    Book b = findBookByIsbn(isbn);
                                    if (b != null) {
                                        s.linkBook(b);
                                    } else {
                                        System.out.println("Warning: book " + isbn
                                                + " referenced by " + s.getName()
                                                + " not found. Skipping.");
                                    }
                                }
                            }
                            students.add(s);
                            loadedStudents++;
                        }
                        default -> System.out.println("Warning: unknown line type '"
                                + type + "' at line " + lineNum);
                    }
                } catch (Exception ex) {
                    System.out.println("Warning: skipping malformed line " + lineNum
                            + ": " + line);
                }
            }

            System.out.println("Library loaded from " + DATA_FILE + " ("
                    + loadedBooks + " books, " + loadedStudents + " students)");

        } catch (IOException e) {
            System.out.println("Error loading library: " + e.getMessage());
        }
    }
}