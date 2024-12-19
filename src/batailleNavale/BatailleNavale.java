package batailleNavale;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class BatailleNavale {

    private JFrame frmBatailleNavale;
    private JTextField fieldSize;
    private JTextField NJoueur1;
    private JTextField NJoueur2;
    private final ButtonGroup buttonGroup1 = new ButtonGroup();
    private final ButtonGroup buttonGroup2 = new ButtonGroup();

    private Joueur j1, j2;

    private int tailleGrille;

    private JRadioButton JoueurG1;
    private JRadioButton JoueurText1;
    private JRadioButton JoueurG2;
    private JRadioButton JoueurText2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BatailleNavale window = new BatailleNavale();
                    window.frmBatailleNavale.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void demarrerPartie() {
        new Thread() {
            public void run() {
                j1.jouerAvec(j2);
            }
        }.start();
    }
    


    /**
     * Create the application.
     */
    public BatailleNavale() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmBatailleNavale = new JFrame();
        frmBatailleNavale.setTitle("Bataille Navale");
        frmBatailleNavale.setBounds(100, 100, 653, 478);
        frmBatailleNavale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelhaut = new JPanel();
        frmBatailleNavale.getContentPane().add(panelhaut, BorderLayout.NORTH);
        panelhaut.setLayout(new BoxLayout(panelhaut, BoxLayout.X_AXIS));

        JLabel lblNewLabel = new JLabel("Taille de Grille :");
        panelhaut.add(lblNewLabel);

        JTextField fieldSize = new JTextField();
        panelhaut.add(fieldSize);
        fieldSize.setColumns(10);

         JoueurG1 = new JRadioButton("Joueur Graphique");
        JRadioButton JoueurText1 = new JRadioButton("Joueur Texte");
         JoueurG2 = new JRadioButton("Joueur Graphique");
        JRadioButton JoueurText2 = new JRadioButton("Joueur Texte");

        JPanel panelbas = new JPanel();
        frmBatailleNavale.getContentPane().add(panelbas, BorderLayout.SOUTH);

        JButton btnNewButton = new JButton("Lancer la Partie");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // lancer la partie

                // récupération des variables

                int taille = Integer.parseInt(fieldSize.getText());
                String NJ1 = NJoueur1.getText();
                String NJ2 = NJoueur2.getText();
                String j1Type = (JoueurG1.isSelected()) ? "Graphique" : ((JoueurText1.isSelected()) ? "Texte" : "Auto");
                String j2Type = (JoueurG2.isSelected()) ? "Graphique" : ((JoueurText2.isSelected()) ? "Texte" : "Auto");

                // création des instances

                int[] taillesNavires = { 2, 3, 4, 5 };

                
                if (JoueurG1.isSelected()) {
                	System.out.println("Salut!");
                	FenetreJoueur f1 = new FenetreJoueur(NJoueur1.getText(), 10);
                    f1.pack();
                    f1.setVisible(true);
                    f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    j1 = new JoueurGraphique(f1.getGrilleDefense(), f1.getGrilleTirs(), NJ1);
                } else if (j1Type=="Texte") {
                    GrilleNavale g = new GrilleNavale(taille, taillesNavires);
                    j1 = new JoueurTexte(g, NJ1);
                } else {
                    GrilleNavale g = new GrilleNavale(taille, taillesNavires);
                    j1 = new JoueurAuto(g, NJ1);
                }

               
                if (JoueurG2.isSelected()) {
                	 FenetreJoueur f2 = new FenetreJoueur(NJoueur2.getText(), 10);
                     f2.pack();
                     f2.setVisible(true);
                     f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    j2 = new JoueurGraphique(f2.getGrilleDefense(), f2.getGrilleTirs(), NJ2);
                } else if (j2Type=="Texte") {
                    GrilleNavale g = new GrilleNavale(taille, taillesNavires);
                    j2 = new JoueurTexte(g, NJ2);
                } else {
                    GrilleNavale g = new GrilleNavale(taille, taillesNavires);
                    j2 = new JoueurAuto(g, NJ2);
                }

                demarrerPartie();
            }
		});
				//------------------------------------
				
				
				
		
		panelbas.add(btnNewButton);
		
		JPanel panelCentre = new JPanel();
		frmBatailleNavale.getContentPane().add(panelCentre, BorderLayout.CENTER);
		panelCentre.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panelCenHaute = new JPanel();
		panelCenHaute.setBorder(new TitledBorder(null, "Joueur 1", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCentre.add(panelCenHaute);
		SpringLayout sl_panelCenHaute = new SpringLayout();
		panelCenHaute.setLayout(sl_panelCenHaute);
		
		JLabel NomJoueur1 = new JLabel("Nom :");
		sl_panelCenHaute.putConstraint(SpringLayout.NORTH, NomJoueur1, 8, SpringLayout.NORTH, panelCenHaute);
		sl_panelCenHaute.putConstraint(SpringLayout.WEST, NomJoueur1, 10, SpringLayout.WEST, panelCenHaute);
		NomJoueur1.setHorizontalAlignment(SwingConstants.LEFT);
		panelCenHaute.add(NomJoueur1);
		
		NJoueur1 = new JTextField();
		sl_panelCenHaute.putConstraint(SpringLayout.NORTH, NJoueur1, -3, SpringLayout.NORTH, NomJoueur1);
		sl_panelCenHaute.putConstraint(SpringLayout.WEST, NJoueur1, 6, SpringLayout.EAST, NomJoueur1);
		panelCenHaute.add(NJoueur1);
		NJoueur1.setColumns(30);
		
		 JoueurG1 = new JRadioButton("Joueur Graphique");
		JoueurG1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonGroup1.add(JoueurG1);
		sl_panelCenHaute.putConstraint(SpringLayout.NORTH, JoueurG1, 12, SpringLayout.SOUTH, NJoueur1);
		sl_panelCenHaute.putConstraint(SpringLayout.WEST, JoueurG1, 0, SpringLayout.WEST, NomJoueur1);
		panelCenHaute.add(JoueurG1);
		
		JRadioButton j1Text = new JRadioButton("Joueur Texte");
		j1Text.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonGroup1.add(j1Text);
		sl_panelCenHaute.putConstraint(SpringLayout.NORTH, j1Text, 6, SpringLayout.SOUTH, JoueurG1);
		sl_panelCenHaute.putConstraint(SpringLayout.WEST, j1Text, 0, SpringLayout.WEST, NomJoueur1);
		panelCenHaute.add(j1Text);
		
		JRadioButton J1auto = new JRadioButton("Joueur Auto");
		J1auto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//-----------------------------------------------
			}
		});
		buttonGroup1.add(J1auto);
		sl_panelCenHaute.putConstraint(SpringLayout.NORTH, J1auto, 6, SpringLayout.SOUTH, j1Text);
		sl_panelCenHaute.putConstraint(SpringLayout.WEST, J1auto, 0, SpringLayout.WEST, NomJoueur1);
		panelCenHaute.add(J1auto);
		
		JPanel panelCenBas = new JPanel();
		panelCenBas.setBorder(new TitledBorder(null, "joueur 2", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCentre.add(panelCenBas);
		SpringLayout sl_panelCenBas = new SpringLayout();
		panelCenBas.setLayout(sl_panelCenBas);
		
		JLabel NomJoueur2 = new JLabel("Nom :");
		sl_panelCenBas.putConstraint(SpringLayout.NORTH, NomJoueur2, 8, SpringLayout.NORTH, panelCenBas);
		sl_panelCenBas.putConstraint(SpringLayout.WEST, NomJoueur2, 10, SpringLayout.WEST, panelCenBas);
		panelCenBas.add(NomJoueur2);
		
		NJoueur2 = new JTextField();
		sl_panelCenBas.putConstraint(SpringLayout.NORTH, NJoueur2, -3, SpringLayout.NORTH, NomJoueur2);
		sl_panelCenBas.putConstraint(SpringLayout.WEST, NJoueur2, 6, SpringLayout.EAST, NomJoueur2);
		panelCenBas.add(NJoueur2);
		NJoueur2.setColumns(30);
		
		JoueurG2 = new JRadioButton("Joueur Graphique");
		JoueurG2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonGroup2.add(JoueurG2);
		sl_panelCenBas.putConstraint(SpringLayout.NORTH, JoueurG2, 16, SpringLayout.SOUTH, NJoueur2);
		sl_panelCenBas.putConstraint(SpringLayout.WEST, JoueurG2, 0, SpringLayout.WEST, NomJoueur2);
		panelCenBas.add(JoueurG2);
		
		JRadioButton j2Text = new JRadioButton("Joueur Texte");
		j2Text.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonGroup2.add(j2Text);
		sl_panelCenBas.putConstraint(SpringLayout.NORTH, j2Text, 6, SpringLayout.SOUTH, JoueurG2);
		sl_panelCenBas.putConstraint(SpringLayout.WEST, j2Text, 0, SpringLayout.WEST, NomJoueur2);
		panelCenBas.add(j2Text);
		
		JRadioButton j2Auto = new JRadioButton("Joueur Auto");
		j2Auto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//--------------------------------------------------
			}
		});
		buttonGroup2.add(j2Auto);
		sl_panelCenBas.putConstraint(SpringLayout.NORTH, j2Auto, 6, SpringLayout.SOUTH, j2Text);
		sl_panelCenBas.putConstraint(SpringLayout.WEST, j2Auto, 0, SpringLayout.WEST, NomJoueur2);
		panelCenBas.add(j2Auto);
		
		//----------------
		
		NJoueur1.setText("Joueur 1");
		NJoueur2.setText("Joueur 2");
		fieldSize.setText("10");
		J1auto.setSelected(true);
		j2Auto.setSelected(true);
	}
	
}
