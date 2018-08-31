package pl.coderslab.entity;

import org.mindrot.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {

    private int id;
    private String email;
    private String username;
    private String password;
    private int personGroupId;
    private String salt;

    public User() {
    }

    public User(String username, String email, String password, int personGroupId) {
        this.username = username;
        this.email = email;
        this.setPassword(password);
        this.personGroupId = personGroupId;
    }

    public int getPersonGroupId() {
        return personGroupId;
    }

    public void setPersonGroupId(int personGroupId) {
        this.personGroupId = personGroupId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.salt = BCrypt.gensalt();
        this.password = BCrypt.hashpw(password, this.salt);
    }

    public void transferPasswordAndSalt(String hashedPassword, String salt) {
        this.password = hashedPassword;
        this.salt = salt;
    }


    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() { // sproboj przeniesc set get  password i salt do userdao i wtedy ustaw im atrybut private
        return salt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", personGroupId=" + personGroupId +
                ", salt='" + salt + '\'' +
                '}';
    }

//============================= database methods ============================================

    public static String createTabUsers = "CREATE TABLE `Workshop_2`.`Users` (\n" +
            "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `email` VARCHAR(255) NOT NULL,\n" +
            "  `username` VARCHAR(255) NOT NULL,\n" +
            "  `password` VARCHAR(60) NOT NULL,\n" +
            "  PRIMARY KEY (`id`),\n" +
            "  UNIQUE INDEX `email_UNIQUE` (`email` ASC));\n";
}

