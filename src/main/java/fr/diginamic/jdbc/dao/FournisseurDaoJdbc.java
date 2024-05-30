package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.mariadb.jdbc.Driver;

import fr.diginamic.jdbc.entites.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDao {

    private Connection connection;

    public FournisseurDaoJdbc() {
        ResourceBundle databaseConfig = ResourceBundle.getBundle("database");
        try {
            DriverManager.registerDriver(new Driver());

            this.connection = DriverManager.getConnection(
                    databaseConfig.getString("database.url"),
                    databaseConfig.getString("database.user"),
                    databaseConfig.getString("database.password"));
        } catch (SQLException exception) {
            exception.printStackTrace();
            closeConnection();
        }
    }

    public FournisseurDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Fournisseur> extraire() {
        try(Statement selectStatement = connection.createStatement();) {
            ResultSet resultSet = selectStatement.executeQuery("SELECT id, nom FROM FOURNISSEUR");
    
            List<Fournisseur> fournisseurs = new ArrayList<>();
            while (resultSet.next()) {
                fournisseurs.add(new Fournisseur(
                        resultSet.getInt("id"),
                        resultSet.getString("nom")));
            }
    
            selectStatement.close();

            return fournisseurs;
        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
        }
        return new ArrayList<>();
    }

    @Override
    public void insert(Fournisseur fournisseur) {
        try(PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO FOURNISSEUR (ID, NOM) VALUES (?, ?)");) {
            insertStatement.setInt(1, fournisseur.getId());
            insertStatement.setString(2, fournisseur.getName());
            insertStatement.executeUpdate();
            
            insertStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
        }
    }

    @Override
    public int update(String ancienNom, String nouveauNom) {
        try(PreparedStatement updateStatement = connection.prepareStatement("UPDATE FOURNISSEUR SET nom =? WHERE nom =?");) {
            updateStatement.setString(1, nouveauNom);
            updateStatement.setString(2, ancienNom);

            int linesChanged = updateStatement.executeUpdate();
            updateStatement.close();

            return linesChanged;
        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
        }

        return 0;
    }

    @Override
    public boolean delete(Fournisseur fournisseur) {
        try(PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM FOURNISSEUR WHERE id =? AND nom =?");) {
            deleteStatement.setInt(1, fournisseur.getId());
            deleteStatement.setString(2, fournisseur.getName());
            int numberOfDeleted = deleteStatement.executeUpdate();
            deleteStatement.close();

            return numberOfDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
        }

        return false;
    }

    private void createConnection(){
        ResourceBundle databaseConfig = ResourceBundle.getBundle("database");
        try {
            DriverManager.registerDriver(new Driver());

            this.connection = DriverManager.getConnection(
                    databaseConfig.getString("database.url"),
                    databaseConfig.getString("database.user"),
                    databaseConfig.getString("database.password"));
        } catch (SQLException exception) {
            exception.printStackTrace();
            closeConnection();
        }
    }

    private void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

}
