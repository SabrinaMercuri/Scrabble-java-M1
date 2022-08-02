package m1.miage.scrabble.commun;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MotTest {

    private Mot mot;
    private Mot mot2;

    @BeforeEach
    public void init() {
        mot = new Mot();
        mot2 = new Mot(new ArrayList<Lettre>(),"toto",0,0);
    }

    @Test
    public void getTotalPoints() {
        assertEquals(0, mot.getTotalPoints());
    }

    @Test
    public void setTotalPoints() {
        mot.setTotalPoints(10);
        assertEquals(10, mot.getTotalPoints());
    }

    @Test
    public void getSens(){
        assertEquals("horizontal", mot.getSens());
    }
    @Test
    public void setSens() {
        mot.setSens("vertical");
        assertEquals("vertical", mot.getSens());
        mot.setSens("horizontal");
        assertEquals("horizontal", mot.getSens());
        mot.setSens("");
        assertEquals("horizontal", mot.getSens());
    }

    @Test
    public void getXY(){
        assertEquals(0, mot.getX());
        assertEquals(0, mot.getY());
    }

    @Test
    public void setXY(){
        mot.setX(1);
        assertEquals(1, mot.getX());
        mot.setY(1);
        assertEquals(1, mot.getY());
    }

    @Test
    public void getMot(){
        assertEquals(new ArrayList<Lettre>(), mot.getMot());
        ArrayList<Lettre> mot3 = new ArrayList<Lettre>();
        mot3.add(new Lettre('c'));
        mot3.add(new Lettre('h'));
        mot3.add(new Lettre('a'));
        mot3.add(new Lettre('t'));
        mot.setMot(mot3);
        assertEquals(mot3, mot.getMot());
    }

    @Test
    public void setMot(){
        ArrayList<Lettre> mot3 = new ArrayList<Lettre>();
        mot3.add(new Lettre('c'));
        mot3.add(new Lettre('h'));
        mot3.add(new Lettre('a'));
        mot3.add(new Lettre('t'));
        mot.setMot(mot3);
        assertEquals(9, mot.getTotalPoints());
    }

    @Test
    public void tostring(){
        assertEquals("mot vide", mot.toString());
        ArrayList<Lettre> mot3 = new ArrayList<Lettre>();
        mot3.add(new Lettre('c'));
        mot3.add(new Lettre('h'));
        mot3.add(new Lettre('a'));
        mot3.add(new Lettre('t'));
        mot.setMot(mot3);
        assertEquals("chat", mot.toString());
    }

}