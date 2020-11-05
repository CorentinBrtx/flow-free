package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

public class Tuyau {
	
	private ArrayList<Case> cases;
	private Case current;
	private Couleur couleur;
	private Extremite extremite1;
	private Extremite extremite2;
	
	public Tuyau(Couleur couleur) {
		/* couleur est la couleur du tuyau
		 * current represente la derniere case du tuyau
		 * cases est la liste de toutes les cases par lequel passe leµ tuyau dans l'ordre
		 */
		this.couleur = couleur;
		this.current = null;
		this.cases = new ArrayList<Case>();
	}
	
	public void setExtremites(Extremite e1, Extremite e2) {
		this.extremite1 = e1;
		this.extremite2 = e2;
	}
	
	public boolean isComplete() {
		/* Verifie que le tuyau est complet, c'est a dire que les deux extremites appartiennent au tuyau
		 * ce qui signifie qu'elles sont reliees
		 */
		if (this.cases.indexOf(extremite1.getCaseCorrespondante()) != -1 && this.cases.indexOf(extremite2.getCaseCorrespondante()) != -1) {
			return true;
		}
		else return false;
	}


	public void selectTuyauCurrent(Case case1) {
		/* Cette fonction permet de modifier le tuyau en définissant la nouvelle case sélectionnée
		 * Si cette case fait deja partie du tuyau, alors on fait en sorte qu'elle devienne la derniere case du tuyau
		 * Sinon, alors on supprime l'ancien tuyau et on en recree un nouveau qui ne contient que la case en question
		 */
		this.current = case1;
		this.current.setDestination("");
		int index = this.cases.indexOf(this.current);
		if (index == -1) {
			for (Case c : this.cases) {
				c.removeTuyau();
			}
			this.cases = new ArrayList<>(Arrays.asList(this.current));
		}
		else {
			for (Case c : this.cases.subList(index+1, this.cases.size())) {
				c.removeTuyau();
			}
			this.cases = new ArrayList<Case>(this.cases.subList(0, index+1));
		}
	}
	
	
	public Color getColor() {
		return this.couleur.getCouleur();
	}
	
	public Couleur getCouleur() {
		return this.couleur;
	}


	public boolean addCase(Case case1, String origin) {
		this.cases.add(case1);
		this.current.setDestination(origin);
		this.current = case1;
		return true;
	}


	public boolean removeCase(Case case1) {
		/* Retire la derniere case seulement si la case en parametre est l'avant derniere
		 * Cette case devient alors la derniere case du tuyau (la current)
		 */
		if (case1 == this.cases.get(this.cases.size() - 2)) {
			this.current.removeTuyau();
			this.current = case1;
			this.current.setDestination("");
			this.cases.remove(this.cases.size() - 1);
			return true;
		}
		else {
			return false;
		}
	}
	
	public String toString() {
		return "Tuyau de couleur " + this.couleur;
	}
	
}
