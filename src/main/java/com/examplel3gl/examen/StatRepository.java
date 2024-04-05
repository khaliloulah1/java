package com.examplel3gl.examen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatRepository {
    private static Connection connection;

    public StatRepository() {
        this.connection = new BD().getConnection();
    }


    public static ObservableList<String> getCap() {
        ObservableList<String> list1 = FXCollections.observableArrayList();


        try {

            String sql =  "SELECT EXTRACT(MONTH FROM date_ajout) AS mois, COUNT(*) AS nombre_de_produits_ajoutes FROM Produit GROUP BY mois ORDER BY mois";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                int o=result.getInt("nombre_de_produits_ajoutes");



                list1.add(String.valueOf(o));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list1;
    }


    public static ObservableList<String> getCap2() {
        ObservableList<String> list = FXCollections.observableArrayList();


        try {

            String sql =  "WITH TousLesMois AS (\n" +
                    "    SELECT EXTRACT(YEAR FROM date_ajout) AS annee, EXTRACT(MONTH FROM date_ajout) AS mois\n" +
                    "    FROM Produit\n" +
                    "    GROUP BY annee, mois\n" +
                    ")\n" +
                    "SELECT\n" +
                    "    CASE\n" +
                    "        WHEN TousLesMois.mois = 1 THEN 'Janvier'\n" +
                    "        WHEN TousLesMois.mois = 2 THEN 'Février'\n" +
                    "        WHEN TousLesMois.mois = 3 THEN 'Mars'\n" +
                    "        WHEN TousLesMois.mois = 4 THEN 'Avril'\n" +
                    "        WHEN TousLesMois.mois = 5 THEN 'Mai'\n" +
                    "        WHEN TousLesMois.mois = 6 THEN 'Juin'\n" +
                    "        WHEN TousLesMois.mois = 7 THEN 'Juillet'\n" +
                    "        WHEN TousLesMois.mois = 8 THEN 'Août'\n" +
                    "        WHEN TousLesMois.mois = 9 THEN 'Septembre'\n" +
                    "        WHEN TousLesMois.mois = 10 THEN 'Octobre'\n" +
                    "        WHEN TousLesMois.mois = 11 THEN 'Novembre'\n" +
                    "        WHEN TousLesMois.mois = 12 THEN 'Décembre'\n" +
                    "    END AS mois_en_lettres,\n" +
                    "    COALESCE(COUNT(Produit.Id), 0) AS nombre_de_produits_ajoutes\n" +
                    "FROM\n" +
                    "    TousLesMois\n" +
                    "LEFT JOIN\n" +
                    "    Produit ON TousLesMois.annee = EXTRACT(YEAR FROM Produit.date_ajout)\n" +
                    "              AND TousLesMois.mois = EXTRACT(MONTH FROM Produit.date_ajout)\n" +
                    "GROUP BY\n" +
                    "    TousLesMois.mois\n" +
                    "ORDER BY\n" +
                    "    TousLesMois.mois;\n";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                String o=result.getString("mois_en_lettres");



                list.add(String.valueOf(o));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }




}
