package pl.coderslab.app;

import pl.coderslab.dao.GroupDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.User;
import pl.coderslab.entity.Group;

import java.sql.SQLException;
import java.util.List;

public class test {

    public static void main(String[] args) {

//        DbManager db = DbManager.getInstance();
//        Connection conn = db.getConnection();

//        System.out.println("========================= all groups ==============");
//        List<Group> allGroups = GroupDao.loadAll();
//        for (Group group : allGroups) {
//            System.out.println(group);
//        }

//        System.out.println("=================== load group by id ===============");
//        Group groupLoadedById = GroupDao.loadById(3);
//        System.out.println(groupLoadedById);

//        System.out.println("=================== add group ===============");
//        Group groupToAdd = new Group();
//        groupToAdd.setName("Blue");
//        Group groupToUpdate = GroupDao.loadById(4);
//        groupToUpdate.setName("Updated name of blue group");
//        GroupDao.saveToDB(groupToUpdate);

//        System.out.println("====================== adding new user ========================");
//        User newUser = new User();
//        newUser.setEmail("a@b.c");
//        newUser.setUsername("hasloToHaslo");
//        newUser.setPassword("haslo");
//        newUser.setPersonGroupId(2);
//        UserDao.addUser(newUser);

        System.out.println("====================== update loaded user ========================");
        User loadedUser = UserDao.loadById(12);

//        UserDao.delete(loadedUser);

//        UserDao.delete(loadedUser);
        System.out.println(loadedUser);
//        loadedUser.setPassword("password");
        System.out.println("=================== change password of user =======================");
        System.out.println(UserDao.changePasswordSuccesfull(loadedUser, "haslo2", "haslo3"));
        loadedUser.setUsername("haslo to <haslo3>");
        UserDao.saveToDB(loadedUser);


        System.out.println("====================== load all users of some group ========================");
        List<User> allUsersInGroup = UserDao.loadUsersByGroupId(2);
        for (User u : allUsersInGroup) {
            System.out.println(u);
        }
        System.out.println("====================== load all users ========================");
        List<User> allUsers = UserDao.loadAllUsers();
        for (User u : allUsers) {
            System.out.println(u);
        }



//        System.out.println("=========================spec id==============");
//        Group g = Group.loadById(conn, 5);
//        System.out.println(g);
////            g.delete(conn);
//        g.setName("redd");
//        g.saveToDB(conn);
//        Group newGroup = new Group("pink");
//        newGroup.saveToDB(conn);

    }


}
