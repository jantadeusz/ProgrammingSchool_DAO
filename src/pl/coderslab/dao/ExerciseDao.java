package pl.coderslab.dao;

import pl.coderslab.entity.Exercise;
import pl.coderslab.service.DbManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDao {

    static public void saveToDB(Exercise exercise) {
        if (exercise.getId() == 0) {
            addExercise(exercise);
        } else {
            updateExercise(exercise);
        }
    }

    static public void addExercise(Exercise exercise) {
        String sql = "INSERT INTO exercise(title, description) VALUES (?,?);";
        List<String> paramsToSet = new ArrayList<>();
        paramsToSet.add(exercise.getTitle());
        paramsToSet.add(exercise.getDescription());
        try {
            exercise.setId(DbManager.executeUpdateOnDatabase(sql, paramsToSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public void updateExercise(Exercise exercise) {
        String sql = "UPDATE exercise SET title=?, description=? where id = ?";
        List<String> paramsToSet = new ArrayList<>();
        int id = exercise.getId();
        paramsToSet.add(exercise.getTitle());
        paramsToSet.add(exercise.getDescription());
        paramsToSet.add(String.valueOf(id));
        try {
            DbManager.executeUpdateOnDatabase(sql, paramsToSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public List<Exercise> loadAll() {
        List<Exercise> result = new ArrayList<>();
        String sql = "SELECT * FROM exercise;";
        try {
            List<String[]> receivedFromDb = DbManager.getData(sql, null);
            for (String[] row : receivedFromDb) {
                Exercise exercise = new Exercise();
                exercise.setId(Integer.parseInt(row[0]));
                exercise.setTitle(row[1]);
                exercise.setDescription(row[2]);
                result.add(exercise);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    static public Exercise loadById(int id) {
        String sql = "SELECT * FROM exercise where id =?;";
        List<String> paramsToSet = new ArrayList<>();
        paramsToSet.add(String.valueOf(id));
        try {
            List<String[]> receivedFromDb = DbManager.getData(sql, paramsToSet);
            if (receivedFromDb != null) {
                for (String[] row : receivedFromDb) {
                    Exercise exercise = new Exercise();
                    exercise.setId(Integer.parseInt(row[0]));
                    exercise.setTitle(row[1]);
                    exercise.setDescription(row[2]);
                    return exercise;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static public void delete(Exercise exercise) {
        try {
            String sql = "DELETE FROM exercise WHERE id=?;";
            int id = exercise.getId();
            if (id != 0) {
                List<String> parametersToQuery = new ArrayList<>();
                parametersToQuery.add(String.valueOf(id));
                DbManager.executeUpdateOnDatabase(sql, parametersToQuery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}





