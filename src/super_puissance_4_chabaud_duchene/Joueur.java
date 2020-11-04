/*
 * Classe Joueur, contient les information de chaque joueur
 */

package super_puissance_4_chabaud_duchene;

/**
 * @author DUCHENE - CHABAUD
 */

public class Joueur {
    String nom;
    String couleur;
    Jeton[] ListeJetons = new Jeton[21];
    int nombreDesintegrateur;
    int nombreJetonsRestant;
    
    public Joueur(String nom_joueur) { //constructeur
        nom = nom_joueur;
        nombreJetonsRestant = 0;
        nombreDesintegrateur = 0;
    }
    public void affecterCouleur(String Couleur) {
        couleur = Couleur;
    }
    public boolean ajouterJeton(Jeton jeton) {
        int i = 0;
        while (ListeJetons[i] != null) { // detection de la première case vide du tableau
            i++;
            if (i == 21) { // si tableau plein, retour erreur
                return false;
            }
        }
        ListeJetons[i] = jeton; // affectation jeton au joueur
        nombreJetonsRestant++;
        return true;
    }
    public void obtenirDesintegrateur() {
        nombreDesintegrateur++;
    }
    public boolean utiliserDesintegrateur() {
        if (nombreDesintegrateur > 0) { // verification possession desintegrateur
            nombreDesintegrateur--;
            return true;
        } else {
            return false;
        }
    }
}
