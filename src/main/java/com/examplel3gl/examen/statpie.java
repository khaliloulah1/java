package com.examplel3gl.examen;

public class statpie {
    private int Nbr_produit;


    public int getNbr_produit() {
        return Nbr_produit;
    }

    public void setNbr_produit(int nbr_produit) {
        Nbr_produit = nbr_produit;
    }

    @Override
    public String toString() {
        return "statpie{" +
                ", Nbr_produit=" + Nbr_produit +
                '}';
    }
}
