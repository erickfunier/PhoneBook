package usecase;

import repository.Repository;
import util.ErrorMessage;

public class RemoveContactUseCase {
    private final Repository repository;

    public RemoveContactUseCase(Repository repository) {
        this.repository = repository;
    }

    public ErrorMessage removeContact(int index) {
        return repository.removeContact(index);
    }
}
