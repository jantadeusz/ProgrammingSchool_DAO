package pl.coderslab.app;

import pl.coderslab.dao.SolutionDao;
import pl.coderslab.service.DbManager;
import pl.coderslab.entity.Solution;
import pl.coderslab.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class testSolution {

    public static void main(String[] args) {

//        DbManager db = DbManager.getInstance();
//
//
//            Connection conn = db.getConnection();
////==================== create new solution ================================
////            Solution solution = new Solution();
////            solution.setDescription("description of solution X");
////            solution.setExerciseId(3);
////            solution.setUserId(1);
////============================== update existing solution =================
////            Solution solution = Solution.loadById(conn, 6);
////            solution.setDescription("after timestamp");
////            solution.saveToDB(conn);
//
//            System.out.println("===== load all solutions of user with spec id=========");
//            Solution[] solutions = Solution.loadAllByUserId(conn, 1);
//            for (Solution s : solutions) {
//                System.out.println(s);
//            }
//
//            System.out.println("===== load all solutions of exercise with spec id=========");
//            Solution[] solutions1 = Solution.loadAllByExerciseId(conn, 3);
//            for (Solution s : solutions1) {
//                System.out.println(s);
//            }
//            System.out.println("===== load all users from spec group =========");
//            User[] users = User.loadAllByGroupId(conn, 3);
//            for (User u : users) {
//                System.out.println(u);
//            }

        System.out.println("====================== load all solutions ========================");
        List<Solution> solutions = SolutionDao.loadAll();
        for (Solution s : solutions) {
            System.out.println(s);
        }

        System.out.println("====================== add solution ========================");
//        Solution newSolution = new Solution();
//        newSolution.setCreated();
//        newSolution.setUpdated();
//        newSolution.setDescription("desc");
//        newSolution.setUserId(1);
//        newSolution.setExerciseId(2);
//        newSolution.setGradeVal(0);
//        SolutionDao.saveToDB(newSolution);
        System.out.println("======================= update solution =====================");
//        Solution loadedSolution = SolutionDao.loadById(1);
//        loadedSolution.setDescription("teraz dopisane 27.08.2018");
//        SolutionDao.saveToDB(loadedSolution);
        System.out.println("======================= load solutions of specific user =====================");
        List<Solution> userSolutions = SolutionDao.loadAllByUserId(11);
        for (Solution s : userSolutions) {
            System.out.println(s);
        }
        System.out.println("======================= load solutions of specific exercise =================");
        List<Solution> exerciseSolutions = SolutionDao.loadAllByExerciseId(2);
        for (Solution s : exerciseSolutions) {
            System.out.println(s);
        }

        System.out.println("======================= load solved solutions without grade =================");
        List<Solution> toGradeSolutions = SolutionDao.loadSolvedWithoutGrade();
        for (Solution s : toGradeSolutions) {
            System.out.println(s);
        }

        System.out.println("======================= give grade for solution and save to db ==============");
        Solution solutionWithoutGrade = SolutionDao.loadById(12);
        solutionWithoutGrade.setGradeVal(4);
        solutionWithoutGrade.setGradeComment("dobrze");
        SolutionDao.saveGradedSolutionToDB(solutionWithoutGrade);
    }
}

