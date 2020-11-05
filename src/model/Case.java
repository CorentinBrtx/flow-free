package model;

public class Case {
	
	protected Extremite extremite;
	protected Tuyau tuyau;
	private String origin;
	private String destination;

	public Case() {
		/* origin et destination ne servent que pour l'affichage graphique du tuyau
		 * tuyau correspond au tuyau auquel appartient la case s'il y en a un
		 */
		this.extremite = null;
		this.tuyau = null;
		this.origin = "";
		this.destination = "";
	}
	
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public Extremite getExtremite() {
		return extremite;
	}

	public void setExtremite(Extremite extremite) {
		this.extremite = extremite;
	}
	
	public Tuyau getTuyau() {
		if (this.tuyau != null) {
			return this.tuyau;
		}
		else if (this.extremite != null) {
			return this.extremite.getTuyau();
		}
		else {
			return null;
		}
	}
	
	public Tuyau selectCase() {
		/* Cette fonction est appelée lorsque l'utilisateur clique sur la case
		 * Elle permet de modifier un tuyau en définissant cette case en tant que bout du tuyau
		 * On appelle pour cela la fonction selectTuyauCurrent qui s'occupe de modifier le tuyau
		 */
		if (this.tuyau != null) {
			this.tuyau.selectTuyauCurrent(this);
			return this.tuyau;
		}
		else if (this.extremite != null) {
			this.tuyau = this.extremite.getTuyau();
			this.tuyau.selectTuyauCurrent(this);
			return this.tuyau;
		}
		else {
			return null;
		}
	}

	public void removeTuyau() {
		this.tuyau = null;	
		this.origin = "";
		this.destination = "";
	}

	public boolean modify(Tuyau tuyau1, String origin) {
		/* Cette fonction est appelée lorsque l'utilisateur utilise les flêches pour modifier un tuyau
		 * Elle ajoute cette case au tuyau spécifié si la case n'est pas deja occupee
		 * Si la case contient le tuyau specifie en parametre, alors on retire une case du tuyau
		 * Cette fonction retourne true si l'action a pu etre effectuée, auquel cas la case est la derniere case du tuyau
		 * Elle retourne false si l'action n'a pas pu etre effectuée
		 */
		if (this.tuyau == null) {
			if (this.extremite == null || this.extremite.getTuyau() == tuyau1) {
				if (tuyau1.addCase(this, origin)) {
					this.tuyau = tuyau1;
					this.origin = origin;
					return true;
				}
					else return false;
			}
			else return false;
		}
		else if (this.tuyau != tuyau1) {
			return false;
		}
		else {
			return tuyau1.removeCase(this);
		}
	}
	
	public String toString() {
		if (this.extremite != null) {
			String couleur = this.extremite.getTuyau().getCouleur().toString();
			return couleur + couleur;
		}
		else if (this.tuyau != null) {
			return this.tuyau.getCouleur().toString() + " ";
		}
		else return "  ";
	}

}
