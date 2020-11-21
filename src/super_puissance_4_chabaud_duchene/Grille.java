/*
 * Classe Grille, contient les information de chaque grille
 */

package super_puissance_4_chabaud_duchene;

/**
 * @author DUCHENE - CHABAUD
 */

public class Grille {
    public static final String ANSI_BLACK = "\u001B[30m"; // différentes couleurs pour l'affichage
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_WHITE = "\u001B[37m";
    
    Cellule[][] cellule = new Cellule[6][7];
    
    public Grille() {
        for (int i =0; i<6;i++) { // attribue l'objets a chaque cellule de la grille
            for (int j = 0; j<7; j++) {
                cellule[i][j] = new Cellule();
            }
        }
    }
    
    public int ajouterJetonDansColonne(Jeton jeton, int colonne) {
        for (int i = 0; i<6;i++) {
            if (cellule[i][colonne].jetonCourant== null) { // récupère la plus basse ligne disponible dans la colonne et y ajoute le jeton
                cellule[i][colonne].affecterJeton(jeton);
                return i;
            }
        }
        return -1;
    }
    public boolean etreRemplie() {
        for (int i = 0; i<6;i++) { //verifie cellule par cellule si elle est occupée par un jeton
            for (int j = 0; j<7;j++) {
                if (cellule[i][j].recupererJeton() == null) {
                    return false;
                }
            }
        }
        return true;
    }
    public void viderGrille() {
        for (int i = 0; i<6;i++) { // vide la grille cellule par cellule
            for (int j = 0; j<7;j++) {
                cellule[i][j].supprimerJeton();
                cellule[i][j].recupererDesintegrateur();
                cellule[i][j].activerTrouNoir();
            }
        }

    }
    public void afficherGrilleSurConsole() {
        for (int i = 5; i>=0;i--) { // affichage de la grille
            for (int j = 0; j<7;j++) {
                if (cellule[i][j].presenceTrouNoir()) {
                    System.out.print(ANSI_BLACK + "O" + ANSI_BLACK); // O noir pour trou noir
                } else if (cellule[i][j].presenceDesintegrateur()) {
                    System.out.print(ANSI_BLACK + "D" + ANSI_BLACK); // D noir pour desintégrateur (ceux placé sous un trou noir ne sont pas affiché car le test est après)
                } else if (cellule[i][j].recupererJeton() == null) {
                    System.out.print(ANSI_WHITE + "O" + ANSI_BLACK); // O blanc pour case vide
                } else if ("rouge".equals(cellule[i][j].lireCouleurDuJeton())) {
                    System.out.print(ANSI_RED + "O" + ANSI_BLACK); // O rouge pour jeton rouge
                } else if ("jaune".equals(cellule[i][j].lireCouleurDuJeton())) {
                    System.out.print(ANSI_YELLOW + "O" + ANSI_BLACK); // O jaune pour jeton jaune
                }
                if (j<6) {
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }
    }
    public boolean celluleOccupee(int ligne, int colonne) {
        return cellule[ligne][colonne].recupererJeton() != null; // verifie si la cellule est déjà occupée (false = cellule vide)
    }
    public String lireCouleurDuJeton(int ligne, int colonne) {
        return cellule[ligne][colonne].lireCouleurDuJeton();
    }
    public boolean etreGagnantePourJoueur(Joueur joueur) {
        for (int i = 0; i<6;i++) { //test victoire ligne
            for (int j = 0; j<4;j++) {
                if (cellule[i][j].lireCouleurDuJeton().equals(joueur.couleur)) {
                    if (cellule[i][j+1].lireCouleurDuJeton().equals(joueur.couleur)) {
                        if (cellule[i][j+2].lireCouleurDuJeton().equals(joueur.couleur)) {
                            if (cellule[i][j+3].lireCouleurDuJeton().equals(joueur.couleur)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i<3;i++) { //test victoire colonne
            for (int j = 0; j<7;j++) {
                if (cellule[i][j].lireCouleurDuJeton().equals(joueur.couleur)) {
                    if (cellule[i+1][j].lireCouleurDuJeton().equals(joueur.couleur)) {
                        if (cellule[i+2][j].lireCouleurDuJeton().equals(joueur.couleur)) {
                            if (cellule[i+3][j].lireCouleurDuJeton().equals(joueur.couleur)) {
                                return true;
                            }
                        }
                    }
                }
            }
        } 
        for (int i = 0; i<3;i++) { //test victoire diagonale 1 (vers haut droit)
            for (int j = 0; j<4;j++) {
                if (cellule[i][j].lireCouleurDuJeton().equals(joueur.couleur)) {
                    if (cellule[i+1][j+1].lireCouleurDuJeton().equals(joueur.couleur)) {
                        if (cellule[i+2][j+2].lireCouleurDuJeton().equals(joueur.couleur)) {
                            if (cellule[i+3][j+3].lireCouleurDuJeton().equals(joueur.couleur)) {
                                return true;
                            }
                        }
                    }
                }
            }
        } 
        for (int i = 3; i<6;i++) { //test victoire diagonale 2 (vers bas droit)
            for (int j = 0; j<4;j++) {
                if (cellule[i][j].lireCouleurDuJeton().equals(joueur.couleur)) {
                    if (cellule[i-1][j-1].lireCouleurDuJeton().equals(joueur.couleur)) {
                        if (cellule[i-2][j-2].lireCouleurDuJeton().equals(joueur.couleur)) {
                            if (cellule[i-3][j-3].lireCouleurDuJeton().equals(joueur.couleur)) {
                                return true;
                            }
                        }
                    }
                }
            }
        } 
        return false;
    }
    /*public void tasserGrille(int ligne, int colonne) {
        for (int i=ligne; i<5;i++) { // décale tout les jetons au dessus de celui supprimé
            cellule[i][colonne].jetonCourant = cellule[i+1][colonne].jetonCourant;
            cellule[i+1][colonne].supprimerJeton();
        }
    }*/
    public void tasserGrille() {
        for (int i = 0; i < 7; i++) {
            tasserColonne(i);
        }
    }
    public void tasserColonne(int colonne) {
        for (int i = 0; i < 5; i++) {
            
                if (cellule[i][colonne].jetonCourant == null) {
                    cellule[i][colonne].jetonCourant = cellule[i+1][colonne].jetonCourant;
                    cellule[i+1][colonne].jetonCourant = null;
                }
            
        }
    }
    public boolean placerTrouNoir(int ligne, int colonne) {
        return cellule[ligne][colonne].placerTrouNoir();
    }
    public boolean placerDesintegrateur(int ligne, int colonne) {
        return cellule[ligne][colonne].placerDesintegrateur();
    }
    public boolean supprimerJeton(int ligne, int colonne) {
        return cellule[ligne][colonne].supprimerJeton();
    }
    public Jeton recupererJeton(int ligne, int colonne) {
        Jeton jeton = cellule[ligne][colonne].recupererJeton(); // retire le jeton de la case visée
        //cellule[ligne][colonne].supprimerJeton();
        return jeton;
    }
    public boolean colonneRemplie(int colonne) {
        if (cellule[5][colonne].jetonCourant == null) {
            return false;
        } else {
            return true;
        }
    }
}
