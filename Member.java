package citylibrary.models;

import java.util.*;

public class Member {
    public int memberId;
    public String name;
    public String email;
    public List<Integer> issuedBooks = new ArrayList<>();

    public Member(int id, String n, String e) {
        this.memberId = id;
        this.name = n;
        this.email = e;
    }

    public void displayMemberDetails() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.print("Issued Books: ");
        for (int b : issuedBooks) System.out.print(b + " ");
        System.out.println();
    }
}
