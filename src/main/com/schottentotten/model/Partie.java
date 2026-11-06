package com.schottentotten.model;

public class Partie {
    private Joueur[] joueurs;
    private Joueur joueurCourant;
    private Plateau plateau;
    private Pioche pioche;

    public Partie(Joueur joueur1, Joueur joueur2, Pioche pioche) {
        this.joueurs = new Joueur[] {joueur1, joueur2};
        this.joueurCourant = joueur1;
        this.plateau = new Plateau();
        this.pioche = pioche;
        distribuer();
    }

    private void distribuer() {
        for (int i = 0; i < this.joueurs.length; i++) {                             //chaque joueurs pioche
            while (!this.joueurs[i].mainPleine() && !this.pioche.estVide()) {
                this.joueurs[i].ajouterCarte(this.pioche.tirerUneCarte());
            }
        }
    }

    public Joueur getJoueurCourant() {
        return this.joueurCourant;
    }

    public Joueur getJoueur(int numero) {
        return this.joueurs[numero];
    }

    public Plateau getPlateau() {
        return this.plateau;
    }

    public Pioche getPioche() {
        return this.pioche;
    }

    // Le joueur courant pose la carte 
    public void jouerCarte(int indiceCarte, int indiceBorne) {
        Borne borne = this.plateau.getBorne(indiceBorne);          // exception si borne invalide
        int numero = this.joueurCourant.getNumero();
        if (!borne.peutRecevoirCarte(numero)) {
            throw new IllegalStateException("Tu ne peux plus jouer sur la borne " + borne.getNumeroBorne());
        }
        this.joueurCourant.getCarte(indiceCarte);                  // exception si indice invalide
        Carte carte = this.joueurCourant.retirerCarte(indiceCarte);
        borne.ajouterCarte(numero, carte);
    }

    public boolean revendiquerBorne(int indiceBorne) {
        return this.plateau.getBorne(indiceBorne).revendiquer(this.joueurCourant);
    }

    // Le joueur courant complète sa main. Renvoie false si la pioche est vide.
    public boolean piocher() {
        if (this.pioche.estVide()) {
            return false;
        }
        this.joueurCourant.ajouterCarte(this.pioche.tirerUneCarte());
        return true;
    }

    public void passerAuJoueurSuivant() {
        if (this.joueurCourant == this.joueurs[0]) {
            this.joueurCourant = this.joueurs[1];
        } else {
            this.joueurCourant = this.joueurs[0];
        }
    }

    // Renvoie le gagnant, ou null si personne n'a encore gagné
    public Joueur determinerGagnant() {
        for (int i = 0; i < this.joueurs.length; i++) {
            if (this.plateau.aGagne(this.joueurs[i])) {
                return this.joueurs[i];
            }
        }
        return null;
    }

    public boolean estTerminee() {
        return determinerGagnant() != null;
    }
}