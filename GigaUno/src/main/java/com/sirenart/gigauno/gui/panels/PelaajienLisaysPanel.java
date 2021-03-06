/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sirenart.gigauno.gui.panels;

import com.sirenart.gigauno.gui.PeliRunko;
import com.sirenart.gigauno.logiikka.pelaaja.Pelaaja;
import com.sirenart.gigauno.logiikka.peli.UnoPeli;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Ensimmäinen state pelissä Luo graafisen käyttöliittymän pelaajien lisäämiseen.
 *
 * @author arska
 */
public class PelaajienLisaysPanel extends JPanel {

    private UnoPeli peli;

    private JTextField uusiPelaajaNimiTextField;

    /**
     * Ensimmäinen state pelissä Luo graafisen käyttöliittymän pelaajien
     * lisäämiseen.
     *
     * @param peli PeliRungossa oleva UnoPeli
     */
    public PelaajienLisaysPanel(UnoPeli peli) {
        this.peli = peli;
        rakennaUusiksiPelaajat();
    }

    private void rakennaUusiksiPelaajat() {
        removeAll();
        setPreferredSize(new Dimension(800, 800));
        setLayout(new GridLayout(8, 2, 10, 30));
        for (int i = 0; i < 6; i++) {
            if (i < peli.getPelaajat().size()) {
                JLabel label = new JLabel();
                label.setText(peli.getPelaajat().get(i).getNimimerkki());
                final int index = i;
                label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                add(label);
                JButton poistaButton = new JButton("poista");
                poistaButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        peli.getPelaajat().remove(index);
                        rakennaUusiksiPelaajat();
                    }
                });
                add(poistaButton);
            } else {
                JLabel uusiPelaajaLabel = new JLabel("<uusi pelaaja?>");
                uusiPelaajaLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                add(uusiPelaajaLabel);
                add(new JLabel(""));
            }
        }
        uusiPelaajaNimiTextField = new JTextField();

        Font font1 = new Font("SansSerif", Font.BOLD, 35);

        uusiPelaajaNimiTextField.setFont(font1);
        uusiPelaajaNimiTextField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(uusiPelaajaNimiTextField);

        JButton lisaaPelaajaButton = new JButton("Lisää pelaaja");
        lisaaPelaajaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                lisaaPelaaja();
            }
        });

        add(lisaaPelaajaButton);

        add(new JLabel(""));

        JButton valmisButton = new JButton("Valmis");
        valmisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                PeliRunko parent = (PeliRunko) getParent().getParent().getParent().getParent();
                parent.pelaajatValittu();
            }
        });

        add(valmisButton);

        validate();
    }

    private void lisaaPelaaja() {
        if (peli.lisaaPelaaja(new Pelaaja(uusiPelaajaNimiTextField.getText()))) {
            uusiPelaajaNimiTextField.setText("");
            rakennaUusiksiPelaajat();
        } else {
            uusiPelaajaNimiTextField.setText("Huono pelaajanimi");
        }

    }

}
