package com.examplel3gl.examen;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Categorierepository {
    private static Connection connection;

    public Categorierepository() {
        this.connection = new BD().getConnection();
    }
    public void delete(int id) {
        try {
            String sql = "DELETE from categorie  where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("categorie supprimer");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void AjoutCategorie(categorie categorie) {
        try {
            String sql = "INSERT categorie  (libelle) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, categorie.getLibelle());

            statement.executeUpdate();
            System.out.println("categorie enregistrer");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void UpdateCategorie(categorie categorie) {
        try {
            String sql = "UPDATE categorie SET libelle = ? where id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, categorie.getLibelle());

            statement.setInt(2, categorie.getId());
            statement.executeUpdate();
            System.out.println("categorie updated");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public static ObservableList<categorie> getAll() {
        ObservableList<categorie> list = FXCollections.observableArrayList();
        try {

            String sql =  "SELECT * from categorie ";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                categorie p =  new categorie();
                p.setId(result.getInt("id"));
                p.setLibelle(result.getString("libelle"));


                list.add(p);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
