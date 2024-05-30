package fr.diginamic.props;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.mariadb.jdbc.Driver;

public class TestConnexionJdbc {
    public static void main(String[] args) {
        ResourceBundle databaseConfig = ResourceBundle.getBundle("database");
        try {
            DriverManager.registerDriver(new Driver());

            Connection connection = DriverManager.getConnection(
                databaseConfig.getString("database.url"), 
                databaseConfig.getString("database.user"), 
                databaseConfig.getString("database.password")
            );

            System.out.println(connection);

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
