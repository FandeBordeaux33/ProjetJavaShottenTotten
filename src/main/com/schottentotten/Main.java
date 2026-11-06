package com.schottentotten;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.schottentotten.model.Joueur;
import com.schottentotten.model.Partie;
import com.schottentotten.model.Pioche;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);                     //pour lire ce que tape le joueur

        Joueur joueur1 = new Joueur("Joueur 1", 0, 6);
        Joueur joueur2 = new Joueur("Joueur 2", 1, 6);
        Partie partie = new Partie(joueur1, joueur2, new Pioche());   //la partie distribue les cartes toute seule

        while (!partie.estTerminee()) {
            Joueur joueur = partie.getJoueurCourant();

            System.out.println();
            System.out.println("===== Tour de " + joueur.getNom() + " =====");
            System.out.println(partie.getPlateau());                  //affiche les 9 bornes
            System.out.println(joueur);                               //affiche la main avec les numéros des cartes

            // on redemande tant que le coup n'est pas valide
            boolean coupValide = false;
            while (!coupValide) {
                try {
                    System.out.print("Numéro de la carte à jouer : ");
                    int indiceCarte = scanner.nextInt();
                    System.out.print("Sur quelle borne (1 à 9) : ");
                    int numeroBorne = scanner.nextInt();
                    partie.jouerCarte(indiceCarte, numeroBorne - 1);  //-1 car les bornes vont de 0 à 8 dans le tableau
                    coupValide = true;
                } catch (InputMismatchException e) {                  //le joueur n'a pas tapé un nombre
                    System.out.println("Il faut taper un nombre.");
                    scanner.nextLine();                               //on jette ce qu'il a tapé
                } catch (RuntimeException e) {                        //coup refusé par le modèle (borne pleine, mauvaise carte...)
                    System.out.println("Coup impossible : " + e.getMessage());
                }
            }

            // revendications : le joueur peut en faire plusieurs, 0 pour arrêter
            int numeroRevendication = -1;
            while (numeroRevendication != 0) {
                System.out.print("Revendiquer une borne ? (1 à 9, 0 pour passer) : ");
                try {
                    numeroRevendication = scanner.nextInt();
                    if (numeroRevendication != 0) {
                        if (partie.revendiquerBorne(numeroRevendication - 1)) {
                            System.out.println("Borne " + numeroRevendication + " gagnée !");
                        } else {
                            System.out.println("Tu ne peux pas revendiquer cette borne.");
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Il faut taper un nombre.");
                    scanner.nextLine();
                    numeroRevendication = -1;                         //on redemande
                } catch (RuntimeException e) {                        //borne qui n'existe pas (ex : 12)
                    System.out.println(e.getMessage());
                    numeroRevendication = -1;
                }
            }

            partie.piocher();                                         //ne fait rien si la pioche est vide
            partie.passerAuJoueurSuivant();
        }

        System.out.println(partie.getPlateau());
        System.out.println("Victoire de " + partie.determinerGagnant().getNom() + " !");
        scanner.close();
    }
}