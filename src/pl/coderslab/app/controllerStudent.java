package pl.coderslab.app;

import org.mindrot.BCrypt;
import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.Solution;
import pl.coderslab.entity.User;

import java.util.List;
import java.util.Scanner;

public class controllerStudent {

    public static void welcomeStudent(Scanner scanner) {
        System.out.println("   #############   Programming School Student panel   #############   ");
        while (true) {

            // perform logging to get logged user ----------------------------------------------------------------------
            // logowanie nie powinno sie odbywac w petli, po udanym ogowaniu powinna byc petla wykonujaca czynosc i wyswitlajaca rozwiazania
            User loggedUser = logging(scanner);
            if (loggedUser == null)
                break;
            int loggedUserId = loggedUser.getId();

            // show list
            showActualSolutionsOfLoggedUser(loggedUserId);

            // get decision from user
            System.out.println("Available options (type number to choose option): " +
                    "\n\t '1' -update solution, 'q' -exit panel");
            String decision = scanner.nextLine();
            if (decision.equals("1")) {
                Solution solutionToUpdate = getSolutionToUpdate(scanner);
                updateSolution(scanner, solutionToUpdate);

            } else if (decision.equals("q")) {
                System.out.println("Exiting student panel");
                break;
            } else {
                System.out.println("Wrong input. Try again.");
            }
        }
    }

    private static User logging(Scanner scanner) {
        User loggedUser;
        while (true) {
            System.out.print("Type your userName or 'quit' -exit main menu. ");
            String login = scanner.nextLine();
            if (login.equals("quit")) {
                return null;
            }
            System.out.print("Type password to your account: ");
            String candidate = scanner.nextLine();
            List<User> users = UserDao.loadAllUsers();
            int id = UserDao.getIdFromUserName(users, login);
            if (id == -1) {
                System.out.println("User name not found. Try again");
            } else {
                loggedUser = UserDao.loadById(id);
                if (BCrypt.checkpw(candidate, loggedUser.getPassword())) {// sprawdzenie czy haslo jest poprawne
                    System.out.println("Welcome " + loggedUser.getUsername());
                    return loggedUser;
                } else {
                    System.out.println("Wrong password");
                }
            }
        }
    }

    private static void showActualSolutionsOfLoggedUser(int id) {
        System.out.println("Your actual solutions in database <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        List<Solution> solutions = SolutionDao.loadAllByUserId(id);
        for (Solution s : solutions) {
            System.out.println(s);
        }
        System.out.println("\t\t\t\t\t\t\t\t\t\t>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> End of solutions");
    }

    private static Solution getSolutionToUpdate(Scanner scanner) {
        Solution solutionToUpdate;
        while (true) {
            System.out.print("Type id of solution you want to update: ");
            try {
                int id = scanner.nextInt();
                scanner.nextLine();
                solutionToUpdate = SolutionDao.loadById(id);
                if (solutionToUpdate != null) {
                    return solutionToUpdate;
                }
            } catch (Exception e) {
                System.out.println("Wrong data. Try again.");
                e.printStackTrace();
            }
        }
    }

    private static void updateSolution(Scanner scanner, Solution solution) {
        System.out.print("Type new description of solution or 'quit' to exit panel: ");
        String newDescription = scanner.nextLine();
        try {
            solution.setDescription(newDescription);
            SolutionDao.saveToDB(solution);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

