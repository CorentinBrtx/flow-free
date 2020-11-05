package view;

import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import controller.Controleur;

public class MenuListener implements MouseAndKeyListener {
	
	private int taille;
	private Fenetre fenetre;
	private Controleur controleur;
	
	public MenuListener(Fenetre fenetre, Controleur controleur, int tailleCase) {
		this.taille = tailleCase*3/4;
		this.fenetre = fenetre;
		this.controleur = controleur;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int positionX = e.getX();
		int positionY = e.getY();
		
		if (positionX>taille*105/100 && positionY-30>4*taille && positionX<taille*10) {
			
			int ligne = ((positionY-30-4*taille)/taille);
			int colonne = (positionX - taille*105/100)/taille;
			int position_index = colonne + 4*ligne;
			
			if (position_index - (position_index/2)*2 != 0 && ligne - (ligne/2)*2 == 0) {
				int niveau = (position_index+1)/2;
				
				if (niveau<=this.controleur.getNbNiveaux()) {
					this.fenetre.launchGame("./Niveaux/lvl_01_0"+niveau+".txt", niveau);
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		/* Cette fonction est utilisee pour afficher la souris en forme de main lorsque l'on passe au dessus d'une zone cliquable */
		int positionX = e.getX();
		int positionY = e.getY();
		
		if (positionX>taille*110/100 && positionY-30>4*taille && positionX<taille*10) {
			
			int ligne = ((positionY-30-4*taille)/taille);
			int colonne = (positionX - taille*110/100)/taille;
			int position_index = colonne + 4*ligne;
			
			if (position_index - (position_index/2)*2 != 0 && ligne - (ligne/2)*2 == 0) {
				int niveau = (position_index+1)/2;
				
				if (niveau<=this.controleur.getNbNiveaux()) {
					this.fenetre.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				else this.fenetre.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			else this.fenetre.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		else this.fenetre.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
	}

}
