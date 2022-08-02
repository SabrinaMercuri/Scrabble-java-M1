package m1.miage.scrabble.commun;

public class Log {
    private static final String ANSI_RESET = "\u001B[0m";

    /**
     * Méthode permettant d'afficher un message dans la console avec une couleur
     * @param message
     * @param color
     */
    public void print(String message, String color) {
        System.out.print(getColorbyText(color) + message + ANSI_RESET);
    }

    /**
     * Méthode permettant d'afficher un message dans la console avec une couleur et un retour ligne
     * @param message
     * @param color
     */
    public void println(String message, String color) {
        System.out.println(getColorbyText(color) + message + ANSI_RESET);
    }

    /**
     * Méthode permettant de formater un message avec une couleur
     * @param message
     * @param color
     * @return le message coloré
     */
    public String string(String message, String color) {
        return getColorbyText(color) + message + ANSI_RESET;
    }

    /**
     * Méthode permettant de récupérer une couleur utf8
     * @param color
     * @return la couleur utf8
     */
    private String getColorbyText(String color) {
        switch (color) {
            default:
                return ANSI_RESET;
            case "red":
               return  "\u001B[31m";
            case "green":
                return  "\u001B[32m";
            case "yellow":
                return  "\u001B[33m";
            case "blue":
                return  "\u001B[34m";
            case "purple":
                return  "\u001B[35m";
            case "cyan":
                return  "\u001B[36m";
            case "white":
                return  "\u001B[37m";
        }
    }
}
