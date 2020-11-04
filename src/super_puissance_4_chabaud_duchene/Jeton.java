/*
 * Classe Jeton, contient les informations de chaque jeton
 */

package super_puissance_4_chabaud_duchene;

/**
 * @author DUCHENE - CHABAUD
 */

public class Jeton {
    String Couleur;
    
    public Jeton(String couleur) {
        Couleur = couleur;
    }
    
    public String lireCouleur() {
        return Couleur;
    }
    
}
