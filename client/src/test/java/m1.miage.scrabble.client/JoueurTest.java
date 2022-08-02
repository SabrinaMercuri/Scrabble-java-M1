package m1.miage.scrabble.client;

import m1.miage.scrabble.commun.Identification;
import m1.miage.scrabble.commun.Lettre;
import m1.miage.scrabble.commun.Mot;
import m1.miage.scrabble.commun.Plateau;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JoueurTest {

    private Joueur j;

    @BeforeEach
    public void init() {
        j = new Joueur ();
        j.setRestTemplate(new RestTemplate());
    }

    @Test
    ///Set et Get en même temps car constructeur vide (ca ferait une répétition)
    public void setGetnombresPoint() {
        j.setNombresPoint(8);
        assertEquals(8,j.getNombresPoint());
    }

    @Test
    ///Set et Get en même temps car constructeur vide (ca ferait une répétition)
    public void setGetLettres() {
        ArrayList<Lettre> l = new ArrayList<Lettre>();
        l.add(new Lettre('c'));
        l.add(new Lettre('h'));
        l.add(new Lettre('a'));
        l.add(new Lettre('t'));
        j.setLettres(l);
        assertEquals(l, j.getLettres());
    }

    @Test
    public void getIdJoueur(){
        assertNull(j.getIdJoueur());
    }

    @Test
    public void operation(){
        int scoreMot = 5;
        j.operation(scoreMot);
        assertEquals(5, j.getNombresPoint());
    }

    @Test
    public void addLettres(){
        ArrayList<Lettre> l = new ArrayList<Lettre>();
        l.add(new Lettre('c'));
        l.add(new Lettre('h'));
        l.add(new Lettre('a'));
        l.add(new Lettre('t'));
        j.setLettres(new ArrayList<Lettre>());
        j.addLettres(l);
        assertEquals(4, j.getLettres().size());
        j.addLettres(l);
        assertEquals(8, j.getLettres().size());
    }

    @Test
    public void suprimmerLettresMain() {
        ArrayList<Lettre> lettres = new ArrayList<>();
        lettres.add(new Lettre('c'));
        lettres.add(new Lettre('h'));
        lettres.add(new Lettre('a'));
        lettres.add(new Lettre('t'));
        lettres.add(new Lettre('o'));
        lettres.add(new Lettre('n'));
        j.setLettres(lettres);


        ArrayList<Lettre> lettresASupr = new ArrayList<>();
        lettresASupr.add(new Lettre('t'));
        lettresASupr.add(new Lettre('h'));
        lettresASupr.add(new Lettre('a'));
        Mot m1 = new Mot(lettresASupr,"vertical",7,7);

        ArrayList<Object> toTest = new ArrayList<>();
        toTest.add(m1);
        toTest.add(null);
        j.suprimmerLettresMain(toTest);

        ArrayList<Lettre> lettresAttendues = new ArrayList<>();
        lettresAttendues.add(new Lettre('c'));
        lettresAttendues.add(new Lettre('o'));
        lettresAttendues.add(new Lettre('n'));

        assertEquals(j.getLettres(), lettresAttendues);

        //vérification de la suppression des lettres en utilisant celle du mot
        lettresASupr = new ArrayList<>();
        lettresASupr.add(new Lettre('t'));
        lettresASupr.add(new Lettre('c'));
        lettresASupr.add(new Lettre('h'));
        lettresASupr.add(new Lettre('a'));
        m1 = new Mot(lettresASupr,"vertical",7,7);
        j.setLettres(lettres);

        toTest = new ArrayList<>();
        toTest.add(m1);
        toTest.add(new Lettre('c'));
        j.suprimmerLettresMain(toTest);

        lettresAttendues = new ArrayList<>();
        lettresAttendues.add(new Lettre('c'));
        lettresAttendues.add(new Lettre('o'));
        lettresAttendues.add(new Lettre('n'));

        assertEquals(j.getLettres(), lettresAttendues);
    }


    @Test
    public void envoyerMotPremierTour() {
        Plateau p = new Plateau();
        ArrayList<Lettre> l = new ArrayList<Lettre>();
        l.add(new Lettre('e'));
        l.add(new Lettre('l'));
        l.add(new Lettre('n'));
        l.add(new Lettre('x'));
        l.add(new Lettre('g'));
        l.add(new Lettre('w'));
        l.add(new Lettre('t'));
        l.add(new Lettre('h'));
        j.setLettres(l);

        ArrayList<Object> mot = new ArrayList<>();

        //verification qu'il passe dans la la partie ou il joue sans mots sur le plateau correctement
        mot = j.envoyerMot(p);
        Mot mot1 = (Mot) mot.get(0);
        assertEquals(mot1.toString(), "hex");

        //verification qu'il n'arrive pas a jouer avec une seul lettre
        l = new ArrayList<>();
        l.add(new Lettre('c'));
        j.setLettres(l);
        mot = j.envoyerMot(p);
        assert(j.getLettres().size()==1);
        assert(mot.get(0)==null && mot.get(1)==null);


        //verification qu'il ne peut pas trouver de mots
        l = new ArrayList<>();
        l.add(new Lettre('w'));
        l.add(new Lettre('w'));
        l.add(new Lettre('w'));
        j.setLettres(l);
        mot = j.envoyerMot(p);
        assert(mot.get(0)==null && mot.get(1)==null);
    }

    @Test
    public void envoyerMotAutresTours() {
        Plateau p = new Plateau();
        ArrayList<Lettre> l = new ArrayList<Lettre>();
        l.add(new Lettre('e'));
        l.add(new Lettre('l'));
        l.add(new Lettre('n'));
        l.add(new Lettre('x'));
        l.add(new Lettre('g'));
        l.add(new Lettre('w'));
        l.add(new Lettre('t'));
        l.add(new Lettre('h'));
        j.setLettres(l);

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


        ArrayList<Object> mot = new ArrayList<>();
        mot = j.envoyerMot(p);
        Mot mot1 = (Mot) mot.get(0);
        p.getMotsPlace().add(mot1);


        assertEquals(mot1.toString(), "wha");
        
    }

    @Test
    public void rechercheMotsApi() {
        ArrayList<Lettre> lettres = new ArrayList<>();
        lettres.add(new Lettre('a'));
        lettres.add(new Lettre('r'));
        lettres.add(new Lettre('p'));
        lettres.add(new Lettre('s'));
        lettres.add(new Lettre('d'));
        j.setLettres(lettres);

        List<String> res =  j.rechercheMotsApi(new Lettre('x'));

        //vérifie que les mots récupérés par l'api correspondent
        List<String> expected = new ArrayList<>();
        expected.add("ax");
        expected.add("pax");
        expected.add("rax");
        expected.add("sax");

        assertEquals(expected, res);
    }

    @Test
    public void getMotsFromStrings() {
        List<String> listMotsString = new ArrayList<>();
        listMotsString.add("ax");
        listMotsString.add("pax");
        listMotsString.add("rax");
        listMotsString.add("sax");

        ArrayList<Lettre> lettres = new ArrayList<>();
        lettres.add(new Lettre('a'));
        lettres.add(new Lettre('d'));
        lettres.add(new Lettre('d'));

        Mot motPlacedOn1 = new Mot(lettres, "horizontal", 7, 7);
        ArrayList<Mot> res = j.getMotsFromStrings(listMotsString, motPlacedOn1, new Lettre('a'));

        //Vérifie que chaque mot récupéré est palcé comme il faut
        assertEquals(7, res.get(0).getX());
        assertEquals(7, res.get(0).getY());
        assertEquals("vertical", res.get(0).getSens());
        assertEquals(2, res.get(0).getMot().size());

        assertEquals(7, res.get(1).getX());
        assertEquals(6, res.get(1).getY());
        assertEquals("vertical", res.get(1).getSens());
        assertEquals(3, res.get(1).getMot().size());

        assertEquals(7, res.get(2).getX());
        assertEquals(6, res.get(2).getY());
        assertEquals("vertical", res.get(2).getSens());
        assertEquals(3, res.get(2).getMot().size());

        assertEquals(7, res.get(3).getX());
        assertEquals(6, res.get(3).getY());
        assertEquals("vertical", res.get(3).getSens());
        assertEquals(3, res.get(3).getMot().size());


        Mot motPlacedOn2 = new Mot(lettres, "vertical", 7, 7);
        ArrayList<Mot> res2 = j.getMotsFromStrings(listMotsString, motPlacedOn2, new Lettre('a'));

        //Vérifie que chaque mot récupéré est palcé comme il faut
        assertEquals(7, res2.get(0).getX());
        assertEquals(7, res2.get(0).getY());
        assertEquals("horizontal", res2.get(0).getSens());
        assertEquals(2, res2.get(0).getMot().size());

        assertEquals(6, res2.get(1).getX());
        assertEquals(7, res2.get(1).getY());
        assertEquals("horizontal", res2.get(1).getSens());
        assertEquals(3, res2.get(1).getMot().size());

        assertEquals(6, res2.get(2).getX());
        assertEquals(7, res2.get(2).getY());
        assertEquals("horizontal", res2.get(2).getSens());
        assertEquals(3, res2.get(2).getMot().size());

        assertEquals(6, res2.get(3).getX());
        assertEquals(7, res2.get(3).getY());
        assertEquals("horizontal", res2.get(3).getSens());
        assertEquals(3, res2.get(3).getMot().size());
    }

}
