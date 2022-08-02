package m1.miage.scrabble.serveur;

import m1.miage.scrabble.commun.Mot;

//todo voir pour les résultats des moyennes dépassant 1 ou 2 chiffres après la virgule

public class Statistique {

    private int totalPoints;
    private int plusGrandeValeurMot;
    private float moyTailleMot;
    private float scoreMoyMot;
    private float placeMoy;
    private int coeffTailleMot; //variable pour moyTailleMot
    private int coeffScoreMot; //variable scoreMoyMot
    private int coeffPlace; //variable placeMoy
    private int lettresPosees;
    private int motsPoses;
    private int plusGrandeValeurPose;

    /**
     * constructeur de la classe
     */
    public Statistique() {
        totalPoints = 0 ;     ///total.joueur
        plusGrandeValeurMot = 0;
        moyTailleMot = 0;
        scoreMoyMot = 0;
        placeMoy = 0;
        coeffTailleMot = 0; //variable coefficient pour moyTailleMot
        coeffScoreMot = 0; //variable coefficient scoreMoyMot
        coeffPlace = 0; //variable coefficient placeMoy
        lettresPosees = 0;
        motsPoses = 0;
        plusGrandeValeurPose = 0;
    }

    /**
     *  Méthode permettant de récupérer le total de point d'un joueur
     * @return totalPoints, le total de point
     */
    public int getTotalPoints() {
        return totalPoints;
    }

    /**
     * Méthode permettant de modifier le total de point
     * @param points, le nombre de points à ajouter au total
     */
    public void setTotalPoints(int points) {
        this.totalPoints += points;
    }

    /**
     * Méthode permettant de récupérer la plus grande valeur de mot qu'un joueur à eu
     * @return plusGrandeValeurMot, la plus grande valeur de mot obtenue
     */
    public int getPlusGrandeValeurMot() {
        return plusGrandeValeurMot;
    }

    /**
     *  Méthode permettant de modifier la plus grande valeur de mot qu'un joueur à eu
     * @param mot, le mot ayant une nouvelle valeur à tester
     */
    public void setPlusGrandeValeurMot(Mot mot) {
        if (mot.getTotalPoints() > this.getPlusGrandeValeurMot()){
            this.plusGrandeValeurMot = mot.getTotalPoints();
        }
    }

    /**
     * Méthode permettant de récupérer la toille moyenne d'un mot mis par un joueur
     * @return moyTailleMot, la taille moyenne d'un mot
     */
    public float getMoyTailleMot() {
        return moyTailleMot;
    }

    /**
     * Méthode permettant de modifier la taille moyenne d'un mot mis par un joueur
     * @param mot, le mot ayant une taille à ajouter à la moyenne
     */
    public void setMoyTailleMot(Mot mot) {
        moyTailleMot = getMoyTailleMot()*coeffTailleMot;
        this.coeffTailleMot++;
        this.moyTailleMot = (moyTailleMot + mot.getMot().size()) / coeffTailleMot;
    }

    /**
     * Méthode permettant de récupérer le score moyen d'un joueur
     * @return scoreMoyMot, le score moyent d'un mot
     */
    public float getScoreMoyMot() {
        return scoreMoyMot;
    }

    /**
     * Méthode permettant de modifier le score moyen d'un mot
     * @param mot, le mot ayant un score à ajouter à la moyenne
     */
    public void setScoreMoyMot(Mot mot) {
        scoreMoyMot = getScoreMoyMot()*coeffScoreMot;
        this.coeffScoreMot++;
        this.scoreMoyMot = (scoreMoyMot + mot.getTotalPoints()) / coeffScoreMot;
    }

    /**
     * Méthode permettant de récupérer la place moyenne d'un joueur lors de partie
     * @return placeMoy, la place moyenne du joueur
     */
    public float getPlaceMoy() {
        return placeMoy;
    }

    /**
     * Méthode permettant de modifier la place moyenne
     * @param place, la place à ajouter à la moyenne
     */
    public void setPlaceMoy(int place) {
        placeMoy = getPlaceMoy()*coeffPlace;
        this.coeffPlace++;
        this.placeMoy = (placeMoy + place) / coeffPlace;
    }

    /**
     * Méthode permettant de récupérer le total des lettres posées
     * @return lettresPosees, le total des lettres posées
     */
    public int getLettresPosees() {
        return lettresPosees;
    }

    /**
     * Méthode permettant de modifier le total des lettres posées
     * @param lettresPosees, le total des lettres posées
     */
    public void setLettresPosees(int lettresPosees) {
        this.lettresPosees += lettresPosees;
    }

    /**
     * Méthode permettant de récupérer le nombre de mots posés
     * @return motsPoses, le nombre de mots posés
     */
    public int getMotsPoses() {
        return motsPoses;
    }

    /**
     * Méthode permettant de modifier le nombre de mots posés
     */
    public void setMotsPoses() {
        this.motsPoses++;
    }

    /**
     * Méthode permettant de récupérer le nombre de max de points pour un mot
     * @return nbMaxPointsMot, le nombre de max de points pour un mot
     */
    public int getPlusGrandeValeurPose() {
        return plusGrandeValeurPose;
    }

    /**
     * Méthode permettant de modifier le nombre de max de points pour un mot posé
     */
    public void setPlusGrandeValeurPose(Mot mot) {
        if (mot.getTotalPoints() > plusGrandeValeurPose) {
            this.plusGrandeValeurPose = mot.getTotalPoints();
        }
    }

    /**
     * Méthode permettant d'afficher les statistiques
     * @return des phrases avec les statistiques
     */
    @Override
    public String toString() {
        return "Total des points du joueur pendant la partie : " + getTotalPoints() +
                "\nScore moyen des mots posés pendant la partie : " + getScoreMoyMot() +
                "\nNombre de lettres posées pendant la partie : " + getLettresPosees() +
                "\nNombre de mots posés pendant la partie : " + getMotsPoses() +
                "\nNombre maximum de point pour un mot pendant la partie : " + getPlusGrandeValeurMot() +
                "\nNombre maximum de point lors de la pose d'un mot pendant la partie : " + getPlusGrandeValeurPose() +
                "\nNombre moyen de lettres jouer pendant la partie : " + getMoyTailleMot();

    }
}
