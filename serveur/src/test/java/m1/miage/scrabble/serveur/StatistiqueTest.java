package m1.miage.scrabble.serveur;

import m1.miage.scrabble.commun.Case;
import m1.miage.scrabble.commun.Lettre;
import m1.miage.scrabble.commun.Mot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StatistiqueTest {

    private Statistique statTest;
    private Mot motTest;
    private Mot motTest2;
    private Mot motTest3;


    @BeforeEach
    public void init() {
        statTest = new Statistique();
        motTest = new Mot();
        motTest2 = new Mot();
        motTest3 = new Mot();

        ArrayList<Lettre> liste = new ArrayList<Lettre>();
        liste.add(new Lettre('c'));
        liste.add(new Lettre('h'));
        liste.add(new Lettre('a'));
        liste.add(new Lettre('t'));
        motTest.setMot(liste);

        ArrayList<Lettre> liste2 = new ArrayList<Lettre>();
        liste2.add(new Lettre('c'));
        liste2.add(new Lettre('h'));
        liste2.add(new Lettre('i'));
        liste2.add(new Lettre('e'));
        liste2.add(new Lettre('n'));
        motTest2.setMot(liste2);

        ArrayList<Lettre> liste3 = new ArrayList<Lettre>();
        liste3.add(new Lettre('e'));
        liste3.add(new Lettre('a'));
        liste3.add(new Lettre('u'));
        motTest3.setMot(liste3);
    }

    @Test
    void getTotalPoints() {
        assertEquals(0, statTest.getTotalPoints());
    }

    @Test
    void setTotalPoints() {
        statTest.setTotalPoints(5);
        assertEquals(5, statTest.getTotalPoints());

        statTest.setTotalPoints(7);
        assertEquals(12, statTest.getTotalPoints());
    }

    @Test
    void getPlusGrandeValeurMot() {
        assertEquals(0, statTest.getPlusGrandeValeurMot());
    }

    @Test
    void setPlusGrandeValeurMot() {
        statTest.setPlusGrandeValeurMot(motTest);
        assertEquals(9, statTest.getPlusGrandeValeurMot());

        statTest.setPlusGrandeValeurMot(motTest2);
        assertEquals(10, statTest.getPlusGrandeValeurMot());

        statTest.setPlusGrandeValeurMot(motTest3);
        assertEquals(10, statTest.getPlusGrandeValeurMot());
    }

    @Test
    void getMoyTailleMot() {
        assertEquals(0, statTest.getMoyTailleMot());
    }

    @Test
    void setMoyTailleMot() {
        statTest.setMoyTailleMot(motTest);
        assertEquals(4, statTest.getMoyTailleMot());

        statTest.setMoyTailleMot(motTest2);
        assertEquals(4.5, statTest.getMoyTailleMot());

        statTest.setMoyTailleMot(motTest3);
        assertEquals(4, statTest.getMoyTailleMot());
    }

    @Test
    void getScoreMoyMot() {
        assertEquals(0, statTest.getScoreMoyMot());
    }

    @Test
    void setScoreMoyMot() {
        statTest.setScoreMoyMot(motTest);
        assertEquals(9, statTest.getScoreMoyMot());

        statTest.setScoreMoyMot(motTest2);
        assertEquals(9.5, statTest.getScoreMoyMot());

        statTest.setScoreMoyMot(motTest3);
        assertEquals(7.333333492279053, statTest.getScoreMoyMot());
    }

    @Test
    void getPlaceMoy() {
        assertEquals(0, statTest.getPlaceMoy());
    }

    @Test
    void setPlaceMoy() {
        statTest.setPlaceMoy(2);
        assertEquals(2, statTest.getPlaceMoy());

        statTest.setPlaceMoy(3);
        assertEquals(2.5, statTest.getPlaceMoy());

        statTest.setPlaceMoy(1);
        assertEquals(2, statTest.getPlaceMoy());
    }

    @Test
    void getLettresPosees() {
        assertEquals(0, statTest.getLettresPosees());
    }

    @Test
    void setLettresPosees() {
        statTest.setLettresPosees(2);
        assertEquals(2, statTest.getLettresPosees());
    }

    @Test
    void getMotsPoses() {
        assertEquals(0, statTest.getMotsPoses());
    }

    @Test
    void setMotsPoses() {
        statTest.setMotsPoses();
        assertEquals(1, statTest.getMotsPoses());
    }

    @Test
    void getPlusGrandeValeurPose() {
        assertEquals(0, statTest.getPlusGrandeValeurPose());
    }

    @Test
    void setPlusGrandeValeurPose() {
        statTest.setPlusGrandeValeurPose(motTest);
        assertEquals(9, statTest.getPlusGrandeValeurPose());

        //simulation d'une lettre double
        motTest.setTotalPoints(10);
        statTest.setPlusGrandeValeurPose(motTest);
        assertEquals(10, statTest.getPlusGrandeValeurPose());

        //simulation d'une case comptre triple
        motTest.setTotalPoints(27);
        statTest.setPlusGrandeValeurPose(motTest);
        assertEquals(27, statTest.getPlusGrandeValeurPose());

        //test avec un mot ayant une valeur posé plus faible
        statTest.setPlusGrandeValeurPose(motTest2);
        assertEquals(27, statTest.getPlusGrandeValeurPose());
    }

    @Test
    void ToString() {
        assertEquals("Total des points du joueur pendant la partie : " + 0 +
                "\nScore moyen des mots posés pendant la partie : " + 0.0 +
                "\nNombre de lettres posées pendant la partie : " + 0 +
                "\nNombre de mots posés pendant la partie : " + 0 +
                "\nNombre maximum de point pour un mot pendant la partie : " + 0 +
                "\nNombre maximum de point lors de la pose d'un mot pendant la partie : " + 0 +
                "\nNombre moyen de lettres jouer pendant la partie : " + 0.0, statTest.toString());
    }
}