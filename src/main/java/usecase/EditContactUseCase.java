package usecase;

import domain.Contact;
import repository.Repository;
import util.ErrorMessage;

public class EditContactUseCase {
    private final Repository repository;
    public EditContactUseCase(Repository repository) {
        this.repository = repository;
    }

    public ErrorMessage editContact(int index, Contact contact) {
        return repository.editContact(index, contact);
    }
}
