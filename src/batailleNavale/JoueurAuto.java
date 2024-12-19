package batailleNavale;

import java.util.Random;

public class JoueurAuto extends JoueurAvecGrille {
	boolean[][] dejaTirees =  new boolean[this.getTailleGrille()][this.getTailleGrille()];
	public JoueurAuto(GrilleNavale g, String nom) {
		/*permet d'obtenir un joueur de nom nom et jouant sur une grille navale g.*/
		super(g,nom);
	}
	
	public JoueurAuto(GrilleNavale g) {
		/*Cette méthode est invoquée sur le joueur défenseur après le choix de l’attaquant, c est la coordonnée à laquelle l’attaquant a choisi d’effectuer un tir. Elle retourne le résultat du tir qui ne peut être que TOUCHE, COULE, A_L_EAU, ou GAMEOVER.*/
		super(g);
	}
	
	protected void retourAttaque(Coordonnee c, int etat) {
		/*réalise l'affichage à la console des étapes de jeu pour l'attaque.*/
			System.out.println("Attaque en " + c);
	}
		
	protected void retourDefense(Coordonnee c, int etat) {
		/*réalise l'affichage à la console des étapes de jeu pour la défense.*/
		System.out.println("Attaqué en " + c + " - Résultat : " + etat);
	}
		
	public Coordonnee choixAttaque() {
		/*doit recueillir au clavier la saisie de la coordonnée à attaquer.*/
		Coordonnee choice =  null;
		while(choice == null) {
			Random random = new Random();
			int ligne = random.nextInt(getGrille().getTaille());
	        int colonne = random.nextInt(getGrille().getTaille());
	        if (dejaTirees[ligne][colonne]) {
	        	choice = null;
	        } else {
	        	choice = new Coordonnee(ligne, colonne);
	        	dejaTirees[ligne][colonne]= true;
	        }
		}
		
        return choice;
    }
	



}
