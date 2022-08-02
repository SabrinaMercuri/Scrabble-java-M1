package m1.miage.scrabble.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import m1.miage.scrabble.commun.Identification;

import java.net.InetAddress;

@SpringBootApplication
public class JoueurApplication {

    @Autowired
    Joueur joueur;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    Environment environment;

    @Value("${nom:}")
    private String nom;

    @Value("${serveurIP:localhost}")
    private String serveurIP;

    @Value("${mode:}")
    private String mode;

    /**
     * Méthode main permettant de lancer l'application
     * @param args, les arguments de la ligne de commande
     */
    public static void main(String[] args) {
        System.out.println("Client running ...");
        SpringApplication.run(JoueurApplication.class, args);
    }

    /**
     * Méthode permettant de renvoyer le restTemplate pour permettre de faire des requêtes vers le serveur
     * @param builder, ce qui permet de créer le restTemplate
     * @return le restTemplate par le builder
     */
    @Bean
    @Autowired
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    /**
     * Méthode permettant de récupérer le port et connecter le joueur au serveur
     * @param restTemplate, ce qui permet de faire la requête post
     * @return un CommandLineRunner avec des arguments
     */
    @Bean
    public CommandLineRunner unClient(RestTemplate restTemplate) {
        return args -> {
            /// connexion
            if(mode.equals("IT")) {
                System.out.println("Mode Test intégration");
                joueur.setRestTemplate(restTemplate);
            }
            else {
                String port = environment.getProperty("local.server.port");
                String nomJoeur = nom;
                if(nomJoeur.equals("")) nomJoeur = "J"+port;
                joueur.setRestTemplate(restTemplate);
                Identification monId = new Identification(nomJoeur, "http://"+InetAddress.getLocalHost().getHostAddress()+":"+port);
                System.out.println(InetAddress.getLocalHost().getHostAddress());
                System.out.println(serveurIP);
                joueur.setserveurIP(serveurIP);
                joueur.connexion(monId);
            }
        };
    }
}