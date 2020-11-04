 /*
 * Classe Cellule, contient les informations de chaque cellules
 */

package super_puissance_4_chabaud_duchene;

/**
 * @author DUCHENE - CHABAUD
 */

public class Cellule {
    Jeton jetonCourant;
    boolean trouNoir;
    boolean desintegrateur;
    
    public Cellule() {
        jetonCourant = null;
        trouNoir = false;
        desintegrateur = false;
    }
    
    public boolean affecterJeton(Jeton jeton) {
        if (jetonCourant == null) { // verifie qu'aucun jeton ne soit déjà présent dans la cellule
            jetonCourant = jeton;
            return true;
        } else {
            return false;
        }
        
    }
    public Jeton recupererJeton() {
        return jetonCourant;
    }
    public boolean supprimerJeton() {
        if (jetonCourant != null) { // vérifie la présence d'un jeton
           jetonCourant = null;
            return true; 
        } else {
            return false;
        }
    }
    public boolean placerTrouNoir() {
        if (trouNoir == false) { // vérifie l'absence de trou noir
            trouNoir = true;
            return true;
        } else {
            return false;
        }
    }
    public boolean placerDesintegrateur() {
        if (desintegrateur == false) { // vérifie l'absence de désintégrateur
            desintegrateur = true;
            return true;
        } else {
            return false;
        }
    }
    public boolean presenceTrouNoir() {
        return trouNoir;
    }
    public boolean presenceDesintegrateur() {
        return desintegrateur;
    }
    public String lireCouleurDuJeton() {
        if (jetonCourant != null) {
            return jetonCourant.lireCouleur();
        } else {
            return "";
        }
    }
    public boolean recupererDesintegrateur() {
        if (desintegrateur) { // vérifie la présence d'un désintégrateur
            desintegrateur = false; // retire le désintégrateur de la cellule
            return true;
        } else {
            return false;
        }
    }
    public boolean activerTrouNoir() {
        if (trouNoir) { // vérifie la présence d'un trou noir
            trouNoir = false; // retire le trou noir de la cellule
            return true;
        } else {
            return false;
        }
    }
}
