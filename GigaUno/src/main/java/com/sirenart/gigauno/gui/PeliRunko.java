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
import javax.swing.BoxLayout;
import javax.swing.SwingUtilities;

/**
 * Created Nov 17, 2016
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
