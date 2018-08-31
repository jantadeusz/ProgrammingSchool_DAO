package pl.coderslab.dao;

import pl.coderslab.entity.Solution;
import pl.coderslab.service.DbManager;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SolutionDao {

    static public void saveToDB(Solution solution) {
        if (solution.getId() == 0) {
            addSoluton(solution);
        } else {
            updateSolution(solution);
        }
    }

    static private void addSoluton(Solution solution) {
        String sql = "INSERT INTO solution(created, updated, description, exercise_id, user_id, grade_val, grade_comment) VALUES (?,?,?,?,?,?,?);";
        List<String> paramsToSet = new ArrayList<>();
        paramsToSet.add(String.valueOf(solution.getCreated()));
        paramsToSet.add(String.valueOf(solution.getUpdated()));
        paramsToSet.add(solution.getDescription());
        paramsToSet.add(String.valueOf(solution.getExerciseId()));
        paramsToSet.add(String.valueOf(solution.getUserId()));
        paramsToSet.add(String.valueOf(solution.getGradeVal()));
        paramsToSet.add(solution.getGradeComment());
        try {
            solution.setId(DbManager.executeUpdateOnDatabase(sql, paramsToSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static private void updateSolution(Solution solution) {
        solution.setUpdated();
        List<String> paramsToSet = new ArrayList<>();
        String sql = "UPDATE solution SET updated = ?, description = ?, exercise_id=?, user_id=?, grade_val=?, grade_comment=? WHERE id = ?;";
        paramsToSet.add(String.valueOf(solution.getUpdated()));
        paramsToSet.add(solution.getDescription());
        paramsToSet.add(String.valueOf(solution.getExerciseId()));
        paramsToSet.add(String.valueOf(solution.getUserId()));
        paramsToSet.add(String.valueOf(solution.getGradeVal()));
        paramsToSet.add(solution.getGradeComment());
        paramsToSet.add(String.valueOf(solution.getId()));
        try {
            DbManager.executeUpdateOnDatabase(sql, paramsToSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public List<Solution> loadAll() {
        List<Solution> result = new ArrayList<>();
        String sql = "SELECT * FROM solution;";
        try {
            List<String[]> receivedFromDb = DbManager.getData(sql, null);
            if (receivedFromDb != null) {
                for (String[] row : receivedFromDb) {
                    Solution solution = createSolutionFromRow(row);
//                    solution.setId(Integer.parseInt(row[0]));
//                    solution.setCreated(Timestamp.valueOf(row[1]));
//                    solution.setUpdated(Timestamp.valueOf(row[2]));
//                    solution.setDescription(row[3]);
//                    solution.setExerciseId(Integer.valueOf(row[4]));
//                    solution.setUserId(Integer.valueOf(row[5]));
//                    solution.setGradeVal(Integer.valueOf(row[6])); // jezeli nie ma oceny jeszcze wystawionej to ma byc 0 domyslnie wpisywane, w przypadku bazodanowego null sa problemy przy ladowaniu do intellij
//                    solution.setGradeComment(row[7]);
                    result.add(solution);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    static public Solution loadById(int id) {
        String sql = "SELECT * FROM solution Where id=?;";
        List<String> parametersToQuery = new ArrayList<>();
        parametersToQuery.add(String.valueOf(id));
        try {
            List<String[]> receivedDataFromDb = DbManager.getData(sql, parametersToQuery);
            if (receivedDataFromDb != null) {
                for (String[] row : receivedDataFromDb) {
                    Solution solution = createSolutionFromRow(row);
                    return solution;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static public void delete(Solution solution) {
        try {
            String sql = "DELETE FROM solution WHERE id=?;";
            int id = solution.getId();
            if (id != 0) {
                List<String> parametersToQuery = new ArrayList<>();
                parametersToQuery.add(String.valueOf(id));
                DbManager.executeUpdateOnDatabase(sql, parametersToQuery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public List<Solution> loadAllByUserId(int id) {
        List<Solution> result = new ArrayList<>();
        String sql = "SELECT * FROM solution where user_id=?;";
        List<String> parametersToQuery = new ArrayList<>();
        parametersToQuery.add(String.valueOf(id));
        try {
            List<String[]> receivedFromDb = DbManager.getData(sql, parametersToQuery);
            if (receivedFromDb != null) {
                for (String[] row : receivedFromDb) {
                    Solution solution = createSolutionFromRow(row);
                    result.add(solution);
                }
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static public List<Solution> loadAllByExerciseId(int id) {
        List<Solution> result = new ArrayList<>();
        String sql = "SELECT * FROM solution WHERE exercise_id = ? ORDER BY updated desc;";
        List<String> parametersToQuery = new ArrayList<>();
        parametersToQuery.add(String.valueOf(id));
        try {
            List<String[]> receivedFromDb = DbManager.getData(sql, parametersToQuery);
            if (receivedFromDb != null) {
                for (String[] row : receivedFromDb) {
                    Solution solution = createSolutionFromRow(row);
                    result.add(solution);
                }
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static public List<Solution> loadSolvedWithoutGrade() {
        List<Solution> result = new ArrayList<>();
        String sql = "SELECT * FROM solution where description is not null and grade_val=0;";
        try {
            List<String[]> receivedFromDb = DbManager.getData(sql, null);
            if (receivedFromDb != null) {
                for (String[] row : receivedFromDb) {
                    Solution solution = createSolutionFromRow(row);
                    result.add(solution);
                }
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Solution createSolutionFromRow(String[] row) {
        Solution solution = new Solution();
        solution.setId(Integer.parseInt(row[0]));
        solution.setCreated(Timestamp.valueOf(row[1]));
        solution.setUpdated(Timestamp.valueOf(row[2]));
        solution.setDescription(row[3]);
        solution.setExerciseId(Integer.valueOf(row[4]));
        solution.setUserId(Integer.valueOf(row[5]));
        solution.setGradeVal(Integer.valueOf(row[6]));
        solution.setGradeComment(row[7]);
        return solution;
    }

    public static void saveGradedSolutionToDB(Solution solution) {
        String sql = "UPDATE solution SET grade_val = ?, grade_comment = ? WHERE id = ?;";
        List<String> parametersToQuery = new ArrayList<>();
        parametersToQuery.add(String.valueOf(solution.getGradeVal()));
        parametersToQuery.add(solution.getGradeComment());
        parametersToQuery.add(String.valueOf(solution.getId()));
        try {
            DbManager.executeUpdateOnDatabase(sql, parametersToQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
