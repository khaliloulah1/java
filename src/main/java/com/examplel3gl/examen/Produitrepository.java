package com.examplel3gl.examen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Produitrepository {
    private static Connection connection;

    public Produitrepository() {
        this.connection = new BD().getConnection();
    }
    public void delete(int id) {
        try {
            String sql = "DELETE from produit  where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("produit supprimer");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void AddProduit(produit produit) {
        try {
            String sql = "INSERT produit  (libelle,quantite,prix_unitaire,idcategorie) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, produit.getLibelle());
            statement.setInt(2, produit.getQuantite());
            statement.setInt(3, produit.getPrix_unitaire());
            statement.setInt(4,produit.getIdcategorie() );
            statement.executeUpdate();
            System.out.println("produit enregistrer");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void Updateproduit(produit produit) {
        try {
            String sql = "UPDATE produit SET libelle = ? , quantite = ?, prix_unitaire = ?, idcategorie =? where id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, produit.getLibelle());
            statement.setInt(2, produit.getQuantite());
            statement.setInt(3, produit.getPrix_unitaire());
            statement.setInt(4,produit.getIdcategorie() );
            statement.setInt(5, produit.getId());
            statement.executeUpdate();
            System.out.println("produit updated");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static ObservableList<String> getCat() {
        ObservableList<String> list = FXCollections.observableArrayList();
        ObservableList<String> list1 = FXCollections.observableArrayList();


        try {

            String sql =  "SELECT ctg.id , ctg.libelle , COUNT(prd.id) as Nbr_produit " +
                    "FROM categorie ctg LEFT JOIN produit prd " +
                    "ON ctg.id = prd.idcategorie GROUP BY ctg.id , ctg.libelle";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while(result.next()) {


            String  t=result.getString("libelle");



                list.add(String.valueOf(t));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<String> getCat1() {
        ObservableList<String> list1 = FXCollections.observableArrayList();


        try {

            String sql =  "SELECT ctg.id , ctg.libelle , COUNT(prd.id) as Nbr_produit " +
                    "FROM categorie ctg LEFT JOIN produit prd " +
                    "ON ctg.id = prd.idcategorie GROUP BY ctg.id , ctg.libelle";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                int o=result.getInt("Nbr_produit");



                list1.add(String.valueOf(o));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list1;
    }



    public ObservableList<produit> getstat() {
        ObservableList<produit> list = FXCollections.observableArrayList();
        try {

            String sql =  "SELECT EXTRACT(MONTH FROM date_ajout) AS mois, COUNT(*)\" +\n" +
                    "                    \" AS nombre_de_produits_ajoutes FROM Produit GROUP BY mois ORDER BY mois";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while(result.next()) {

                produit p =  new produit();
                p.setId(result.getInt("id"));
                p.setLibelle(result.getString("libelle"));
                p.setQuantite(result.getInt("quantite"));
                p.setPrix_unitaire(result.getInt("prix_unitaire"));
                p.setIdcategorie(result.getInt("idcategorie"));



                list.add(p);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<Integer> getCategories() {
        ObservableList<Integer> categories = FXCollections.observableArrayList();
        try {
            String sql = "SELECT id, libelle FROM categorie"; // Query to fetch category names and IDs
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int categoryId = result.getInt("id");
                String categoryName = result.getString("libelle");
                categories.add(categoryId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public static ObservableList<String> getCategories1() {
        ObservableList<String> categories = FXCollections.observableArrayList();
        try {
            String sql = "SELECT id, libelle FROM categorie"; // Query to fetch category names and IDs
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int categoryId = result.getInt("id");
                String categoryName = result.getString("libelle");
                categories.add(categoryName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }


// Assuming you have a TableView<Category> called categorietxt


    public ObservableList<produit> getAll() {
        ObservableList<produit> list = FXCollections.observableArrayList();
        try {

            String sql =  "SELECT * from produit as p,categorie as c where p.idcategorie=c.id ";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while(result.next()) {

                produit p =  new produit();
                p.setId(result.getInt("id"));
                p.setLibelle(result.getString("p.libelle"));
                p.setQuantite(result.getInt("quantite"));
                p.setPrix_unitaire(result.getInt("prix_unitaire"));
                p.setIdcategorie(result.getInt("idcategorie"));
                p.setLibellec(result.getString("c.libelle"));
                System.out.println(result.getString("c.libelle"));





                list.add(p);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }



}
