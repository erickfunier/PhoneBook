package repository;

import domain.Contact;
import domain.Organization;
import domain.Person;
import domain.PhoneBook;
import org.junit.jupiter.api.Test;
import util.ErrorMessage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

class InFileTest {

    private Repository repo;

    public InFileTest() {
        repo = new InFile("phonebook.db");
    }

    private void mockRepo() {
        repo = new InFile("phonebook.db");

        saveFile(new PhoneBook());
    }

    private void saveFile(PhoneBook phoneBook) {
        OutputStream outputStream;
        ObjectOutputStream objectOutputStream;
        try {
            outputStream = new FileOutputStream("phonebook.db");
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(phoneBook);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException ignored) {
        }
    }

    @Test
    void addContactPerson() {
        mockRepo();
        Contact contact = new Person(
                "John",
                "Lucas",
                "(123) 1234-1234",
                LocalDate.now(),
                "M"
        );
        ErrorMessage errorMessage = repo.addContact(contact);
        if (errorMessage.isError()) {
            System.out.println("[addContactPerson] FAIL: Unexpected fail when add Person contact!");
            return;
        }
        System.out.println("[addContactPerson] PASS");
    }

    @Test
    void addContactOrganization() {
        mockRepo();
        Contact contact = new Organization(
                "Robert's Tires",
                "1st Ave Klein, 345",
                "(123) 1234-1234"
        );
        ErrorMessage errorMessage = repo.addContact(contact);
        if (errorMessage.isError()) {
            System.out.println("[addContactOrganization] FAIL: Unexpected fail when add Organization contact");
            return;
        }
        System.out.println("[addContactOrganization] PASS");
    }

    @Test
    void editContact() {
        mockRepo();
        Contact contact = new Person(
                "John",
                "Lucas",
                "(123) 1234-1234",
                LocalDate.now(),
                "M"
        );
        ErrorMessage errorMessage = repo.addContact(contact);
        if (errorMessage.isError()) {
            System.out.println("[editContact] FAIL: Unexpected fail when add contact!");
            return;
        }

        Contact contact1 = new Person(
                "John1",
                "Lucas",
                "(123) 1234-1234",
                LocalDate.now(),
                "M"
        );

        errorMessage = repo.editContact(0, contact1);
        if (errorMessage.isError()) {
            System.out.println("[editContact] FAIL: Unexpected fail when edit contact!");
            return;
        }
        System.out.println("[editContact] PASS");
    }

    @Test
    void removeContact() {
        mockRepo();
        ErrorMessage errorMessage = repo.removeContact(0);
        if (!errorMessage.isError()) {
            System.out.println("[removeContact] FAIL: Unexpected fail when remove contact!");
            return;
        }

        Contact contact = new Person(
                "John",
                "Lucas",
                "(123) 1234-1234",
                LocalDate.now(),
                "M"
        );
        errorMessage = repo.addContact(contact);
        if (errorMessage.isError()) {
            System.out.println("[removeContact] FAIL: Unexpected fail when add contact!");
            return;
        }

        errorMessage = repo.removeContact(0);
        if (errorMessage.isError()) {
            System.out.println("[removeContact] FAIL: Unexpected fail when remove contact!");
            return;
        }
        System.out.println("[removeContact] PASS");
    }

    @Test
    void getContact() {
        mockRepo();
        Contact contact = repo.getContact(0);

        if (contact != null) {
            System.out.println("[getContact] FAIL: Unexpected fail when get contact from empty list!");
            return;
        }

        contact = new Person(
                "John",
                "Lucas",
                "(123) 1234-1234",
                LocalDate.now(),
                "M"
        );

        ErrorMessage errorMessage = repo.addContact(contact);
        if (errorMessage.isError()) {
            System.out.println("[getContact] FAIL: Unexpected fail when add contact!");
            return;
        }

        contact = repo.getContact(0);
        if (contact == null) {
            System.out.println("[getContact] FAIL: Unexpected fail when get contact!");
            return;
        }
        System.out.println("[getContact] PASS");
    }
}