package usecase;

import repository.Repository;

public class CountContactUseCase {
    private final Repository repository;

    public CountContactUseCase(Repository repository) {
        this.repository = repository;
    }

    public int countContact() {
        return repository.countContact();
    }
}
