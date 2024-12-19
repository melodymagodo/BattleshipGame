package batailleNavale;

public class testJoueur extends Joueur {

    public testJoueur(int tailleGrille, String nom) {
        super(tailleGrille, nom);
    }

    // Implementing the abstract methods
    @Override
    public Coordonnee choixAttaque() {
        return new Coordonnee(1, 1); // Return a dummy coordinate for testing
    }

    @Override
    public int defendre(Coordonnee c) {
        return TOUCHE; // Simulate a hit for testing purposes
    }

    @Override
    protected void retourAttaque(Coordonnee c, int etat) {
        // Simulate attack feedback for testing
        System.out.println("Attaque en " + c + " - Résultat: " + etat);
    }

    @Override
    protected void retourDefense(Coordonnee c, int etat) {
        // Simulate defense feedback for testing
        System.out.println("Défense en " + c + " - Résultat: " + etat);
    }
    public static void main(String[] args) {
        // Create two test players
        Joueur joueur1 = new testJoueur(10, "Joueur 1");
        Joueur joueur2 = new testJoueur(10, "Joueur 2");

        // Start a game between the two players
        joueur1.jouerAvec(joueur2);
}}
