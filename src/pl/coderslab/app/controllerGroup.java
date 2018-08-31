package pl.coderslab.app;

import pl.coderslab.dao.GroupDao;
import pl.coderslab.entity.Group;

import java.util.List;
import java.util.Scanner;

public class controllerGroup {

    public static void options(Scanner scanner) {
        while (true) {
            System.out.println("   #############   Programming School Groups administration panel   #############   ");
            System.out.println("Current groups: =====================================================================");
            List<Group> currentGroups = GroupDao.loadAll();
            for (Group g : currentGroups) {
                System.out.println(g);
            }
            System.out.println("End of current groups: ==============================================================");

            System.out.println("Available options (type word): " +
                    "\n\t 'add' -add new group, 'edit' -edit group, 'del' -delete group, 'q' -exit panel");
            String initAnswer = scanner.nextLine();
            if (initAnswer.equals("add")) {
                addNewGroup(scanner);

            } else if (initAnswer.equals("edit")) {
                editOneGroup(scanner);

            } else if (initAnswer.equals("del")) {
                deleteOneGroup(scanner);

            } else if (initAnswer.equals("q")) {
                System.out.println("Exit panel ======================================================================");
                break;

            } else {
                System.out.println("Wrong input. Try again");
            }
        }
    }

    private static void addNewGroup(Scanner scanner) {
        Group newGroup = new Group();
        System.out.print("Type group name: ");
        newGroup.setName(scanner.nextLine());
        GroupDao.saveToDB(newGroup);
    }

    private static void editOneGroup(Scanner scanner) {
        while (true) {
            System.out.print("Type id of group to edit: ");
            if (scanner.hasNextInt()) {
                int idToEdit = scanner.nextInt();
                scanner.nextLine();
                Group groupToEdit = GroupDao.loadById(idToEdit);
                if (groupToEdit != null) {
                    System.out.print("Type new name: ");
                    groupToEdit.setName(scanner.nextLine());
                    GroupDao.saveToDB(groupToEdit);
                    break;
                }
            } else {
                System.out.println("Wrong input. Try again.");
                scanner.next();
            }
        }
    }

    private static void deleteOneGroup(Scanner scanner) {
        while (true) {
            System.out.print("Type id of group to remove: ");
            if (scanner.hasNextInt()) {
                int idToDelete = scanner.nextInt();
                scanner.nextLine();
                Group groupToDelete = GroupDao.loadById(idToDelete);
                if (groupToDelete != null) {
                    GroupDao.delete(groupToDelete);
                    break;
                }
            } else {
                System.out.println("Wrong input. Try again.");
                scanner.next();
            }
        }
    }
}

