/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sirenart.gigauno.logiikka.kortit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author arska
 */
public class KorttiPakka {

    private List<Kortti> kortit;

    public KorttiPakka(List<Kortti> kortit) {
        this.kortit = kortit;
    }

    public KorttiPakka() {
        kortit = new ArrayList<>();
    }

    public Kortti nosta() {
        if (kortit.size() > 0) {
            Kortti nostettava = kortit.get(kortit.size() - 1);
            kortit.remove(kortit.size() - 1);
            return nostettava;
        }
        return null;
    }

    public Kortti ylin() {
        if (kortit.size() > 0) {
            Kortti ylinKortti = kortit.get(kortit.size() - 1);
            return ylinKortti;
        }
        return null;
    }

    public boolean voikoLaittaaKortin(Kortti kortti) {
        if (kortti != null) {
            Kortti ylinKortti = ylin();

            if (kortti.getVari() == KorttiVari.ERIKOIS || kortti.getVari() == ylinKortti.getVari()) {
                //Värisääntö
                return true;
            } else if (kortti.getTyyppi() == ylinKortti.getTyyppi()) {
                //Sama tyyppi sääntö
                return true;
            }
        }
        return false;
    }

    public void alustus(Map<KorttiTyyppi, Boolean> erikoiskorttiValinnat, int korttiKerroin) {
        // Käydään kaikki kortit läpi
        for (KorttiTyyppi korttiTyyppi : KorttiTyyppi.values()) {
            // Tarkastetaan kuuluuko kortti erikoiskortteihin
            if (erikoiskorttiValinnat.keySet().contains(korttiTyyppi)) {
                // Kortti on erikoiskortti, katsotaan onko se laitettu päälle
                if (erikoiskorttiValinnat.get(korttiTyyppi)) {
                    // Lisää kortti pakkaan
                    for (int i = 0; i < korttiTyyppi.getPakassaKortteja() * korttiKerroin; i++) {
                        kortit.add(new Kortti(KorttiVari.ERIKOIS, korttiTyyppi));
                    }
                }
            } else {
                // Kortti on siis tavallinen numerokortti
                // Lisää kortti pakkaan
                for (int i = 0; i < korttiTyyppi.getPakassaKortteja() * korttiKerroin; i++) {
                    // Joka värissä
                    kortit.add(new Kortti(KorttiVari.VIHREA, korttiTyyppi));
                    kortit.add(new Kortti(KorttiVari.KELTAINEN, korttiTyyppi));
                    kortit.add(new Kortti(KorttiVari.PUNAINEN, korttiTyyppi));
                    kortit.add(new Kortti(KorttiVari.SININEN, korttiTyyppi));
                }
            }
        }
        sekoita();
    }

    public void lisaa(Kortti kortti) {
        if (kortti != null) {
            kortit.add(kortti);
        }
    }

    public int jaljella() {
        return kortit.size();
    }

    public void sekoita() {
        long seed = System.nanoTime();
        Collections.shuffle(kortit, new Random(seed));
    }

    public List<Kortti> getKortit() {
        return kortit;
    }

}
