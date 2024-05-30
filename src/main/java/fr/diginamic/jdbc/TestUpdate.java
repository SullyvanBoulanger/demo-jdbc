package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.mariadb.jdbc.Driver;

public class TestUpdate {
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

            Statement updateStatement = connection.createStatement();
            updateStatement.executeUpdate("UPDATE FOURNISSEUR SET NOM = 'Maison des Peintures' WHERE id = 4");
            updateStatement.close();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
