package usecase;

import domain.Contact;
import domain.Person;
import org.junit.jupiter.api.Test;
import repository.InMemory;
import repository.Repository;
import util.ErrorMessage;

import java.time.LocalDate;

class AddContactUseCaseTest {
    Repository repo;
    public AddContactUseCaseTest() {
        this.repo = new InMemory();
    }

    @Test
    void addContact() {
        Contact contact = new Person(
                "John",
                "Lucas",
                "(123) 1234-1234",
                LocalDate.now(),
                "M"
        );

        ErrorMessage errorMessage = repo.addContact(contact);
        if (errorMessage.isError()) {
            System.out.println("FAIL: Unexpected fail when add contact");
        } else {
            System.out.println("PASS");
        }
    }
}