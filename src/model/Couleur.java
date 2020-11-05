package model;

import java.awt.Color;

public class Couleur {
	
	private Color couleur;
	
	public Couleur(Color couleur) {
		this.couleur = couleur;
	}
	
	public String toString() {
		return couleur.toString();
	}
	
	public Color getCouleur() {
		return this.couleur;
	}

}
