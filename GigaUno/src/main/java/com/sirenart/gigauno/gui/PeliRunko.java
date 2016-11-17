/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sirenart.gigauno.gui;

import com.sirenart.gigauno.gui.panels.PelaajienLisaysPanel;
import com.sirenart.gigauno.gui.panels.PelisaantoPanel;
import com.sirenart.gigauno.logiikka.peli.UnoPeli;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import javax.swing.BoxLayout;

/**
 * Created Nov 17, 2016
 * @author arska
 */
public class PeliRunko extends javax.swing.JFrame {
    
    private UnoPeli peli = new UnoPeli();
    private PelaajienLisaysPanel pelaajienLisaysPanel;
    private PelisaantoPanel pelisaantoPanel;
    

    public PeliRunko() throws HeadlessException {
        initComponents();
    }
    
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gigauno");
        setSize(800, 800);
                
        pelaajienLisaysPanel = new PelaajienLisaysPanel(peli);
        
        getContentPane().add(pelaajienLisaysPanel, BorderLayout.PAGE_START);
        
        pack();
    }
    
    public void pelaajatValittu(){
        if(peli.paataPelaajienLisays()){
            getContentPane().remove(pelaajienLisaysPanel);
            pelisaantoPanel = new PelisaantoPanel(peli);
            getContentPane().add(pelisaantoPanel);
        }
    }

    
    
}
