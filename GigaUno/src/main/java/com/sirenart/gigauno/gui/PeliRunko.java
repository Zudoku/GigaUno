/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sirenart.gigauno.gui;

import com.sirenart.gigauno.gui.panels.GamePlayPanel;
import com.sirenart.gigauno.gui.panels.PelaajienLisaysPanel;
import com.sirenart.gigauno.gui.panels.PelisaantoPanel;
import com.sirenart.gigauno.logiikka.peli.UnoPeli;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import javax.swing.SwingUtilities;

/**
 * PeliRunko on graafisen käyttöliittymän pääkontrolleri. Sillä on UnoPeli ja se
 * ohjaa JPanelien vaihtoa kun liikutaan esim. pelaajien lisäyksestä sääntöjen
 * valitsemiseen
 *
 * @author arska
 */
public class PeliRunko extends javax.swing.JFrame {

    private UnoPeli peli = new UnoPeli();
    private PelaajienLisaysPanel pelaajienLisaysPanel;
    private PelisaantoPanel pelisaantoPanel;
    private GamePlayPanel gameplayPanel;

    /**
     * PeliRunko on graafisen käyttöliittymän pääkontrolleri. Sillä on UnoPeli
     * ja se ohjaa JPanelien vaihtoa kun liikutaan esim. pelaajien lisäyksestä
     * sääntöjen valitsemiseen
     *
     * @throws HeadlessException Kun Swing failaa ?
     */
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

    /**
     * Metodi jota kutsutaan kun pelaajat ovat valittu graafisessa liittymässä.
     */
    public void pelaajatValittu() {
        if (peli.paataPelaajienLisays()) {

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    getContentPane().removeAll();
                    getContentPane().invalidate();
                    //getContentPane().add(pelaajienLisaysPanel, BorderLayout.PAGE_START);
                    pelisaantoPanel = new PelisaantoPanel(peli);
                    getContentPane().add(pelisaantoPanel);
                    validate();
                }
            });

        }
    }

    /**
     * Metodi jota kutsutaan kun säännöt ovat valittu graafisessa liittymässä.
     */
    public void saannotValittu() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                getContentPane().removeAll();
                getContentPane().invalidate();

                gameplayPanel = new GamePlayPanel(peli);
                getContentPane().add(gameplayPanel);
                validate();
            }
        });
    }

}
