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
 * Kuvastaa korttipakkaa, pakasta voi nostaa ja siihen voi laittaa kortteja.
 *
 * @author arska
 */
public class KorttiPakka {

    /**
     * Lista pakan nykyisistä korteista.
     *
     * @see Kortti
     */
    private List<Kortti> kortit;

    /**
     * Kuvastaa korttipakkaa, pakasta voi nostaa ja siihen voi laittaa kortteja.
     *
     * @param kortit Aloituskortit
     */
    public KorttiPakka(List<Kortti> kortit) {
        this.kortit = kortit;
    }

    /**
     * Kuvastaa korttipakkaa, pakasta voi nostaa ja siihen voi laittaa kortteja.
     * Alustaa tyhjän korttipakan
     */
    public KorttiPakka() {
        kortit = new ArrayList<>();
    }

    /**
     * Metodi jolla voi nostaa pakasta kortin, palauttaa ylimmän kortin ja
     * samalla poistaa sen pakasta. Toimii samalla tavalla kuin ylin() mutta
     * samalla poistaa kortin pakasta.
     *
     * @return null jos korttipakka on tyhjä, muulloin ylin kortti.
     */
    public Kortti nosta() {
        if (kortit.size() > 0) {
            Kortti nostettava = kortit.get(kortit.size() - 1);
            kortit.remove(kortit.size() - 1);
            return nostettava;
        }
        return null;
    }

    /**
     * Palauttaa ylimmän kortin pakassa, mutta ei tuhoa sitä kuten nosta().
     *
     * @return null jos korttipakka on tyhjä, muulloin ylin kortti.
     */
    public Kortti ylin() {
        if (kortit.size() > 0) {
            Kortti ylinKortti = kortit.get(kortit.size() - 1);
            return ylinKortti;
        }
        return null;
    }

    /**
     * Metodilla voi tarkistaa voiko korttia laittaa tähän korttipakkaan. Kortin
     * voi laittaa jos väri tai tyyppi on sama tai kortti on erikoiskortti.
     *
     * @param kortti Kortti jonka haluat laittaa
     * @return true jos voi laitta, muulloin false
     */
    public boolean voikoLaittaaKortin(Kortti kortti) {
        if (kortti != null) {
            Kortti ylinKortti = ylin();

            if (kortti.getVari() == KorttiVari.ERIKOIS || kortti.getVari() == ylinKortti.getVari()
                    || ylinKortti.getVari() == KorttiVari.ERIKOIS && !(ylinKortti.getTyyppi() == KorttiTyyppi.VILLI || ylinKortti.getTyyppi() == KorttiTyyppi.VILLINOSTANELJA)) {
                //Värisääntö + erikoiskortti
                return true;
            } else if (kortti.getTyyppi() == ylinKortti.getTyyppi()) {
                //Sama tyyppi sääntö
                return true;
            }
        }
        return false;
    }

    /**
     * Alustaa korttipakan annetuilla säännöillä kun halutaan tehdä nostopakka
     * peliin.
     *
     * @param erikoiskorttiValinnat Erikoiskorttivalinnat , katso PeliAsetukset
     * @param korttiKerroin kerroin jolla kerrotaan korttien määrä pakassa
     */
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

    /**
     * Lisää kortin pakosti pakkaan.
     * (ei tarkasta sääntöjä)
     *
     * @param kortti Kortti joka halutaan lisätä
     */
    public void lisaa(Kortti kortti) {
        if (kortti != null) {
            kortit.add(kortti);
        }
    }

    /**
     * Palauttaa jäljellä olevien korttien määrän.
     *
     * @return jäljellä olevien korttien määrä
     */
    public int jaljella() {
        return kortit.size();
    }

    /**
     * Sekoittaa pakan satunnaiseen järjestykseen.
     */
    public void sekoita() {
        long seed = System.nanoTime();
        Collections.shuffle(kortit, new Random(seed));
    }

    public List<Kortti> getKortit() {
        return kortit;
    }

}
