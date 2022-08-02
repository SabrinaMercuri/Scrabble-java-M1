package m1.miage.scrabble.commun;

public class Case {

    private int posX;
    private int posY;
    private Lettre lettre;

    /**
     * Constructeur de la classe case
     * @param posX, abscisse de la case
     * @param posY, ordonnée de la case
     */
    public Case(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Méthode permettant de récupérer l'absisse de la case
     * @return posX, l'abscisse
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Méthode permettant de récupérer l'ordonnée de la case
     * @return posY, l'ordonnée
     */
    public int getPosY() {
        return posY;
    }
    public void setLettre(Lettre l){
        lettre = l;
    }
    public Lettre getLettre(){
        return lettre;
    }

    public boolean equals(Case c){
        return (this.getPosX()==c.getPosX() && this.getPosY()==c.getPosY());
    }
}
