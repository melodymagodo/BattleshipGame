package batailleNavale;

import java.util.Scanner;

public class JoueurTexte extends JoueurAvecGrille {
	private Scanner sc;
//represents a human player who interacts with the game 
	//scanner object to read input from the console 
	//constructors ..
public JoueurTexte(GrilleNavale g, String nom) {
	super(g,nom);//initialize the player's name
	this.sc = new Scanner(System.in);// initialise the scanner
}


public JoueurTexte(GrilleNavale g) {
	super(g);
	this.sc =new Scanner(System.in);}


protected void retourAttaque(Coordonnee c, int etat) {
	 System.out.println("Attaque en " + c);
}

protected void retourDefense(Coordonnee c, int etat) {
	System.out.println("Attaqué en " + c + " - Résultat : " + etat);
}


public Coordonnee choixAttaque() {
	
	    /* Recueille au clavier la saisie de la coordonnée à attaquer. */
	    System.out.println("Entrez les coordonnées de votre attaque (format : colonne ligne, ex: A1) :");
	    String s = sc.next();
	    try {
	        return new Coordonnee(s); // Assume Coordonnee validates input
	    } catch (IllegalArgumentException e) {
	        System.out.println("Format invalide. Veuillez réessayer.");
	        return choixAttaque(); // Retry until the user inputs a valid coordinate
	    }
	

}
}
