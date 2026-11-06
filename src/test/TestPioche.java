import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.schottentotten.model.Carte;
import com.schottentotten.model.Pioche;


public class TestPioche {
    @Test
    public void testPioche() {
        Pioche pioche = new Pioche();
        Carte carte1 = pioche.tirerUneCarte();
        System.out.println(carte1);
        assertNotNull(carte1);   
        assertNotNull(pioche);    
        assertEquals(53, pioche.getCarteRestantes());   

        for (int i = 0; i < 54; i++) {                  //on tire toutes les cartes
            pioche.tirerUneCarte();
        }
        assertEquals(0, pioche.getCarteRestantes());        

        Carte carte = pioche.tirerUneCarte();          //on tire toutes les cartes
        assertNull(carte);  
    }


}

