/*
 * Classe Partie, permet le déroulement complet d'un partie
 */

package super_puissance_4_chabaud_duchene;

import java.util.Random;
import java.util.Scanner;

/**
 * @author DUCHENE - CHABAUD
 */

public class Partie {
    Joueur[] ListeJoueurs = new Joueur[2]; 
    Joueur joueurCourant;
    Jeton[] ListeJetons = new Jeton[42];
    Grille grille = new Grille();
        
    public void attribuerCouleursAuxJoueurs() {
        Random r = new Random();
        
        int num = r.nextInt(2); // nombre aléatoire 0 ou 1
        ListeJoueurs[num].affecterCouleur("rouge");
        ListeJoueurs[(num+1)%2].affecterCouleur("jaune"); // (num+1)%2 permet de transformer 1 en 0 / 0 en 1
    }
    public void initialiserPartie() {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        
        for (int i = 1; i<=2;i++) { // Attribution des noms aux joueurs
            System.out.println("Quel est le nom du joueur " + i);
            String nom_joueur = sc.nextLine();
            ListeJoueurs[i-1] = new Joueur(nom_joueur);
        }
        this.attribuerCouleursAuxJoueurs();
        
        grille.viderGrille();
        for (int i = 0; i<3;i++) { // placement 3 desintegrateurs
            boolean valide = false;
            while (valide == false) {
                valide = grille.placerDesintegrateur(r.nextInt(6),r.nextInt(7));
            }
        }
        int desintegrateurNoir1 = r.nextInt(5); // création 2 désintegrateurs placés sur des trou noirs
        int desintegrateurNoir2;
        do {
            desintegrateurNoir2 = r.nextInt(5);
        } while (desintegrateurNoir2 == desintegrateurNoir1);
        for (int i = 0; i<5;i++) { // placement 5 trou noirs
            boolean valide = false;
            while (valide == false) {
                int ligne = r.nextInt(6);
                int colonne = r.nextInt(7);
                valide = grille.placerTrouNoir(ligne,colonne);
                if (valide) {
                    if (i == desintegrateurNoir1 || i == desintegrateurNoir2) { // placement des 2 désintégrateurs associés
                        grille.placerDesintegrateur(ligne,colonne);
                    }
                }                
            }
        }
        /*for (int i = 0; i<=41;i++) {
            if (i < 21) { // attribution de ses 21 jetons au joueur 1
                joueurCourant = ListeJoueurs[0];
                String couleur = joueurCourant.couleur;
                ListeJetons[i] = new Jeton(couleur);
                joueurCourant.ajouterJeton(ListeJetons[i]);                
            } else { // attribution de ses 21 jetons au joueur 2
                joueurCourant = ListeJoueurs[1];
                String couleur = joueurCourant.couleur;
                ListeJetons[i] = new Jeton(couleur);
                joueurCourant.ajouterJeton(ListeJetons[i]);                
            }
            
        }*/
        for (int i = 0; i < 21;i++) { // en remplacement des lignes du dessus
            ListeJoueurs[0].ajouterJeton(new Jeton(joueurCourant.couleur));
            ListeJoueurs[1].ajouterJeton(new Jeton(joueurCourant.couleur));                        
        }
    }
    public void debuterPartie() {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        boolean victoire = false;
        
        this.initialiserPartie();
        grille.afficherGrilleSurConsole();
        joueurCourant = ListeJoueurs[r.nextInt(2)];
        System.out.println(joueurCourant.nom + " commence");
        
        do { // tour de jeu
            int colonne;
            int ligne;
            int test;
            int choix;
            
            if (joueurCourant.nombreDesintegrateur > 0) {
                do {
                    System.out.println(joueurCourant.nom + ", voulez vous jouer ou récupérer un jeton ?\n1: Jouer\n2:Récupérer\n3: Utiliser désintégrateur");
                    choix = sc.nextInt();
                } while (choix < 1 || choix > 3);
            } else{
                do {
                    System.out.println(joueurCourant.nom + ", voulez vous jouer ou récupérer un jeton ?\n1: Jouer\n2:Récupérer");
                    choix = sc.nextInt();
                } while (choix < 1 || choix > 2);
            }
                        
            if (choix == 1) { // jouer un jeton
                test = 0;
                System.out.println("Choisi une colonne"); // choix colonne et vérirification de la validité du numéro
                do { // demander tant que le joueur choisi une colonne pleine
                    do { // demander tant que le joueur choisi une colonne invalide
                        if (test != 0) {
                            System.out.println("Le numéro de colonne choisi est incorect");
                        }
                        colonne = sc.nextInt()-1;
                        test++;
                    } while (colonne < 0 || colonne > 6);
                    ligne = grille.ajouterJetonDansColonne(joueurCourant.ListeJetons[joueurCourant.nombreJetonsRestant-1], colonne);
                } while (ligne == -1);
                joueurCourant.nombreJetonsRestant--;

                if (grille.cellule[ligne][colonne].presenceTrouNoir()) { // en cas de présence de trou noir supprime le jeton et le trou noir
                    grille.supprimerJeton(ligne, colonne);
                    grille.cellule[ligne][colonne].activerTrouNoir();
                }
                if (grille.cellule[ligne][colonne].presenceDesintegrateur()) { // en cas de présence de desintégrateur supprime le jeton et le désintégrateur
                    grille.cellule[ligne][colonne].recupererDesintegrateur();
                    joueurCourant.nombreDesintegrateur++;
                }
                victoire = grille.etreGagnantePourJoueur(joueurCourant); // test victoire
            } else if (choix == 2) { // récupérer un jeton
                test = 0;
                System.out.println("Choisi une colonne"); // choix colonne et vérirification de la validité du numéro
                do {    
                    do { // demander tant que le joueur choisi une colonne vide
                        do { // demander tant que le joueur choisi une colonne invalide
                            if (test != 0) {
                                System.out.println("Le numéro de colonne choisi est incorect");
                            }
                            colonne = sc.nextInt()-1;
                            test++;
                        } while (colonne < 0 || colonne > 6);
                    } while (grille.celluleOccupee(0,colonne) == false);
                    test = 0;
                    System.out.println("Choisi une ligne"); // choix ligne et vérirification de la validité du numéro
                    do { // demander tant que le joueur choisi une ligne vide
                        do { // demander tant que le joueur choisi une ligne invalide
                            if (test != 0) {
                                System.out.println("Le numéro de ligne choisi est incorect");
                            }
                            ligne = sc.nextInt()-1;
                            test++;
                        } while (ligne < 0 || ligne > 5);
                    } while (grille.celluleOccupee(ligne,colonne) == false);
                } while (!grille.lireCouleurDuJeton(ligne, colonne).equals(joueurCourant.couleur));
                joueurCourant.nombreJetonsRestant++;
                grille.recupererJeton(ligne, colonne); // pas de récupération retour ?
                grille.tasserGrille(ligne, colonne);
                int autreJoueur;
                if (joueurCourant == ListeJoueurs[0]) {
                    autreJoueur = 1;
                } else {
                    autreJoueur = 0;
                }
                victoire = grille.etreGagnantePourJoueur(ListeJoueurs[autreJoueur]); // test victoire autre joueur
                if (victoire == false) { // si autre joueur ne gagne pas (règle le soucis de faute de jeu)
                    victoire = grille.etreGagnantePourJoueur(joueurCourant); // test victoire
                } else {
                    joueurCourant = ListeJoueurs[autreJoueur];
                }
            } else { // utiliser un désintégrateur
                test = 0;
                System.out.println("Choisi une colonne"); // choix colonne et vérirification de la validité du numéro
                do {    
                    do { // demander tant que le joueur choisi une colonne vide
                        do { // demander tant que le joueur choisi une colonne invalide
                            if (test != 0) {
                                System.out.println("Le numéro de colonne choisi est incorect");
                            }
                            colonne = sc.nextInt()-1;
                            test++;
                        } while (colonne < 0 || colonne > 6);
                    } while (grille.celluleOccupee(0,colonne) == false);
                    test = 0;
                    System.out.println("Choisi une ligne"); // choix ligne et vérirification de la validité du numéro
                    do { // demander tant que le joueur choisi une ligne vide
                        do { // demander tant que le joueur choisi une ligne invalide
                            if (test != 0) {
                                System.out.println("Le numéro de ligne choisi est incorect");
                            }
                            ligne = sc.nextInt()-1;
                            test++;
                        } while (ligne < 0 || ligne > 5);
                    } while (grille.celluleOccupee(ligne,colonne) == false);
                } while (grille.lireCouleurDuJeton(ligne, colonne).equals(joueurCourant.couleur));
                grille.recupererJeton(ligne, colonne); // pas de récupération retour ?
                grille.tasserGrille(ligne, colonne);
                int autreJoueur;
                if (joueurCourant == ListeJoueurs[0]) {
                    autreJoueur = 1;
                } else {
                    autreJoueur = 0;
                }
                victoire = grille.etreGagnantePourJoueur(ListeJoueurs[autreJoueur]); // test victoire autre joueur
                if (victoire == false) { // si autre joueur ne gagne pas (règle le soucis de faute de jeu)
                    victoire = grille.etreGagnantePourJoueur(joueurCourant); // test victoire
                } else {
                    joueurCourant = ListeJoueurs[autreJoueur];
                }
            }
            
            
            grille.afficherGrilleSurConsole(); //affichage fin de tour
            
            
            if (grille.etreRemplie() && victoire == false) { // test égalitée
                victoire = true;
                joueurCourant = null;
            }
            if (ListeJoueurs[0].nombreJetonsRestant == 0 && ListeJoueurs[1].nombreJetonsRestant == 0 && victoire == false) {
                victoire = true;
                joueurCourant = null;
            }
            
            if (joueurCourant == ListeJoueurs[0] && victoire == false) { // passage tour joueur suivant sauf cas victoire ou manque de jeton pour le joueur suivant
                joueurCourant = ListeJoueurs[1];
                if (joueurCourant.nombreJetonsRestant == 0) {
                    joueurCourant = ListeJoueurs[0];
                }
            } else if (joueurCourant == ListeJoueurs[1] && victoire == false) {
                joueurCourant = ListeJoueurs[0];
                if (joueurCourant.nombreJetonsRestant == 0) {
                    joueurCourant = ListeJoueurs[1];
                }
            }
        } while(victoire == false);
        
        if (joueurCourant == null) { // affichage résultats
            System.out.println("Il n'y as pas de vainqueur");
        } else {
            System.out.println(joueurCourant.nom + " à gagné");
        }
    }
}
