package m1.miage.scrabble.commun;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlateauTest {


    private Plateau p;

    @BeforeEach
    public void init(){
        p=new Plateau();
    }

    @Test
    public void getGrille(){
        assertEquals(15,p.getGrille().size());
    }

    @Test
    public void getLettreDouble(){
        assertEquals(24,p.getLettreDouble().size());
    }

    @Test
    public void getLettreTriple(){
        assertEquals(12,p.getLettreTriple().size());
    }

    @Test
    public void getLMotDouble(){
        assertEquals(16,p.getMotDouble().size());
    }

    @Test
    public void getLMotTriple(){
        assertEquals(8,p.getMotTriple().size());
    }

    @Test
    public void getMotPlace(){
        assertEquals(0,p.getMotsPlace().size());
    }

    @Test
    public void isMotInside() {
        Plateau p = new Plateau();
        ArrayList<Lettre> lettres = new ArrayList<>();
        lettres.add(new Lettre('c'));
        lettres.add(new Lettre('h'));
        lettres.add(new Lettre('a'));
        lettres.add(new Lettre('t'));

        //Le mot est dans le plateau au milieu
        Mot m1 = new Mot(lettres, "vertical", 7, 7);
        assert(p.isMotInside(m1));

        //Le mot déborde en bas
        Mot m2 = new Mot(lettres, "vertical", 7, 13);
        assertFalse(p.isMotInside(m2));

        //Le mot déborde en à droite
        Mot m3 = new Mot(lettres, "horizontal", 12, 7);
        assertFalse(p.isMotInside(m3));

        //Le mot déborde en haut
        Mot m4 = new Mot(lettres, "vertical", 7, -6);
        assertFalse(p.isMotInside(m4));

        //Le mot déborde en à gauche
        Mot m5 = new Mot(lettres, "horizontal", -7, 7);
        assertFalse(p.isMotInside(m5));

    }

    @Test
    public void isPlacable() {
        Plateau p = new Plateau();

        //m1 (CHAT)
        ArrayList<Lettre> l1s = new ArrayList<>();
        l1s.add(new Lettre('c'));
        l1s.add(new Lettre('h'));
        l1s.add(new Lettre('a'));
        l1s.add(new Lettre('t'));
        Mot m1 = new Mot(l1s, "vertical", 7, 7);
        p.getMotsPlace().add(m1);
        p.getGrille().get(7).get(7).setLettre(l1s.get(0));
        p.getGrille().get(7).get(8).setLettre(l1s.get(1));
        p.getGrille().get(7).get(9).setLettre(l1s.get(2));
        p.getGrille().get(7).get(10).setLettre(l1s.get(3));

        //m2 (TEST)
        ArrayList<Lettre> l2s = new ArrayList<>();
        l2s.add(new Lettre('t'));
        l2s.add(new Lettre('e'));
        l2s.add(new Lettre('s'));
        l2s.add(new Lettre('t'));
        Mot m2 = new Mot(l2s, "horizontal", 7, 10);
        p.getMotsPlace().add(m2);
        p.getGrille().get(7).get(10).setLettre(l2s.get(0));
        p.getGrille().get(8).get(10).setLettre(l2s.get(1));
        p.getGrille().get(9).get(10).setLettre(l2s.get(2));
        p.getGrille().get(10).get(10).setLettre(l2s.get(3));


        //motToPlace1
        //Le mot se place bien sur m2 (TEST)
        ArrayList<Lettre> l3s = new ArrayList<>();
        l3s.add(new Lettre('i'));
        l3s.add(new Lettre('s'));
        Mot motToPlace1 = new Mot(l3s, "vertical", 9, 9);

        assert(p.isPlacable(motToPlace1, m2));

        //motToPlace2
        //Le mot ne peut pas être placé sur m2 (TEST)
        //car il y a une collision avec m1 (CHAT)
        ArrayList<Lettre> l4s = new ArrayList<>();
        l4s.add(new Lettre('m'));
        l4s.add(new Lettre('e'));
        Mot motToPlace2 = new Mot(l3s, "vertical", 8, 9);

        assertFalse(p.isPlacable(motToPlace2, m2));

    }


}