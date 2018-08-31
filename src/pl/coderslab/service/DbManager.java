package pl.coderslab.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbManager {

    private static final String dbName = "Workshop_2";
    private static final String dbUser = "root";
    private static final String dbPass = "coderslab";

    private static Connection createConn() throws SQLException {
        String connUrl = "jdbc:mysql://localhost:3306/" + dbName + "?useSSL=false&characterEncoding=utf8";
        Connection connection = DriverManager.getConnection(connUrl, dbUser, dbPass);
        return connection;
    }

    /**
     * Execute insert query and return id of created element, if not then null
     *
     * @param sqlCommand
     * @param params
     * @return id or null
     * @throws SQLException
     */
    public static Integer executeUpdateOnDatabase(String sqlCommand, List<String> params) throws SQLException {
        try (Connection conn = createConn()) {
            String[] generatedColumns = {"id"};
            PreparedStatement pst = conn.prepareStatement(sqlCommand, generatedColumns);
            if (params != null) {
                int i = 1;
                for (String p : params) {
                    pst.setString(i++, p);
                }
            }
            pst.executeUpdate();
            ResultSet res = pst.getGeneratedKeys();
            if (res.next())
                return res.getInt(1);
            else
                return null;
        } catch (SQLException e) {
            throw e;
        }
    }

    public static List<String[]> getData(String query, List<String> params) throws SQLException {
        try (Connection conn = createConn()) {
            PreparedStatement st = conn.prepareStatement(query);
            if (params != null) {
                int i = 1;
                for (String p : params) {
                    st.setString(i++, p);
                }
            }
            ResultSet rs = st.executeQuery();
            ResultSetMetaData columns = rs.getMetaData();
            List<String[]> result = new ArrayList<>();
            while (rs.next()) {
                String[] row = new String[columns.getColumnCount()];
                for (int j = 1; j <= columns.getColumnCount(); j++) {
                    row[j - 1] = rs.getString(columns.getColumnName(j));
                }
                result.add(row);
            }
            return result;
        } catch (SQLException e) {
            throw e;
        }
    }
}
