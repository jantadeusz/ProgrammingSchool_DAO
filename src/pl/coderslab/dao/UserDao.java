package pl.coderslab.dao;
//Uwaga: patrz andotacja w User przy getSalt

import org.mindrot.BCrypt;
import pl.coderslab.entity.User;
import pl.coderslab.service.DbManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    static public void saveToDB(User user) {
        if (user.getId() == 0) {
            addUser(user);
        } else {
            updateUser(user);
        }
    }

    static private void addUser(User user) {
        String sql = "INSERT INTO Users(username, email, password, user_group_id, salt) VALUES (?,?,?,?,?)";
        List<String> paramsToSet = new ArrayList<>();
        paramsToSet.add(user.getUsername());
        paramsToSet.add(user.getEmail());
        paramsToSet.add(user.getPassword());
        paramsToSet.add(String.valueOf(user.getPersonGroupId()));
        paramsToSet.add(user.getSalt()); // nie wiem czy takie ustawianie pól za pomoca geterów jest poprawne
        // ale narazie nie mam innego pomysłu, getterów dla password i salt wydaje mi się że nie powinno być typu public
        try {
            user.setId(DbManager.executeUpdateOnDatabase(sql, paramsToSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static private void updateUser(User user) {
        List<String> paramsToSet = new ArrayList<>();
        String sql = "UPDATE Users SET username=?, email=?, password=?, user_group_id=?, salt =? where id = ?";
        paramsToSet.add(user.getUsername());
        paramsToSet.add(user.getEmail());
        paramsToSet.add(user.getPassword());
        paramsToSet.add(String.valueOf(user.getPersonGroupId()));
        paramsToSet.add(user.getSalt());
        paramsToSet.add(String.valueOf(user.getId()));
        try {
            DbManager.executeUpdateOnDatabase(sql, paramsToSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //dodatkowa metoda na zmiane hasla ktora przyjmuje istniejace haslo
    static public boolean changePasswordSuccesfull(User loggedUser, String oldPass, String newPass) {
        if (BCrypt.checkpw(oldPass, loggedUser.getPassword())) {// true jesli haslo jest poprawne
            loggedUser.setPassword(newPass);
            return true;
        }
        return false;
    }

    static public User loadById(int id) {

        String sql = "SELECT * FROM Users where id=?";
        List<String> parametersToQuery = new ArrayList<>();
        parametersToQuery.add(String.valueOf(id));
        try {
            List<String[]> receivedDataFromDb = DbManager.getData(sql, parametersToQuery);
            if (receivedDataFromDb != null) {
                User loadedUser = new User();
                for (String[] row : receivedDataFromDb) {
                    loadedUser.setId(Integer.parseInt(row[0]));
                    loadedUser.setEmail(row[1]);
                    loadedUser.setUsername(row[2]);
                    loadedUser.transferPasswordAndSalt(row[3], row[5]); // nie jest to chyba zbyt eleganckie ale działa
                    loadedUser.setPersonGroupId(Integer.parseInt(row[4]));
                    return loadedUser; // zostanie odczytany tylko pierwszy wiersz bo for zrobi tylko jedną iterację
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static public void delete(User user) {
        try {
            String sql = "DELETE FROM Users WHERE id=?";
            int id = user.getId();
            if (id != 0) {
                List<String> parametersToQuery = new ArrayList<>();
                parametersToQuery.add(String.valueOf(id));
                DbManager.executeUpdateOnDatabase(sql, parametersToQuery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public List<User> loadAllUsers() {
        List<User> result = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try {
            List<String[]> receivedFromDb = DbManager.getData(sql, null);
            for (String[] row : receivedFromDb) {
                User user = new User();
                user.setId(Integer.parseInt(row[0]));
                user.setEmail(row[1]);
                user.setUsername(row[2]);
                user.transferPasswordAndSalt(row[3], row[5]);
                user.setPersonGroupId(Integer.parseInt(row[4]));
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    static public List<User> loadUsersByGroupId(int id) {
        List<User> result = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE user_group_id=?";
        List<String> parametersToQuery = new ArrayList<>();
        parametersToQuery.add(String.valueOf(id));
        try {
            List<String[]> receivedFromDb = DbManager.getData(sql, parametersToQuery);
            for (String[] row : receivedFromDb) {
                User user = new User();
                user.setId(Integer.parseInt(row[0]));
                user.setEmail(row[1]);
                user.setUsername(row[2]);
                user.transferPasswordAndSalt(row[3], row[5]);
                user.setPersonGroupId(Integer.parseInt(row[4]));
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;


    }

    public static int getIdFromUserName(List<User> users, String input) {
        int resultId = -1;
        for (User u : users) {
            if (input.equals(u.getUsername())) {
                resultId = u.getId();
            }
        }
        return resultId;
    }
}
