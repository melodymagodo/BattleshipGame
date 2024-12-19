package batailleNavale;

public abstract class JoueurAvecGrille extends Joueur {
	private GrilleNavale grille;

	public JoueurAvecGrille(GrilleNavale g, String nom) {
		super(g.getTaille(), nom);/* calls the constructor of the parent class (the class from which
									 JoueurAvecGrille inherits).*/
		this.grille = g;
		if (nom.isEmpty() ) {
			throw new IllegalArgumentException("Le nom est vide ");
		}
	}

	public JoueurAvecGrille(GrilleNavale g) {
		super(g.getTaille());
		this.grille = g;
	}

	public GrilleNavale getGrille() {
		return grille;
	}

	public int defendre(Coordonnee c) {

		/*
		 * c est la coordonnée où l'attaquant a décidé de tirer. Cette méthode renvoie
		 * le résultat du tir, qui peut être TOUCHE, COULE, A_L_EAU ou GAMEOVER..
		 */
		grille.recoitTir(c);
		if (grille.perdu())
			return GAMEOVER;
		else if (grille.estCoule(c))
			return COULE;
		else if (grille.estTouche(c))
			return TOUCHE;

		return A_L_EAU;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      
	}
}
