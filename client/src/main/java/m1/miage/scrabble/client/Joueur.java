package m1.miage.scrabble.client;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;



import m1.miage.scrabble.commun.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Scope("singleton")
public class Joueur {

	@Autowired
	JoueurApplication appJoueur;

	private Identification idJoueur;
	private int nombresPoint;
	private ArrayList<Lettre> lettres;
	private RestTemplate restTemplate;
	private String serveurIP = "";
	Log log = new Log();

	public int cptAPI = 0;

	/**
	 * Méthode permettant de récupérer le nombre de points du joueur
	 * @return nombresPoint, le nombre de point du joueur
	 */
	public int getNombresPoint() {
		return nombresPoint;
	}

	/**
	 * Méthode permettant de modifier le nombre de points du joueur
	 * @param nombresPoint, le nouveau nombre de points
	 */
	public void setNombresPoint(int nombresPoint) {
		this.nombresPoint = nombresPoint;
	}

	/**
	 * Méthode permettant de récupérer la liste des lettres du joueur
	 * @return lettres, les lettres que possede le joueur
	 */
	public ArrayList<Lettre> getLettres() {
		return lettres;
	}

	/**
	 * Méthode permettant de modifier la liste des lettres du joueur
	 * @param lettres, les nouvelles lettres du joueur
	 */
	public void setLettres(ArrayList<Lettre> lettres) {
		this.lettres = lettres;
	}

	/**
	 * Méthode permettant de récupérer l'identification du joueur
	 * @return idJoueur, identification du joueur
	 */
	public Identification getIdJoueur(){
		return idJoueur;
	}

	/**
	 * Méthode permettant de modifier l'ip du serveur
	 * @param serveurIP,
	 */
	public void setserveurIP(String serveurIP) {
		this.serveurIP=serveurIP;
	}

	/**
	 * Méthode permettant la connexion du joueur au serveur
	 * @param id, l'identification du joueur
	 */
	public void  connexion(Identification id) {
		this.idJoueur = id;
		Boolean val = restTemplate.postForObject("http://"+serveurIP+":8080/connexion/",idJoueur, Boolean.class);
		log.println("Joueur > état de la connexion : "+val, "cyan");
	}

	/**
	 * Méthode permettant d'échanger les lettres d'un joueur avec les lettres de la pioche
	 */
	public ArrayList<Lettre> echanqerLettre(){
		ArrayList<Lettre> change = new ArrayList<Lettre>();
		int nbLettreChange = (int)Math.round(Math.random()* lettres.size())-1;
		for(int i=0;i<nbLettreChange;i++){
			int index = (int)Math.round(Math.random()* lettres.size())-1;
			if(index==-1){
				index++;
			}
			change.add(lettres.get(index));
			lettres.remove(index);
		}
		ArrayList<Object> envoi = new ArrayList<Object>();
		envoi.add(change);
		return change;
	}

	/**
	 * Méthode permettant au joueur de jouer un mot
	 * @param plateau, etat actuel du jeu
	 * @return Arraylist Mot à l'indice 0 et les autres sont les lettres
	 */
	public ArrayList<Object> envoyerMot(Plateau plateau) {
		///random pour déterminer le joker
		for(Lettre l : lettres) {
			if(l.getNbPoints()==0) {
				l.setLettre((char)(Math.floor(Math.random()*26)+97));
			}
		}

		ArrayList<Object> motEtLettres = new ArrayList<Object>();
		//permet de préparer l'objet a renvoyer
		motEtLettres.add(null);
		motEtLettres.add(null);

		Mot meilleurMot = null;
		int meilleurMotPoint = 0;
		//verifier que l'on possède assez de lettres pour jouer
		if(lettres.size() < 2) {
			log.println("Auncun mot jouable", "red");
			return motEtLettres;
		}

		// Jouer quand le plateau est vide
		if(plateau.getMotsPlace().size() == 0){
			meilleurMot = meilleurMotMain(plateau);
			motEtLettres.set(0,meilleurMot);
			return motEtLettres;
		}

		ArrayList<Object> meilleurMotsPerpendiculaire = meilleurPerpendiculaire(plateau) ;

		//TODO Changer le return quand on auras plusieur manière de renvoyer des mots
		if(meilleurMotsPerpendiculaire.get(0)!= null && meilleurMotsPerpendiculaire.get(1)!=null){
			motEtLettres = meilleurMotsPerpendiculaire;
		}
		return motEtLettres ;
	}


	/**
	 * Renvoi le  mot qui rapporte le plus de points avec la main du joueur
	 * @param plateau
	 * @return
	 */
	private Mot meilleurMotMain(Plateau plateau) {
		StringBuilder lettresString = new StringBuilder();
		for (Lettre l : lettres) {
			lettresString.append(l.getLettre());
		}
		URL url;
		HttpURLConnection connection;
		ArrayList<String> mots;
		System.out.println("https://scrabble.now.sh/api?letters=" + lettresString);
		mots = restTemplate.getForObject("https://scrabble.now.sh/api?letters=" + lettresString, ArrayList.class);
		cptAPI ++;
		if (mots.size() > 0) {
			Mot meilleurMot = null;
			int meilleurMotPoint = 0;

			for (String mot : mots) {
				ArrayList<Lettre> lettresMot = new ArrayList<>();

				String sens = "";

				Mot motCourrant = null;

				for (int i = 0; i < mot.length(); i++) {lettresMot.add(new Lettre(mot.charAt(i)));}

				if (Math.random() < 0.5) {
					sens = "horizontal";
					int index = plateau.getGrille().size() - 1;
					motCourrant = new Mot(lettresMot, sens, 7, 7);
				} else {
					sens = "vertical";
					int index = 0;
					motCourrant = new Mot(lettresMot, sens, 7, 7);
				}
				if(calculerPointMot(motCourrant,plateau)>meilleurMotPoint){
					meilleurMot = motCourrant;
					meilleurMotPoint = calculerPointMot(meilleurMot,plateau);
				}
			}
			return meilleurMot;
		}
		return null;
	}

	/**
	 * Renvoie la liste des mots perpendiculaire qui peuvent être placer sur la grille
	 *
	 * @return liste des mots perpendiculaires
	 */
	ArrayList<Object> meilleurPerpendiculaire(Plateau plateau) {
		HashMap<Mot,Lettre> motsPerpendiculaire = new HashMap<>();
		ArrayList<Mot> motsPerpendiculaireMot = new ArrayList<>();
		ArrayList<Lettre> motsPerpendiculaireLettre = new ArrayList<>();


		ArrayList<Mot> motGrille = plateau.getMotsPlace();


		if (motGrille.size()>0){
			//pour tout les mots de la grille
			for (Mot mot : motGrille) {
				//Pour chacune des lettres de la grille
				for (Lettre lettrePlatal: mot.getMot()) {
					ArrayList<Mot> motstmp = new ArrayList<>();
					ArrayList<String> motsString = new ArrayList<>();

					motsString.addAll(rechercheMotsApi(lettrePlatal));
					motstmp.addAll(getMotsFromStrings(motsString, mot, lettrePlatal));
					if (motstmp.size()>0){
						//pour chacun des mots qui a été trouvé pour la lettre du plateau
						for (Mot motToPlace : motstmp) {
							if(plateau.isPlacable(motToPlace, mot)){
								motsPerpendiculaireLettre.add(lettrePlatal);
								motsPerpendiculaireMot.add(motToPlace);
							}
						}
					}
				}
			}
		}

		Mot meilleurMot = null;
		int meilleurMotPoint = 0;
		Lettre meilleurMotLettre = null;
		for (int i = 0; i < motsPerpendiculaireMot.size(); i++) {
			Mot mot = motsPerpendiculaireMot.get(i);

			if(calculerPointMot(mot,plateau)>meilleurMotPoint){
				meilleurMot = mot;
				meilleurMotPoint = calculerPointMot(meilleurMot,plateau);
				meilleurMotLettre = motsPerpendiculaireLettre.get(i);;
			}
		}

		//TODO voir les logs qui manque
		ArrayList<Object> toReturn = new ArrayList<>();
		toReturn.add(meilleurMot);
		toReturn.add(meilleurMotLettre);
		return toReturn;

	}

	ArrayList<Mot> getMotsFromStrings(List<String> listMots, Mot mot, Lettre lettre) {
		String sens = mot.getSens();
		ArrayList<Mot> mots = new ArrayList<>();

		for(String m : listMots) {
			int posLettreOld = mot.getMot().indexOf(lettre);
			int posLettreNew = m.indexOf(lettre.toString());

			ArrayList<Lettre> lettres = new ArrayList<>();
			for(int i=0;i<m.length();i++) lettres.add(new Lettre(m.charAt(i)));

			if(sens.equals("horizontal")) {
				mots.add(new Mot(lettres, "vertical", mot.getX() + posLettreOld, mot.getY() - posLettreNew));
			} else {
				mots.add(new Mot(lettres, "horizontal", mot.getX() - posLettreNew, mot.getY() + posLettreOld));
			}
		}
		return mots;
	}

	/**
	 * Fait la recherche des différents mots sous forme de string en prenant en compte la lettre du plateau
	 * ne retourne que les mots qui contiennent la lettre du plateau
	 * @param lettrePlateau lettre du plateau avec laquelle essayer de créer un mot
	 * @return ensemble des mots trouvé qui possédent la lettre du plateau
	 */
	List<String> rechercheMotsApi(Lettre lettrePlateau) {
		ArrayList<String> motsString;
		String lettresString = "";
		for(Lettre l : lettres) lettresString += l.toString();
		lettresString += lettrePlateau.toString();

		motsString = restTemplate.getForObject("https://scrabble.now.sh/api?letters=" + lettresString, ArrayList.class);
		cptAPI ++;
		List<String> mots = motsString.stream().filter(s -> s.contains(lettrePlateau.toString())).collect(Collectors.toList());
		System.out.print("");
		return mots;
	}

	/**
	 * Méthode permettant d'ajouter le score du mot au score du joueur
	 * @param scoreMot, le score du mot que le joueur a joué
	 */
	public void operation(int scoreMot) {
		nombresPoint += scoreMot;
	}

	/**
	 * Méthode permettant d'ajouter des lettres au joueur
	 * @param lettres, les lettres à ajouter au joueur
	 */
	public void addLettres(ArrayList<Lettre> lettres) {
		for (Lettre l : lettres) {
			this.lettres.add(l);
		}
	}

	/**
	 * Méthode permettant de suprimmer de la main les lettres que le joueur a placé
	 * @param res, [0] Mot qui est posé
	 *             [1] liste des lettres déjà présentes sur le plateau qu'il ne faut pas supprimer
	 */
	public void suprimmerLettresMain(ArrayList<Object> res) {
		Mot m = (Mot) res.get(0);
		ArrayList<Lettre> mot = m.getMot();
		ArrayList<Lettre> newMot = new ArrayList<>();
		newMot.addAll(mot);

		if(res != null && res.size() > 1 && res.get(1) != null) {
			Lettre l = (Lettre) res.get(1);
			int id = newMot.indexOf(l);
			if(id != -1) newMot.remove(id);
		}

		while (newMot.size() > 0) {
			int id = lettres.indexOf(newMot.get(0));
			if(id != -1) lettres.remove(id);
			newMot.remove(0);
		}
	}

	public int calculerPointMot(Mot motJoue, Plateau p) {
		int mTriple = 0;
		int mDouble = 0;

		for (int i = 0; i < motJoue.getMot().size(); i++) {

			if (motJoue.getSens().equals("horizontal")) {
				p.getGrille().get(motJoue.getX() + i).get(motJoue.getY()).setLettre(motJoue.getMot().get(i));
				motJoue.setTotalPoints(calculPointMotLdoubleLtriple(motJoue,p.getGrille().get(motJoue.getX() + i).get(motJoue.getY()), motJoue.getMot().get(i),p));
				if( caseMotDouble(p.getGrille().get(motJoue.getX() + i).get(motJoue.getY()),p)){
					mDouble++;
				}
				else if(caseMotTriple(p.getGrille().get(motJoue.getX() + i).get(motJoue.getY()),p)){
					mTriple++;
				}
			}
			else {
				p.getGrille().get(motJoue.getX()).get(motJoue.getY() + i).setLettre(motJoue.getMot().get(i));
				motJoue.setTotalPoints(calculPointMotLdoubleLtriple(motJoue,p.getGrille().get(motJoue.getX()).get(motJoue.getY() + i), motJoue.getMot().get(i),p));
				if( caseMotDouble(p.getGrille().get(motJoue.getX()).get(motJoue.getY()+i),p)){
					mDouble++;
				}
				else if(caseMotTriple(p.getGrille().get(motJoue.getX()).get(motJoue.getY()+i),p)){
					mTriple++;
				}
			}
		}

		motJoue.setTotalPoints(motJoue.getTotalPoints()*(int)Math.pow(2,mDouble)*(int)Math.pow(3,mTriple));
		//Todo si on créer des cas a plusieurs chevauchements les changer ici
		if(motJoue.getMot().size()>7){
			motJoue.setTotalPoints(motJoue.getTotalPoints()+50);
		}
		return motJoue.getTotalPoints();
	}

	/**
	 * Méthode permettant de savoir si une case est "mot double"
	 * @param aCase,p
	 * @return si la case est "mot double"
	 */
	public boolean caseMotDouble(Case aCase, Plateau p) {
		for(Case c : p.getMotDouble()){
			if(aCase.equals(c)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Méthode permettant de savoir si une case est "mot triple"
	 * @param aCase,p
	 * @return si la case est "mot triple"
	 */
	public boolean caseMotTriple(Case aCase,Plateau p) {
		for(Case c : p.getMotTriple()){
			if(aCase.equals(c)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Méthode permettant de calculer le nombre de points d'un mot double ou triple
	 * @param motJoue
	 * @param aCase
	 * @param lettre
	 * @param p
	 * @return le nombre de points du mot
	 */
	public int calculPointMotLdoubleLtriple(Mot motJoue, Case aCase, Lettre lettre, Plateau p) {
		if(caseLettreDouble(aCase,p)){
			return motJoue.getTotalPoints()+lettre.getNbPoints();
		}
		else if(caseLettreTriple(aCase,p)){
			return motJoue.getTotalPoints()+(lettre.getNbPoints()*2);
		}
		return motJoue.getTotalPoints();
	}

	/**
	 * Méthode permettant de savoir si une case est "lettre double"
	 * @param aCase,p
	 * @return si la case est lettre double"
	 */
	public boolean caseLettreDouble(Case aCase,Plateau p) {
		for(Case c : p.getLettreDouble()){
			if(aCase.equals(c)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Méthode permettant de savoir si une case est "lettre triple"
	 * @param aCase,p
	 * @return si la case est lettre triple"
	 */
	public boolean caseLettreTriple(Case aCase,Plateau p) {
		for(Case c : p.getLettreTriple()){
			if(aCase.equals(c)){
				return true;
			}
		}
		return false;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
}
