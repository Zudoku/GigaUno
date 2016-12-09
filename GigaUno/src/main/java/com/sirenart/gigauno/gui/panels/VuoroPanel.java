package com.sirenart.gigauno.gui.panels;

import com.sirenart.gigauno.logiikka.era.PeliAlusta;
import com.sirenart.gigauno.logiikka.kortit.Kortti;
import com.sirenart.gigauno.logiikka.pelaaja.Pelaaja;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Graafinen käyttöliittymä itse vuoron pelaamiseen. Pelaaja voi laittaa kortin,
 * nostaa kortin tai huutaa uno tai bustata unon.
 *
 * @author arska
 */
public class VuoroPanel extends JPanel {

    private GamePlayPanel parentPanel;
    private PeliAlusta alusta;

    /**
     * Graafinen käyttöliittymä itse vuoron pelaamiseen. Pelaaja voi laittaa
     * kortin, nostaa kortin tai huutaa uno tai bustata unon.
     *
     * @param parentPanel kontrolleri jota kutsutaan kun ollaan valmiita.
     */
    public VuoroPanel(GamePlayPanel parentPanel) {
        this.parentPanel = parentPanel;
        initComponents();
    }

    /**
     * Rakentaa käyttöliittymän uusiksi.
     */
    public void initComponents() {
        this.alusta = parentPanel.getPeli().getNykyinenEra();
        if (alusta == null) {
            return;
        }
        removeAll();
        invalidate();
        setPreferredSize(new Dimension(800, 800));
        setLayout(new GridLayout(3, 3, 20, 20));
        //Yläbaari
        Font font1 = new Font("SansSerif", Font.BOLD, 15);
        JLabel ekaKortti = new JLabel("");
        if (alusta.getEkaKorttiLaitettu() != null) {
            ekaKortti = new JLabel("" + alusta.getEkaKorttiLaitettu().getTyyppi().name());
            switch (alusta.getEkaKorttiLaitettu().getVari()) {
                case KELTAINEN:
                    ekaKortti.setBackground(Color.yellow);
                    break;

                case PUNAINEN:
                    ekaKortti.setBackground(Color.red);
                    break;

                case SININEN:
                    ekaKortti.setBackground(Color.blue);
                    break;

                case VIHREA:
                    ekaKortti.setBackground(Color.green);
                    break;

                case ERIKOIS:
                    ekaKortti.setBackground(Color.DARK_GRAY);
                    break;
            }
            ekaKortti.setOpaque(true);
            ekaKortti.setFont(font1);
            ekaKortti.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        }

        add(ekaKortti);
        JLabel header = new JLabel("Aika pelata vuorosi!");

        header.setFont(font1);
        header.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(header);
        int korttejaMahisLaittaa = 5 - alusta.getKorttejaLaitettu();
        add(new JLabel("<html>" + "Kortteja nostopakassa: " + alusta.getNostoPakka().getKortit().size() + "</br>" + " Kortteja laittopakassa: " + alusta.getLaittoPakka().getKortit().size()
                + " Kortteja mahdollisuus laittaa: " + korttejaMahisLaittaa + "</html>"));

        //Pelaajien korttimäärä ja uno bustaus napit
        JPanel players = new JPanel();
        players.setLayout(new GridLayout(alusta.getPelaajat().size() - 1, 2, 5, 5));
        for (int i = 0; i < alusta.getPelaajat().size(); i++) {
            Pelaaja p = alusta.getPelaajat().get(i);

            if (p != alusta.nykyinenPelaaja()) {
                JLabel pelaajaNimiLabel = new JLabel("" + p.getNimimerkki() + " (" + p.getEraTiedot().getKortit().getKortit().size() + ")");

                if (p.getEraTiedot().isSaaVoittaa()) {
                    pelaajaNimiLabel.setBackground(Color.red);
                } else if (p.getEraTiedot().onkoPelaajallaUnoTila()) {
                    pelaajaNimiLabel.setBackground(Color.orange);
                }
                pelaajaNimiLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                pelaajaNimiLabel.setOpaque(true);
                players.add(pelaajaNimiLabel);

                JButton bustaa = new JButton("Paljasta");
                bustaa.addActionListener((ActionEvent ae) -> {
                    alusta.pelajaPaljastaaUnon(p);
                    initComponents();
                });
                players.add(bustaa);
            }
        }
        add(players);

        // Pakan nykyinen kortti
        Kortti ylin = alusta.getLaittoPakka().ylin();
        JLabel nykyinen = new JLabel(ylin.getTyyppi().name());
        nykyinen.setFont(font1);
        nykyinen.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        switch (ylin.getVari()) {
            case KELTAINEN:
                nykyinen.setBackground(Color.yellow);
                break;

            case PUNAINEN:
                nykyinen.setBackground(Color.red);
                break;

            case SININEN:
                nykyinen.setBackground(Color.blue);
                break;

            case VIHREA:
                nykyinen.setBackground(Color.green);
                break;

            case ERIKOIS:
                nykyinen.setBackground(Color.DARK_GRAY);
                break;
        }
        nykyinen.setOpaque(true);
        add(nykyinen);

        // Sinun kortit
        JPanel cards = new JPanel();
        List<Kortti> kortit = alusta.nykyinenPelaaja().getEraTiedot().getKortit().getKortit();
        cards.setLayout(new GridLayout(kortit.size(), 2, 5, 5));
        for (int i = 0; i < kortit.size(); i++) {
            Kortti p = kortit.get(i);
            JLabel korttinimi = new JLabel("" + p.getTyyppi().name());
            korttinimi.setOpaque(true);
            switch (p.getVari()) {
                case KELTAINEN:
                    korttinimi.setBackground(Color.yellow);
                    break;

                case PUNAINEN:
                    korttinimi.setBackground(Color.red);
                    break;

                case SININEN:
                    korttinimi.setBackground(Color.blue);
                    break;

                case VIHREA:
                    korttinimi.setBackground(Color.green);
                    break;

                case ERIKOIS:
                    korttinimi.setBackground(Color.DARK_GRAY);
                    break;
            }

            korttinimi.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
            cards.add(korttinimi);

            JButton laita = new JButton("laita");
            laita.addActionListener((ActionEvent ae) -> {
                alusta.pelaajaLaittaaKortin(p);
                initComponents();
            });
            cards.add(laita);
        }
        add(cards);
        // sano uno
        JButton sanoUno = new JButton("Sano Uno");
        if (alusta.nykyinenPelaaja().getEraTiedot().isUnoHuudettu()) {
            sanoUno.setBackground(Color.green);
        }
        sanoUno.addActionListener((ActionEvent ae) -> {
            alusta.pelaajaHuutaaUno();
            if (alusta.pelaajaLopettaaVuoron()) {
                parentPanel.vuoroOhi();
            } else {
                initComponents();
            }
        });
        add(sanoUno);
        // Nosta kortti
        JButton nostaKortti = new JButton("Nosta kortti");
        nostaKortti.addActionListener((ActionEvent ae) -> {
            alusta.pelaajaNostaaKortin();
            if (alusta.pelaajaLopettaaVuoron()) {
                parentPanel.vuoroOhi();
            } else {
                initComponents();
            }

        });
        add(nostaKortti);
        //Lopeta vuoro
        JButton lopetaVuoro = new JButton("Lopeta");
        if (!alusta.isPelaajaSaaLopettaaVuoron()) {
            lopetaVuoro.setBackground(Color.darkGray);
        }
        lopetaVuoro.addActionListener((ActionEvent ae) -> {
            if (alusta.pelaajaLopettaaVuoron()) {
                parentPanel.vuoroOhi();
            }

        });
        add(lopetaVuoro);

        validate();
    }

}
