package m1.miage.scrabble.serveur;

import m1.miage.scrabble.commun.Identification;

public class Joueur {

    private Identification idJoueur;
    private Statistique statJoueur;

    /**
     * Constructeur de la classe Joueur serveur
     * @param idJoueur, identification du joueur
     * @param statJoueur, statistiques du joueur
     */
    public Joueur(Identification idJoueur, Statistique statJoueur) {
        this.idJoueur = idJoueur;
        this.statJoueur = statJoueur;
    }

    /**
     * Méthode permettant de récupérer l'identification du joueur
     * @return idJoueur, l'identification du joueur
     */
    public Identification getIdJoueur() {
        return idJoueur;
    }

    /**
     * Méthode permettant de récupérer les statistiques du joueur
     * @return statJoueur, les stats du joueur
     */
    public Statistique getStatJoueur() {
        return statJoueur;
    }

    /**
     * Méthode permettant de modifier l'identification du joueur
     * @return idJoueur, la nouvelle identification du joueur
     */
    public void setIdJoueur(Identification idJoueur) {
        this.idJoueur = idJoueur;
    }

    /**
     * Méthode permettant de modifier les statistiques du joueur
     * @return statJoueur, les nouvelles stats du joueur
     */
    public void setStatJoueur(Statistique statJoueur) {
        this.statJoueur = statJoueur;
    }
}
