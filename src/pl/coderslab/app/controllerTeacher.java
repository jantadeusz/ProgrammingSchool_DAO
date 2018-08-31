package pl.coderslab.app;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.Exercise;
import pl.coderslab.entity.Solution;
import pl.coderslab.entity.User;

import java.util.List;
import java.util.Scanner;

public class controllerTeacher {

    public static void welcomeTeacher(Scanner scanner) {
        while (true) {
            System.out.println("   #############   Programming School Teacher panel   #############   ");
            System.out.println("Available options (type word): " +
                    "\n\t 'add' -assign new exercise to student, 'view' -view student's solutions, 'grade' -give grades to solutions, 'q' -exit panel");
            String initAnswer = scanner.nextLine();

            if (initAnswer.equals("add")) {
                Solution zeroStateSolution = new Solution();
                printStudents();
                assignStudentIdToEmptySolution(scanner, zeroStateSolution);
                printExercises();
                assignExerciseIdToEmptySolution(scanner, zeroStateSolution);
                SolutionDao.saveToDB(zeroStateSolution);

            } else if (initAnswer.equals("view")) {
                printStudents();
                viewSolutionsOfSpecificStudent(scanner);

            } else if (initAnswer.equals("grade")) {
                printSolvedSolutionsWithoutGrade();
                justifySolution(scanner);

            } else if (initAnswer.equals("q")) {
                System.out.println("Exit panel ======================================================================");
                break;

            } else {
                System.out.println("Wrong input. Try again");
            }
        }
    }

    private static void printStudents() {
        List<User> users = UserDao.loadAllUsers();
        for (User u : users) {
            System.out.println(u);
        }
    }

    private static Solution assignStudentIdToEmptySolution(Scanner scanner, Solution solution) {
        while (true) {
            System.out.print("Type id of student to assign new exercise: ");
            if (scanner.hasNextInt()) {
                int idToAssign = scanner.nextInt();
                scanner.nextLine();
                solution.setUserId(idToAssign);
                return solution;
            } else {
                System.out.println("Wrong input. Try again.");
                scanner.next();
            }
        }
    }

    private static void printExercises() {
        System.out.println("Current exercises in database <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        List<Exercise> exercises = ExerciseDao.loadAll();
        for (Exercise e : exercises) {
            System.out.println(e);
        }
        System.out.println("End of current exercises in database >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

    }

    private static Solution assignExerciseIdToEmptySolution(Scanner scanner, Solution solution) {
        while (true) {
            System.out.print("Type id of exercise to assign: ");
            if (scanner.hasNextInt()) {
                int exToAssign = scanner.nextInt();
                scanner.nextLine();
                solution.setExerciseId(exToAssign);
                return solution;
            } else {
                System.out.println("Wrong input. Try again.");
                scanner.next();
            }
        }
    }

    private static void viewSolutionsOfSpecificStudent(Scanner scanner) {
        while (true) {
            System.out.print("Type id of student to view solutions: ");
            if (scanner.hasNextInt()) {
                int idToView = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Current solutions of student with id: " + idToView + " <<<<<<<<<<<<<<");
                List<Solution> solutions = SolutionDao.loadAllByUserId(idToView);
                for (Solution s : solutions) {
                    System.out.println(s);
                }
                System.out.println("End of solutions. >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                break;
            } else {
                System.out.println("Wrong input. Try again.");
                scanner.next();
            }
        }
    }

    private static void printSolvedSolutionsWithoutGrade() {
        List<Solution> solutions = SolutionDao.loadSolvedWithoutGrade();
        for (Solution s : solutions) {
            System.out.println(s);
        }
    }

    private static void justifySolution(Scanner scanner) {
        System.out.print("Type id of solution to give grade and comment: ");
        if (scanner.hasNextInt()) {
            int idToGrade = scanner.nextInt();
            scanner.nextLine();
            Solution solutionToGrade = SolutionDao.loadById(idToGrade);
            solutionToGrade = giveGradeValueForSolution(solutionToGrade, scanner);
            System.out.println("Insert grade comment for this solution: ");
            String inputForGradeComment = scanner.nextLine();
            solutionToGrade.setGradeComment(inputForGradeComment);
            SolutionDao.saveToDB(solutionToGrade);
        } else {
            System.out.println("Wrong input. Try again.");
            scanner.next();
        }
    }

    public static Solution giveGradeValueForSolution(Solution solutionToGrade, Scanner scanner) {
        while (true) {
            System.out.println("Insert grade between 1-6 for this solution: ");
            if (scanner.hasNextInt()) {
                int inputForGradeValue = scanner.nextInt();
                scanner.nextLine();
                if (inputForGradeValue >= 1 && inputForGradeValue <= 6) {
                    solutionToGrade.setGradeVal(inputForGradeValue);
                    break;
                }
                System.out.println("Wrong input. Try again.");
            } else {
                System.out.println("Wrong input. Try again.");
                scanner.next();
            }
        }
        return solutionToGrade;
    }
}