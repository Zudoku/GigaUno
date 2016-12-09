package com.sirenart.gigauno.gui.panels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Graafinen käyttöliittymä voittajan näyttämiseen.
 *
 * @author arska
 */
public class VoittoPanel extends JPanel {

    /**
     * Initialisoi tämän paneelin JLabelin annetulla nimellä.
     * 
     * @param pelaajanNimi voittavan pelaajan nimimerkki
     */
    public void initializeComponents(String pelaajanNimi) {
        removeAll();
        invalidate();
        setPreferredSize(new Dimension(800, 800));
        setLayout(new GridLayout(1, 1, 0, 0));

        JLabel pelaajaLabel = new JLabel("Pelaaja " + pelaajanNimi + " voitti!");
        Font font1 = new Font("SansSerif", Font.BOLD, 40);
        pelaajaLabel.setFont(font1);
        pelaajaLabel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        add(pelaajaLabel);


        validate();

    }
}
