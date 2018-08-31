package pl.coderslab.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Exercise {


    private int id;
    private String title;
    private String description;

    public Exercise() {
    }

    public static String getCreateTabExercise() {
        return createTabExercise;
    }

    public static void setCreateTabExercise(String createTabExercise) {
        Exercise.createTabExercise = createTabExercise;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    //============================= database methods ============================================

    public static String createTabExercise = "CREATE TABLE `exercise` (\n" +
            "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `title` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,\n" +
            "  `description` text COLLATE utf8_polish_ci,\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;";

}