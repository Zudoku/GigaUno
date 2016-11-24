/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sirenart.gigauno.gui.panels;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Created Nov 24, 2016
 *
 * @author arska
 */
public class VarmistusPanel extends JPanel {

    private GamePlayPanel parentPanel;

    /**
     * Graafinen käyttöliittymä siihen kun varmistetaan, että oikea pelaaja on
     * pelaamassa
     *
     * @param parentPanel kontrolleri jota kutsutaan kun ollaan valmiita.
     */
    public VarmistusPanel(GamePlayPanel parentPanel) {
        this.parentPanel = parentPanel;
    }

    public void initializeComponents(String pelaajanNimi) {
        removeAll();
        invalidate();
        setPreferredSize(new Dimension(800, 800));
        setLayout(new GridLayout(2, 1, 0, 0));

        JLabel pelaajaLabel = new JLabel("Pelaajan " + pelaajanNimi + " vuoro.");

        add(pelaajaLabel);

        JButton jatkaButton = new JButton("Valmis");
        jatkaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                parentPanel.vuoronVahvistus();
            }
        });
        add(jatkaButton);

        validate();

    }

}
