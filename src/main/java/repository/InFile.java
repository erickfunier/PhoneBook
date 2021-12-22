package repository;

import domain.Contact;
import domain.PhoneBook;
import util.ErrorMessage;

import java.io.*;

public class InFile implements Repository {

    private final String filePath;

    public InFile(String filePath) {
        this.filePath = filePath;
    }

    private ErrorMessage saveFile(PhoneBook phoneBook) {
        OutputStream outputStream;
        ObjectOutputStream objectOutputStream;
        try {
            outputStream = new FileOutputStream(filePath);
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(phoneBook);
            objectOutputStream.flush();
            objectOutputStream.close();
            return new ErrorMessage(false, "File saved!");
        } catch (IOException e) {
            return new ErrorMessage(true, "Cannot save in file!");
        }
    }

    @Override
    public ErrorMessage addContact(Contact contact) {
        PhoneBook phoneBook = getList();
        phoneBook.getPhoneBook().add(contact);

        return saveFile(phoneBook);
    }

    @Override
    public PhoneBook getList() {
        InputStream inputStream;
        ObjectInputStream objectInputStream;
        PhoneBook phoneBook;

        try {
            inputStream = new FileInputStream(filePath);
            objectInputStream = new ObjectInputStream(inputStream);
            phoneBook = (PhoneBook) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            phoneBook = new PhoneBook();
        }

        return phoneBook;
    }

    @Override
    public int countContact() {
        PhoneBook phoneBook = getList();

        return phoneBook.getPhoneBook().size();
    }

    @Override
    public Contact getContact(int index) {
        PhoneBook phoneBook = getList();
        if (index < phoneBook.getPhoneBook().size()) {
            return phoneBook.getPhoneBook().get(index);
        } else {
            return null;
        }
    }

    @Override
    public ErrorMessage removeContact(int index) {
        PhoneBook phoneBook = getList();
        try {
            phoneBook.getPhoneBook().remove(index);
            return saveFile(phoneBook);
        } catch (IndexOutOfBoundsException e) {
            return new ErrorMessage(true, "Index higher than Phonebook size.\n");
        }
    }

    @Override
    public ErrorMessage editContact(int index, Contact contact) {
        PhoneBook phoneBook = getList();
        try {
            phoneBook.getPhoneBook().set(index, contact);
            return saveFile(phoneBook);
        } catch (NullPointerException e) {
            return new ErrorMessage(true, "Index higher than Phonebook size.\n");
        }
    }
}
