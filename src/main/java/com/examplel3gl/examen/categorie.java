package com.examplel3gl.examen;

public class categorie {
    private int id;
    private String libelle;

    public categorie( String libelle) {
        this.libelle = libelle;
    }

    public categorie() {

    }

    public categorie(int categoryId, String categoryName) {

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

    @Override
    public String toString() {
        return "categorie{" +

                ", libelle='" + libelle + '\'' +
                '}';
    }


}
