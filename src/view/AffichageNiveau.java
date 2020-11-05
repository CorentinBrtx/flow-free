package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

import controller.Controleur;

public class AffichageNiveau extends JPanel {
	
	private Controleur controleur;
	private int tailleCase;
	private int remplissage;
	private int taux_remplissage;
	
	public AffichageNiveau(Controleur controleur, int tailleCase) {
		super();
		
		this.controleur = controleur;
		this.tailleCase = tailleCase;
		

		this.setBackground(Color.DARK_GRAY);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		int nbLignes = this.controleur.getNbLignes();
		int nbColonnes = this.controleur.getNbColonnes();
		
		/* On trace le plateau vide */
		
		g.setColor(Color.WHITE);
		g.drawRect(tailleCase, tailleCase, tailleCase*nbColonnes, tailleCase*nbLignes);
		
		for (int i=1; i<nbLignes; i++) {
			g.drawLine(tailleCase, tailleCase+tailleCase*i, tailleCase+tailleCase*nbColonnes, tailleCase+tailleCase*i);
		}
		for (int j=1; j<nbColonnes; j++) {
			g.drawLine(tailleCase+tailleCase*j, tailleCase, tailleCase+tailleCase*j, tailleCase+tailleCase*nbLignes);
		}
		
		remplissage = 0;
		
		/* On remplit le tableau */
		
		for (int i=0; i<nbLignes; i++) {
			for (int j=0; j<nbColonnes; j++) {
				
				Color color = controleur.getColor(i, j);
				if (color != null) {
					
					remplissage += 1;
					
					String origin = controleur.getOrigin(i, j);
					String destination = controleur.getDestination(i, j);
					
					g.setColor(color);
					
					switch (origin) {
					case "H":
						g.fillRect(tailleCase*135/100+tailleCase*j, tailleCase*(i+1)-2, tailleCase*30/100, tailleCase*65/100+2);
						break;
					case "B":
						g.fillRect(tailleCase*135/100+tailleCase*j, tailleCase*135/100+tailleCase*i, tailleCase*30/100, tailleCase*65/100+2);
						break;
					case "G":
						g.fillRect(tailleCase*(j+1)-2, tailleCase*135/100+tailleCase*i, tailleCase*65/100+2, tailleCase*30/100);
						break;
					case "D":
						g.fillRect(tailleCase*135/100+tailleCase*j, tailleCase*135/100+tailleCase*i, tailleCase*65/100+2, tailleCase*30/100);
						break;
					default:
						g.fillOval(tailleCase*125/100+tailleCase*j, tailleCase*125/100+tailleCase*i, tailleCase*50/100 , tailleCase*50/100);
					}
					
					switch (destination) {
					case "B":
						g.fillRect(tailleCase*135/100+tailleCase*j, tailleCase*(i+1), tailleCase*30/100, tailleCase*65/100);
						break;
					case "H":
						g.fillRect(tailleCase*135/100+tailleCase*j, tailleCase*135/100+tailleCase*i, tailleCase*30/100, tailleCase*65/100);
						break;
					case "D":
						g.fillRect(tailleCase*(j+1), tailleCase*135/100+tailleCase*i, tailleCase*65/100, tailleCase*30/100);
						break;
					case "G":
						g.fillRect(tailleCase*135/100+tailleCase*j, tailleCase*135/100+tailleCase*i, tailleCase*65/100, tailleCase*30/100);
						break;
					default:
						g.fillOval(tailleCase*125/100+tailleCase*j, tailleCase*125/100+tailleCase*i, tailleCase*50/100 , tailleCase*50/100);
					}
					
					if (this.controleur.getCase(i, j).getExtremite() != null) {
						g.fillOval(tailleCase*115/100+tailleCase*j, tailleCase*115/100+tailleCase*i, tailleCase*70/100 , tailleCase*70/100);
					}
					
					if (this.controleur.getCurrentLigne() == i && this.controleur.getCurrentColonne() == j) {
						g.drawRect(tailleCase*108/100+tailleCase*j, tailleCase*108/100+tailleCase*i, tailleCase*84/100, tailleCase*84/100);
					}
				}
			}
		};
		
		taux_remplissage = remplissage*100/(nbLignes*nbColonnes);
		
		/* On affiche les différents affichages sur les côtés du plateau (boutons de navigation, remplissage, etc.) */
		g.setColor(Color.WHITE);
		g.setFont(new Font("", Font.PLAIN, tailleCase/4));
		g.drawString("Remplissage : " + taux_remplissage + "%" , tailleCase, tailleCase*(nbLignes+2));
		if (this.controleur.isComplete()) {
			g.setFont(new Font("", Font.PLAIN, tailleCase));
			g.drawString("Bravo, c'est gagné !", tailleCase/2, tailleCase*(nbLignes+3)/2);
		}
		
		int numeroNiveau = this.controleur.getNumeroNiveau();
		
		g.setFont(new Font("", Font.PLAIN, tailleCase/2));
		g.drawString("Niveau "+numeroNiveau, tailleCase*3, tailleCase*7/10);
		
		
		if (numeroNiveau > 1) {
			g.setFont(new Font("", Font.PLAIN, tailleCase/3));
			g.drawString("Niveau précédent", tailleCase, tailleCase/2+tailleCase*(nbLignes+1));
		}
		
		if (numeroNiveau<this.controleur.getNbNiveaux()) {
			g.setFont(new Font("", Font.PLAIN, tailleCase/3));
			g.drawString("Niveau suivant", tailleCase/4+tailleCase*(nbColonnes-1), tailleCase/2+tailleCase*(nbLignes+1));
		}
		
		
		g.setFont(new Font("", Font.PLAIN, tailleCase/3));
		g.drawString("Menu", tailleCase/2, tailleCase/2);
	}

}
