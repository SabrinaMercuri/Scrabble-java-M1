package m1.miage.scrabble.commun;

import java.util.ArrayList;

public class Mot {

    private ArrayList<Lettre> mot;
    private int totalPoints;
    private String sens;
    private int x, y;

    /**
     * Constructeur vide de la classe Mot
     */
    public Mot() {
       this(new ArrayList<Lettre>(),"horizontal",0,0);
    }

    /**
     * Constructeur de la classe Mot
     * @param mot liste des lettres composant le mot
     * @param sens sens dans lequel le mot sera posé (vertical ou honrizontal)
     * @param x abscisse de la première lettre du mot
     * @param y ordonnée de la première lettre du mot
     */
    public Mot (ArrayList<Lettre> mot, String sens, int x, int y){
        this.mot = mot;
        this.x = x;
        this.y = y;
        totalPoints = calculPoints();
        if (sens.equals("horizontal") || sens.equals("vertical") ){
            this.sens = sens;
        }
        else {
            this.sens = "horizontal";
        }
    }

    /**
     * Méthode permettant de calculer les points du mot
     * @return somme, la somme des points de chaque lettre
     */
    public int calculPoints(){
        int somme = 0;
        for (Lettre l : mot) {
            somme += l.getNbPoints();
        }
        return somme;
    }

    /**
     *Méthode permettant de récupérer le mot
     * @return mot, le mot à récupérer
     */
    public ArrayList<Lettre> getMot() {
        return mot;
    }

    /**
     * Méthode permettant de récupérer le total des points
     * @return totalPoints, le total des points du mot
     */
    public int getTotalPoints() {
        return totalPoints;
    }

    /**
     * Méthode pour récupérer le sens du mot
     * @return sens, le sens dans lequel est placé le mot (vertical ou horizontal)
     */
    public String getSens() {
        return sens;
    }

    /**
     * Méthode permettant de récupérer l'abscisse de la première lettre
     * @return x, abscisse de la première lettre du mot
     */
    public int getX() {
        return x;
    }

    /**
     * Méthode permettant de récupérer l'ordonnée de la première lettre
     * @return y, ordonnée de la première lettre du mot
     */
    public int getY() {
        return y;
    }

    /**
     * Méthode permettant de modifier le mot
     * @param mot, liste de lettre composant le nouveau mot
     */
    public void setMot(ArrayList<Lettre> mot) {
        this.mot = mot;
        this.setTotalPoints(this.calculPoints());
    }

    /**
     * Méthode permettant de modifier le total des points du mot
     * @param totalPoints, le nouveau total des points
     */
    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    /**
     * Méthode permettant de modifier le sens du mot
     * @param sens, nouveau sens du mot (si sens="" horizontal est choisi par défaut)
     */
    public void setSens(String sens) {
        if (sens.equals("horizontal") || sens.equals("vertical") ){
            this.sens = sens;
        }
        else {
            this.sens = "horizontal";
        }
    }

    /**
     * Méthode permettant de modifier l'abscisse de la première lettre du mot
     * @param x, la nouvelle abscisse
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Méthode permettant de modifier l'ordonnée de la première lettre du mot
     * @param y, la nouvelle ordonnée
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Méthode permettant d'afficher le mot
     * @return le mot
     */
    @Override
    public String toString() {
        String result = "";
        if(mot.size()==0){
            return "mot vide";
        }
        for(Lettre l : mot){
            result+=l.getLettre();
        }
        return result;
    }
}
