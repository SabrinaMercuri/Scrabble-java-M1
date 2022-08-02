package m1.miage.scrabble.serveur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionnairePartieApplication {
	/**
	 * Méthode main permettant de lancer l'application
	 * @param args, les arguments de la ligne de commande
	 */
	public static void main(String[] args) {
		System.out.println("Server running ...");
		SpringApplication.run(GestionnairePartieApplication.class, args);
	}

}
