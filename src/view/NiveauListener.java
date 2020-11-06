package view;

import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;

import controller.Controleur;

public class NiveauListener implements MouseAndKeyListener {
	
	private Fenetre fenetre;
	private Controleur controleur;
	private AffichageNiveau affichageNiveau;
	private int tailleCase;
	private int nbLignes;
	private int nbColonnes;
	

	public NiveauListener(Fenetre fenetre, Controleur controleur, AffichageNiveau affichageNiveau, int tailleCase) {
		super();
		
		this.fenetre = fenetre;
		this.controleur = controleur;
		this.affichageNiveau = affichageNiveau;
		this.tailleCase = tailleCase;
	}
	
	public void update() {
		this.nbLignes = this.controleur.getNbLignes();
		this.nbColonnes = this.controleur.getNbColonnes();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int positionX = e.getX();
		int positionY = e.getY();
		
		int numeroNiveau = this.controleur.getNumeroNiveau();
		
		if (positionX>tailleCase && positionY-30>tailleCase && positionX<tailleCase*(1+nbColonnes) && positionY-30<tailleCase*(1+nbLignes)) {
			controleur.selectCase((positionY-tailleCase-30)/tailleCase, (positionX-tailleCase)/tailleCase);
		}
		else if (positionX < tailleCase*3/2 && positionY-30 < tailleCase*3/4) {
			this.fenetre.returnMenu();
		}

		else if (numeroNiveau > 1 && positionX>tailleCase && positionX<tailleCase+tailleCase*5/2 && positionY-30>tailleCase*(nbLignes+1)
				&& positionY-30<tailleCase*3/4+tailleCase*(nbLignes+1)) {
			this.fenetre.launchGame("./Niveaux/lvl_01_0"+(numeroNiveau-1)+".txt", numeroNiveau-1);
		}
		else if (numeroNiveau<this.controleur.getNbNiveaux() && positionX>tailleCase/4+tailleCase*(nbColonnes-1) 
				&& positionX<tailleCase/4+tailleCase*(nbColonnes-1)+tailleCase*2
				&& positionY-30>tailleCase*(nbLignes+1) && positionY-30<tailleCase*3/4+tailleCase*(nbLignes+1) ) {
			this.fenetre.launchGame("./Niveaux/lvl_01_0"+(numeroNiveau+1)+".txt", numeroNiveau+1);
		}
		
		this.fenetre.repaint();
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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int currentLigne = this.controleur.getCurrentLigne();
		int currentColonne = this.controleur.getCurrentColonne();
		if (currentLigne != -1 && currentColonne != -1) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				if (this.controleur.modifyCase(this.controleur.getTuyau(currentLigne, currentColonne), currentLigne-1, currentColonne, "B")) {
					this.controleur.setCurrent(currentLigne-1, currentColonne);
				}
				break;
			case KeyEvent.VK_DOWN:
				if (this.controleur.modifyCase(this.controleur.getTuyau(currentLigne, currentColonne), currentLigne+1, currentColonne, "H")) {
					this.controleur.setCurrent(currentLigne+1, currentColonne);
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (this.controleur.modifyCase(this.controleur.getTuyau(currentLigne, currentColonne), currentLigne, currentColonne+1, "G")) {
					this.controleur.setCurrent(currentLigne, currentColonne+1);
				}
				break;
			case KeyEvent.VK_LEFT:
				if (this.controleur.modifyCase(this.controleur.getTuyau(currentLigne, currentColonne), currentLigne, currentColonne-1, "D")) {
					this.controleur.setCurrent(currentLigne, currentColonne-1);
				}
				break;
			}
		}
		this.affichageNiveau.repaint();

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
		
		File directory = new File("./Niveaux");
		int nbNiveaux = directory.list().length-1;
		
		int numeroNiveau = this.controleur.getNumeroNiveau();
		
		if (positionX>tailleCase && positionY-30>tailleCase && positionX<tailleCase*(1+nbColonnes) && positionY-30<tailleCase*(1+nbLignes)) {
			if (controleur.getColor((positionY-tailleCase-30)/tailleCase, (positionX-tailleCase)/tailleCase) != null) {
				this.fenetre.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			else this.fenetre.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		else if (positionX < tailleCase*3/2 && positionY-30 < tailleCase*3/4) {
			this.fenetre.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		else if (numeroNiveau > 1 && positionX>tailleCase && positionX<tailleCase+tailleCase*5/2 && positionY-30>tailleCase*(nbLignes+1)
				&& positionY-30<tailleCase*3/4+tailleCase*(nbLignes+1)) {
			this.fenetre.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		else if (numeroNiveau<nbNiveaux && positionX>tailleCase/4+tailleCase*(nbColonnes-1) 
				&& positionX<tailleCase/4+tailleCase*(nbColonnes-1)+tailleCase*2
				&& positionY-30>tailleCase*(nbLignes+1) && positionY-30<tailleCase*3/4+tailleCase*(nbLignes+1) ) {
			this.fenetre.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		else this.fenetre.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
	}

}
