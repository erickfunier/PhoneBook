package usecase;

import domain.PhoneBook;
import repository.Repository;

public class ListContactUseCase {
    private final Repository repository;

    public ListContactUseCase(Repository repository) {
        this.repository = repository;
    }

    public PhoneBook getList() {
        return repository.getList();
    }
}
