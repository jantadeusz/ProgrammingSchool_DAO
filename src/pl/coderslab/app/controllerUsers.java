package pl.coderslab.app;

import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.User;

import java.util.List;
import java.util.Scanner;

public class controllerUsers {

    public static void options(Scanner scanner) {
        while (true) {
            System.out.println("   #############   Programming School Users administration panel   #############   ");
            System.out.println("Current students: ===================================================================");
            List<User> currentUsers = UserDao.loadAllUsers();
            for (User user : currentUsers) {
                System.out.println(user);
            }
            System.out.println("End of current students: ============================================================");
            System.out.println("Available options (type word): " +
                    "\n\t 'add' -add new student, 'edit' -edit student, 'del' -delete student, 'q' -exit panel");
            String initAnswer = scanner.nextLine();
            if (initAnswer.equals("add")) {
                addNewUser(scanner);

            } else if (initAnswer.equals("edit")) {
                editOneUser(scanner);

            } else if (initAnswer.equals("del")) {
                deleteOneUser(scanner);

            } else if (initAnswer.equals("q")) {
                System.out.println("Exit panel ======================================================================");
                break;

            } else {
                System.out.println("Wrong input. Try again");
            }
        }
    }

    private static void addNewUser(Scanner scanner) {
        User newUser = new User();
        System.out.print("Type user name: ");
        newUser.setUsername(scanner.nextLine());
        System.out.print("Type user email: ");
        newUser.setEmail(scanner.nextLine());
        while (true) {
            System.out.print("Insert number of user group: ");
            if (scanner.hasNextInt()) {
                newUser.setPersonGroupId(scanner.nextInt());
                scanner.nextLine();
                break;
            } else {
                System.out.println("Wrong input. Try again.");
                scanner.next();
            }
        }
        System.out.print("Type user password: ");
        newUser.setPassword(scanner.nextLine());
        UserDao.saveToDB(newUser);
    }

    private static void editOneUser(Scanner scanner) {
        while (true) {
            System.out.print("Type id of user to edit: ");
            if (scanner.hasNextInt()) {
                int idToEdit = scanner.nextInt();
                scanner.nextLine();
                User userToEdit = UserDao.loadById(idToEdit);
                if (userToEdit != null) {
                    System.out.print("Type new username: ");
                    userToEdit.setUsername(scanner.nextLine());
                    System.out.print("Type new email: ");
                    userToEdit.setEmail(scanner.nextLine());
                    System.out.print("Type new password: ");
                    userToEdit.setPassword(scanner.nextLine());
                    System.out.print("Type new group: ");
                    userToEdit.setPersonGroupId(scanner.nextInt());
                    scanner.nextLine();
                    UserDao.saveToDB(userToEdit);
                    break;
                }
            } else {
                System.out.println("Wrong input. Try again.");
                scanner.next();
            }
        }
    }

    private static void deleteOneUser(Scanner scanner) {
        while (true) {
            System.out.print("Type id of user to remove: ");
            if (scanner.hasNextInt()) {
                int idToDelete = scanner.nextInt();
                scanner.nextLine();
                User userToDelete = UserDao.loadById(idToDelete);
                if (userToDelete != null) {
                    UserDao.delete(userToDelete);
                    break;
                }
            } else {
                System.out.println("Wrong input. Try again.");
                scanner.next();
            }
        }
    }
}
