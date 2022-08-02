package m1.miage.scrabble.serveur;

import m1.miage.scrabble.commun.Identification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JoueurServeurTest {

    Joueur j;

    @BeforeEach
    public void init(){
        j=new Joueur(new Identification("toto","http://localhost:8081"),new Statistique());
    }

    @Test
    // set + get
    public void getIdJoueur(){
        Identification id =new Identification("toto","http://localhost:8081");
        j.setIdJoueur(id);
        assertEquals(id,j.getIdJoueur());
    }

    @Test
    // set + get
    public void getStatJoueur(){
        Statistique stat = new Statistique();
        j.setStatJoueur(stat);
        assertEquals(stat,j.getStatJoueur());
    }

}
