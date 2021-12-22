package repository;

import domain.Contact;
import domain.PhoneBook;
import util.ErrorMessage;

public class InMemory implements Repository {

    private final PhoneBook phoneBook = new PhoneBook();

    @Override
    public PhoneBook getList() {
        return phoneBook;
    }

    @Override
    public ErrorMessage addContact(Contact contact) {
        this.phoneBook.getPhoneBook().add(contact);
        return new ErrorMessage(false, "The record added.\n");
    }

    @Override
    public int countContact() {
        return this.phoneBook.getPhoneBook().size();
    }

    @Override
    public Contact getContact(int index) {
        if (index < this.phoneBook.getPhoneBook().size()) {
            return this.phoneBook.getPhoneBook().get(index);
        } else {
            return null;
        }
    }

    @Override
    public ErrorMessage removeContact(int index) {
        if (index < this.phoneBook.getPhoneBook().size()) {
            this.phoneBook.getPhoneBook().remove(index);
            return new ErrorMessage(false, "The record removed.\n");
        } else {
            return new ErrorMessage(true, "Index higher than Phonebook size.\n");
        }
    }

    @Override
    public ErrorMessage editContact(int index, Contact contact) {
        if (index < this.phoneBook.getPhoneBook().size()) {
            this.phoneBook.getPhoneBook().set(index, contact);
            return new ErrorMessage(false, "The record updated.\n");
        } else {
            return new ErrorMessage(true, "Index higher than Phonebook size.\n");
        }

    }
}
