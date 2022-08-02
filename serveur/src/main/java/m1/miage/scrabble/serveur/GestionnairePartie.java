package m1.miage.scrabble.serveur;

import m1.miage.scrabble.commun.Case;
import m1.miage.scrabble.commun.Lettre;
import m1.miage.scrabble.commun.Log;
import m1.miage.scrabble.commun.Plateau;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import m1.miage.scrabble.commun.Mot;

import java.util.ArrayList;

@Component
@Scope("singleton")
public class GestionnairePartie implements Runnable {

	@Autowired
	GestionnairePartieWebControlleur ctrl; // pour pouvoir gerer en fonction des eveneemnt du clien ;)

	Thread partie;
	Plateau plateau;
	ArrayList<Joueur> joueurs;
	ArrayList<Lettre> pioche;
	int nbPasse;
	Log log = new Log();

	Boolean stopAtEnd = true;
	Boolean end = false;

	/**
	 * Méthode permettant l'inistialisation de la partie et lancement du thread
	 */
	public void lancerPartie() {
		if (partie == null) {
			pioche = new ArrayList<Lettre>();
			initPioche();
			plateau = new Plateau();
			joueurs = ctrl.getJoueurs();
			nbPasse=0;
			partie = new Thread(this);
			partie.start();
		} else {
			log.println("GameManager > la partie est déjà démarrée", "purple");
		}
	}

	/**
	 * Méthode permettant le déroulement de la partie
	 */
	@Override
	public void run() {
		// la partie se lance la x)
		// distribution des lettres
		ArrayList<Character> premiereLettreMain = new ArrayList<>();
		premiereLettreMain.add(null);
		premiereLettreMain.add(null);
		for (int nbJoueur = 0; nbJoueur < joueurs.size(); nbJoueur++) {
			ArrayList<Lettre> main = initMain();
			premiereLettreMain.set(nbJoueur, main.get(0).getLettre());
			Boolean mainDepart = ctrl.envoyerMainAuJoueur(main, joueurs.get(nbJoueur));
		}

		//Tirage au sort pour définir le joueur qui commence
		String l0 = premiereLettreMain.get(0)+"";
		String l1 = premiereLettreMain.get(1)+"";
		if(l0.compareTo(l1) > 0) {
			Joueur jTemp = joueurs.get(0);
			joueurs.set(0, joueurs.get(1));
			joueurs.set(1, jTemp);
			String lTemp = l0;
			l0 = l1;
			l1 = lTemp;
		}
		log.println("Tirage au sort pour définir le joueur qui commence: ", "red");
		log.println("- " + joueurs.get(0).getIdJoueur().getNom() + ": " + l0, "red");
		log.println("- " + joueurs.get(1).getIdJoueur().getNom() + ": " + l1, "red");
		log.println(joueurs.get(0).getIdJoueur().getNom() + " commence la partie", "red");

		while (nbPasse<6) {
			for (int nbJoueur = 0; nbJoueur < joueurs.size(); nbJoueur++) {
				Mot motJoue = ctrl.demanderAuJoueurDeJouer(joueurs.get(nbJoueur), plateau);
				int lettrePlacees = 0;
				if(motJoue==null ){
					log.println("GameManager > demande au joueur "+joueurs.get(nbJoueur).getIdJoueur().getNom()+" d'echanger ses lettres", "purple");
					Boolean oskour = ctrl.proposeEchange(joueurs.get(nbJoueur));
					nbPasse++;
					log.println(nbPasse+"","red");
				}
				else{
					nbPasse=0;
					lettrePlacees = placerMot(motJoue,joueurs.get(nbJoueur));
					joueurs.get(nbJoueur).getStatJoueur().setLettresPosees(lettrePlacees);
					joueurs.get(nbJoueur).getStatJoueur().setMotsPoses();
					//log.println(""+lettrePlacees,"blue");
					log.println("GameManager > " + joueurs.get(nbJoueur).getIdJoueur().getNom() + " a joué " + motJoue, "purple");
					// distribution des lettres après avoir joué
					Boolean mainDepart = ctrl.envoyerLettresAuJoueur(distributionLettres(lettrePlacees), joueurs.get(nbJoueur));
				}
			}
		}

		end();
	}

	/**
	 * Affichages de fin de partie
	 */
	public void end() {
		log.println("GameManager > la partie est finie ", "purple");
		System.out.println(plateau);

		partie = null;

		log.println("\n -----------Statistiques------------\n", "yellow");
		for(Joueur j : joueurs) {
			log.println("Stats >  Joueur : "+ j.getIdJoueur().getNom() + "\n" + j.getStatJoueur().toString() +"\n", "yellow");
		}
		ctrl.envoyerFin();
		// fin brutale (pour abréger sur travis).
		end = true;
		if(stopAtEnd) System.exit(0);
	}

	/**
	 * Méthode permettant d'initier la pioche
	 */
	public void initPioche() {

		pioche.add(new Lettre('z'));
		pioche.add(new Lettre('q'));
		pioche.add(new Lettre('j'));
		pioche.add(new Lettre('x'));
		pioche.add(new Lettre('k'));

		for (int i = 0; i < 2; i++) {
			pioche.add(new Lettre('f'));
			pioche.add(new Lettre('h'));
			pioche.add(new Lettre('v'));
			pioche.add(new Lettre('w'));
			pioche.add(new Lettre('y'));
			pioche.add(new Lettre('b'));
			pioche.add(new Lettre('c'));
			pioche.add(new Lettre('m'));
			pioche.add(new Lettre('u'));
			pioche.add(new Lettre((char)0));
		}

		for (int i = 0; i < 3; i++) {
			pioche.add(new Lettre('g'));
		}

		for (int i = 0; i < 4; i++) {
			pioche.add(new Lettre('d'));
			pioche.add(new Lettre('l'));
			pioche.add(new Lettre('s'));
			pioche.add(new Lettre('u'));
		}

		for (int i = 0; i < 6; i++) {
			pioche.add(new Lettre('r'));
			pioche.add(new Lettre('n'));
			pioche.add(new Lettre('t'));
		}

		for (int i = 0; i < 8; i++) {
			pioche.add(new Lettre('o'));
		}

		for (int i = 0; i < 9; i++) {
			pioche.add(new Lettre('a'));
			pioche.add(new Lettre('i'));
		}

		for (int i = 0; i < 12; i++) {
			pioche.add(new Lettre('e'));
		}
	}

	/**
	 * Méthode permettant d'initier la main du joueur
	 * @return main, la main du joueur avec les 7 premieres lettres
	 */
	public ArrayList<Lettre> initMain() {
		int index = 0;
		ArrayList<Lettre> main = new ArrayList<Lettre>();
		for (int i = 0; i < 7; i++) {
			index = (int) Math.round(Math.random() * (pioche.size()-1));
			main.add(pioche.get(index));
			pioche.remove(index);
		}
		return main;
	}

	/**
	 * Méthode permettant de placer un mot sur le plateau
	 * @param motJoue
	 * @param j, Joueur
	 * @return lettres, le nombre de lettre que le joueur a posé
	 */
	public int placerMot(Mot motJoue, Joueur j) {
		int lettres = motJoue.getMot().size();
		int mTriple = 0;
		int mDouble = 0;

		for(Joueur jo : joueurs) {
			if(jo.getIdJoueur().getNom().equals(j.getIdJoueur().getNom())) {
				jo.getStatJoueur().setPlusGrandeValeurMot(motJoue);
				jo.getStatJoueur().setMoyTailleMot(motJoue);
			}
		}

		for (int i = 0; i < motJoue.getMot().size(); i++) {

			if (motJoue.getSens().equals("horizontal")) {
				if (plateau.getGrille().get(motJoue.getX() + i).get(motJoue.getY()).getLettre() != null) {
					if( plateau.getGrille().get(motJoue.getX() + i).get(motJoue.getY()).getLettre().getLettre() == motJoue.getMot().get(i).getLettre()) {
						log.println("" + (plateau.getGrille().get(motJoue.getX() + i).get(motJoue.getY()).getLettre().getLettre() == motJoue.getMot().get(i).getLettre()), "blue");
						lettres--;
					}
				}
				plateau.getGrille().get(motJoue.getX() + i).get(motJoue.getY()).setLettre(motJoue.getMot().get(i));
				motJoue.setTotalPoints(calculPointMotLdoubleLtriple(motJoue,plateau.getGrille().get(motJoue.getX() + i).get(motJoue.getY()), motJoue.getMot().get(i)));
				if( caseMotDouble(plateau.getGrille().get(motJoue.getX() + i).get(motJoue.getY()))){
					mDouble++;
				}
				else if(caseMotTriple(plateau.getGrille().get(motJoue.getX() + i).get(motJoue.getY()))){
					mTriple++;
				}
			}
			else {
				if (plateau.getGrille().get(motJoue.getX()).get(motJoue.getY() + i).getLettre() != null) {
					if( plateau.getGrille().get(motJoue.getX()).get(motJoue.getY() + i).getLettre().getLettre() == motJoue.getMot().get(i).getLettre()) {
						log.println("" + (plateau.getGrille().get(motJoue.getX()).get(motJoue.getY() + i).getLettre().getLettre() == motJoue.getMot().get(i).getLettre()), "blue");
						lettres--;
					}
				}
				plateau.getGrille().get(motJoue.getX()).get(motJoue.getY() + i).setLettre(motJoue.getMot().get(i));
				motJoue.setTotalPoints(calculPointMotLdoubleLtriple(motJoue,plateau.getGrille().get(motJoue.getX()).get(motJoue.getY() + i), motJoue.getMot().get(i)));
				if( caseMotDouble(plateau.getGrille().get(motJoue.getX()).get(motJoue.getY()+i))){
					mDouble++;
				}
				else if(caseMotTriple(plateau.getGrille().get(motJoue.getX()).get(motJoue.getY()+i))){
					mTriple++;
				}
			}
		}
		motJoue.setTotalPoints(motJoue.getTotalPoints()*(int)Math.pow(2,mDouble)*(int)Math.pow(3,mTriple));


		// ajout des points du mot au score du joueur
		//ajout bonus
		///mise à jour de la moyenne du mot joué
		//mise à joueur du score total du joueur
		if (this.scrabble(lettres) == true){
			Boolean mainDepart = ctrl.majScoreJoueur(motJoue.getTotalPoints()+50,j.getIdJoueur());
			for(Joueur jo : joueurs) {
				if(jo.getIdJoueur().getNom().equals(j.getIdJoueur().getNom())) {
					jo.getStatJoueur().setScoreMoyMot(motJoue);
					jo.getStatJoueur().setTotalPoints(motJoue.getTotalPoints()+50);
					jo.getStatJoueur().setPlusGrandeValeurPose(motJoue);
				}
			}
		}else{
			Boolean mainDepart = ctrl.majScoreJoueur(motJoue.getTotalPoints(),j.getIdJoueur());
			for(Joueur jo : joueurs) {
				if(jo.getIdJoueur().getNom().equals(j.getIdJoueur().getNom())) {
					jo.getStatJoueur().setScoreMoyMot(motJoue);
					jo.getStatJoueur().setTotalPoints(motJoue.getTotalPoints());
					jo.getStatJoueur().setPlusGrandeValeurPose(motJoue);
				}
			}
		}

        plateau.getMotsPlace().add(motJoue);
		return lettres;
	}

	/**
	 * Méthode permettant de savoir si une case est "mot double"
	 * @param aCase
	 * @return si la case est "mot double"
	 */
	public boolean caseMotDouble(Case aCase) {
		for(Case c : plateau.getMotDouble()){
			if(aCase.equals(c)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Méthode permettant de savoir si une case est "mot triple"
	 * @param aCase
	 * @return si la case est "mot triple"
	 */
	public boolean caseMotTriple(Case aCase) {
		for(Case c : plateau.getMotTriple()){
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
	 * @return le nombre de points du mot
	 */
	public int calculPointMotLdoubleLtriple(Mot motJoue, Case aCase, Lettre lettre) {
		if(caseLettreDouble(aCase)){
			return motJoue.getTotalPoints()+lettre.getNbPoints();
		}
		else if(caseLettreTriple(aCase)){
			return motJoue.getTotalPoints()+(lettre.getNbPoints()*2);
		}
		return motJoue.getTotalPoints();
	}

	/**
	 * Méthode permettant de savoir si une case est "lettre double"
	 * @param aCase
	 * @return si la case est lettre double"
	 */
	public boolean caseLettreDouble(Case aCase) {
		for(Case c : plateau.getLettreDouble()){
			if(aCase.equals(c)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Méthode permettant de savoir si une case est "lettre triple"
	 * @param aCase
	 * @return si la case est lettre triple"
	 */
	public boolean caseLettreTriple(Case aCase) {
		for(Case c : plateau.getLettreTriple()){
			if(aCase.equals(c)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Méthode permettant de distribuer les lettres manquantes au joueur
	 * @param lettres manquantes
	 * @return main, les nouvelles lettres ajoutées dans la main
	 */
	public ArrayList<Lettre> distributionLettres(int lettres) {
		int index = 0;
		int taille = lettres;
		ArrayList<Lettre> main = new ArrayList<Lettre>();
		if(pioche.size()<=lettres){
			taille = pioche.size();
		}
		for (int i = 0; i < taille; i++) {
			index = (int) Math.round(Math.random() * (pioche.size()-1));
			main.add(pioche.get(index));
			pioche.remove(index);
		}
		return main;
	}

	/**
	 * Méthode permettant d'échanger les lettres du joueur
	 * @param lettres à échanger
	 * @return change, les nouvelles lettres du joueur
	 */
	public ArrayList<Lettre> echangeLettre(ArrayList<Lettre> lettres) {
		ArrayList<Lettre> change = new ArrayList<Lettre>();
		if(pioche.size()>lettres.size()){
			for (int i = 0; i < lettres.size(); i++) {
				int index = (int) Math.round(Math.random() * (pioche.size()-1));
				change.add(pioche.get(index));
				pioche.remove(index);
			}
			for (int i = 0; i < lettres.size(); i++) {
				pioche.add(lettres.get(i));
			}
			return change;
		}
		return lettres;
	}

	/**
	 * Méthode permettant de savoir si le joueur à joueur 7 lettres d'un coup
	 * @param lettres, les lettres posées
	 * @return true ou false, si le joueur a fait un scrabble ou non
	 */
	public boolean scrabble(int lettres){
		if (lettres == 7){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Méthode permettant d'arrêter la partie
	 * @param stopAtEnd, true ou false
	 */
	public void setStopAtEnd(Boolean stopAtEnd) {
		this.stopAtEnd = stopAtEnd;
	}
}
