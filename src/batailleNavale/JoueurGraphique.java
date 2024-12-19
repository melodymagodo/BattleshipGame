package batailleNavale;


import java.awt.Color;
import javax.swing.JOptionPane;

public class JoueurGraphique extends JoueurAvecGrille {
	
    private GrilleGraphique grilleTirs;
    private GrilleNavaleGraphique grilleDefense;
    

    
   
    /*permet d'obtenir un joueur graphique de nom nom qui effectue des tirs en cliquant sur
    grilleTirs et dont la flotte est placée sur grilleDefense.*/
    public JoueurGraphique(GrilleNavaleGraphique grilleDefense, GrilleGraphique grilleTirs, String nom) {
        super(grilleDefense, nom);
        this.grilleDefense=grilleDefense;
        this.grilleTirs = grilleTirs;
    }

    /* permet d'obtenir un joueur graphique qui effectue des tirs en cliquant sur grilleTirs et
dont la flotte est placée sur grilleDefense.
     */
    
    public JoueurGraphique(GrilleNavaleGraphique grilleDefense, GrilleGraphique grilleTirs) {
        super(grilleDefense);
        this.grilleTirs = grilleTirs;
    }
    
    /* Consiste à récupérer la coordonnée choisie depuis grilleTirs. */
    public Coordonnee choixAttaque() {
        Coordonnee c = grilleTirs.getCoordonneeSelectionnee();
        
        //boolean resultatTir = grilleDefense.recoitTir(c);
        return c;
    }
    
    /* Affichage d'un JOptionPane lorsque le tir a touché ou coulé un navire, ou lorsque la partie
est perdue.
     */
    protected void retourDefense(Coordonnee c, int etat) {
        Color couleur = etat == A_L_EAU ? Color.BLUE : Color.RED;
        grilleDefense.getGrilleGraphique().colorie(c, couleur);
        switch(etat) {
            case TOUCHE:
                JOptionPane.showMessageDialog(grilleDefense.getGrilleGraphique(), "Le tir a touché un navire en " + c);
                break;
            case COULE:
                JOptionPane.showMessageDialog(grilleDefense.getGrilleGraphique(), "Le tir a coulé un navire en " + c);
                break;
            case GAMEOVER:
                JOptionPane.showMessageDialog(grilleDefense.getGrilleGraphique(), "Vous avez perdu!!!");
                break;
        }
    }
    
    
    protected void retourAttaque(Coordonnee c, int etat) {
        Color couleur = etat == A_L_EAU ? Color.BLUE : Color.RED;
        grilleTirs.colorie(c, couleur);
        switch (etat) {
            case TOUCHE:
                JOptionPane.showMessageDialog(grilleTirs, "Vous avez touché un navire en " + c);
                break;
            case COULE:
                JOptionPane.showMessageDialog(grilleTirs, "Vous avez coulé un navire en " + c);
                break;
            case GAMEOVER:
                JOptionPane.showMessageDialog(grilleTirs, "Vous avez gagné!!!");
                break;
        }
    }
}
