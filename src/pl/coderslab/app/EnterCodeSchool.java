package pl.coderslab.app;

import java.util.Scanner;

// Main execution file for Coderslab Workshop using pattern: Data Access Object
public class EnterCodeSchool {
    public static void main(String[] args) {
        while (true) {
            System.out.println("   #############   Main Code School menu (EnterCodeSchool)   #############   ");
            System.out.println("Available panels (type number to choose option): " +
                    "\n\t'1' -student panel, '2' -teacher panel, '3' -admin panel, 'q' -exit program");
            Scanner scanner = new Scanner(System.in);
            String decision = scanner.nextLine();
            if (decision.equals("1")) {
                controllerStudent.welcomeStudent(scanner);

            } else if (decision.equals("2")) {
                controllerTeacher.welcomeTeacher(scanner);

            } else if (decision.equals("3")) {
                welcomeAdmin(scanner);

            } else if (decision.equals("q")) {
                System.out.println("Good luck and keep coding :)");
                scanner.close();
                break;
            } else {
                System.out.println("Wrong input. Try again");
            }
        }
    }

    public static void welcomeAdmin(Scanner scanner) {
        while (true) {
            System.out.println("   #############   Administration panel   #############   ");
            System.out.println("Available options (type number to choose option): " +
                    "\n\t '1' -edit students, '2' -edit exercises, '3' -edit groups, 'q' -exit panel");
            String decision = scanner.nextLine();
            if (decision.equals("1")) {
                controllerUsers.options(scanner);

            } else if (decision.equals("2")) {
                controllerExercises.options(scanner);

            } else if (decision.equals("3")) {
                controllerGroup.options(scanner);

            } else if (decision.equals("q")) {
                System.out.println("Exiting admin panel");
                break;
            } else {
                System.out.println("Wrong input. Try again.");
            }
        }
    }
}

