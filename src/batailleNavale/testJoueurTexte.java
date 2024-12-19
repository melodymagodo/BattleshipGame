package batailleNavale;

public class testJoueurTexte {

    public static void main(String[] args) {
    	// Créez une grille pour le test
        GrilleNavale grille = new GrilleNavale(10, new int[]{5, 4, 3, 3, 2});
       // GrilleNavale grille = new GrilleNavale(10, 1);
     	 grille.ajouteNavire(new Navire(new Coordonnee("A1"),1,false));
     	 
     	 System.out.println(grille);
     	 boolean res = grille.recoitTir(new Coordonnee("A1"));
    	 System.out.println(res);
        
}}
