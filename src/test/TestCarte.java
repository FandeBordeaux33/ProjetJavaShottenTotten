import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.schottentotten.model.Carte;

public class TestCarte {
    @Test
    public void testCarte() {
        Carte carte1 = new Carte(5, 2);
        System.out.println(carte1);
        assertEquals(5, carte1.getNumero());
        assertEquals(2, carte1.getCouleur());
        Carte joker = new Carte(0, 0);
         assertEquals("[Tactique-Joker]", joker.toString());
    }

}