package usecase;

import domain.Contact;
import repository.Repository;
import util.ErrorMessage;

public class AddContactUseCase {
    Repository repository;
    public AddContactUseCase(Repository repository) {
        this.repository = repository;
    }

    public ErrorMessage addContact(Contact contact) {
        return repository.addContact(contact);
    }
}
