package com.schottentotten.model;

import java.util.Random;

public class Pioche {
    private int cartes_restantes;
    private Carte[] cartes;

    public Pioche() {
        this.cartes = new Carte[54];                                        //Tout est dans le package model
        this.cartes_restantes = 0;

        // Création des cartes
        for (int c = 1; c <= 6; c++) {
            for (int n = 1; n <= 9; n++) {
                this.cartes[this.cartes_restantes] = new Carte(n, c);
                this.cartes_restantes++;                                        
            }
        }                                                                   //incrémente d'une carte donc à la fin de la boucle, this.cartes_restantes = 54
        melangerCarte();
    }


    public void melangerCarte() {
        Random rand = new Random();                                         //créer un objet de type Random
        for (int i = 0; i < this.cartes_restantes; i++) {
            int indexAleatoire = rand.nextInt(this.cartes_restantes);       //donc numéro de 0 à 53 (54 valeurs)
            // On échange la carte i avec une carte choisie au hasard
            Carte temp = this.cartes[i];
            this.cartes[i] = this.cartes[indexAleatoire];
            this.cartes[indexAleatoire] = temp;                             //permute carte [i] avec carte [indexAléatoire]
        }
    }

    public Carte tirerUneCarte() {                                          
        if (this.cartes_restantes <= 0) {
            return null; 
        }
        this.cartes_restantes--;                                    //enleve une carte
        Carte carteTiree = this.cartes[this.cartes_restantes];
        this.cartes[this.cartes_restantes] = null;                  //la carte d'indice "this.cartes_restantes" devient null
        return carteTiree;
    }

    public int getCarteRestantes() {                    //avoir le nombre de carte restante
        return this.cartes_restantes;
    }

    public boolean estVide() {                          //true s'il n'y a plus de carte
        return this.cartes_restantes == 0;
    }

    @Override
    public String toString() {                                  //affichage propre de la carte
        String resultat = "";
        for (int i = 0; i < this.cartes_restantes; i++) {
            resultat += this.cartes[i] + "\n";
        }
        return resultat;
    }
}
    
