package com.sirenart.gigauno;

import com.sirenart.gigauno.gui.PeliRunko;

/**
 * GigaUnon Main luokka, tekee uuden Pelirungon eli graafisen käyttöliittymän,
 * joka sisältää pelin.
 *
 * @author arska
 */
public class Main {

    /**
     * GigaUnon Main luokka, tekee uuden Pelirungon eli graafisen
     * käyttöliittymän, joka sisältää pelin.
     *
     * @param args argumentteja ei käsitellä mitenkään
     */
    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            new PeliRunko().setVisible(true);
        });

    }

}
