package com.sirenart.gigauno.gui.panels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Graafinen käyttöliittymä siihen kun varmistetaan, että oikea pelaaja on
 * pelaamassa.
 *
 * @author arska
 */
public class VarmistusPanel extends JPanel {

    private GamePlayPanel parentPanel;

    /**
     * Graafinen käyttöliittymä siihen kun varmistetaan, että oikea pelaaja on
     * pelaamassa.
     *
     * @param parentPanel kontrolleri jota kutsutaan kun ollaan valmiita.
     */
    public VarmistusPanel(GamePlayPanel parentPanel) {
        this.parentPanel = parentPanel;

    }
    /**
     * Initialisoi tämän paneelin JLabelit annetulla nimellä.
     * @param pelaajanNimi Nykyisen vuoron pelaajan nimimerkki
     */
    public void initializeComponents(String pelaajanNimi) {
        removeAll();
        invalidate();
        setPreferredSize(new Dimension(800, 800));
        setLayout(new GridLayout(2, 1, 0, 0));

        JLabel pelaajaLabel = new JLabel("Pelaajan " + pelaajanNimi + " vuoro!");
        Font font1 = new Font("SansSerif", Font.BOLD, 60);
        pelaajaLabel.setFont(font1);
        pelaajaLabel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        add(pelaajaLabel);

        JButton jatkaButton = new JButton("Valmis");
        jatkaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                parentPanel.vuoronVahvistus();
            }
        });
        jatkaButton.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        add(jatkaButton);

        validate();

    }

}
