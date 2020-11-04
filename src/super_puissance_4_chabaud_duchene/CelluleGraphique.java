/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package super_puissance_4_chabaud_duchene;

import javax.swing.JButton;

/**
 *
 * @author nykol
 */
public class CelluleGraphique extends JButton {
    Cellule celluleAssociee;
    
    public CelluleGraphique(Cellule cellule) {
        celluleAssociee = cellule;
    }
}
