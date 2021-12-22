package controller;

import domain.Contact;
import domain.Organization;
import domain.Person;
import domain.PhoneBook;
import port.Cli;
import repository.Repository;
import usecase.*;
import util.ErrorMessage;
import util.InputValidation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static util.InputValidation.isNumeric;

public class Controller {

    private final Cli cli;

    // Use Cases
    private final AddContactUseCase addContactUseCase;
    private final EditContactUseCase editContactUseCase;
    private final GetContactUseCase getContactUseCase;
    private final ListContactUseCase listContactUseCase;
    private final RemoveContactUseCase removeContactUseCase;
    private final CountContactUseCase countContactUseCase;

    public Controller(Cli cli, Repository repo) {
        this.cli = cli;
        this.addContactUseCase = new AddContactUseCase(repo);
        this.getContactUseCase = new GetContactUseCase(repo);
        this.listContactUseCase = new ListContactUseCase(repo);
        this.removeContactUseCase = new RemoveContactUseCase(repo);
        this.editContactUseCase = new EditContactUseCase(repo);
        this.countContactUseCase = new CountContactUseCase(repo);
    }

    public LocalDate getBirthDate() {
        String birthDate = cli.getString();
        ErrorMessage errorMessage = InputValidation.birthDateValidation(birthDate);

        if (!errorMessage.isError()) {
            return LocalDate.parse(birthDate);
        } else {
            System.out.printf("Error: %s\n", errorMessage.getMessage());
            return null;
        }
    }

    public String getGender() {
        String gender = cli.getString();
        ErrorMessage errorMessage = InputValidation.genderValidation(gender);
        if (!errorMessage.isError()) {
            return gender;
        } else {
            System.out.printf("Error: %s\n", errorMessage.getMessage());
            return "[no data]";
        }
    }

    public String getPhoneNumber() {
        String inputText = cli.getString();
        ErrorMessage errorMessage = InputValidation.phoneValidation(inputText);

        if (!errorMessage.isError()) {
            return inputText;
        } else {
            System.out.printf("Error: %s\n", errorMessage.getMessage());
            return "[no number]";
        }
    }

    public void newContact() {
        cli.printAddMenu();
        String commandAdd = cli.getCommand();
        switch (commandAdd) {
            case "person":
                newPerson();
                break;
            case "organization":
                newOrganization();
                break;
        }
    }

    public void newPerson() {
        cli.printMessage("Enter the name: ");
        String name = cli.getString();

        cli.printMessage("Enter the surname: ");
        String surname = cli.getString();

        cli.printMessage("Enter the birthdate: ");
        LocalDate birthDate = getBirthDate();

        cli.printMessage("Enter the gender (M, F): ");
        String gender = getGender();

        cli.printMessage("Enter the number: ");
        String phoneNumber = getPhoneNumber();

        Contact contact = new Person(name, surname, phoneNumber, birthDate, gender);
        ErrorMessage errorMessage = addContactUseCase.addContact(contact);
        if (errorMessage.isError()) {
            cli.printMessage(errorMessage.getMessage());
        } else {
            cli.printMessage("The record added.\n");
        }
    }

    public void newOrganization() {
        cli.printMessage("Enter the organization name: ");
        String name = cli.getString();

        cli.printMessage("Enter the address: ");
        String address = cli.getString();

        cli.printMessage("Enter the number: ");
        String phoneNumber = getPhoneNumber();

        Contact contact = new Organization(name, address, phoneNumber);
        ErrorMessage errorMessage = addContactUseCase.addContact(contact);

        if (errorMessage.isError()) {
            cli.printMessage(errorMessage.getMessage());
        } else {
            cli.printMessage("The record added.\n");
        }
    }

    public void listContacts() {
        PhoneBook phoneBook = listContactUseCase.getList();
        int i = 0;
        for (Contact contact : phoneBook.getPhoneBook()) {
            if (contact instanceof Organization) {
                cli.printMessage(i+1 + ". " +
                        contact.getName() + "\n");
            } else {
                cli.printMessage(i+1 + ". " +
                        contact.getName() + " " + ((Person) contact).getSurname() + "\n");
            }
            i++;
        }
        if (countContactUseCase.countContact() > 0) {
            cli.printMessage("\n[list] Enter action ([number], back): ");
            String command = cli.getCommand();
            if (!command.equals("back")) {
                if (isNumeric(command)) {
                    int index = Integer.parseInt(command)-1;
                    printContact(index);

                    cli.printMessage("\n[record] Enter action (edit, delete, menu): ");
                    String subCommand = cli.getCommand();
                    while (!subCommand.equals("menu")) {
                        switch (subCommand) {
                            case "edit":
                                editContact(index);
                                break;
                            case "delete":
                                removeContact(index);
                                break;
                        }
                        subCommand = cli.getCommand();
                    }
                } else {
                    cli.printMessage("Invalid action!");
                }
            }
        } else {
            cli.printMessage("The phonebook is empty!\n");
        }
    }

    public void countContacts() {
        cli.printMessage("The Phone Book has " + countContactUseCase.countContact() + " records.\n");
    }

    public void editContact(int index) {
        Contact contact = getContactUseCase.getContact(index);
        if (contact instanceof Person) {
            cli.printMessage("Select a field (name, surname, birth, gender, number): ");
            String field = cli.getCommand();
            switch (field) {
                case "name":
                    cli.printMessage("Enter name: ");
                    contact.setName(cli.getString());
                    contact.setLastEditTime(LocalDateTime.now());
                    break;
                case "surname":
                    cli.printMessage("Enter surname: ");
                    ((Person) contact).setSurname(cli.getString());
                    contact.setLastEditTime(LocalDateTime.now());
                    break;
                case "birth":
                    cli.printMessage("Enter birth date: ");
                    ((Person) contact).setBirthDate(getBirthDate());
                    contact.setLastEditTime(LocalDateTime.now());
                    break;
                case "gender":
                    cli.printMessage("Enter gender: ");
                    ((Person) contact).setGender(getGender());
                    contact.setLastEditTime(LocalDateTime.now());
                    break;
                case "number":
                    cli.printMessage("Enter number: ");
                    contact.setPhoneNumber(getPhoneNumber());
                    contact.setLastEditTime(LocalDateTime.now());
                    break;
                default:
                    cli.printMessage("Wrong option!");
                    return;
            }
        } else {
            cli.printMessage("Select a field (name, address, number): ");
            String field = cli.getCommand();
            switch (field) {
                case "name":
                    cli.printMessage("Enter name: ");
                    contact.setName(cli.getString());
                    contact.setLastEditTime(LocalDateTime.now());
                    break;
                case "address":
                    cli.printMessage("Enter address: ");
                    ((Organization) contact).setAddress(cli.getString());
                    contact.setLastEditTime(LocalDateTime.now());
                    break;
                case "number":
                    cli.printMessage("Enter number: ");
                    contact.setPhoneNumber(getPhoneNumber());
                    contact.setLastEditTime(LocalDateTime.now());
                    break;
                default:
                    cli.printMessage("Wrong option!");
                    return;
            }
        }
        ErrorMessage errorMessage = editContactUseCase.editContact(index, contact);
        if (errorMessage.isError()) {
            cli.printMessage(errorMessage.getMessage());
        } else {
            cli.printMessage("The record updated!\n\n");
        }
    }

    public void removeContact(int index) {
        ErrorMessage errorMessage = removeContactUseCase.removeContact(index);
        if (errorMessage.isError()) {
            cli.printMessage(errorMessage.getMessage());
        } else {
            cli.printMessage("The record removed!\n");
        }
    }

    public void printPhoneBook(ArrayList<Integer> indexes) {
        int i = 1;
        for (int index : indexes) {
            Contact contact = getContactUseCase.getContact(index);
            if (contact instanceof Person)  {
                cli.printMessage(i + ". " + contact.getName() + " " + ((Person) contact).getSurname() + "\n");
            } else if (contact instanceof Organization) {
                cli.printMessage(i + ". " + contact.getName() + "\n");
            }
            i++;
        }
    }

    public void printContact(int index) {
        if (index < countContactUseCase.countContact()) {
            Contact contact = getContactUseCase.getContact(index);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            if (contact instanceof Organization) {
                cli.printMessage("Organization name: " + contact.getName() + "\n" +
                        "Address: " + ((Organization) contact).getAddress() + "\n" +
                        "Number: " + contact.getPhoneNumber() + "\n" +
                        "Time created: " + contact.getTimeCreated().format(dateTimeFormatter) + "\n" +
                        "Time last edit: " + contact.getLastEditTime().format(dateTimeFormatter) + "\n");
            } else {
                cli.printMessage("Name: " + contact.getName() + "\n" +
                        "Surname: " + ((Person) contact).getSurname() + "\n" +
                        "Birth date: " + (((Person) contact).getBirthDate() == null ? "[no data]" : ((Person) contact).getBirthDate()) + "\n" +
                        "Gender: " + ((Person) contact).getGender() + "\n" +
                        "Number: " + contact.getPhoneNumber() + "\n" +
                        "Time created: " + contact.getTimeCreated().format(dateTimeFormatter) + "\n" +
                        "Time last edit: " + contact.getLastEditTime().format(dateTimeFormatter) + "\n");
            }
        }
    }

    public void searchContacts() {
        ArrayList<Integer> searchResultIndex = makeSearch();

        printPhoneBook(searchResultIndex);

        cli.printMessage("\n[search] Enter action ([number], back, again): ");
        String command = cli.getCommand();
        while (!command.equals("back")) {
            if (!isNumeric(command)) {
                if ("again".equals(command)) {
                    searchResultIndex = makeSearch();
                }
            } else {
                int index = Integer.parseInt(command);
                if (index <= searchResultIndex.size()) {
                    printContact(index);
                }

                cli.printMessage("\n[record] Enter action (edit, delete, menu): ");
                String subCommand = cli.getCommand();
                while (!subCommand.equals("menu")) {
                    switch (subCommand) {
                        case "edit":
                            editContact(index);
                            break;
                        case "delete":
                            removeContact(index);
                            break;
                    }
                    subCommand = cli.getCommand();
                }
                return;
            }

            command = cli.getCommand();
        }
    }

    public ArrayList<Integer> makeSearch() {
        cli.printMessage("Enter search query: ");
        String query = cli.getString();
        Pattern pattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
        ArrayList<Integer> searchResultIndex = new ArrayList<>();
        int i = 0;
        for (Contact contact : listContactUseCase.getList().getPhoneBook()) {
            String name;
            if (contact instanceof Person)  {
                name = contact.getName() + " " + ((Person) contact).getSurname() + " " + contact.getPhoneNumber();
            } else {
                name = contact.getName() + " " + contact.getPhoneNumber();
            }

            Matcher matcher = pattern.matcher(name);

            if (matcher.find()) {
                searchResultIndex.add(i);
            }
            i++;
        }

        cli.printMessage("Found " + i + " results: \n");

        return searchResultIndex;
    }
}
