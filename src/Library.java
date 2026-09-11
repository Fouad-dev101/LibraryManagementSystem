import java.util.ArrayList;

public class Library {

    private ArrayList<Book> books;
    private ArrayList<Student> students;
    // constructor
    
    public Library() {
        this.books = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle() + " successfully");
    }
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added: " + student.getName() + " successfully");
    }


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

}