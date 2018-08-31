package pl.coderslab.app;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.User;
import pl.coderslab.service.DbManager;
import pl.coderslab.entity.Exercise;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class testExercise {
    public static void main(String[] args) {
//
//        DbManager db = DbManager.getInstance();
//            Connection conn = db.getConnection();
//            Exercise[] exercises = Exercise.loadAll(conn);
//            for (Exercise e : exercises) {
//                System.out.println(e);
//            }
//            Exercise ex= Exercise.loadById(conn,4);
//            ex.setDescription("ooooopis");
//            ex.saveToDB(conn);
//            ex.delete(conn);
        System.out.println("====================== load all exercises ========================");
        List<Exercise> exercises = ExerciseDao.loadAll();
        for (Exercise e : exercises) {
            System.out.println(e);
        }

        System.out.println("====================== add exercise ========================");
//        Exercise exercise1 = new Exercise();
//        exercise1.setTitle("tytul 27.08");
//        exercise1.setDescription("opis1234");
//        ExerciseDao.saveToDB(exercise1);

        System.out.println("====================== load exercise, modify and save to db ========================");
        Exercise loadedEx = ExerciseDao.loadById(9);
//        loadedEx.setTitle("jakis nowy11");
//        loadedEx.setDescription("intellij11");
//
//        System.out.println(loadedEx.getId());
//        System.out.println(loadedEx.getTitle());
//        System.out.println(loadedEx.getDescription());
//
//        ExerciseDao.saveToDB(loadedEx);




        System.out.println("====================== delete exercise ========================");
        ExerciseDao.delete(loadedEx);

    }
}
