package controller;

import domain.Contact;
import domain.Organization;
import domain.Person;
import org.junit.jupiter.api.Test;
import port.Cli;
import repository.InMemory;
import repository.Repository;

import javax.naming.ldap.Control;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static util.InputValidation.isNumeric;

class ControllerTest {

    private final Controller controller;
    private final Repository repository;

    public ControllerTest() {
        repository = new InMemory();
        controller = new Controller(new Cli(), repository);
    }

    private void mockPhonebook() {
        Contact contact = new Person(
                "John",
                "Test",
                "(123) 1234-1234",
                LocalDate.parse("2010-10-10"),
                "M"
        );

        repository.addContact(contact);

        Contact contact1 = new Organization(
                "John Tires",
                "1st Ave Road",
                "(123) 1234-1234"
        );

        repository.addContact(contact1);
    }

    @Test
    void printPhoneBook() {
        ArrayList<Integer> indexes = new ArrayList<>();

        controller.printPhoneBook(indexes); // Expected null print as the index list and the phonebook is empty

        indexes.add(0);
        indexes.add(1);

        controller.printPhoneBook(indexes); // Expected null print as the phonebook is empty

        mockPhonebook();

        controller.printPhoneBook(indexes);
        System.out.println("[printPhoneBook] PASS");
    }

    @Test
    void printContact() {
        int index = 0;
        controller.printContact(index); // Expected null print cause the phonebook is empty

        mockPhonebook();

        controller.printContact(index);
        System.out.println("[printContact] PASS");
    }

    @Test
    void makeSearch() {
        String query = "John";
        mockPhonebook();
        Pattern pattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
        ArrayList<Integer> searchResultIndex = new ArrayList<>();
        int i = 0;
        for (Contact contact : repository.getList().getPhoneBook()) {
            String name;
            if (contact instanceof Person)  {
                name = contact.getName() + " " + ((Person) contact).getSurname() + " " + contact.getPhoneNumber();
            } else {
                name = contact.getName() + " " + contact.getPhoneNumber();
            }

            Matcher matcher = pattern.matcher(name);

            if (matcher.find()) {
                searchResultIndex.add(i);
            }
            i++;
        }
        if (searchResultIndex.size() != 2) {
            System.out.println("[makeSearch] FAIL: More results than expected!");
        } else {
            System.out.println("[makeSearch] PASS");
        }
    }
}