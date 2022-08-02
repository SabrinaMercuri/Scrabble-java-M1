package m1.miage.scrabble.serveur;

import m1.miage.scrabble.commun.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
public class GestionnairePartieWebControlleur {

    private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
    Log log = new Log();

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    GestionnairePartie gestionnairePartie;

    /**
     * Méthode de permettant de récupérer la liste des joueurs connectés
     * @return joueurs, les joueurs conenctés
     */
    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }

    /**
     * Méthode permettant de renvoyer le restTemplate pour permettre de faire des requêtes vers le serveur
     * @param builder, ce qui permet de créer le restTemplate
     * @return le restTemplate par le builder
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
        return builder.build();
    }

    /**
     * Méthode permettant de récupérer l'évenement "connexion",
     * d'ajouter le joueur à la partie et lui dire que la connexion est acceptée
     * @param joueurId
     * @return true pour le joueur qui s'est connecté
     */
    @PostMapping("/connexion/")
    public synchronized boolean getValue(@RequestBody Identification joueurId) {
        if(joueurs.size() > 1) return false;
        Joueur joueur = new Joueur(joueurId,new Statistique());
        joueurs.add(joueur);
        log.print("GestionnairePartie > connexion acceptée de "+joueurId.getNom() + " (", "red");
        log.print(joueurId.getUrl(), "cyan");
        log.println(")", "red");

        if(joueurs.size() > 1) {
            gestionnairePartie.lancerPartie();
        }
        return true;
    }

    /**
     * Méthode permettant de dire au joueur de jouer quand c'est son tour
     * @param joueurId, l'identification du joueur
     * @param plateau, état du jeu
     * @return le mot joué par le joueur
     */
    public Mot demanderAuJoueurDeJouer(Joueur joueurId, Plateau plateau) {
        Mot resultat = new Mot();
        if (joueurId != null) {
            resultat = restTemplate.postForObject(joueurId.getIdJoueur().getUrl()+"/jouer", plateau, Mot.class);
        }
        return resultat;
    }

    /**
     * Méthode permettant d'envoyer les lettres au joueur
     * @param main, les lettres envoyées au joueur
     * @param joueurId, l'identification du joueur
     * @return envoi, true ou false si ca s'est bien passé ou non
     */
    public Boolean envoyerMainAuJoueur(ArrayList<Lettre> main, Joueur joueurId){
        Boolean envoi = false;
        if (joueurId != null) {
            envoi = restTemplate.postForObject(joueurId.getIdJoueur().getUrl()+"/mainDepart", main , Boolean.class);
        }
        return envoi;
    }

    /**
     * Méthode permettant d'envoyer les lettres au joueur
     * @param lettres, les lettres envoyées au joueur
     * @param joueurId, l'identification du joueur
     * @return envoi, true ou false si ca s'est bien passé ou non
     */
    public Boolean envoyerLettresAuJoueur(ArrayList<Lettre> lettres, Joueur joueurId){
        Boolean envoi = false;
        if (joueurId != null) {
            envoi = restTemplate.postForObject(joueurId.getIdJoueur().getUrl()+"/distributionLettres", lettres , Boolean.class);
        }
        return envoi;
    }

    /**
     * Méthode permettant de dire au joueur quand la partie est terminée et ca stop le programme
     */
    public void envoyerFin() {
       for(Joueur j : joueurs){
           restTemplate.exchange(j.getIdJoueur().getUrl()+"/finir", HttpMethod.POST, null, Void.class);
       }
    }

    /**
     * Méthode permettant d'envoyer les points du mots pour qu'il mette à jour le score
     * @param totalPoints, nombre de points à ajouter
     * @param idJoueur
     * @return true si la requête s'est bien passée
     */
    public Boolean majScoreJoueur(int totalPoints, Identification idJoueur) {
        Boolean envoi=false;
        if(idJoueur !=null){
           envoi = restTemplate.postForObject(idJoueur.getUrl()+"/majScore",totalPoints+"",Boolean.class);
        }
        return envoi;
    }

    /**
     * Méthode permettant de demander au joueur un échange
     */
    public Boolean proposeEchange(Joueur joueur) {
        ArrayList<String> change = restTemplate.postForObject(joueur.getIdJoueur().getUrl()+"/demandeEchange", null,ArrayList.class);
        ArrayList<Lettre> changeLettre = new ArrayList<>();
        for (String character : change) {
            changeLettre.add(new Lettre(character.charAt(0)));
        }
        changeLettre = gestionnairePartie.echangeLettre(changeLettre);
        change = new ArrayList<>();
        for (Lettre lettre : changeLettre) {
            change.add(lettre.getLettre()+"");
        }
        restTemplate.postForObject(joueur.getIdJoueur().getUrl()+"/echange",change, ArrayList.class);
        return true;
    }
}