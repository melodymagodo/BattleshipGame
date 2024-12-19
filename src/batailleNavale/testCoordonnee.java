package batailleNavale;

public class testCoordonnee {
    public static void main(String[] args) {
        // Test constructor with Java indices (0-based)
        //Coordonnee coord1 = new Coordonnee(0, 0); // Should correspond to "A1"
        Coordonnee coord2 = new Coordonnee(25, 25); // Should correspond to "Z26"
        
        // Test constructor with battle grid notation (A1, Z26)
        //Coordonnee coord3 = new Coordonnee("A1"); // Should correspond to (0, 0)
        //Coordonnee coord4 = new Coordonnee("Z26"); // Should correspond to (25, 25)
        
        // Test toString() method
        //System.out.println("coord1 (0, 0) as string: " + coord1.toString()); // Expected "A1"
        //System.out.println("coord2 (25, 25) as string: " + coord2.toString()); // Expected "Z26"
        //////System.out.println("coord3 (A1) as string: " + coord3.toString()); // Expected "A1"
        //System.out.println("coord4 (Z26) as string: " + coord4.toString()); // Expected "Z26"
        
        // Test equals() method
        //System.out.println("coord1 equals coord3? " + coord1.equals(coord3)); // Expected true
        //System.out.println("coord2 equals coord4? " + coord2.equals(coord4)); // Expected true
        //System.out.println("coord1 equals coord2? " + coord1.equals(coord2)); // Expected false

        // Test voisine() method
        //Coordonnee coord5 = new Coordonnee(0, 1); // "B1"
       // Coordonnee coord6 = new Coordonnee(1, 0); // "A2"
       // System.out.println("coord1 voisine coord5? " + coord1.voisine(coord5)); // Expected true
       // System.out.println("coord1 voisine coord6? " + coord1.voisine(coord6)); // Expected true
       // System.out.println("coord5 voisine coord6? " + coord5.voisine(coord6)); // Expected true
    }
}

