package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.mariadb.jdbc.Driver;

public class TestInsertion {
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

            Statement insertStatement = connection.createStatement();
            insertStatement.executeUpdate("INSERT INTO FOURNISSEUR (NOM) VALUES ('La Maison de la Peinture')");
            insertStatement.close();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
