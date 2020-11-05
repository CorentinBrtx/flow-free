package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

import controller.Controleur;

public class AffichageMenu extends JPanel {
	
	private int taille;
	private Controleur controleur;

	public AffichageMenu(Controleur controleur, int tailleCase) {
		super();
		
		this.taille = tailleCase*3/4;
		this.controleur = controleur;
		
		this.setBackground(Color.DARK_GRAY);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("", Font.PLAIN, taille));
		g.drawString("Flow Free", taille*3, taille*3);
		
		g.setFont(new Font("", Font.PLAIN, taille*3/4));
		
		int nbNiveaux = this.controleur.getNbNiveaux();
		
		for (int k=0; k<nbNiveaux; k++) {
			int ligne = k/4;
			int colonne = k-ligne*4;
			
			g.drawRect(taille*2 + colonne*taille*2, taille*4 + ligne*taille*2, taille, taille);
			g.drawString(String.valueOf(k+1), taille*232/100 + colonne*taille*2, taille*475/100 + ligne*taille*2);
		}		
		
	}
	
	

}
