package jeu;

import java.util.Scanner;

public class JoueurConsoleZQSD extends JoueurConsole {
	
	public JoueurConsoleZQSD() {
		super.heros=new Heros(this);
	}

	public JoueurConsoleZQSD(String nom) {
		this.nom=nom;
	}

	@Override
	// Ne devra pas �tre modulable par l'automate?
	public Action getNouvelleAction() {
		Scanner sc = new Scanner(System.in);
		char codeAction = sc.nextLine().charAt(0); // Récupère le premier caractere
		sc.close();
		
		switch (codeAction) {
		case '0':
			return Action.NE_RIEN_FAIRE;
		case 'Z' :
		case 'z' :
			return Action.ALLER_EN_HAUT;
		case 'Q' :
		case 'q' :
			return Action.ALLER_A_GAUCHE;
			
		case 'S' :
		case 's' :
			return Action.ALLER_EN_BAS;

		case 'D' :
		case 'd' :
			return Action.ALLER_A_DROITE;
			
		default:
			return Action.NE_RIEN_FAIRE;
		}
	}

}