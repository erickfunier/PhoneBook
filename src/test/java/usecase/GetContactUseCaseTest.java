package usecase;

import domain.Contact;
import domain.Person;
import org.junit.jupiter.api.Test;
import repository.InMemory;
import repository.Repository;
import util.ErrorMessage;

import java.time.LocalDate;

class GetContactUseCaseTest {
    Repository repo;
    public GetContactUseCaseTest() {
        this.repo = new InMemory();
    }

    @Test
    void getContact() {
        Contact contact = repo.getContact(0);
        if (contact != null) {
            System.out.println("FAIL: Unexpected index failure when get contact!");
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
            System.out.println("FAIL: Unexpected add contact error!");
            return;
        }

        contact = repo.getContact(0);
        if (contact == null) {
            System.out.println("FAIL: Unexpected get contact error, out of index!");
            return;
        }
        System.out.println("PASS");
    }
}