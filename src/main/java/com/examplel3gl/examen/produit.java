package com.examplel3gl.examen;

import javafx.collections.ObservableList;

public class produit extends categorie{

    private int id;
    private String libelle;
    private int quantite;
    private int prix_unitaire;

    private Integer idcategorie;
    private String libellec;

    public String getLibellec() {
        return libellec;
    }

    public void setLibellec(String libellec) {
        this.libellec = libellec;
    }

    public produit(String libelle, int quantite, int prix_unitaire, ObservableList<Integer> items) {
        this.libelle = libelle;
        this.quantite = quantite;
        this.prix_unitaire = prix_unitaire;
        this.idcategorie = super.getId();

    }

    public produit() {

    }



    public produit(String libelle, int quantite, int prix_unitaire, int idcategorie) {
        this.libelle = libelle;
        this.quantite = quantite;
        this.prix_unitaire = prix_unitaire;
        this.idcategorie = idcategorie;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(int prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public int getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    @Override
    public String toString() {
        return "produit{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", quantite=" + quantite +
                ", prix_unitaire=" + prix_unitaire +
                ", idcategorie=" + idcategorie +
                '}';
    }
}