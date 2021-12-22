package usecase;

import domain.PhoneBook;
import org.junit.jupiter.api.Test;
import repository.InMemory;
import repository.Repository;

class ListContactUseCaseTest {
    Repository repo;
    public ListContactUseCaseTest() {
        this.repo = new InMemory();
    }

    @Test
    void getList() {
        PhoneBook phoneBook = repo.getList();
        if (phoneBook == null) {
            System.out.println("FAIL: Unexpected null return!");
            return;
        }
        System.out.println("PASS");
    }
}