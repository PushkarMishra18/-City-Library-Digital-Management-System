package citylibrary.manager;

import citylibrary.models.*;
import java.io.*;
import java.util.*;

public class LibraryManager {

    public Map<Integer, Book> books = new HashMap<>();
    public Map<Integer, Member> members = new HashMap<>();

    // LOAD DATA
    public void loadFromFile() {
        try {
            File bf = new File("books.txt");
            if (bf.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(bf));
                String line;
                while ((line = br.readLine()) != null) {
                    int id = Integer.parseInt(line);
                    String title = br.readLine();
                    String author = br.readLine();
                    String category = br.readLine();
                    boolean issued = Boolean.parseBoolean(br.readLine());
                    books.put(id, new Book(id, title, author, category, issued));
                }
                br.close();
            }

            File mf = new File("members.txt");
            if (mf.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(mf));
                String line;
                while ((line = br.readLine()) != null) {
                    int id = Integer.parseInt(line);
                    String name = br.readLine();
                    String email = br.readLine();
                    int count = Integer.parseInt(br.readLine());
                    Member m = new Member(id, name, email);

                    for (int i = 0; i < count; i++) {
                        m.issuedBooks.add(Integer.parseInt(br.readLine()));
                    }
                    members.put(id, m);
                }
                br.close();
            }

        } catch (Exception e) {
            System.out.println("Error loading files.");
        }
    }

    // SAVE DATA
    public void saveToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("books.txt"));
            for (Book b : books.values()) {
                bw.write(b.bookId + "\n");
                bw.write(b.title + "\n");
                bw.write(b.author + "\n");
                bw.write(b.category + "\n");
                bw.write(b.isIssued + "\n");
            }
            bw.close();

            BufferedWriter mw = new BufferedWriter(new FileWriter("members.txt"));
            for (Member m : members.values()) {
                mw.write(m.memberId + "\n");
                mw.write(m.name + "\n");
                mw.write(m.email + "\n");
                mw.write(m.issuedBooks.size() + "\n");

                for (int id : m.issuedBooks)
                    mw.write(id + "\n");
            }
            mw.close();

        } catch (Exception e) {
            System.out.println("Error saving files.");
        }
    }

    // ADD BOOK (correct output format)
    public void addBook(Scanner sc) {
        sc.nextLine();

        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        int id = books.size() == 0 ? 101 : Collections.max(books.keySet()) + 1;

        books.put(id, new Book(id, title, author, category, false));
        saveToFile();

        System.out.println("Book added successfully with ID: " + id);
    }

    // ADD MEMBER
    public void addMember(Scanner sc) {
        sc.nextLine();

        System.out.print("Enter Member Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        int id = members.size() == 0 ? 201 : Collections.max(members.keySet()) + 1;

        members.put(id, new Member(id, name, email));
        saveToFile();

        System.out.println("Member added successfully with ID: " + id);
    }

    // ISSUE BOOK
    public void issueBook(Scanner sc) {
        System.out.print("Enter Book ID: ");
        int bid = sc.nextInt();

        System.out.print("Enter Member ID: ");
        int mid = sc.nextInt();

        if (!books.containsKey(bid)) {
            System.out.println("Book not found!");
            return;
        }
        if (!members.containsKey(mid)) {
            System.out.println("Member not found!");
            return;
        }
        if (books.get(bid).isIssued) {
            System.out.println("Book already issued!");
            return;
        }

        books.get(bid).isIssued = true;
        members.get(mid).issuedBooks.add(bid);

        saveToFile();
        System.out.println("Book issued successfully!");
    }

    // RETURN BOOK
    public void returnBook(Scanner sc) {
        System.out.print("Enter Book ID: ");
        int bid = sc.nextInt();

        System.out.print("Enter Member ID: ");
        int mid = sc.nextInt();

        if (!books.containsKey(bid) || !members.containsKey(mid)) {
            System.out.println("Invalid IDs!");
            return;
        }

        books.get(bid).isIssued = false;
        members.get(mid).issuedBooks.remove(Integer.valueOf(bid));

        saveToFile();
        System.out.println("Book returned successfully!");
    }

    // SEARCH BOOKS
    public void searchBooks(Scanner sc) {
        sc.nextLine();
        System.out.print("Enter search keyword: ");
        String key = sc.nextLine().toLowerCase();

        for (Book b : books.values()) {
            if (b.title.toLowerCase().contains(key) ||
                b.author.toLowerCase().contains(key) ||
                b.category.toLowerCase().contains(key)) {
                b.displayBookDetails();
                System.out.println("------------------");
            }
        }
    }

    // SORT BOOKS
    public void sortBooks() {
        List<Book> list = new ArrayList<>(books.values());

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).title.compareTo(list.get(j).title) > 0) {
                    Book t = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, t);
                }
            }
        }

        for (Book b : list) {
            b.displayBookDetails();
            System.out.println("------------------");
        }
    }
}
