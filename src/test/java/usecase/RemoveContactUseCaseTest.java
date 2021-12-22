package usecase;

import domain.Contact;
import domain.Person;
import org.junit.jupiter.api.Test;
import repository.InMemory;
import repository.Repository;
import util.ErrorMessage;

import java.time.LocalDate;

class RemoveContactUseCaseTest {
    Repository repo;
    public RemoveContactUseCaseTest() {
        this.repo = new InMemory();
    }

    @Test
    void removeContact() {
        ErrorMessage errorMessage = repo.removeContact(0);
        if (!errorMessage.isError()) {
            System.out.println("FAIL: Unexpected fail when remove contact from empty list");
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
            System.out.println("FAIL: Unexpected fail when add contact");
            return;
        }

        errorMessage = repo.removeContact(0);
        if (errorMessage.isError()) {
            System.out.println("FAIL: Unexpected fail when remove contact");
        }
        System.out.println("PASS");
    }
}