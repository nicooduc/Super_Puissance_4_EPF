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
    
    public CelluleGraphique(Cellule cellule) {
        celluleAssociee = cellule;
    }




    @Override

    public void paintComponent (Graphics G){
        super.paintComponent(G);
        setIcon(img_vide); //l'image vide est l'image de défault
}
}