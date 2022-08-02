package m1.miage.scrabble.commun;

public class Lettre {

    private char lettre;
    private int nbPoints;

    /**
     * Constructeur de la classe lettre
     * @param l, un charactère
     */
    public Lettre (char l){
        lettre = l;
        nbPoints = attributionPoints();
    }

    public Lettre (){}

    //Todo ajouter les joker
    /**
     * Méthode permettant de retourner la valeur de la lettre
     * @return la valeur de la lettre
     */
    private int attributionPoints() {
        switch (lettre) {
            case 0 :
                return 0;
            case 'q':
            case 'z':
                return 10;
            case 'x':
            case 'j':
                return 8;
            case 'k':
                return 5;
            case 'f':
            case 'h':
            case 'v':
            case 'w':
            case 'y':
                return 4;
            case 'b':
            case 'c':
            case 'm':
            case 'p':
                return 3;
            case 'd':
            case 'g':
                return 2;
            default:
                return 1;
        }
    }

    /**
     * Méthode permettant de récupérer la lettre
     * @return lettre, la lettre
     */
    public char getLettre() {
        return lettre;
    }

    /**
     * Méthode permettant de récupérer le nombre de point de la lettre
     * @return nbPoint, le nombre de points de la lettre
     */
    public int getNbPoints() {
        return nbPoints;
    }

    /**
     * Méthode permettant de modifier la lettre
     * @param lettre, la lettre
     */
    public void setLettre(char lettre) {
        this.lettre = lettre;
    }

    /**
     * Méthode permettant de modifier les points de la lettre
     * @param nbPoints, les nouveaux points de la lettre
     */
    public void setNbPoints(int nbPoints) {
        this.nbPoints = nbPoints;
    }

    /**
     * Méthode permettant d'afficher la lettre
     * @return lettre, la lettre à afficher
     */
    @Override
    public String toString() {
        return ""+lettre;
    }

    /**
     * Méthode permettant de dire si une lettre est égale à une autre
     * @param o, la lettre
     * @return true ou false, si c'est égale ou pas
     */
    @Override
    public boolean equals(Object o) {
        Lettre l = (Lettre) o;
        return this.getLettre() == l.getLettre();
    }

}
