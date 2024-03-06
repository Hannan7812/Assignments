/**
 * The Book class represents a book in a library management system.
 * It contains information about the title, author, genre, and availability of the book.
 */
public class Book {
    private String title;
    private String author;
    private String genre;
    private String available;

    public Book(String title, String author, String genre, String available) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.available = available;
    }

    public String get_title() {
        return title;
    }

    public String get_author() {
        return author;
    }

    public String get_genre() {
        return genre;
    }

    public String get_available() {
        return available;
    }

    public void set_title(String new_title) {
        title = new_title;
    }

    public void set_author(String new_author) {
        author = new_author;
    }
    public void set_genre(String new_genre) {
        genre = new_genre;
    }

    public void set_available(String new_available) {
        available = new_available;
    }
}