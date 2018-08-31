package pl.coderslab.app;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.entity.Exercise;

import java.util.List;
import java.util.Scanner;

public class controllerExercises {

    public static void options(Scanner scanner) {
        while (true) {
            System.out.println("   #############   Programming School Exercises administration panel   #############   ");
            System.out.println("Current exercises: ==================================================================");
            List<Exercise> currentExercises = ExerciseDao.loadAll();
            for (Exercise e : currentExercises) {
                System.out.println(e);
            }
            System.out.println("End of current exercises: ===========================================================");

            System.out.println("Available options (type word): " +
                    "\n\t'add' -add new exercise, 'edit' -edit exercise, 'del' -delete exercise, 'q' -exit panel");
            String initAnswer = scanner.nextLine();
            if (initAnswer.equals("add")) {
                addNewExercise(scanner);

            } else if (initAnswer.equals("edit")) {
                editOneExercise(scanner);

            } else if (initAnswer.equals("del")) {
                deleteOneExercise(scanner);

            } else if (initAnswer.equals("q")) {
                System.out.println("Exit panel ======================================================================");
                break;

            } else {
                System.out.println("Wrong input. Try again");
            }
        }
    }

    private static void addNewExercise(Scanner scanner) {
        Exercise newExercise = new Exercise();
        System.out.print("Type exercie title: ");
        newExercise.setTitle(scanner.nextLine());
        System.out.print("Type exercise description: ");
        newExercise.setDescription(scanner.nextLine());
        ExerciseDao.saveToDB(newExercise);
    }

    private static void editOneExercise(Scanner scanner) {
        while (true) {
            System.out.print("Type id of exercise to edit: ");
            if (scanner.hasNextInt()) {
                int idToEdit = scanner.nextInt();
                scanner.nextLine();
                Exercise exerciseToEdit = ExerciseDao.loadById(idToEdit);
                if (exerciseToEdit != null) {
                    System.out.print("Type new title: ");
                    exerciseToEdit.setTitle(scanner.nextLine());
                    System.out.print("Type new description: ");
                    exerciseToEdit.setDescription(scanner.nextLine());
                    ExerciseDao.saveToDB(exerciseToEdit);
                    break;
                }
            } else {
                System.out.println("Wrong input. Try again.");
                scanner.next();
            }
        }
    }

    private static void deleteOneExercise(Scanner scanner) {
        while (true) {
            System.out.print("Type id of exercise to remove: ");
            if (scanner.hasNextInt()) {
                int idToDelete = scanner.nextInt();
                scanner.nextLine();
                Exercise exerciseToDelete = ExerciseDao.loadById(idToDelete);
                if (exerciseToDelete != null) {
                    ExerciseDao.delete(exerciseToDelete);
                    break;
                }
            } else {
                System.out.println("Wrong input. Try again.");
                scanner.next();
            }
        }
    }
}