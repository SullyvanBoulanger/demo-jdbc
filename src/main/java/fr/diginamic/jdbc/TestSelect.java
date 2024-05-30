package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.mariadb.jdbc.Driver;

import fr.diginamic.jdbc.entites.Fournisseur;

public class TestSelect {
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

            Statement selectStatement = connection.createStatement();
            ResultSet resultSet = selectStatement.executeQuery("SELECT id, nom FROM FOURNISSEUR");
            
            List<Fournisseur> fournisseurs = new ArrayList<>();
            while (resultSet.next()) {
                fournisseurs.add(new Fournisseur(
                    resultSet.getInt("id"), 
                    resultSet.getString("nom")
                ));
            }
            
            selectStatement.close();

            connection.close();

            fournisseurs.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
