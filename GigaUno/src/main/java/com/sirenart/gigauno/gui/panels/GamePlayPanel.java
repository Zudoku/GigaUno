/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sirenart.gigauno.gui.panels;

import com.sirenart.gigauno.logiikka.peli.UnoPeli;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 * Created Nov 24, 2016
 *
 * @author arska
 */
public class GamePlayPanel extends JPanel {

    private UnoPeli peli;

    public static final String ERATIEDOT = "ERATIEDOT";
    public static final String VARMISTUS = "VARMISTUS";
    public static final String VUORO = "VUORO";

    private VuoroPanel vuoroPanel;
    private EraTiedotPanel eraTiedotPanel;
    private VarmistusPanel varmistusPanel;

    /**
     * Kontrolleri joka ohjaa pelin kulkua kun peli on käynnissä.
     *
     * @param peli Peli jota pelataan.
     */
    public GamePlayPanel(UnoPeli peli) {
        this.peli = peli;
        initComponents();
        CardLayout cl = (CardLayout) (getLayout());
        cl.show(this, ERATIEDOT);
    }

    private void initComponents() {
        vuoroPanel = new VuoroPanel(this);
        eraTiedotPanel = new EraTiedotPanel(this);
        varmistusPanel = new VarmistusPanel(this);

        removeAll();
        setPreferredSize(new Dimension(800, 800));
        setLayout(new CardLayout());

        add(vuoroPanel, VUORO);
        add(eraTiedotPanel, ERATIEDOT);
        add(varmistusPanel, VARMISTUS);

        eraTiedotPanel.paivita(peli);

        validate();

    }

    public void aloitaUusiEra() {
        peli.aloitaPeli();
    }

    public void vuoroOhi() {
        CardLayout cl = (CardLayout) (getLayout());
        if (peli.getNykyinenEra().isEraLoppu()) {
            eraTiedotPanel.paivita(peli);

            validate();
            cl.show(this, ERATIEDOT);
        } else {
            varmistusPanel.initializeComponents(peli.getNykyinenEra().nykyinenPelaaja().getNimimerkki());
            validate();
            cl.show(this, VARMISTUS);
        }

    }

    public void vuoronVahvistus() {
        CardLayout cl = (CardLayout) (getLayout());
        //vuoroPanel
        validate();
        cl.show(this, VUORO);
    }

}
