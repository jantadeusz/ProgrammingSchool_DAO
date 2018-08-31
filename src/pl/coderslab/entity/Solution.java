package pl.coderslab.entity;

import java.sql.*;

public class Solution {

    private int id;
    private Timestamp created;
    private Timestamp updated;
    private String description;
    private int exerciseId;
    private int userId;
    private int gradeVal;
    private String gradeComment;


    public Solution() {
        setCreated();
        setUpdated();
    }

    public void setGradeVal(int gradeVal) {
        this.gradeVal = gradeVal;
    }

    public void setGradeComment(String gradeComment) {
        this.gradeComment = gradeComment;
    }

    public int getGradeVal() {
        return gradeVal;
    }

    public String getGradeComment() {
        return gradeComment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated() {
        Timestamp now = new Timestamp(new java.util.Date().getTime());
        this.created = now;
    }

    public void setCreated(Timestamp timestamp) {
        this.created = timestamp;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated() {
        Timestamp now = new Timestamp(new java.util.Date().getTime());
        this.updated = now;
    }

    public void setUpdated(Timestamp timestamp) {
        this.updated = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static String getCreateTabSolution() {
        return createTabSolution;
    }

    public static void setCreateTabSolution(String createTabSolution) {
        Solution.createTabSolution = createTabSolution;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", created=" + created +
                ", updated=" + updated +
                ", description='" + description + '\'' +
                ", exerciseId=" + exerciseId +
                ", userId=" + userId +
                ", gradeVal=" + gradeVal +
                ", gradeComment=" + gradeComment +
                '}';
    }

    //============================= database methods ============================================

    public static String createTabSolution = "CREATE TABLE `solution` (\n" +
            "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `created` datetime DEFAULT NULL,\n" +
            "  `updated` datetime DEFAULT NULL,\n" +
            "  `description` text COLLATE utf8_polish_ci,\n" +
            "  `exercise_id` int(11) DEFAULT NULL,\n" +
            "  `user_id` bigint(20) DEFAULT NULL,\n" +
            "  'grade_val' INT DEFAULT NULL,\n" +
            "  'grade_comment' MEDIUMTEXT DEFAULT NULL,\n" +
            "  PRIMARY KEY (`id`),\n" +
            "  KEY `fk_solution_1_idx` (`user_id`),\n" +
            "  KEY `fk_solution_2_idx` (`exercise_id`),\n" +
            "  CONSTRAINT `fk_solution_1` FOREIGN KEY (`user_id`) REFERENCES `Users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,\n" +
            "  CONSTRAINT `fk_solution_2` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;";
}
