package jeu;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Partie {

	// Taille du monde
	public static final int LARGEUR = 10;

	public static final int HAUTEUR = 12;

	private InterfaceUtilisateur interfaceUtilisateur; // Interface du jeu

	private Joueur joueur1;

	private Joueur joueur2;

	private List<Personnage> personnages;

	/*
	 * 
	 * 0,0------------------largeur,0 ------------------------------
	 * hauteur,0------hauteur,largeur
	 */
	private Case decor[][];

	public Partie(InterfaceUtilisateur interfaceUtilisateur, Joueur joueur1, Joueur joueur2) {
		this.interfaceUtilisateur = interfaceUtilisateur;
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;

		personnages = new LinkedList<Personnage>();

		decor = new Case[HAUTEUR][LARGEUR];

		creerDecor();

		// TODO verifier si la case est vraiment accessible, pour l'instant on
		// n'en tiens pas encore compte
		personnages.add(new Heros(joueur1, decor[0][0]));
		personnages.add(new Heros(joueur2, decor[0][1]));

	}

	private void creerDecor() {

		for (int h = 0; h < HAUTEUR; h++) {
			for (int l = 0; l < LARGEUR; l++) {
				decor[h][l] = new Case(Type.HERBE, h, l);
			}
		}

		// Tout le décor est crée, il faut maintenant chainée les cases

		for (int h = 0; h < HAUTEUR; h++) {
			for (int l = 0; l < LARGEUR; l++) {
				// Chainage en haut
				if (h == 0)
					decor[h][l].setCaseEnHaut(decor[HAUTEUR - 1][l]);
				else
					decor[h][l].setCaseEnHaut(decor[h - 1][l]);

				// Chainage en bas
				if (h == HAUTEUR - 1)
					decor[h][l].setCaseEnBas(decor[0][l]);
				else
					decor[h][l].setCaseEnBas(decor[h + 1][l]);

				// Chainage a gauche
				if (l == 0)
					decor[h][l].setCaseAGauche(decor[h][LARGEUR - 1]);
				else
					decor[h][l].setCaseAGauche(decor[h][l - 1]);

				// Chainage a droite
				if (l == LARGEUR - 1)
					decor[h][l].setCaseADroite(decor[h][0]);
				else
					decor[h][l].setCaseADroite(decor[h][l + 1]);
			}
		}

		for (int h = 0; h < decor.length; h++) {
			decor[h][5].setTypeDeLaCase(Type.ARBRE);
			decor[h][8].setTypeDeLaCase(Type.CHAMPS);
		}

	}

	public void jouerTour() {

		interfaceUtilisateur.afficherMap(decor);
		interfaceUtilisateur.afficherPersonnages(personnages);

		//On parcourt la liste des personnages et on fait effectuer une action à chacun
		for (Iterator<Personnage> iterator = personnages.iterator(); iterator.hasNext();) {
			Personnage personnage = iterator.next();

			Action actionAfaire = Action.NE_RIEN_FAIRE;

			if (personnage instanceof Heros) {

				interfaceUtilisateur.demanderNouvelleAction(personnage.getJoueur().getNom());
				actionAfaire = personnage.getJoueur().getNouvelleAction();

			} /*
				 * else {
				 * 
				 * //TODO Choisir la transitiona affecté à un personnage qui
				 * n'est pas un héros (qui n'est pas jouable) //TODO Récuperer
				 * l'action à effectuer }
				 * 
				 * 
				 * //TODO Faire executer l'action
				 */
			switch (actionAfaire) {
				case ALLER_A_DROITE:
					personnage.allerADroite();
					break;
				case ALLER_A_GAUCHE:
					personnage.allerAGauche();
					break;
				case ALLER_EN_HAUT:
					personnage.allerEnHaut();
					break;
				case ALLER_EN_BAS:
					personnage.allerEnBas();
					break;
				case ATTAQUER:
					personnage.attaquer();
					break;
				
				case NE_RIEN_FAIRE:
				default:

					break;
			}
		}

	}

	public boolean estTermine() {
		return false;
	}

}
