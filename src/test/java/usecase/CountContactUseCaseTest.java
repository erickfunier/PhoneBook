package usecase;

import domain.Contact;
import domain.Person;
import org.junit.jupiter.api.Test;
import repository.InMemory;
import repository.Repository;
import util.ErrorMessage;

import java.time.LocalDate;

class CountContactUseCaseTest {
    Repository repo;
    public CountContactUseCaseTest() {
        this.repo = new InMemory();
    }

    @Test
    void countContact() {
        int count;

        Contact contact = new Person(
                "John",
                "Lucas",
                "(123) 1234-1234",
                LocalDate.now(),
                "M"
        );

        ErrorMessage errorMessage = repo.addContact(contact);
        if (errorMessage.isError()) {
            System.out.println("FAIL: Unexpected fail when add contact!");
            return;
        }

        count = repo.countContact();
        if (count == 0) {
            System.out.println("FAIL: Unexpected fail when count contact!");
        } else {
            System.out.println("PASS");
        }
    }
}