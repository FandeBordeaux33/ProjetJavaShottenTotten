import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.schottentotten.model.Carte;
import com.schottentotten.model.Joueur;


public class TestJoueur{
    @Test
    public void testPioche() {
        Joueur Pierre = new Joueur("Pierre", 0, 7);
        assertNotNull(Pierre);       
        assertEquals(Pierre.getNom(), "Pierre"); 
        System.out.println(Pierre.getNom());
        assertEquals(Pierre.getNumero(), 0);   
        System.out.println(Pierre.getNumero());

        Carte[] lamain = Pierre.getMain();
        System.out.println(lamain);

        assertEquals(Pierre.getNombreCartes(), 0); 
        System.out.println(Pierre.getNumero());
    }
}

