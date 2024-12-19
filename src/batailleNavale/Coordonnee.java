package batailleNavale;

public class Coordonnee implements Comparable<Coordonnee> {

	private int ligne;
	private int colonne;

	public int getColonne() {
		return colonne;
	}

	public void setColonne(int colonne) {
		this.colonne = colonne;
	}

	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	public int getLigne() {
		return ligne;
	}

	public Coordonnee(int ligne, int colonne) {
//		if (ligne < 1 || ligne > 26 || colonne < 1 || colonne > 26) {
//			throw new IllegalArgumentException("Les indices de ligne et de colonne doivent être entre 1 et 26 inclus: " + ligne + " lignes, " + colonne + " colonnes");
//		}
		this.ligne = ligne;
		this.colonne = colonne;

//				System.out.println(this.colonne);
//				System.out.println(this.ligne);

	}

	public Coordonnee(String s) {
		if (s == null || s.length() < 2) {
			throw new IllegalArgumentException("La chaîne de coordonnée est invalide : " + s);
		}
		char c = s.charAt(0);
		int l = Integer.parseInt(s.substring(1));
		if (c < 'A' || c > 'Z') {
			throw new IllegalArgumentException("La lettre doit être entre 'A' et 'Z' : " + colonne);
		}
		this.colonne = (int) c - '@';
		this.ligne = l;

		// System.out.println(colonne);
		// System.out.println(ligne);

//		try {
		if (l < 1 || l > 26) {
			throw new IllegalArgumentException("Le chiffre doit entre entre 1 et 26 inclus : " + ligne);
		}
//		}catch(Exception e){
//			System.out.println("Le chiffre doit entre entre 1 et 26 inclus : " + ligne);
//			System.out.println("On passe le tour pour l'instant");
//			Scanner sc = new Scanner(System.in);
//			String str = sc.nextLine().trim().toUpperCase();
//		}
	}

	public String toString() {
		char l = (char) (this.colonne + '@');
		return l + Integer.toString(this.ligne);
	}

	public boolean equals(Object obj) {
		return (obj == this);
	}

	public boolean voisine(Coordonnee c) {
		if (this.ligne == c.ligne && this.colonne == c.colonne) 
			throw new IllegalArgumentException("les bateaux ne peuvent pas utiliser les memes coordonnees");
		if (this.ligne == c.ligne) // sur meme ligne
			return ( this.colonne + 1 == c.colonne || this.colonne - 1 == c.colonne);
		else if (this.colonne == c.colonne) //sur meme colonne
			return (this.ligne + 1 == c.ligne || this.ligne - 1 == c.ligne);
		else//cas ou sont en diagonale donc voisinage pas possible
			return false;
	}

	public int compareTo(Coordonnee c) { // Retourne le résultat de la comparaison de this et de c.

		// Une coordonnée est considérée inférieure à une autre, si elle se trouve sur
		// une ligne

		// inférieure ou si elle se trouve sur la même ligne mais sur une colonne
		// inférieure.

		// compare ligne then colonne

		if (this.ligne < c.ligne)
			return -1;
		else if ((this.ligne == c.ligne) && (this.colonne < c.colonne))
			return -1;
		else
			return 1;
	}


}
