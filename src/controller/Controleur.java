package controller;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.Case;
import model.Couleur;
import model.Extremite;
import model.Plateau;
import model.Tuyau;

public class Controleur {
	
	protected Plateau plateau;
	private int currentLigne;
	private int currentColonne;
	private int numeroNiveau;
	private int nbNiveaux;
	
	public Controleur() {
		this.currentLigne = -1;
		this.currentColonne = -1;
		
		File directory = new File("./Niveaux");
		nbNiveaux = directory.list().length-1;
	}
	
	public Tuyau getTuyau(int ligne, int colonne) {
		return plateau.getTuyau(ligne, colonne);
	}
	
	public Tuyau selectCase(int ligne, int colonne) {
		/* Cette fonction est appelée lorsque l'utilisateur clique pour selectionner une case
		 * Si on recoit null, c'est que la case ne peut pas etre selectionnee
		 * Sinon, alors on met a jour la colonne et la ligne de la case actuelle 
		 */
		Tuyau tuyau = plateau.selectCase(ligne, colonne);
		if (tuyau != null) {
			this.currentLigne = ligne;
			this.currentColonne = colonne;
		}
		return tuyau;
	}
	
	public Color getColor(int ligne, int colonne) {
		Tuyau tuyau = this.getTuyau(ligne, colonne);
		if (tuyau != null) {
			return tuyau.getColor();
		}
		else return null;
	}
	
	public boolean modifyCase(Tuyau tuyau, int ligne, int colonne, String origin) {
		/* Cette fonction est appelée lorsque l'utilisateur utilise les fleches pour modifier un tuyau
		 */
		if (ligne<0 || ligne>=plateau.getNbLignes() || colonne<0 || colonne>=plateau.getNbColonnes()) {
			return false;
		}
		else {
			return plateau.modifyCase(tuyau, ligne, colonne, origin);
		}
	}
	
	public Case getCase(int ligne, int colonne) {
		return plateau.getCase(ligne, colonne);
	}
	
	public String getOrigin(int ligne, int colonne) {
		return plateau.getOrigin(ligne, colonne);
	}
	
	public String getDestination(int ligne, int colonne) {
		return plateau.getDestination(ligne, colonne);
	}
	
	public boolean isComplete() {
		return plateau.isComplete();
	}
	
	public void buildGame(String filename, int numeroNiveau) {
		/* Cette fonction est utilisée pour lire le fichier correspondant au niveau selectionné
		 * et pour créer ensuite le plateau correspondant
		 */
		
		try {

			File file = new File(filename);
			
			this.numeroNiveau = numeroNiveau;
			
			Scanner myScanner = new Scanner(file);
			
			String[] taille = myScanner.nextLine().split(" ");
			int nbLignes = Integer.parseInt(taille[0]);
			int nbColonnes = Integer.parseInt(taille[1]);
			
			this.plateau = new Plateau(nbLignes, nbColonnes);
			
			int nbCouleurs = Integer.parseInt(myScanner.nextLine());
			
			for (int i=0; i<nbCouleurs; i++) {
				
				Couleur couleur = new Couleur((Color) Color.class.getField(myScanner.nextLine()).get(null));
				
				Tuyau tuyau = new Tuyau(couleur);
				
				Extremite e1 = new Extremite(tuyau);
				Extremite e2 = new Extremite(tuyau);
				
				tuyau.setExtremites(e1, e2);
				
				String[] position1 = myScanner.nextLine().split(" ");
				int ligne1 = Integer.parseInt(position1[0]);
				int colonne1 = Integer.parseInt(position1[1]);
				this.plateau.setExtremite(e1, ligne1, colonne1);
				
				String[] position2 = myScanner.nextLine().split(" ");
				int ligne2 = Integer.parseInt(position2[0]);
				int colonne2 = Integer.parseInt(position2[1]);
				this.plateau.setExtremite(e2, ligne2, colonne2);
				
				this.currentLigne = -1;
				this.currentColonne = -1;
			}
			
			myScanner.close();
			
		} catch (FileNotFoundException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			System.out.println("Erreur dans la création du niveau");
			e.printStackTrace();
		}
	}

	public int getNbLignes() {
		return this.plateau.getNbLignes();
	}
	
	public int getNbColonnes() {
		return this.plateau.getNbColonnes();
	}
	
	public int getNumeroNiveau() {
		return numeroNiveau;
	}

	public int getCurrentLigne() {
		return currentLigne;
	}

	public int getCurrentColonne() {
		return currentColonne;
	}

	public void setCurrent(int ligne, int colonne) {
		this.currentLigne = ligne;
		this.currentColonne = colonne;
	}

	public int getNbNiveaux() {
		return nbNiveaux;
	}

}
