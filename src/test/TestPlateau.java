import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.schottentotten.model.Joueur;
import com.schottentotten.model.Plateau;


public class TestPlateau {
    @Test
    public void unPlateauNeufA9BornesEtPersonneNAGagne() {
        Plateau plateau = new Plateau();
        Joueur pierre = new Joueur("Pierre", 0, 6);

        assertEquals(9, plateau.getNombreBornes());
        assertEquals(1, plateau.getBorne(0).getNumeroBorne());     // indice 0 = borne n°1
        assertEquals(9, plateau.getBorne(8).getNumeroBorne());     // indice 8 = borne n°9
        assertEquals(0, plateau.nombreBornesPossedees(pierre));
        assertFalse(plateau.aGagne(pierre));
    }
}