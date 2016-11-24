/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sirenart.gigauno.gui.panels;

import com.sirenart.gigauno.logiikka.kortit.KorttiTyyppi;
import com.sirenart.gigauno.logiikka.pelaaja.Pelaaja;
import com.sirenart.gigauno.logiikka.peli.UnoPeli;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Created Nov 24, 2016
 *
 * @author arska
 */
public class EraTiedotPanel extends JPanel {

    private UnoPeli peli;
    private GamePlayPanel parentPanel;

    /**
     * Graafinen käyttöliittymä siihen kun näytetään erän välissä tiedot miten
     * peli on edennyt.
     *
     * @param parentPanel kontrolleri jota kutsutaan kun ollaan valmiita.
     */
    public EraTiedotPanel(GamePlayPanel parentPanel) {
        this.parentPanel = parentPanel;
    }

    public void paivita(UnoPeli peli) {
        removeAll();
        invalidate();
        setPreferredSize(new Dimension(800, 800));
        setLayout(new GridLayout(4, 1, 10, 30));
        JLabel header = new JLabel("Erätiedot");

        Font font1 = new Font("SansSerif", Font.BOLD, 35);
        header.setFont(font1);
        header.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(header);

        // Pelaajien scoret
        String[] scoreColumns = {"Pelaaja", "Pisteet", "Erävoittoja"};
        Object[][] scoreTiedot = new Object[peli.getPelaajat().size()][3];
        for (int i = 0; i < peli.getPelaajat().size(); i++) {
            Pelaaja p = peli.getPelaajat().get(i);
            scoreTiedot[i][0] = p.getNimimerkki();
            scoreTiedot[i][1] = p.getPisteita();
            scoreTiedot[i][2] = p.getEraVoittoja();
        }

        JTable scoreTable = new JTable(scoreTiedot, scoreColumns);
        JScrollPane scrollPaneScore = new JScrollPane(scoreTable);
        scoreTable.setFillsViewportHeight(true);
        add(scrollPaneScore);

        // Pelin säännot
        String[] saannot = {"Sääntö", "Arvo"};
        Map<KorttiTyyppi, Boolean> erikoisValinnat = peli.getPeliAsetukset().getErikoiskorttiValinnat();
        Object[][] saantoTiedot = new Object[erikoisValinnat.keySet().size() + 3][2];
        for (int i = 0; i < erikoisValinnat.size(); i++) {
            KorttiTyyppi p = (KorttiTyyppi) erikoisValinnat.keySet().toArray()[i];
            saantoTiedot[i][0] = p.toString();
            saantoTiedot[i][1] = erikoisValinnat.get(p);
        }
        saantoTiedot[erikoisValinnat.keySet().size()][0] = "Aikaraja";
        saantoTiedot[erikoisValinnat.keySet().size()][1] = peli.getPeliAsetukset().getAikaRajoitus();

        saantoTiedot[erikoisValinnat.keySet().size() + 1][0] = "Korttikerroin";
        saantoTiedot[erikoisValinnat.keySet().size() + 1][1] = peli.getPeliAsetukset().getKorttiKerroin();

        saantoTiedot[erikoisValinnat.keySet().size() + 2][0] = "Voittoraja";
        saantoTiedot[erikoisValinnat.keySet().size() + 2][1] = peli.getPeliAsetukset().getVoittoRaja();

        JTable saantoTable = new JTable(saantoTiedot, saannot);

        JScrollPane scrollPaneSaanto = new JScrollPane(saantoTable);
        saantoTable.setFillsViewportHeight(true);
        add(scrollPaneSaanto);

        JButton aloitaUusiButton = new JButton("Aloita uusi erä");
        aloitaUusiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                parentPanel.aloitaUusiEra();
            }
        });
        add(aloitaUusiButton);
        validate();
    }

}
