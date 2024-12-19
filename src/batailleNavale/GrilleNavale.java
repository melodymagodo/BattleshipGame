package batailleNavale;

public class GrilleNavale {
	private Navire[] navires;
	private int nbNavires;
	private int taille;
	private Coordonnee[] tirsRecus;
	private int nbTirsRecus;

	public int coordonneAleatoire() {
		return (int) ((Math.random() * taille) + 1);
	}

	public boolean orientationAleatoire() {
		return (((int) (Math.random() * 2)) == 1);// si egal a un alors vertical, sinon horizontal
	}

	public GrilleNavale(int taille, int[] taillesNavires) {
		tirsRecus = new Coordonnee[taille * taille];
		nbTirsRecus = 0;

		this.taille = taille;// taille de la grille
		nbNavires = 0;//taillesNavires.length;// nombre de na vires sur la grille
		System.out.println("nb navires est : " + nbNavires);
		this.navires = new Navire[taillesNavires.length];// taille du tableau initialisée
		// initialiser les navires a l'intérieur du tableau ??
		this.placementAuto(taillesNavires);
//		for (int i = 1; i - 1 < taillesNavires.length; i++) {// chaque navire va se faire initialiser
//			int coord = coordonneAleatoire();// lignes
//			int coord2 = coordonneAleatoire();// colonnes
//			boolean orientation = orientationAleatoire();
//			// verification si il y a de la place entre pour placer navire (eviter
//			// outOfBonds)
//			// on vérifie si la longueur du navire à la ligne/colonne dépasse les limites de
//			// la grille
//
//			if (i > 1) {
//				if (((taillesNavires[i - 1] + coord) > taille) || ((taillesNavires[i - 1] + coord2) > taille))
//					i--;// recalcul des coordonnees
//				else {
//					for (int k = 0; k < i - 1; k++) {
//
//						if ((this.navires[k].chevauche(
//								new Navire(new Coordonnee(coord, coord2), taillesNavires[i - 1], orientation)))
//								|| (this.navires[k].touche(new Navire(new Coordonnee(coord, coord2),
//										taillesNavires[i - 1], orientation)))) {
//							i--;
//							break;
//						} else {
//							this.navires[i - 1] = new Navire(new Coordonnee(coord, coord2), taillesNavires[i - 1],
//									orientation);
//							System.out.println("navire " + i + " : " + navires[i - 1]);
//						}
//					}
//
//				}
//
//			} else if (((taillesNavires[i - 1] + coord) > taille) || ((taillesNavires[i - 1] + coord2) > taille))
//				i--;
//			else {
//				this.navires[i - 1] = new Navire(new Coordonnee(coord, coord2), taillesNavires[i - 1], orientation);
//				System.out.println("navire " + i + " : " + navires[i - 1]);
//			}
//
//		}
	}

	public GrilleNavale(int taille, int nbNavires) {
		tirsRecus = new Coordonnee[taille * taille];
		nbTirsRecus = 0;
		this.taille = taille;
		navires = new Navire[nbNavires];
	}

//	public String toString() {
//		// remplissage d'un tableau à 2D
//		// indices vont etre i et j avec +1 pour incorporer le fait qu'une ligne et une
//		// colonne ne sont pas disponibles
//		// tab[][0] = que des lettres
//		// tab[0][] = chiffres
//		// donc lorsque i=0 sinon ou j =0 => condition
//		// sinon .
//		// création terminé d'une grille vide
//		// ensuite pour les valeurs des navires dans navire[k] et tirsRecus[k], on
//		// places des X, O et #
//
//		String[][] s = new String[this.taille + 1][this.taille + 1];
//
//		for (int i = 0; i < taille + 1; i++) {
//			for (int j = 0; j < taille + 1; j++) {
//				if (i == 0)
//					s[i][j] = "" + ((char) (j + 64)) + " ";
//				else if (j == 0) {
//					if (i < 10 && i > 0)
//						s[i][j] = i + " ";
//					else
//						s[i][j] = i + "";
//				} else
//					s[i][j] = ". ";
//				// System.out.print(s[i][j]);
//			}
//			// System.out.println("");
//		}
//
//		int l;
//		int l2;
//		int c;
//		int c2;
//
//		if (nbTirsRecus > 0) {
//			for (int i = 0; i < tirsRecus.length; i++) {
//				l = tirsRecus[i].getLigne();
//				c = tirsRecus[i].getColonne();
//				s[l][c] = "O ";
//			}
//		}
//
//		String result = "";
//
//		for (int i = 0; i < nbNavires; i++) {
//			boolean vertical = navires[i].estVertical();
//
//			l = navires[i].getDebut().getLigne();
//			c = navires[i].getDebut().getColonne();
//
//			l2 = navires[i].getFin().getLigne();
//			c2 = navires[i].getFin().getColonne();
//			if (vertical) {// si vrai alors fin ligne à deb ligne
//				for (int j = 0; j <= l2 - 2; j++) {
//					System.out.println(l2-2);
//					if (s[l + j][c] == "O ")
//						s[l + j][c] = "X ";
//					else
//						s[l + j][c] = "# ";
//				}
//			} else {
//				for (int j = 0; j <= c2 - c; j++) {
//					if (s[l][c + j] == "O ")
//						s[l][c + j] = "X ";
//					else
//						s[l][c + j] = "# ";
//				}
//			}
//		}
//		for (int i = 0; i < taille + 1; i++) {
//			for (int j = 0; j < taille + 1; j++) {
//				result += s[i][j];
//			}
//			result += "\n";
//		}
//		return result;
//	}
////		}
	public String toString() {
		StringBuffer sb = new StringBuffer("");
		sb.append(initLettres());
		for (int i = 0; i < taille; i++) {
			if (i < 9)
				sb.append(' ');
			sb.append(i + 1);
			sb.append(' ');
			for (int j = 0; j < taille; j++) {
				Coordonnee courant = new Coordonnee(i, j);
				if (estDansTirsRecus(courant)) {
					if (estTouche(courant)) {
						sb.append('x');
					} else {
						sb.append('O');
					}
				} else {
					if (navirePresent(courant))
						sb.append('#');
					else
						sb.append('.');
				}
				sb.append(' ');
			}
			sb.append('\n');
		}
		return sb.toString();
	}

	private String initLettres() {
		// methode outil pour le ToString
		StringBuffer sb = new StringBuffer("   ");
		for (int i = 0; i < taille; i++) {
			sb.append((char) ('A' + i));
			sb.append(' ');
		}
		sb.append('\n');
		return sb.toString();

	}
	
	private boolean navirePresent(Coordonnee c) {
		for (int i = 0; i < navires.length; i++) {
			if (navires[i].contient(c))
				return true;
		}

		return false;

	}

	public int getTaille() {
		return taille;
	}

//	public boolean ajouteNavire(Navire n) {
//		// verification si c'est possible => est-cequ'il dépasse pas la bordure de la
//		// grille
//		// et vérification si'il n'y a pas de chevauchement et du toucher
//		// si c'est bon on ajoute et on renvoie true sinon faux;
//
//		int navireLong = (n.longueur(n.estVertical()));
//
//		if (n.estVertical()) {// si vertical on compare longueur avec ligne debut > taille sinon longueur avec
//								// colonne debut
//			if ((navireLong + n.getDebut().getLigne()) > this.taille) {
//				// System.out.print("le navire ne rentre pas dans la grille");
//				return false;
//			} else {// navire rentre dans la grille, test si chevauche ou pas
//				for (int k = 0; k < nbNavires; k++) {// pour chaque navire sur la grille
//
//					if ((this.navires[k].chevauche(n)) || (this.navires[k].touche(n)) || this.navires[k] == n) {
//						// System.out.print(("le navire chevauche ou touche le navire: ") + k);
//						return false;
//					}
//				}
//				Navire[] copie = new Navire[nbNavires + 1];
//				System.arraycopy(navires, 0, copie, 0, nbNavires);
//				copie[nbNavires] = n;
//
//				nbNavires++;
//				navires = copie;// navires prend la valeur de copie
//				System.out.println("je suis un navire vertical");
//				return true;
//
//			}
//
//		} else {// horizontal
//			if ((navireLong + n.getDebut().getColonne()) > this.taille) {
//				// System.out.print("le navire ne rentre pas dans la grille");
//				return false;
//			} else {// navire rentre dans la grille, test si chevauche ou pas
//				for (int k = 0; k < nbNavires; k++) {// pour chaque navire sur la grille
//					if ((navires[k].chevauche(n)) || (navires[k].touche(n))) {
//						// System.out.print(("le navire chevauche ou touche le navire: ") + k);
//						return false;
//					}
//				}
//				Navire[] copie = new Navire[nbNavires + 1];
//				System.arraycopy(navires, 0, copie, 0, nbNavires);
//				copie[nbNavires] = n;
//				
//				nbNavires++;
//				navires = copie;// navires prend la valeur de copie
//				System.out.println("je suis un navire horizontal");
//				return true;
//
//			}
//
//		}
//	}
	
	public boolean ajouteNavire(Navire n) {
		for(int i=0; i<this.nbNavires; i++) {
			if (this.navires[i].touche(n)) return false;
			if (this.navires[i].chevauche(n)) return false;
		}
		this.navires[this.nbNavires] = n;
		nbNavires++;
		return true;
	}
	public void placementAuto(int[] taillesNavires) {
		// pour chaque navire n, on verifie si ses coordonnees generees automatiquement
		// permettent l'emplacement
		// pour chaque navire ==> on utilise ajouteNavire? ==> navire(coordonnee
		// aleatoire, tailleNavire[i], verticalAleatoire)
		for (int i = 0; i < taillesNavires.length;i++) {
			boolean placement = false;
			while(!placement) {
			Coordonnee aleatoire = new Coordonnee(coordonneAleatoire(), coordonneAleatoire());
			boolean orientation = orientationAleatoire();
			Navire n = new Navire(aleatoire, taillesNavires[i], orientation);
			if (this.ajouteNavire(n)) {
				//this.ajouteNavire(n);
				placement = true;
				//i++;
			}
		}
			}
	}

	private boolean estDansGrille(Coordonnee c) {
		// Retourne true si et seulement si c est dans this.
		if (c == null)
			throw new NullPointerException(" Coordonnee c ne peut pas etre nulle");
		return (c.getLigne() <= this.taille - 1 && c.getColonne() <= this.taille - 1);
	}

	private boolean estDansTirsRecus(Coordonnee c) {
		// Retourne true si et seulement si c correspond à un tir reçu par this.
		if (!this.estDansGrille(c))
			throw new IllegalArgumentException("Les coordonnees sont hors de la grille : " + c);
		else if (this.nbTirsRecus > 0) {
			for (int i = 0; i < this.nbTirsRecus; i++) {
				if (this.tirsRecus[i] == c)
					return true;
			}
		}
		return false;
	}

	private boolean ajouteDansTirsRecus(Coordonnee c) {
		// Ajoute c aux tirs reçus de this si nécessaire. Retourne true si et seulement
		// si this est modifié.
		if (!this.estDansGrille(c))
			throw new IllegalArgumentException("Les coordonnees sont hors de la grille : " + c);
		else if (!this.estDansTirsRecus(c)) {
			tirsRecus[this.nbTirsRecus] = c;
			nbTirsRecus++;
			return true;
		}
		return false;
	}

	public boolean recoitTir(Coordonnee c) {
		// Ajoute c aux tirs reçus de this si nécessaire. Retourne true si et seulement
		// si c ne correspondait pas déjà à un tir reçu et a permis de toucher un navire
		// de this.
		if (!this.estDansGrille(c))
			throw new IllegalArgumentException("Les coordonnees sont hors de la grille : " + c);
		else if (this.estDansGrille(c) && !this.estDansTirsRecus(c)) {
			this.ajouteDansTirsRecus(c);
			for (int i = 0; i < nbNavires; i++) {
				if (navires[i].recoitTir(c)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean estTouche(Coordonnee c) {
		// Retourne true si et seulement si un des navires présents dans this a été
		// touché en c.
		if (!this.estDansGrille(c))
			throw new IllegalArgumentException("Les coordonnees sont hors de la grille : " + c);
		else
			for (int i = 0; i < nbNavires; i++) {
				if (navires[i].estTouche(c)) {
					return true;
				}
			}
		return false;
	}

	public boolean estALEau(Coordonnee c) {
		// Retourne true si et seulement si c correspond à un tir reçu dans l'eau par
		// this.
		if (!this.estDansGrille(c))
			throw new IllegalArgumentException("Les coordonnees sont hors de la grille : " + c);
		else if (!this.estTouche(c)) {
			return true;
		}
		return false;
	}

	public boolean estCoule(Coordonnee c) {
		// Retourne true si et seulement si un des navires présents dans this a été
		// touché en c et est coulé.
		if (!this.estDansGrille(c))
			throw new IllegalArgumentException("Les coordonnees sont hors de la grille : " + c);
		else
			for (int i = 0; i < nbNavires; i++) {
				if (navires[i].estTouche(c) && navires[i].estCoule()) {
					return true;
				}
			}
		return false;
	}

	public boolean perdu() {
		// Retourne true si et seulement si tous les navires de this ont été coulés.
		int navCoules = 0;
		for (int i = 0; i < nbNavires; i++) {
			if (navires[i].estCoule()) {
				navCoules++;
			}
		}
		if (navCoules == this.nbNavires) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] tailles = {5,3,7};
		GrilleNavale g = new GrilleNavale(10, tailles);
		System.out.println(g);
	}
}
