package m1.miage.scrabble.client;

import m1.miage.scrabble.commun.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@RestController
public class JoueurWebControlleur {

    @Autowired
    Joueur joueur;

    @Autowired
    JoueurApplication appJoueur;

    Log log = new Log();

    /**
     * Méthode permettant de récupérer l'évenement "jouer"
     * @param plateau, l'état actuel du jeu
     * @return un mot joué par joueur
     */
    @PostMapping("/jouer")
    public Mot jouer(@RequestBody Plateau plateau) {
        log.print("Main : [", "cyan");
        for(Lettre l: joueur.getLettres()) log.print(l.getLettre()+" ", "red");
        log.println("]", "cyan");
        log.println("On me demande de jouer sur", "cyan");
        System.out.println(plateau);
        ArrayList<Object> mot = joueur.envoyerMot(plateau);
        if (mot.get(0) != null ){
            joueur.suprimmerLettresMain(mot);
        }
        log.println("Mot '" + mot + "' envoyé", "cyan");
        return (Mot) mot.get(0);
    }

    /**
     * Méthode permettant de terminer la partie
     */
    @PostMapping("/finir")
    public void finir() {
        System.out.println("Nombre de fois que l'api externe du choix de mot a été appelé pour le joueur : "+joueur.cptAPI);
        // fin brutale (pour abréger sur travis), mais il faut répondre un peu après
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                log.println("Joueur > fin du programme", "cyan");
                try {
                    TimeUnit.MILLISECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.exit(0);
                }

            }
        });
        t.start();
    }

    /**
     * Méthode permettant de donner la main de départ au joueur
     * @param main, liste de lettres
     * @return true si la requête s'est bien passée
     */
    @PostMapping("/mainDepart")
    public Boolean mainDepart(@RequestBody ArrayList<Lettre> main) {
        joueur.setLettres(main);
        return true;
    }

    /**
     * Méthode permettant de distribuer les lettres manquantes au joueur
     * @param lettres, liste de lettres
     * @return true si la requête s'est bien passée
     */
    @PostMapping("/distributionLettres")
    public Boolean distributionLettres(@RequestBody ArrayList<Lettre> lettres) {
        joueur.addLettres(lettres);
        return true;
    }

    /**
     * Méthode permettant de mettre a jour le score du joueur
     * @param score, nouveau score
     * @return rue si la requête s'est bien passée
     */
    @PostMapping("/majScore")
    public Boolean majScore(@RequestBody String score) {
        int majScore = Integer.parseInt(score);
        joueur.operation(majScore);
        log.println("Le mot envoyé vaut "+score+" points.","blue");
        log.println("Mon score actuel est de "+joueur.getNombresPoint(),"green");
        return true;
    }

    @PostMapping("/demandeEchange")
    public ArrayList<Character> demandeEchange(){
        ArrayList<Lettre> change = joueur.echanqerLettre();
        ArrayList<Character> changeChar = new ArrayList<>();
        for (Lettre lettre : change) {
            changeChar.add(lettre.getLettre());
        }
        return changeChar;
    }

    @PostMapping("/echange")
    public void echange(@RequestBody ArrayList<String> echange){
        ArrayList<Lettre> echangeLettre = new ArrayList<>();
        for (String character : echange) echangeLettre.add(new Lettre(character.charAt(0)));
        joueur.addLettres(echangeLettre);
    }
}
