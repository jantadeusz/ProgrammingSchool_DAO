package pl.coderslab.dao;

import pl.coderslab.entity.Group;
import pl.coderslab.service.DbManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDao {

    static public void saveToDB(Group group) {
        if (group.getId() == 0) {
            addGroup(group);
        } else {
            updateGroup(group);
        }
    }

    static private void addGroup(Group group) {
        String sql = "INSERT INTO User_group(name) VALUES (?)";
        List<String> paramsToSet = new ArrayList<>();
        paramsToSet.add(group.getName());
        Integer id = 0;
        try {
            id = DbManager.executeUpdateOnDatabase(sql, paramsToSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        group.setId(id);
    }

    static private void updateGroup(Group group) {
        List<String> paramsToSet = new ArrayList<>();
        String sql = "UPDATE User_group SET name=? where id = ?";
        paramsToSet.add(group.getName());
        paramsToSet.add(String.valueOf(group.getId()));
        try {
            DbManager.executeUpdateOnDatabase(sql, paramsToSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public List<Group> loadAll() {
        List<Group> result = new ArrayList<>();
        String sql = "SELECT * FROM User_group;";
        try {
            List<String[]> receivedDataFromDb = DbManager.getData(sql, null);
            for (String[] row : receivedDataFromDb) {
                Group group = new Group();
                group.setId(Integer.parseInt(row[0]));
                group.setName(row[1]);
                result.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    static public Group loadById(int id) {
        String sql = "SELECT * FROM User_group where id =?;";
        List<String> parametersToQuery = new ArrayList<>();
        parametersToQuery.add(String.valueOf(id));
        try {
            List<String[]> receivedDataFromDb = DbManager.getData(sql, parametersToQuery);
            if (receivedDataFromDb != null) {
                Group loadedGroup = new Group();
                for (String[] row : receivedDataFromDb) {
                    loadedGroup.setId(Integer.parseInt(row[0]));
                    loadedGroup.setName(row[1]);
                    return loadedGroup; // zostanie odczytany tylko pierwszy wiersz bo for zrobi tylko jedną iterację
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static public void delete(Group group) {
        try {
            String sql = "DELETE FROM User_group WHERE id=?";
            int id = group.getId();
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