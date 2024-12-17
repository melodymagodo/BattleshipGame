package batailleNavale;


public class GrilleNavale {

	private Navire[] navires;
	private int nbNavires;
	private int taille;
	private Coordonnee[] tirsRecus;
	private int nbTirsRecus;
	
//_________________________Constructeur 1 ______________________________________________________________
		
		/* permet d'obtenir une grille navale de taille taille dans laquelle ont été
		 * placés automatiquement taillesNavires.length navires dont les tailles sont données dans taillesNavires.
		 */

	public GrilleNavale(int taille, int[] taillesNavires) {
		if (taille < 1 || taille > 26)
			throw new IllegalArgumentException("taille de la grille incorrecte");
		this.taille = taille;
		this.nbNavires = 0;
		this.navires = new Navire[taillesNavires.length];
		this.tirsRecus = new Coordonnee[taille * taille];
		this.nbTirsRecus = 0;
		placementAuto(taillesNavires);
	}
//____________________________Constructeur 2___________________________________________________
	 
	/* permet d'obtenir une grille navale vide de taille taille pouvant accueillir jusqu'à nbNavires.*/
	 
	public GrilleNavale(int taille, int nbNavires) {
		
		if (taille < 1 || taille > 26)
			throw new IllegalArgumentException("taille de la grille incorrecte");

		this.taille = taille;
		this.nbNavires = 0; 
		this.navires = new Navire[nbNavires];
		this.tirsRecus = new Coordonnee[taille * taille];
		this.nbTirsRecus = 0;
	}
	
//_________________________________toString_________________________________________
	public String toString() {
		StringBuffer grille = new StringBuffer();
		grille.append("  ");
		for (int i = 1; i <= taille; i++) {
			grille.append((char) ('A' + i - 1)).append(" ");
		}
		grille.append("\n");

		for (int i = 1; i <= taille; i++) {
			if (i<10)
				grille.append(i).append("  ");
			else 
				grille.append(i).append(" ");
			for (int j = 1; j <= taille; j++) {
				Coordonnee c = new Coordonnee(i-1, j-1);
				
				if (estTouche(c)) {
					grille.append("X ");
				}else if (contientNavire(c)) {// normalement c'est la méthode contient de la classe navir
					grille.append("# ");
				} else if (estALEau(c)) {
					grille.append("O "); 
				} else {
					grille.append(". ");
				}
			}
			grille.append("\n");
		}
		return grille.toString();
	}
//______________________________________________________________________________________________
	public int getTaille() {
		/*Accesseur en lecture.*/
		return taille;
	}
//___________________________________________Ajouter un Navire____________________________________________________	
	
	 /* 
	  * Retourne true après avoir ajouté n à this si cet ajout est possible. L'ajout
	  * est impossible si n touche ou chevauche un navire déjà présent dans this, ou
	  * encore si n dépasse les limites de this.
	  */

	public boolean ajouteNavire(Navire n) {
        if (!estDansGrille(n.getDebut()) || !estDansGrille(n.getFin()) || !(nbNavires < navires.length))
            return false;
        for (Navire navire : navires) {
            if (navire != null && (navire.chevauche(n) || navire.touche(n)))
                return false;
        }navires[nbNavires++] = n;
        return true;
    }

//----------------------------------------------- placementAuto ------------------------------------------------------------------------------------------------------------------------------------
// Place automatiquement et aléatoirement taillesNavires.length navires dont les tailles sont données dans taillesNavire.

	public void placementAuto(int[] taillesNavires) {
		int i = 0;
		while(i< taillesNavires.length) {
			Navire newNavire ;
			int ligne = (int) (taille * Math.random());
			int colonne = (int) (taille * Math.random());
			Coordonnee point = new Coordonnee(ligne,colonne);
			boolean estvertical = Math.random()<0.5;
			newNavire = new Navire(point, taillesNavires[i], estvertical);
			if (ajouteNavire(newNavire))
				i++;
		}
	}

	public void placementAuto2(int[] taillesNavires) {
		int i = 0;
		while(navires[taillesNavires.length -1]==null) {
			int ligne = (int) (taille * Math.random());
			int colonne = (int) (taille * Math.random());
			Coordonnee point = new Coordonnee(ligne,colonne);
			boolean estvertical = Math.random()<0.5;
			Navire newNavire = new Navire(point, taillesNavires[i], estvertical);
			boolean chevauchement = false;
			
			for (int j=0 ; j<i ; j++) {
				if (navires[j].chevauche(newNavire)) {
					chevauchement = true;
				}
			} if (newNavire.getFin().getLigne() <= taille -1 && newNavire.getFin().getColonne() <= taille - 1 && !chevauchement) {
				navires[i] = newNavire;
				i++;
			}
		}
	}


//________________________________________estDansGrille_____________________________________________
	public boolean estDansGrille(Coordonnee c) {
	/*Retourne true si et seulement si c est dans this.*/
		return c.getLigne() >= 0 && c.getLigne() < this.taille  && c.getColonne() >= 0 && c.getColonne() < this.taille ; 
	}


 
//____________________________________________contientNavire_________________________________________________________________
	    
	    public boolean contientNavire(Coordonnee c) {// j'ai ajouté cette méthode pour pouvoir écrire la méthode string tosting
	        for (Navire n : navires) {
	            if (n != null && n.contient(c))
	                return true;
	        } return false;
	    }

//----------------------------------------------- estDansTirsRecus ------------------------------------------------------------------------------------------------------------------------------------
// Retourne true si et seulement si c correspond à un tir reçu par this.

	 public boolean estDansTirsRecus(Coordonnee c) {
			for (int i = 0; i < tirsRecus.length; i++) {
				if (tirsRecus[i] != null && tirsRecus[i].equals(c))
					return true;
			}return false;
		}
	    
//________________________________________ajouteDansTirsRecus____________________________________________________________
	    
		 /* Ajoute c aux tirs reçus de this si nécessaire. Retourne true si 
		  * et seulement si this est modifié.
		  */
		 
	    private boolean ajouteDansTirsRecus(Coordonnee c) {
	    	if (!estDansTirsRecus(c)&& (estDansGrille(c) && (nbTirsRecus < tirsRecus.length))) {
	    		tirsRecus[nbTirsRecus++]=c;
	    		return true; 
	    }return false; 
	    }
 //_____________________________________recoitTir_____________________________________________________________	    
	   
	    public boolean recoitTir2(Coordonnee c) {
	        /*
	         * Ajoute c aux tirs reçus de this si nécessaire. Retourne true si et seulement 
	         * si c ne correspondait pas déjà à un tir reçu et a permis de toucher un 
	         * navire de this.
	         */
	            if (!estDansGrille(c) || !ajouteDansTirsRecus(c)) 
	                return false;
	            ajouteDansTirsRecus(c);
	            for (Navire navire : navires) {
	                if (navire.estTouche(c)) 
	                    return true;
	            }return false;
	       }
	    
	    public boolean recoitTir(Coordonnee c) {
	        /*
	    	 * Ajoute c aux tirs reçus de this si nécessaire. Retourne true si et seulement 
	    	 * si c ne correspondait pas déjà à un tir reçu et a permis de toucher un 
	    	 * navire de this.
	    	 */
	            if (!estDansGrille(c) || estDansTirsRecus(c)) 
	                return false;
	            ajouteDansTirsRecus(c);
	            for (Navire navire : navires) {
	            	if (navire!=null &&navire.recoitTir(c)) 
	                    return true;
	            }
	            return false;
	       }

//__________________________________estTouche__________________________________________________________________
	    /* Retourne true si et seulement si un des navires présents dans this a été touché en c. */
	    public boolean estTouche(Coordonnee c) {
	        for (Navire navire : navires) {
	            if (navire != null && navire.estTouche(c)) 
	                return true;
	        } return false; 
	    }
//__________________________________estALEau____________________________________________________________________	    	   
	    
		 /* Retourne true si et seulement si c correspond à un tir reçu dans l'eau par this.*/
		 
	    public boolean estALEau(Coordonnee c) {
	        return estDansTirsRecus(c) && !(estTouche(c)); 
	    }
//__________________________________estCoule____________________________________________________________________	    
	    
		 /* Retourne true si et seulement si un des navires présents dans this a été
		 * touché en c et est coulé */
	   
	    public boolean estCoule2(Coordonnee c) {
	    	
	    	for(int i=0; i<nbNavires; i++) {
	    		
	    		if (this.navires[i].estTouche(c) && this.navires[i].estCoule()) {
	    	return true; 
	    		}
	    	}
	    		return false; 
	    }
	    
	    public boolean estCoule(Coordonnee c) {
	        for (Navire n : navires) {
	            if (n!=null&&n.contient(c) && n.estCoule()) {
	                return true;
	            }
	        }

	        return false;
	    }
	    
	    
//___________________________________perdu_____________________________________________________________________
	    /* Retourne true si et seulement si tous les navires de this ont été coulés. */
	    
	    public boolean perdu() {
	    	
	    	for (int i = 0; i < this.nbNavires; i++)
	    		if (!(this.navires[i].estCoule()))
	    			return false;
	    	return true;
	    	}

	    
		public static void main(String[] args) {
	        // Assumez que grilleNavale est votre objet GrilleNavale
	        GrilleNavale grilleNavale1 = new GrilleNavale(12, 3);
	  
	        
	     // Afficher la grille dans la console
	        System.out.println(grilleNavale1.toString());

	        
	        int tailleGrille = 12;
	        int[] taillesNavires = {1, 4, 3, 5};
	        
	        // Assumez que grilleNavale est votre objet GrilleNavale
	       GrilleNavale grilleNavale2 = new GrilleNavale(tailleGrille, taillesNavires);
	  
	        
	     // Afficher la grille dans la console
	       System.out.println(grilleNavale2.toString());
	        
	    }
	    
}
	
