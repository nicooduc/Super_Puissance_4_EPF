/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package super_puissance_4_chabaud_duchene;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author nykol
 */
public class CelluleGraphique extends JButton {
    Cellule celluleAssociee;
    ImageIcon img_vide = new javax.swing.ImageIcon (getClass().getResource("/images/celluleVide.png"));
    ImageIcon img_desint = new javax.swing.ImageIcon (getClass().getResource("/images/desintegrateur.png"));
    ImageIcon img_jetonJaune = new javax.swing.ImageIcon (getClass().getResource("/images/jetonJaune.png"));
    ImageIcon img_jetonRouge = new javax.swing.ImageIcon (getClass().getResource("/images/jetonRouge.png"));
    ImageIcon img_trouNoir = new javax.swing.ImageIcon (getClass().getResource("/images/trouNoir.png"));

    public CelluleGraphique(Cellule cellule) {
        celluleAssociee = cellule;
    }




    @Override

    public void paintComponent (Graphics G){
        super.paintComponent(G);
        if (celluleAssociee.presenceTrouNoir()) {
            setIcon(img_trouNoir);
        } else if (celluleAssociee.presenceDesintegrateur()) {
            setIcon(img_desint);
        } else {
            String couleurJeton = celluleAssociee.lireCouleurDuJeton();
            switch (couleurJeton) {
                case "rouge" :
                    setIcon(img_jetonRouge);
                    break;
                case "jaune" :
                    setIcon(img_jetonJaune);
                    break;
                default :
                    setIcon(img_vide); //l'image vide est l'image de défault
                    break;
            }
        }
    }
}