/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sirenart.gigauno.gui.panels;

import com.sirenart.gigauno.logiikka.peli.UnoPeli;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

/**
 * Kontrolleri joka ohjaa pelin kulkua kun peli on käynnissä.
 *
 * @author arska
 */
public class GamePlayPanel extends JPanel {

    private UnoPeli peli;

    public static final String ERATIEDOT = "ERATIEDOT";
    public static final String VARMISTUS = "VARMISTUS";
    public static final String VUORO = "VUORO";
    public static final String VOITTO = "VOITTO";

    private VuoroPanel vuoroPanel;
    private EraTiedotPanel eraTiedotPanel;
    private VarmistusPanel varmistusPanel;
    private VoittoPanel voittoPanel;

    private Timer timer;

    private String currentScreen = "";

    /**
     * Kontrolleri joka ohjaa pelin kulkua kun peli on käynnissä.
     *
     * @param peli Peli jota pelataan.
     */
    public GamePlayPanel(UnoPeli peli) {
        this.peli = peli;
        peli.aloitaPeli();
        initComponents();
        CardLayout cl = (CardLayout) (getLayout());
        cl.show(this, ERATIEDOT);
        currentScreen = ERATIEDOT;
        this.timer = new Timer();
        if (this.peli.getPeliAsetukset().getAikaRajoitus() > 0) {
            this.timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (currentScreen.equals(VUORO)) {
                        vuoroPanel.setKulunut(vuoroPanel.getKulunut() + 1);
                    }
                }
            }, 0, 1000);
        }
    }

    private void initComponents() {
        vuoroPanel = new VuoroPanel(this);
        eraTiedotPanel = new EraTiedotPanel(this);
        varmistusPanel = new VarmistusPanel(this);
        voittoPanel = new VoittoPanel();

        removeAll();
        setPreferredSize(new Dimension(800, 800));
        setLayout(new CardLayout());

        add(vuoroPanel, VUORO);
        add(eraTiedotPanel, ERATIEDOT);
        add(varmistusPanel, VARMISTUS);
        add(voittoPanel, VOITTO);

        eraTiedotPanel.paivita(peli);

        validate();

    }

    /**
     * Kontrolleri metodi silloin kun halutaan aloittaa uusi erä.
     */
    public void aloitaUusiEra() {

        peli.seuraavaEra();
        CardLayout cl = (CardLayout) (getLayout());
        varmistusPanel.initializeComponents(peli.getNykyinenEra().nykyinenPelaaja().getNimimerkki());
        cl.show(this, VARMISTUS);
        currentScreen = VARMISTUS;
    }

    /**
     * Kontrolleri metodi silloin kun vuoro on lopetettu.
     */
    public void vuoroOhi() {
        CardLayout cl = (CardLayout) (getLayout());
        vuoroPanel.setKulunut(0);
        if (peli.getNykyinenEra().isEraLoppu()) {
            if (peli.peliOhi()) {
                voittoPanel.initializeComponents(peli.getVoittaja().getNimimerkki());
                cl.show(this, VOITTO);
                currentScreen = VOITTO;
                return;
            }
            eraTiedotPanel.paivita(peli);

            validate();
            cl.show(this, ERATIEDOT);
            currentScreen = ERATIEDOT;
        } else {
            varmistusPanel.initializeComponents(peli.getNykyinenEra().nykyinenPelaaja().getNimimerkki());
            validate();
            cl.show(this, VARMISTUS);
            currentScreen = VARMISTUS;
        }

    }

    /**
     * Kontrolleri metodi silloin kun vuoro on vahvistettu.
     */
    public void vuoronVahvistus() {
        CardLayout cl = (CardLayout) (getLayout());
        //vuoroPanel
        validate();
        vuoroPanel.initComponents();
        cl.show(this, VUORO);
        currentScreen = VUORO;
    }

    public UnoPeli getPeli() {
        return peli;
    }

}
