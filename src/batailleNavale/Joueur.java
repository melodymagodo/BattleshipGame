package batailleNavale;

public abstract class Joueur {
	public final static int TOUCHE = 1;
	public final static int COULE = 2;
	public final static int A_L_EAU = 3;
	public final static int GAMEOVER = 4;
	private Joueur adversaire;
	private int tailleGrille;
	private String nom;

	
	//constructors with gridsize and name 
	public Joueur(int tailleGrille, String nom) {

		this.tailleGrille = tailleGrille;
		this.nom = nom;
	}

	//constructor with gridsize only and no provided name 
	public Joueur(int tailleGrille) {
		this.tailleGrille = tailleGrille;
		this.nom = "joueur1";// name is not passed in the param so we provide a default name

	}

	public int getTailleGrille() {//getter for the gridsize
		return this.tailleGrille;
	}

	public String getNom() {//getter for the name 
		return this.nom;

	}

     public void jouerAvec(Joueur j) {
       if (j!= null) {//we check the validity of the opponent
    	   this.adversaire=j;
    	   j.adversaire=this;
    	   
    	   System.out.println(j.nom + "is playing with" +this.nom);
    	   
      deroulementJeu(this, j);
     }else {
	           throw new IllegalArgumentException("Joueur cannot be null");
     }
}
     
	private static void deroulementJeu(Joueur attaquant, Joueur defenseur) {
		int res = 0;
		while (res != GAMEOVER) {
			Coordonnee c = attaquant.choixAttaque();
			res = defenseur.defendre(c);
			attaquant.retourAttaque(c, res);
			defenseur.retourDefense(c, res);
			Joueur x = attaquant;
			attaquant = defenseur;
			defenseur = x;
		}
	}


protected abstract void retourAttaque(Coordonnee c, int etat);

protected abstract void retourDefense(Coordonnee c, int etat);

public abstract Coordonnee choixAttaque();


 public abstract int defendre(Coordonnee c); }
 
