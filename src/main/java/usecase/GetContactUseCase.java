package usecase;

import domain.Contact;
import repository.InMemory;
import repository.Repository;

public class GetContactUseCase {
    private final Repository repository;

    public GetContactUseCase(Repository repository) {
        this.repository = repository;
    }

    public Contact getContact(int index) {
        try {
            return repository.getContact(index);
        } catch (NullPointerException e) {
            return null;
        }
    }
}
