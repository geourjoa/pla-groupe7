package jeu;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Partie {

	// Taille du monde
	public static final int LARGEUR = 12;

	public static final int HAUTEUR = 10;

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

		for (int i = 0; i < decor.length; i++) {
			for (int j = 0; j < decor[i].length; j++) {
				decor[i][j] = new Case(Type.HERBE);
			}
		}

		for (int i = 0; i < decor.length; i++) {
			decor[i][5] = new Case(Type.ARBRE);
			decor[i][8] = new Case(Type.CHAMPS);
		}

		ajouterPersonnage(new Heros(joueur1, 0, 0));
		ajouterPersonnage(new Heros(joueur2,HAUTEUR-1,LARGEUR-1));

	}

	private void ajouterPersonnage(Heros heros) {
		// TODO verifier si la case est vraiment accessible, pour l'instant on
		// n'en tiens pas compte
		personnages.add(heros);

	}

	public void jouerTour() {

		System.out.println(personnages.size());
		
		interfaceUtilisateur.afficherMap(decor, personnages);
		

		for (Iterator<Personnage> iterator = personnages.iterator(); iterator.hasNext();) {
			Personnage personnage = iterator.next();

			Action actionAfaire = Action.NE_RIEN_FAIRE;

			if (personnage instanceof Heros) {

				interfaceUtilisateur.demanderNouvelleAction();
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
					if (estParcourable((personnage.getPositionX() + 1)%LARGEUR, personnage.getPositionY()))
					{
						personnage.allerADroite();
					}
					
					break;
				case ALLER_A_GAUCHE:
					if (estParcourable((personnage.getPositionX() - 1)%LARGEUR, personnage.getPositionY()))
					{
						personnage.allerAGauche();
					}
					break;
				case ALLER_EN_HAUT:
					if (estParcourable(personnage.getPositionX(), (personnage.getPositionY() - 1)%HAUTEUR))
					{				
						personnage.allerEnHaut();
					}
					break;
				case ALLER_EN_BAS:
					if (estParcourable(personnage.getPositionX(), (personnage.getPositionY() + 1)%HAUTEUR))
					{
						personnage.allerEnBas();
					}
					break;

				// Si on arrive la, soit la commande est mauvaise soit on ne
				// doit rien faire
				case NE_RIEN_FAIRE:
				default:

					break;
			}
		}

	}

	public boolean estTermine() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean estParcourable(int x, int y) {
		// TODO pour l'instant toute les cases sont parcourables, on peut être à
		// plusieurs dessus etc..
		return true;
	}
}
