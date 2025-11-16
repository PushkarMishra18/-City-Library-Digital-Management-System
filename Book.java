package citylibrary.models;

public class Book {
    public int bookId;
    public String title;
    public String author;
    public String category;
    public boolean isIssued;

    public Book(int id, String t, String a, String c, boolean issued) {
        this.bookId = id;
        this.title = t;
        this.author = a;
        this.category = c;
        this.isIssued = issued;
    }

    public void displayBookDetails() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Category: " + category);
        System.out.println("Issued: " + (isIssued ? "Yes" : "No"));
    }
}
