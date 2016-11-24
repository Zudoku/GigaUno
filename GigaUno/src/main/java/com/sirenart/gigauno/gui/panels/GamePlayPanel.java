/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sirenart.gigauno.gui.panels;

import com.sirenart.gigauno.logiikka.peli.UnoPeli;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 * Created Nov 24, 2016
 * @author arska
 */
public class GamePlayPanel extends JPanel {
    
    private UnoPeli peli;
    
    public final String ERATIEDOT = "ERATIEDOT";
    public final String VARMISTUS = "VARMISTUS";
    public final String VUORO = "VUORO";
    
    
    private VuoroPanel vuoroPanel;
    private EraTiedotPanel eraTiedotPanel;
    private VarmistusPanel varmistusPanel;

    public GamePlayPanel(UnoPeli peli) {
        this.peli = peli;
        initComponents();
        CardLayout cl = (CardLayout)(getLayout());
        cl.show(this, ERATIEDOT);
    }
    
    private void initComponents(){
        vuoroPanel = new VuoroPanel();
        eraTiedotPanel = new EraTiedotPanel();
        varmistusPanel = new VarmistusPanel();
        
        removeAll();
        setPreferredSize(new Dimension(800, 800));
        setLayout(new CardLayout());
        
        add(vuoroPanel, VUORO);
        add(eraTiedotPanel, ERATIEDOT);
        add(varmistusPanel, VARMISTUS);
        
        eraTiedotPanel.paivita(peli);
        
        validate();
        
    }

}
