package domain;

import java.io.Serializable;
import java.util.ArrayList;

public class PhoneBook implements Serializable {
    private ArrayList<Contact> phoneBook;

    public PhoneBook() {
        this.phoneBook = new ArrayList<>();
    }

    public ArrayList<Contact> getPhoneBook() {
        return phoneBook;
    }

    public void setPhoneBook(ArrayList<Contact> phoneBook) {
        this.phoneBook = phoneBook;
    }
}
