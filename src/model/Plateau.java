package model;

public class Plateau {

	protected Case[] cases;
	private int nbLignes;
	private int nbColonnes;
	
	public Plateau(int nbLignes, int nbColonnes) {
		this.nbLignes = nbLignes;
		this.nbColonnes = nbColonnes;
		this.cases = new Case[nbLignes*nbColonnes];
		for (int i=0; i<nbLignes*nbColonnes;i++) {
			this.cases[i] = new Case();
		}
	}

	public Tuyau getTuyau(int ligne, int colonne) {
		Case case1 = cases[nbColonnes*ligne + colonne];
		return case1.getTuyau();
	}
	
	public Tuyau selectCase(int ligne, int colonne) {
		Case case1 = cases[nbColonnes*ligne + colonne];
		return case1.selectCase();
	}

	public boolean modifyCase(Tuyau tuyau, int ligne, int colonne, String origin) {
		if (ligne >= nbLignes || colonne >= nbColonnes) {
			return false;
		}
		else {
			Case case1 = cases[nbColonnes*ligne + colonne];
			return case1.modify(tuyau, origin);
		}
	}
	
	public void display() {
		for (int i=0; i<nbLignes; i++) {
			for (int j=0; j<nbColonnes; j++) {
				System.out.print("|" + this.cases[i*nbLignes + j]);
			}
			System.out.println("|");
		}
	}
	
	public String getOrigin(int ligne, int colonne) {
		Case case1 = cases[nbColonnes*ligne + colonne];
		return case1.getOrigin();
	}
	
	public String getDestination(int ligne, int colonne) {
		Case case1 = cases[nbColonnes*ligne + colonne];
		return case1.getDestination();
	}
	
	public Case getCase(int ligne, int colonne) {
		Case case1 = cases[nbColonnes*ligne + colonne];
		return case1;
	}
	
	public int getNbLignes() {
		return nbLignes;
	}

	public int getNbColonnes() {
		return nbColonnes;
	}
	
	public boolean isComplete() {
		for (Case c: cases) {
			if (c.getTuyau() == null || !(c.getTuyau().isComplete())) {
				return false;
			}
		}
		return true;
	}

	public void setExtremite(Extremite extremite, int ligne, int colonne) {
		Case case1 = cases[nbColonnes*ligne + colonne];
		case1.setExtremite(extremite);
		extremite.setCaseCorrespondante(case1);
	}

}
