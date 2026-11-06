package com.schottentotten.model;

public class Plateau {
    private Borne[] bornes;

    public Plateau() {
        this.bornes = new Borne[9];
        for (int i = 0; i < 9; i++) {
            this.bornes[i] = new Borne(i + 1);   // bornes numérotées de 1 à 9 pour l'affichage
        }
    }

    // indice de 0 à 8
    public Borne getBorne(int indice) {
        if (indice < 0 || indice >= this.bornes.length) {
            throw new IllegalArgumentException("Borne inexistante : " + indice
                    + " (il faut un nombre entre 0 et " + (this.bornes.length - 1) + ")");
        }
        return this.bornes[indice];
    }

    public int getNombreBornes() {
        return this.bornes.length;
    }

    public int nombreBornesPossedees(Joueur joueur) {
        int compteur = 0;
        for (int i = 0; i < this.bornes.length; i++) {
            if (this.bornes[i].getProprietaire() == joueur) {
                compteur++;
            }
        }
        return compteur;
    }

    // Victoire : 5 bornes au total, OU 3 bornes côte à côte
    public boolean aGagne(Joueur joueur) {
        if (nombreBornesPossedees(joueur) >= 5) {
            return true;
        }
        int aLaSuite = 0;
        for (int i = 0; i < this.bornes.length; i++) {
            if (this.bornes[i].getProprietaire() == joueur) {
                aLaSuite++;
                if (aLaSuite == 3) {
                    return true;
                }
            } else {
                aLaSuite = 0;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String resultat = "";
        for (int i = 0; i < this.bornes.length; i++) {
            resultat += this.bornes[i] + "\n";
        }
        return resultat;
    }
}
