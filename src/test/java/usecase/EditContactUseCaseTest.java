package usecase;

import domain.Contact;
import domain.Person;
import org.junit.jupiter.api.Test;
import repository.InMemory;
import repository.Repository;
import util.ErrorMessage;

import java.time.LocalDate;

class EditContactUseCaseTest {
    Repository repo;
    public EditContactUseCaseTest() {
        this.repo = new InMemory();
    }

    @Test
    void editContact() {
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

        Contact contact1 = new Person(
                "John1",
                "Lucas",
                "(123) 1234-1234",
                LocalDate.now(),
                "M"
        );

        errorMessage = repo.editContact(0, contact1);
        if (errorMessage.isError()) {
            System.out.println("FAIL: Unexpected fail when edit contact!");
            return;
        }
        System.out.println("PASS");
    }
}