package m1.miage.scrabble.commun;

import java.util.ArrayList;

public class Plateau {

    private ArrayList<ArrayList<Case>> grille;
    private ArrayList<Case> lettreDouble;
    private ArrayList<Case> lettreTriple;
    private ArrayList<Case> motDouble;
    private ArrayList<Case> motTriple;
    private ArrayList<Mot>  motsPlace;

    private Log log = new Log();

    /**
     * Constructeur du plateau vide
     */
    public Plateau() {
        grille = initGrille();
        lettreDouble = initLettreDouble();
        lettreTriple = initLettreTriple();
        motDouble = initMotDouble();
        motTriple = initMotTriple();
        motsPlace = new ArrayList<Mot>();
    }

    /**
     * Méthode permettant d'initialiser toutes les cases de la grille
     * @return result, une liste avec toutes les cases
     */
    private ArrayList<ArrayList<Case>> initGrille() {
        ArrayList<ArrayList<Case>> result = new ArrayList<ArrayList<Case>>();
        for(int i=0;i<15;i++){
            ArrayList<Case> ligne = new ArrayList<Case>();
            for (int j=0;j<15;j++){
                ligne.add(new Case(i, j));
            }
            result.add(ligne);
        }
        return result;
    }

    /**
     * Méthode permettant d'initialiser les cases de types mot compte triple
     * @return result, une liste avec toutes les cases de type mot triple
     */
    private ArrayList<Case> initMotTriple() {
        ArrayList<Case> result = new ArrayList<Case>();
        result.add(new Case(0,0));
        result.add(new Case(0,7));
        result.add(new Case(0,14));
        result.add(new Case(7,0));
        result.add(new Case(7,14));
        result.add(new Case(14,0));
        result.add(new Case(14,7));
        result.add(new Case(14,14));
        return result;

    }

    /**
     * Méthode permettant d'initialiser les cases de types mot compte double
     * @return result, une liste avec toutes les cases de type mot double
     */
    private ArrayList<Case> initMotDouble() {
        ArrayList<Case> result = new ArrayList<Case>();
        result.add(new Case(1,1));
        result.add(new Case(1,13));
        result.add(new Case(2,2));
        result.add(new Case(2,12));
        result.add(new Case(3,3));
        result.add(new Case(3,11));
        result.add(new Case(4,4));
        result.add(new Case(4,10));
        result.add(new Case(10,4));
        result.add(new Case(10,10));
        result.add(new Case(11,3));
        result.add(new Case(11,11));
        result.add(new Case(12,2));
        result.add(new Case(12,12));
        result.add(new Case(13,1));
        result.add(new Case(13,13));
        return result;
    }

    /**
     * Méthode permettant d'initialiser les cases de types lettre compte triple
     * @return result, une liste avec toutes les cases de type lettre triple
     */
    private ArrayList<Case> initLettreTriple() {
        ArrayList<Case> result = new ArrayList<Case>();
        result.add(new Case(1,5));
        result.add(new Case(1,9));
        result.add(new Case(5,1));
        result.add(new Case(5,5));
        result.add(new Case(5,9));
        result.add(new Case(5,13));
        result.add(new Case(9,1));
        result.add(new Case(9,5));
        result.add(new Case(9,9));
        result.add(new Case(9,13));
        result.add(new Case(13,5));
        result.add(new Case(13,9));
        return result;
    }

    /**
     * Méthode permettant d'initialiser les cases de types lettre compte double
     * @return result, une liste avec toutes les cases de type lettre double
     */
    private ArrayList<Case> initLettreDouble() {
        ArrayList<Case> result = new ArrayList<Case>();
        result.add(new Case(0,3));
        result.add(new Case(0,11));
        result.add(new Case(2,6));
        result.add(new Case(2,8));
        result.add(new Case(3,0));
        result.add(new Case(3,7));
        result.add(new Case(3,14));
        result.add(new Case(6,2));
        result.add(new Case(6,6));
        result.add(new Case(6,8));
        result.add(new Case(6,12));
        result.add(new Case(7,3));
        result.add(new Case(7,11));
        result.add(new Case(8,2));
        result.add(new Case(8,6));
        result.add(new Case(8,8));
        result.add(new Case(8,12));
        result.add(new Case(11,0));
        result.add(new Case(11,7));
        result.add(new Case(11,14));
        result.add(new Case(12,6));
        result.add(new Case(12,8));
        result.add(new Case(14,3));
        result.add(new Case(14,11));
        return result;
    }

    /**
     * Méthode permettant de récupérer la grille
     * @return grille, la grille
     */
    public ArrayList<ArrayList<Case>> getGrille() {
        return grille;
    }

    /**
     * Méthode permettant de récupérer la liste des cases lettre double
     * @return lettreDouble, la liste des cases lettre double
     */
    public ArrayList<Case> getLettreDouble() {
        return lettreDouble;
    }

    /**
     * Méthode permettant de récupérer la liste des cases lettre triple
     * @return lettreTriple, la liste des cases lettre triple
     */
    public ArrayList<Case> getLettreTriple() {
        return lettreTriple;
    }

    /**
     * Méthode permettant de récupérer la liste des cases mot double
     * @return lettreDouble, la liste des cases mot double
     */
    public ArrayList<Case> getMotDouble() {
        return motDouble;
    }

    /**
     * Méthode permettant de récupérer la liste des cases mot triple
     * @return motTriple, la liste des cases mot triple
     */
    public ArrayList<Case> getMotTriple() {
        return motTriple;
    }

    /**
     * Méthode permettant de récupérer la liste des mots déjà placés
     * @return motsPlace, la liste des mots déjà placés
     */
    public ArrayList<Mot> getMotsPlace() {
        return motsPlace;
    }

    /**
     * Méthode permettant de modifier la grille
     * @param grille, la nouvelle grille
     */
    public void setGrille(ArrayList<ArrayList<Case>> grille) { this.grille = grille; }

    /**
     * Méthode permettant de modifier les cases lettre double
     * @param lettreDouble, les nouvelles cases
     */
    public void setLettreDouble(ArrayList<Case> lettreDouble) { this.lettreDouble = lettreDouble; }

    /**
     * Méthode permettant de modifier les cases lettre triple
     * @param lettreTriple, les nouvelles cases
     */
    public void setLettreTriple(ArrayList<Case> lettreTriple) { this.lettreTriple = lettreTriple; }

    /**
     * Méthode permettant de modifier les cases mot double
     * @param motDouble, les nouvelles cases
     */
    public void setMotDouble(ArrayList<Case> motDouble) { this.motDouble = motDouble; }

    /**
     * Méthode permettant de modifier les cases mot triple
     * @param motTriple, les nouvelles cases
     */
    public void setMotTriple(ArrayList<Case> motTriple) { this.motTriple = motTriple; }

    /**
     * Méthode permettant de modifier les mots places
     * @param motsPlace, les mots places
     */
    public void setMotsPlace(ArrayList<Mot> motsPlace) { this.motsPlace = motsPlace; }

    /**
     * Méthode permettant d'afficher le plateau
     * @return res, le plateau
     */
    @Override
    public String toString() {
        String res = "";
        for(int i=0;i<grille.size();i++) {
            for(int j=0;j<grille.get(i).size();j++) {
                res += log.string("[", "cyan");
                if(grille.get(j).get(i).getLettre() == null) res += " ";
                else res += log.string(grille.get(j).get(i).getLettre().toString().toUpperCase(), "red");
                res += log.string("]", "cyan");
            }
            res += "\n";
        }
        return res;
    }

    /**
     * Vérifi si un mot est placable dans le plateau
     * @param motToPlace mot à placer
     * @param motToIgnore mot duquel il est issu (doit être ignoré pour les collisions)
     * @return true si placable
     */
    public boolean isPlacable(Mot motToPlace, Mot motToIgnore) {

        //Vérifier que le mot ne déborde pas
        if(!isMotInside(motToPlace)) return false;

        //vérifier qu'il n'y a pas de collision

        //récuperer les cases du MotToIgnore dans une liste
        ArrayList<Case> motToIgnoreCases = new ArrayList<>();
        if(motToIgnore.getSens().equals("horizontal")) {
            int lastCase = motToIgnore.getX() + motToIgnore.getMot().size();
            for (int x = motToIgnore.getX(); x < lastCase; x++) {
                int y = motToIgnore.getY();
                motToIgnoreCases.add(grille.get(x).get(y));
            }
        }else {
            int lastCase = motToIgnore.getY() + motToIgnore.getMot().size();
            for (int y = motToIgnore.getY(); y < lastCase; y++) {
                int x = motToIgnore.getX();
                motToIgnoreCases.add(grille.get(x).get(y));
            }
        }

        //récupérer les cases qu'on doit vérifier dans un autre liste
        ArrayList<Case> casesToCheck = new ArrayList<>();
        if(motToPlace.getSens().equals("horizontal")) {
            int y = motToPlace.getY();

            //Vérification cases au dessus et en dessous pas out of bounds
            boolean checkTop = false;
            if(y > 0) checkTop = true;
            boolean checkBottom = false;
            if(y < 14) checkBottom = true;

            int lastCase = motToPlace.getX() + motToPlace.getMot().size();
            for (int x = motToPlace.getX(); x < lastCase; x++) {
                casesToCheck.add(grille.get(x).get(y));
                if(checkTop) casesToCheck.add(grille.get(x).get(y-1));
                if(checkBottom) casesToCheck.add(grille.get(x).get(y+1));
            }

            //ajout des cases à gauche et à droite du mot
            if(motToPlace.getX()-1 >= 0) {
                casesToCheck.add(grille.get(motToPlace.getX()-1).get(y));
            }
            if(motToPlace.getX() + motToPlace.getMot().size() <= 14) {
                casesToCheck.add(grille.get(motToPlace.getX() + motToPlace.getMot().size()).get(y));
            }

        } else {
            int x = motToPlace.getX();

            //Vérification cases au dessus et en dessous pas out of bounds
            boolean checkLeft = false;
            if(x > 0) checkLeft = true;
            boolean checkRight = false;
            if(x < 14) checkRight = true;

            int lastCase = motToPlace.getY() + motToPlace.getMot().size();
            for (int y = motToPlace.getY(); y < lastCase; y++) {
                casesToCheck.add(grille.get(x).get(y));
                if(checkLeft) casesToCheck.add(grille.get(x-1).get(y));
                if(checkRight) casesToCheck.add(grille.get(x+1).get(y));
            }

            //ajout des cases en haut et en bas du mot
            if(motToPlace.getY()-1 >= 0) {
                casesToCheck.add(grille.get(x).get(motToPlace.getY()-1));
            }
            if(motToPlace.getY() + motToPlace.getMot().size() <= 14) {
                casesToCheck.add(grille.get(x).get(motToPlace.getY() + motToPlace.getMot().size()));
            }
        }


        //DeleteAll la whiteList sur la liste des cases à vérifier
        casesToCheck.removeAll(motToIgnoreCases);

        //si il y a une lettre sur une case à vérifier return false;
        for (Case caseToCheck : casesToCheck) {
            if(caseToCheck.getLettre() != null) return false;
        }

        return true;
    }

    /**
     * Vérifi si un mot ne déborde pas de la grille
     * @param mot le mot à placer
     * @return true si le mot ne déborde pas de la grille
     */
    public boolean isMotInside(Mot mot) {
        //Vérifi que la première lettre est dans le plateau
        if(mot.getX() < 0 || mot.getX() > 14 || mot.getY() < 0 || mot.getY() > 14 ) return false;

        //Vérifi que la dernière lettre est dans le plateau
        if(mot.getSens().equals("horizontal")) {
            int x = mot.getX() + mot.getMot().size() - 1;
            if(x < 0 || x > 14) return false;
        }else {
            int y = mot.getY() + mot.getMot().size() - 1;
            if(y < 0 || y > 14) return false;
        }
        return true;
    }

}
