public class EBook extends Book {

    private double fileSizeMB;
    private String format;

    public EBook(String title, String author, String isbn, double fileSizeMB, String format) {
        super(title, author, isbn);
        this.fileSizeMB = fileSizeMB;
        this.format = format;
    }

    public double getFileSizeMB() {
        return fileSizeMB;
    }

    public String getFormat() {
        return format;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s by %s | Available: %s | File Size: %.2f MB | Format: %s",
                getIsbn(), getTitle(), getAuthor(),
                isAvailable() ? "Yes" : "No",
                fileSizeMB, format);
    }
}