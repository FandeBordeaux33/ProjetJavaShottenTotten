package com.schottentotten.model;

// Les combinaisons possibles, de la plus faible à la plus forte.
public enum TypeCombinaison {
    SOMME,          // n'importe quelles cartes
    SUITE,          // ex : 4 5 6 de couleurs différentes
    COULEUR,        // ex : 2 7 9 tous Rouge
    BRELAN,         // ex : 8 8 8
    SUITE_COULEUR   // ex : 4 5 6 tous Bleu
}