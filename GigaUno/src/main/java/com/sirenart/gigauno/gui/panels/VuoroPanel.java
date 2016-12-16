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
import java.util.Timer;
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
    private int kulunut = 0;
    private int paivitettuKulunut = 0;
    private boolean paivitetaan = false;

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
     * Laittaa kulunut arvon.
     * Jos aikarajoitus on päällä ja GUI:n voi päivittää, niin päivitetään GUI.
     * @param kulunut sekunteja vuoroa kulunut
     */
    public void setKulunut(int kulunut) {
        this.kulunut = kulunut;
        if (kulunut > parentPanel.getPeli().getPeliAsetukset().getAikaRajoitus()) {
            alusta.pelaajaNostaaKortin();
            if (alusta.pelaajaLopettaaVuoron()) {
                parentPanel.vuoroOhi();
            } else {
                initComponents();
            }
        } else if (this.kulunut != paivitettuKulunut && !paivitetaan) {
            initComponents();

        }
    }

    public int getKulunut() {
        return kulunut;
    }

    /**
     * Rakentaa käyttöliittymän uusiksi.
     */
    public void initComponents() {
        this.alusta = parentPanel.getPeli().getNykyinenEra();
        if (alusta == null) {
            return;
        }
        paivitetaan = true;
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
                    ekaKortti.setBackground(new Color(247, 241, 126));
                    break;

                case PUNAINEN:
                    ekaKortti.setBackground(new Color(247, 126, 126));
                    break;

                case SININEN:
                    ekaKortti.setBackground(new Color(126, 166, 247));
                    break;

                case VIHREA:
                    ekaKortti.setBackground(new Color(126, 247, 136));
                    break;

                case ERIKOIS:
                    ekaKortti.setBackground(Color.LIGHT_GRAY);
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
        String tietoString = "<html>" + "Kortteja nostopakassa: " + alusta.getNostoPakka().getKortit().size() + "</br>" + " Kortteja laittopakassa: " + alusta.getLaittoPakka().getKortit().size()
                + " Kortteja mahdollisuus laittaa: " + korttejaMahisLaittaa;
        if (parentPanel.getPeli().getPeliAsetukset().getAikaRajoitus() > 0) {
            tietoString += (" Vuoroa jäljellä: " + (parentPanel.getPeli().getPeliAsetukset().getAikaRajoitus() - kulunut));
            paivitettuKulunut = this.kulunut;
        }
        tietoString += "</html>";

        add(new JLabel(tietoString));

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
                    if (!paivitetaan) {
                        initComponents();
                    }
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
                nykyinen.setBackground(new Color(247, 241, 126));
                break;

            case PUNAINEN:
                nykyinen.setBackground(new Color(247, 126, 126));
                break;

            case SININEN:
                nykyinen.setBackground(new Color(126, 166, 247));
                break;

            case VIHREA:
                nykyinen.setBackground(new Color(126, 247, 136));
                break;

            case ERIKOIS:
                nykyinen.setBackground(Color.LIGHT_GRAY);
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
                    korttinimi.setBackground(new Color(247, 241, 126));
                    break;

                case PUNAINEN:
                    korttinimi.setBackground(new Color(247, 126, 126));
                    break;

                case SININEN:
                    korttinimi.setBackground(new Color(126, 166, 247));
                    break;

                case VIHREA:
                    korttinimi.setBackground(new Color(126, 247, 136));
                    break;

                case ERIKOIS:
                    korttinimi.setBackground(Color.LIGHT_GRAY);
                    break;
            }

            korttinimi.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
            cards.add(korttinimi);

            JButton laita = new JButton("laita");
            laita.addActionListener((ActionEvent ae) -> {
                alusta.pelaajaLaittaaKortin(p);
                if (!paivitetaan) {
                    initComponents();
                }

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
            } else if (!paivitetaan) {
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
            } else if (!paivitetaan) {
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
        paivitetaan = false;
    }

}
