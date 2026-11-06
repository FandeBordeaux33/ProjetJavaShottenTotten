import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;


import org.junit.jupiter.api.Test;

import com.schottentotten.model.Joueur;
import com.schottentotten.model.Partie;
import com.schottentotten.model.Pioche;


public class TestPartie {
    @Test
    public void auDebutChaqueJoueurA6Cartes() {
        Joueur pierre = new Joueur("Pierre", 0, 6);
        Joueur paul = new Joueur("Paul", 1, 6);
        Partie partie = new Partie(pierre, paul, new Pioche());

        assertEquals(6, pierre.getNombreCartes());
        assertEquals(6, paul.getNombreCartes());
        assertEquals(42, partie.getPioche().getCarteRestantes());   // 54 - 2 x 6
        assertSame(pierre, partie.getJoueurCourant());              // c'est Pierre qui commence
    }
}