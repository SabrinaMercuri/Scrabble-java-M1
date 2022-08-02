package m1.miage.scrabble.commun;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LettreTest {

    private Lettre l;

    @BeforeEach
    public void init() {
        l = new Lettre('s');
    }

    @Test
    public void getLettre(){
        assertEquals('s', l.getLettre());
    }

    @Test
    public void getNbPoints() {
        assertEquals(1, l.getNbPoints());
        assertEquals('s', l.getLettre());
        l = new Lettre('g');
        assertEquals(2, l.getNbPoints());
        l = new Lettre('d');
        assertEquals(2, l.getNbPoints());
        l = new Lettre('p');
        assertEquals(3, l.getNbPoints());
        l = new Lettre('m');
        assertEquals(3, l.getNbPoints());
        l = new Lettre('c');
        assertEquals(3, l.getNbPoints());
        l = new Lettre('b');
        assertEquals(3, l.getNbPoints());
        l = new Lettre('y');
        assertEquals(4, l.getNbPoints());
        l = new Lettre('w');
        assertEquals(4, l.getNbPoints());
        l = new Lettre('v');
        assertEquals(4, l.getNbPoints());
        l = new Lettre('h');
        assertEquals(4, l.getNbPoints());
        l = new Lettre('f');
        assertEquals(4, l.getNbPoints());
        l = new Lettre('k');
        assertEquals(5, l.getNbPoints());
        l = new Lettre('j');
        assertEquals(8, l.getNbPoints());
        l = new Lettre('x');
        assertEquals(8, l.getNbPoints());
        l = new Lettre('z');
        assertEquals(10, l.getNbPoints());
        l = new Lettre('q');
        assertEquals(10, l.getNbPoints());
        l = new Lettre((char)0);
        assertEquals(0, l.getNbPoints());
    }

    @Test
    public void equals() {
        Lettre a = new Lettre('a');
        Lettre b = new Lettre('b');
        Lettre c = new Lettre('a');

        assertEquals(a, c);
        assertNotEquals(a, b);
    }

    @Test
    public void setLettre() {
        l = new Lettre((char)0);
        l.setLettre('a');
        assertEquals('a', l.getLettre());
    }

    @Test
    public void setNbPoints() {
        l = new Lettre((char)0);
        assertEquals(0, l.getNbPoints());
        l.setNbPoints(1);
        assertEquals(1, l.getNbPoints());
    }

    @Test
    public void ToString() {
        l = new Lettre('a');
        assertEquals("a", l.toString());
    }

}
