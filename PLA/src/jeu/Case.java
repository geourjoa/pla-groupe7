package jeu;

public class Case {

	private Type typeDeLaCase;
	
	private Case caseADroite;
	
	private Case caseAGauche;
	
	private Case caseEnBas;
	
	private Case caseEnHaut;
	
	private Personnage personnagePresent;

	public Case(Type typeDeLaCase) {
		this.setTypeDeLaCase(typeDeLaCase);
		personnagePresent=null;
	}

	public Type getTypeDeLaCase() {
		return typeDeLaCase;
	}

	public void setTypeDeLaCase(Type typeDeLaCase) {
		this.typeDeLaCase = typeDeLaCase;
	}
	
	

	public Case getCaseADroite() {
		return caseADroite;
	}

	public void setCaseADroite(Case caseADroite) {
		this.caseADroite = caseADroite;
	}

	public Case getCaseAGauche() {
		return caseAGauche;
	}

	public void setCaseAGauche(Case caseAGauche) {
		this.caseAGauche = caseAGauche;
	}

	public Case getCaseEnBas() {
		return caseEnBas;
	}

	public void setCaseEnBas(Case caseEnBas) {
		this.caseEnBas = caseEnBas;
	}

	public Case getCaseEnHaut() {
		return caseEnHaut;
	}

	public void setCaseEnHaut(Case caseEnHaut) {
		this.caseEnHaut = caseEnHaut;
	}
	
	public Personnage getPersonnagePresent() {
		return personnagePresent;
	}
	
	public void retirerPersonnagePresent(){
		this.personnagePresent = null;
	}

	public void placerPersonnage(Personnage personnage) {
		this.personnagePresent = personnage;
	}

	@Override
	public String toString() {
		
		String s =" ";
		
		if(personnagePresent!=null){
			s="P";
		}

		switch (typeDeLaCase) {
			case HERBE:
				return s + ". ";
			case ARBRE:
				return s + "| ";
			case CHAMPS:
				return s + " #";

			default:
				return s + ".";
		}
	}

	

}
