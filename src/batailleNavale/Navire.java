package batailleNavale;

public class Navire {

	private Coordonnee debut;
	private Coordonnee fin;
	private Coordonnee[] partiesTouchees;
	private int nbTouchees;

	public Navire(Coordonnee debut, int longueur, boolean estVertical) {
		/*
		 * this constructor initializes the ship with a starting coordinate, length and
		 * direction .. which is in this case vertical
		 */
		if (longueur <= 1) {
			throw new IllegalArgumentException("Bateau trop petit! (2 cases minimum)");
		}
		this.debut = debut;
		if (estVertical) {
			this.fin = new Coordonnee(debut.getLigne()+ longueur,debut.getColonne());
		}else {
			this.fin= new Coordonnee(debut.getLigne(),debut.getColonne()+ longueur);
		}
		this.partiesTouchees = new Coordonnee[longueur];// initialize the part to track the parts that have been hit
		this.nbTouchees = 0;// at the beginning no parts are hit

	}

	// methodes

	public String toString() {
		// Returns a string representing this in the form "Navire(B1, 4, horizontal)"
		// (pour un navire de taille 4 placé horizontalement par exemple).
		String res = "Navire(";
		res += debut.toString();
		if (estVertical()) {// if vertical
			int longueur = fin.getLigne() - debut.getLigne() + 1;// how to get the length of the ship ..end coordinates
																	// -start coordinates
			res += ", " + longueur + ", vertical)";// add the coma ..concatenate the , and vertical
		} else {// horizontal
			int longueur = fin.getColonne() - debut.getColonne() + 1;
			res += ", " + longueur + ", horizontal)";
		}
		return res;
	}

	public Coordonnee getDebut() {
		// Getter for the starting coordinate (debut).
		return debut;
	}

	public Coordonnee getFin() {
		// Getter for the ending coordinate (fin).
		return fin;
	}
	
	public int longueur(boolean i) {
		if (i)
			return fin.getLigne()-debut.getLigne();
		else
			return fin.getColonne()-debut.getColonne();
	}

	public boolean estVertical() {
		// retourne true si et seulement si le navire est vertical.
		return debut.getColonne() == fin.getColonne();
	}

	public boolean contient(Coordonnee c) {
		// Returns true if the given coordinate (c) is part of the ship.
		// also check if it is horizontal or vertical
		return (c.getColonne() == debut.getColonne() && c.getLigne() >= debut.getLigne()
				&& c.getLigne() <= fin.getLigne())
				|| (c.getLigne() == debut.getLigne() && c.getColonne() >= debut.getColonne()
						&& c.getColonne() <= fin.getColonne());
	}

	public boolean touche(Navire n) {
		// Retourne true si et seulement si this est adjacent à n. L'adjacence par la
		// diagonale ne compte pas.
		return (((n.debut.getLigne() <= this.debut.getLigne() && n.fin.getLigne() >= this.debut.getLigne())
				|| (this.debut.getLigne() <= n.debut.getLigne() && this.fin.getLigne() >= n.debut.getLigne()))
				&& (n.debut.getColonne() == this.fin.getColonne() + 1
						|| this.debut.getColonne() == n.fin.getColonne() + 1))
				|| (((n.debut.getColonne() <= this.debut.getColonne() && n.fin.getColonne() >= this.debut.getColonne())
						|| (this.debut.getColonne() <= n.debut.getColonne()
								&& this.fin.getColonne() >= n.debut.getColonne()))
						&& (n.debut.getLigne() == this.fin.getLigne() + 1
								|| this.debut.getLigne() == n.fin.getLigne() + 1));
	}

		


	public boolean chevauche(Navire n) {
		// Returns true if the current ship and the given ship (n) overlap.
		return ((n.debut.getLigne() <= this.debut.getLigne() && n.fin.getLigne() >= this.debut.getLigne())
				|| (this.debut.getLigne() <= n.debut.getLigne() && this.fin.getLigne() >= n.debut.getLigne()))
				&& ((n.debut.getColonne() <= this.debut.getColonne() && n.fin.getColonne() >= this.debut.getColonne())
						|| (this.debut.getColonne() <= n.debut.getColonne()
								&& this.fin.getColonne() >= n.debut.getColonne()));
	}
		
	

	public boolean recoitTir(Coordonnee c) {
		// Updates the state of the ship when it receives a shot at coordinate c.
		// Returns true if the shot hits the ship.
		if (this.contient(c)) {
			int indice = estVertical() ? c.getLigne() - debut.getLigne() : c.getColonne() - debut.getColonne();
			partiesTouchees[indice] = c;
			this.nbTouchees++;
			return true;
		}
		return false;
	}

	public boolean estTouche(Coordonnee c) {
		// Returns true if the part of the ship at coordinate c is hit.
		return contient(c) && partiesTouchees[estVertical() ? c.getLigne() - debut.getLigne()
				: c.getColonne() - debut.getColonne()] != null;
	}

	public boolean estTouche() {
		// Returns true if at least one part of the ship is hit.
		return nbTouchees > 0;
	}

	public boolean estCoule() {
		// Returns true if all parts of the ship are hit, meaning the ship is sunk...

		if (nbTouchees == fin.getLigne() - debut.getLigne() + 1)
			return true;
		else return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Coordonnee c = new Coordonnee(2,3);
		Coordonnee c2 = new Coordonnee(3,4);
		Navire n = new Navire(c, 5, true);
		Navire n2 = new Navire(c2, 5, true);
		System.out.println(n.touche(n2));

	}

}