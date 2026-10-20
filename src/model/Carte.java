package com.schottenTotten.model;

/**
 * Représente une carte du jeu (conforme au diagramme de classe).
 */
public class Carte {
    // Attributs privés (Encapsulation)
    private int numero;  // Valeur de 1 à 9 (ou 0 pour un Joker tactique)
    private int couleur; // Couleur de 1 à 6 (ou 0 pour une carte Tactique)

    // Constructeur pour créer une carte
    public Carte(int numero, int couleur) {
        this.numero = numero;
        this.couleur = couleur;
    }

    // Getters pour récupérer les valeurs (comme sur ton diagramme)
    public int getCouleur() {
        return this.couleur;
    }

    public int getNumero() {
        return this.numero;
    }

    // Méthode utile pour afficher la carte sous forme de texte dans la console
    @Override
    public String toString() {
        if (this.couleur == 0) {
            return "[Tactique-Joker]";
        }
        String[] nomsCouleurs = {"", "Rouge", "Bleu", "Vert", "Jaune", "Violet", "Marron"};
        return "[" + this.numero + " " + nomsCouleurs[this.couleur] + "]";
    }
}