/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sirenart.gigauno.gui.panels;

import com.sirenart.gigauno.gui.PeliRunko;
import com.sirenart.gigauno.logiikka.kortit.KorttiTyyppi;
import com.sirenart.gigauno.logiikka.peli.PeliAsetukset;
import com.sirenart.gigauno.logiikka.peli.UnoPeli;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created Nov 17, 2016
 * @author arska
 */
public class PelisaantoPanel extends JPanel {
    private UnoPeli peli;
    private PeliAsetukset peliAsetukset;
    
    /**
     * Toinen state pelissä,
     * luo graafisen käyttöliittymän sääntöjen valitsemiseen
     * 
     * @param peli PeliRungossa oleva UnoPeli
     */
    public PelisaantoPanel(UnoPeli peli) {
        this.peli = peli;
        this.peliAsetukset = PeliAsetukset.getDefaultAsetukset();
        initComponents();
    }
    
    private void initComponents(){
        removeAll();
        setPreferredSize(new Dimension(800, 800));
        setLayout(new GridLayout(9, 2, 10, 30));
        
        Font font1 = new Font("SansSerif", Font.BOLD, 35);
        
        JLabel header = new JLabel("Pelin asetukset");
        header.setFont(font1);
        header.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(header);
        JLabel selitys = new JLabel("<html>Valitse Peliin haluamasi kortit, voittoraja, korttikerroin ja aikaraja</html>");
        
        selitys.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        add(selitys);
        for(KorttiTyyppi tyyppi : peliAsetukset.getErikoiskorttiValinnat().keySet()) {
            JCheckBox checkbox = new JCheckBox(tyyppi.toString());
            checkbox.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            checkbox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    toggleKortti(tyyppi);
                }
            });
            checkbox.setSelected(peliAsetukset.getErikoiskorttiValinnat().get(tyyppi));
            add(checkbox);
        }
        add(new JLabel());
        
        JLabel aikarajaLabel = new JLabel("Pelin vuoroaikaraja:  -1 = ei rajaa | 60 = 1 minuutti");
        aikarajaLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(aikarajaLabel);
        JSpinner aikaraja = new JSpinner(new SpinnerNumberModel(peliAsetukset.getAikaRajoitus(), -1, 60, 1));
        aikaraja.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                aikaRajaToggle((int)aikaraja.getModel().getValue());
            }
        });
        aikaraja.setFont(font1);
        add(aikaraja);
        
        JLabel korttikerroinLabel = new JLabel("Korttikerroin: ");
        korttikerroinLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(korttikerroinLabel);
        JSpinner korttikerroin = new JSpinner(new SpinnerNumberModel(peliAsetukset.getKorttiKerroin(), 1, 10, 1));
        korttikerroin.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                korttikerroinToggle((int)korttikerroin.getModel().getValue());
            }

            
        });

        korttikerroin.setFont(font1);
        add(korttikerroin);
        
        JLabel voittorajaLabel = new JLabel("Pelin voittoraja: ");
        voittorajaLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(voittorajaLabel);
        JSpinner voittoraja = new JSpinner(new SpinnerNumberModel(peliAsetukset.getVoittoRaja(), 100, 800, 50));
        voittoraja.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                korttikerroinToggle((int)voittoraja.getModel().getValue());
            }

            
        });
        voittoraja.setFont(font1);
        add(voittoraja);
        
        add(new JLabel());
        JButton valmisButton = new JButton("Valmis");
        valmisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                PeliRunko parent = (PeliRunko) getParent().getParent().getParent().getParent();
                peli.setPeliAsetukset(peliAsetukset);
                parent.saannotValittu();
            }
        });
        
        
        valmisButton.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(valmisButton);
        validate();
    }
    public void korttikerroinToggle(int i) {
        peliAsetukset.setKorttiKerroin(i);
        peli.setPeliAsetukset(peliAsetukset);
    }
    
    public void toggleKortti(KorttiTyyppi tyyppi) {
        peliAsetukset.getErikoiskorttiValinnat().put(tyyppi, !peliAsetukset.getErikoiskorttiValinnat().get(tyyppi));
        peli.setPeliAsetukset(peliAsetukset);
    }
    
    public void aikaRajaToggle(int arvo) {
        peliAsetukset.setAikaRajoitus(arvo);
        peli.setPeliAsetukset(peliAsetukset);
    }
    
    public void pisterajaToggle(int i) {
        peliAsetukset.setVoittoRaja(i);
        peli.setPeliAsetukset(peliAsetukset);
    }
    

}
