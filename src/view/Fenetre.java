package view;

import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JFrame;

import controller.Controleur;

public class Fenetre extends JFrame {
	
	private AffichageNiveau affichageNiveau;
	private AffichageMenu affichageMenu;
	private Controleur controleur;
	private MenuListener menuListener;
	private NiveauListener niveauListener;
	private int tailleCase;
	
	public Fenetre(int tailleCase) {
		super();
		
		this.controleur = new Controleur();
		
		this.tailleCase = tailleCase;
		
		this.setPreferredSize(new Dimension(tailleCase*10, tailleCase*10));
		this.setTitle("Flow Free");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.affichageMenu = new AffichageMenu(controleur, tailleCase);
		this.add(affichageMenu);
		
		this.affichageNiveau = new AffichageNiveau(controleur, tailleCase);
		
		this.menuListener = new MenuListener(this, controleur, this.tailleCase);
		this.niveauListener = new NiveauListener(this, controleur, affichageNiveau, tailleCase);
		
		this.addKeyListener(menuListener);
		this.addMouseListener(menuListener);
		this.addMouseMotionListener(menuListener);
		
		this.pack();
		this.setVisible(true);
	}
	
	public void launchGame(String filename, int numeroNiveau) {
		/* Lorsque cette fonction est appelee, on construit le niveau avec le controleur,
		 * puis on modifie les listener pour utiliser ceux du niveau plutot que ceux du menu
		 */
		
		this.controleur.buildGame(filename, numeroNiveau);
		
		this.remove(affichageMenu);
		this.add(affichageNiveau);
		
		this.removeKeyListener(niveauListener);
		this.removeMouseListener(niveauListener);
		this.removeMouseMotionListener(niveauListener);
		
		this.niveauListener.update();
		
		this.removeKeyListener(menuListener);
		this.removeMouseListener(menuListener);
		this.removeMouseMotionListener(menuListener);
		this.addKeyListener(niveauListener);
		this.addMouseListener(niveauListener);
		this.addMouseMotionListener(niveauListener);
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
		this.pack();
		this.setVisible(true);
		this.repaint();
	}
	
	public void returnMenu() {
		/* A l'inverse de la precedente, cette fonction permet de retourner au fonctionnement en mode menu */
		
		this.remove(affichageNiveau);
		this.add(affichageMenu);
		
		this.removeKeyListener(niveauListener);
		this.removeMouseListener(niveauListener);
		this.removeMouseMotionListener(niveauListener);
		this.addKeyListener(menuListener);
		this.addMouseListener(menuListener);
		this.addMouseMotionListener(menuListener);
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
		this.repaint();
	}
}
