package model;

import java.util.Random;

public class Pioche {
    private int cartes_restantes;
    private Carte[] cartes;

    public Pioche(boolean varianteTactique) {
        int tailleMax = varianteTactique ? 56 : 54;
        this.cartes = new Carte[tailleMax];
        this.cartes_restantes = 0;

        // Création des 54 cartes classiques : 6 couleurs (1 à 6) et 9 numéros (1 à 9)
        for (int c = 1; c <= 6; c++) {
            for (int n = 1; n <= 9; n++) {
                this.cartes[this.cartes_restantes] = new Carte(n, c);
                this.cartes_restantes++;
            }
        }
        melangerCarte();
    }


    public void melangerCarte() {
        Random rand = new Random();                                         //créer un objet de type Random
        for (int i = 0; i < this.cartes_restantes; i++) {
            int indexAleatoire = rand.nextInt(this.cartes_restantes);       //donc numéro de 0 à 51
            // On échange la carte i avec une carte choisie au hasard
            Carte temp = this.cartes[i];
            this.cartes[i] = this.cartes[indexAleatoire];
            this.cartes[indexAleatoire] = temp;                             //permute carte [i] avec carte [indexAléatoire]
        }
    }

    // Retire et renvoie la carte du dessus de la pioche
    public Carte tirerUneCarte() {
        if (this.cartes_restantes <= 0) {
            return null; // Plus de cartes dans la pioche !
        }
        this.cartes_restantes--;
        Carte carteTiree = this.cartes[this.cartes_restantes];
        this.cartes[this.cartes_restantes] = null;
        return carteTiree;
    }

    public int getCarteRestantes() {
        return this.cartes_restantes;
    }


    @Override
    public String toString() {
        String resultat = "";
        for (int i = 0; i < this.cartes_restantes; i++) {
            resultat += this.cartes[i] + "\n";
        }
        return resultat;
    }
}
    
class TestPioche {
        public static void main(String[] args) {
            Pioche pioche = new Pioche(false);
            System.out.println(pioche);
            
            Carte carte1 = new Carte(5, 2);
            System.out.println(carte1);
        }
}
    
