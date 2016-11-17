/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sirenart.gigauno.gui.panels;

import com.sirenart.gigauno.logiikka.peli.UnoPeli;
import javax.swing.JPanel;

/**
 * Created Nov 17, 2016
 * @author arska
 */
public class PelisaantoPanel extends JPanel {
    private UnoPeli peli;
    
    public PelisaantoPanel(UnoPeli peli) {
        this.peli = peli;
        initComponents();
    }
    
    private void initComponents(){
        
    }
}
