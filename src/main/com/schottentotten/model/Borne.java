package com.schottentotten.model;

import java.util.Arrays;

public class Borne {
    private Joueur proprietaire;    // null tant que personne n'a revendiqué la borne
    private int numeroBorne;
    private Carte[] coteA;          // côté du joueur 0
    private Carte[] coteB;          // côté du joueur 1
    private int nbCartesA;
    private int nbCartesB;
    private int premierComplet;     // numéro du joueur qui a complété son côté en premier (-1 sinon)

    public Borne(int numeroBorne) {
        this(numeroBorne, 3);
    }

    public Borne(int numeroBorne, int nbCartesMax) {
        this.numeroBorne = numeroBorne;
        this.coteA = new Carte[nbCartesMax];
        this.coteB = new Carte[nbCartesMax];
        this.nbCartesA = 0;
        this.nbCartesB = 0;
        this.premierComplet = -1;
        this.proprietaire = null;
    }

    public int getNumeroBorne() {
        return this.numeroBorne;
    }

    public Joueur getProprietaire() {
        return this.proprietaire;
    }

    public boolean estRevendiquee() {
        return this.proprietaire != null;
    }

    private void verifierNumeroJoueur(int numeroJoueur) {
        if (numeroJoueur != 0 && numeroJoueur != 1) {
            throw new IllegalArgumentException("Numéro de joueur invalide : " + numeroJoueur); //lance une erreur
        }
    }
    
    public boolean estComplete(int numeroJoueur) {  //le joueur a complété ? 
        verifierNumeroJoueur(numeroJoueur);
        if (numeroJoueur == 0) {
            return this.nbCartesA == this.coteA.length;     //renvoie vrai ou faux 
        }
        return this.nbCartesB == this.coteB.length;         //renvoie vrai ou faux 
    }

    public boolean peutRecevoirCarte(int numeroJoueur) {
        return !estRevendiquee() && !estComplete(numeroJoueur);     // ne dois pas être revanqidué et ne dois pas être complète
    }

    // Renvoie une copie des cartes posées d'un côté

    public Carte[] getCartes(int numeroJoueur) {
        verifierNumeroJoueur(numeroJoueur);
        if (numeroJoueur == 0) {
            return Arrays.copyOf(this.coteA, this.nbCartesA);       //renvoie les cartes côté joueur A
        }
        return Arrays.copyOf(this.coteB, this.nbCartesB);           //renvoie les cartes côté joueur B 
    }

    public void ajouterCarte(int numeroJoueur, Carte carte) {
        verifierNumeroJoueur(numeroJoueur);
        if (estRevendiquee()) {
            throw new IllegalStateException("La borne " + this.numeroBorne + " est déjà revendiquée.");
        }
        if (estComplete(numeroJoueur)) {
            throw new IllegalStateException("Ton côté de la borne " + this.numeroBorne + " est déjà plein.");
        }
        if (numeroJoueur == 0) {
            this.coteA[this.nbCartesA] = carte;
            this.nbCartesA++;
        } else {
            this.coteB[this.nbCartesB] = carte;
            this.nbCartesB++;
        }

        if (estComplete(numeroJoueur) && this.premierComplet == -1) {
            this.premierComplet = numeroJoueur;
        }
    }

    public static int calculerSomme(Carte[] cartes) {
        int somme = 0;
        for (int i = 0; i < cartes.length; i++) {
            somme += cartes[i].getNumero();
        }
        return somme;
    }


    // Compare les deux côtés quand ils sont complets et renvoei le numéro du joueur gagnant
    public int comparer() {
        if (!estComplete(0) || !estComplete(1)) {
            return -1;
        }
        TypeCombinaison typeA = calculerType(getCartes(0));
        TypeCombinaison typeB = calculerType(getCartes(1));
        if (typeA != typeB) {                                                   //si les deux types ne sont pas 
            if (typeA.compareTo(typeB) > 0) {
                return 0;
            }
            return 1;
        }
        int sommeA = calculerSomme(getCartes(0));
        int sommeB = calculerSomme(getCartes(1));
        if (sommeA > sommeB) {
            return 0;
        }
        if (sommeB > sommeA) {
            return 1;
        }
        return this.premierComplet;   // égalité parfaite : celui qui a fini en premier gagne
    }

    public boolean revendiquer(Joueur joueur) {
        if (estRevendiquee()) {
            return false;
        }
        if (comparer() == joueur.getNumero()) {
            this.proprietaire = joueur;
            return true;
        }
        return false;
    }




    public static TypeCombinaison calculerType(Carte[] cartes) {
        boolean memeCouleur = true;
        boolean memeNumero = true;
        for (int i = 1; i < cartes.length; i++) {                       //même couleur ? 
            if (cartes[i].getCouleur() != cartes[0].getCouleur()) {
                memeCouleur = false;
            }
            if (cartes[i].getNumero() != cartes[0].getNumero()) {       //même numéro ? 
                memeNumero = false;
            }
        }

        int[] numeros = new int[cartes.length];                         //c'est une suite ? 
        for (int i = 0; i < cartes.length; i++) {
            numeros[i] = cartes[i].getNumero();
        }
        Arrays.sort(numeros);
        boolean suite = true;
        for (int i = 1; i < numeros.length; i++) {
            if (numeros[i] != numeros[i - 1] + 1) {
                suite = false;
            }
        }

        if (suite && memeCouleur) {                                     //décision de la suite de la plus faible à la plus forte 
            return TypeCombinaison.SUITE_COULEUR;
        }
        if (memeNumero) {
            return TypeCombinaison.BRELAN;
        }
        if (memeCouleur) {
            return TypeCombinaison.COULEUR;
        }
        if (suite) {
            return TypeCombinaison.SUITE;
        }
        return TypeCombinaison.SOMME;
    }



    @Override
    public String toString() {
        String etat = "libre";
        if (estRevendiquee()) {
            etat = "à " + this.proprietaire.getNom();
        }
        return Arrays.toString(getCartes(0)) + "  | Borne " + this.numeroBorne + " (" + etat + ") |  "
                + Arrays.toString(getCartes(1));
    }

}

