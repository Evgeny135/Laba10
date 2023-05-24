package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            Properties properties = new Properties();
            InputStream in = Files.newInputStream(Path.of("C:\\Users\\trish\\IdeaProjects\\Laba10\\src\\main\\resources\\application.properties"));
            properties.load(in);
            URL = properties.getProperty("db.url");
            USER = properties.getProperty("db.user");
            PASSWORD = properties.getProperty("db.password");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

        public static Connection open(){
            try {
                return DriverManager.getConnection(URL,USER,PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

}
