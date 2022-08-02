package m1.miage.scrabble.commun;

public class Identification {

    private String nom;
    private String url;

    /**
     * Constructeur de la classe identification
     * @param nom, le nom du joueur
     * @param url, l'url que le joueur utilise
     */
    public Identification(String nom, String url) {
        this.nom = nom;
        this.url = url;
    }

    /**
     * Méthode permettant de récuperer le nom du joueur
     * @return le nom du joueur
     */
    public String getNom(){
         return this.nom;
    }

    /**
     * Méthode permettant de récupérer l'url du joueur
     * @return l'url du joueur
     */
    public String getUrl(){
        return this.url;
    }
}