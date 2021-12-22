import port.Cli;
import controller.Controller;
import repository.*;

public class Main {
    public static void main(String[] args) {
        Cli cli = new Cli();
        Repository repo;
        if (args.length > 0) {
            repo = new InFile(args[0]);
            cli.printMessage("open phonebook.db\n");
        } else {
            repo = new InMemory();
        }

        Controller controller = new Controller(cli, repo);

        cli.printMenu();
        String command = cli.getCommand();
        while (!command.equals("exit")) {
            switch (command) {
                case "add":
                    controller.newContact();
                    break;
                case "count":
                    controller.countContacts();
                    break;
                case "list":
                    controller.listContacts();
                    break;
                case "search":
                    controller.searchContacts();
                    break;
                default:
                    break;
            }
            cli.printMenu();
            command = cli.getCommand();
        }
    }
}
