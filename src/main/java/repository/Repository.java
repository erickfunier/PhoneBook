package repository;

import domain.Contact;
import domain.PhoneBook;
import util.ErrorMessage;

public interface Repository {
    ErrorMessage addContact(Contact contact);
    ErrorMessage editContact(int index, Contact contact);
    PhoneBook getList();
    int countContact();
    Contact getContact(int index);
    ErrorMessage removeContact(int index);
}
