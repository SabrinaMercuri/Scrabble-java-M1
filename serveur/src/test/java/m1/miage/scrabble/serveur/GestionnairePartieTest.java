package m1.miage.scrabble.serveur;

import m1.miage.scrabble.commun.Case;
import m1.miage.scrabble.commun.Lettre;
import m1.miage.scrabble.commun.Mot;
import m1.miage.scrabble.commun.Plateau;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GestionnairePartieTest {

    GestionnairePartie gestioTest;

    @BeforeEach
    public void init(){
        gestioTest = new GestionnairePartie();
    }

    @Test
    void initPioche() {
        gestioTest.pioche = new ArrayList<Lettre>();
        gestioTest.initPioche();
        //taille normale de la pioche = 100
        assert (gestioTest.pioche.size()==100);
    }

    @Test
    public void caseMotDouble(){
        gestioTest.plateau = new Plateau();
        assertTrue(gestioTest.caseMotDouble(new Case(1,1)));
        assertFalse(gestioTest.caseMotDouble(new Case(0,1)));
    }

    @Test
    public void caseMotTriple(){
        gestioTest.plateau = new Plateau();
        assertTrue(gestioTest.caseMotTriple(new Case(0,0)));
        assertFalse(gestioTest.caseMotTriple(new Case(0,1)));
    }

    @Test
    public void caseLettreTriple(){
        gestioTest.plateau = new Plateau();
        assertTrue(gestioTest.caseLettreTriple(new Case(1,5)));
        assertFalse(gestioTest.caseLettreTriple(new Case(0,1)));
    }

    @Test
    public void caseLettreDouble(){
        gestioTest.plateau = new Plateau();
        assertTrue(gestioTest.caseLettreDouble(new Case(0,3)));
        assertFalse(gestioTest.caseLettreDouble(new Case(0,1)));
    }

    @Test
    public void calculMotPoint(){

        gestioTest.plateau = new Plateau();
        ArrayList<Lettre> lettres = new ArrayList<Lettre>();
        lettres.add(new Lettre('c'));
        lettres.add(new Lettre('h'));
        lettres.add(new Lettre('a'));
        lettres.add(new Lettre('t'));
        Mot mot = new Mot(lettres,"horizontal",0,0);

        assertEquals(9,gestioTest.calculPointMotLdoubleLtriple(mot,new Case(0,1),mot.getMot().get(0)));
        assertEquals(15,gestioTest.calculPointMotLdoubleLtriple(mot,new Case(1,5),mot.getMot().get(0)));
        assertEquals(12,gestioTest.calculPointMotLdoubleLtriple(mot,new Case(0,3),mot.getMot().get(0)));
    }

    @Test
    public void echange(){
        gestioTest.pioche = new ArrayList<Lettre>();
        gestioTest.initPioche();

        ArrayList<Lettre> lettres = new ArrayList<Lettre>();
        lettres.add(new Lettre('c'));
        lettres.add(new Lettre('h'));
        lettres.add(new Lettre('a'));
        lettres.add(new Lettre('t'));

        ArrayList<Lettre> result = gestioTest.echangeLettre(lettres);
        assertEquals(4,result.size());
    }

    @Test
    public void distributionLettres(){
        gestioTest.pioche = new ArrayList<Lettre>();
        gestioTest.initPioche();

        ArrayList<Lettre> lettres = gestioTest.distributionLettres(3);
        assertEquals(3,lettres.size());
    }
}