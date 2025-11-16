package citylibrary.mainapp;

import citylibrary.manager.LibraryManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        LibraryManager lib = new LibraryManager();
        lib.loadFromFile();

        while (true) {
            System.out.println("\n=== City Library Digital Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Books");
            System.out.println("6. Sort Books");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> lib.addBook(sc);
                case 2 -> lib.addMember(sc);
                case 3 -> lib.issueBook(sc);
                case 4 -> lib.returnBook(sc);
                case 5 -> lib.searchBooks(sc);
                case 6 -> lib.sortBooks();
                case 7 -> {
                    lib.saveToFile();
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
