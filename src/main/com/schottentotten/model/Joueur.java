package com.schottentotten.model;

public class Joueur {
    private String nom;
    private int numeroJoueur;   // 0 ou 1 en fonction du joueur
    private Carte[] main;
    private int nbCartes;       // nombre de cases réellement occupées dans main

    public Joueur(String nom, int numeroJoueur, int tailleMain) {
        if (numeroJoueur != 0 && numeroJoueur != 1) {                                   
            throw new IllegalArgumentException("Le numéro de joueur doit être 0 ou 1.");
        }
        this.nom = nom;
        this.numeroJoueur = numeroJoueur;
        this.main = new Carte[tailleMain];                      
        this.nbCartes = 0;                                      //au début il n'a aucune carte
    }

    public String getNom() {
        return this.nom;
    }

    public int getNumero() {
        return this.numeroJoueur;
    }

    public Carte[] getMain() {                                  //renvoie une copie de la main
        Carte[] tableau = new Carte[this.nbCartes];             //tableau juste de la bonne taille, sans les cases vides
        for (int i = 0; i < this.nbCartes; i++) {
            tableau[i] = this.main[i];
        }
        return tableau;                                         //si on modifie la copie, la vraie main ne bouge pas
    }

    public int getNombreCartes() {                              //avoir le nombre de cartes en main
        return this.nbCartes;
    }

    public boolean mainPleine() {                               //true si toutes les cases de la main sont prises
        return this.nbCartes == this.main.length;
    }

    public Carte getCarte(int indice) {                         //regarder une carte sans la retirer
        verifierIndice(indice);
        return this.main[indice];
    }

    public void ajouterCarte(Carte carte) {
        if (mainPleine()) {                                     //plus de place dans la main
            throw new IllegalStateException("La main de " + this.nom + " est déjà pleine.");
        }
        this.main[this.nbCartes] = carte;                       //on met la carte dans la première case libre
        this.nbCartes++;                                        //ajoute une carte
    }

    // Retire la carte à la position indice et décale les suivantes vers la gauche
    public Carte retirerCarte(int indice) {
        verifierIndice(indice);
        Carte carteRetiree = this.main[indice];                 //on garde la carte avant de l'écraser
        for (int i = indice; i < this.nbCartes - 1; i++) {
            this.main[i] = this.main[i + 1];                    //chaque carte prend la place de celle d'avant
        }
        this.nbCartes--;                                        //enlève une carte
        this.main[this.nbCartes] = null;                        //la dernière case est maintenant en double, on la vide
        return carteRetiree;
    }

    private void verifierIndice(int indice) {                   //private : seulement utilisée dans cette classe
        if (indice < 0 || indice >= this.nbCartes) {            //indice hors de la main
            throw new IllegalArgumentException("Indice de carte invalide : " + indice
                    + " (il faut un nombre entre 0 et " + (this.nbCartes - 1) + ")");
        }
    }

    @Override
    public String toString() {                                  //affichage de la main avec le numéro de chaque carte
        String resultat = this.nom + " : ";
        for (int i = 0; i < this.nbCartes; i++) {
            resultat += i + ":" + this.main[i] + " ";           //ex : "Pierre : 0:[5 Bleu] 1:[2 Rouge]"
        }
        return resultat;
    }
}