/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sirenart.gigauno.logiikka.pelaaja;

import com.sirenart.gigauno.logiikka.kortit.KorttiKasi;

/**
 *
 * @author arska
 */
public class EraTiedot {

    private int pelaajaIndeksi; //Pelaajan paikka pelilaudalla
    private boolean unoHuudettu; // Onko pelaaja huutanut tällä kierroksella uno
    private boolean saaVoittaa;
    private KorttiKasi kortit;

    /**
     * Tämä luokka kuvastaa pelajan yhden erän tietoja.
     * Tällä luokalla on KorttiKasi jossa on tämänhetkiset kortit
     * ja pelaajaIndeksi ja tieto siitä onko UNO huudettu vai ei.
     * 
     * @param pelaajaIndeksi Tämän erän paikka pelissä
     * @param kortit Tämän erän aloituskortit
     */
    public EraTiedot(int pelaajaIndeksi, KorttiKasi kortit) {
        this.pelaajaIndeksi = pelaajaIndeksi;
        this.unoHuudettu = false;
        this.saaVoittaa = false;
        this.kortit = kortit;
    }

    public void setUnoHuudettu(boolean unoHuudettu) {
        this.unoHuudettu = unoHuudettu;
        if (unoHuudettu) {
            saaVoittaa = true;
        }
    }

    public boolean isUnoHuudettu() {
        return unoHuudettu;
    }

    public int getPelaajaIndeksi() {
        return pelaajaIndeksi;
    }

    public KorttiKasi getKortit() {
        return kortit;
    }

    public boolean onkoPelaajallaUnoTila() {
        return (kortit.getKortit().size() == 1);
    }

    public void setSaaVoittaa(boolean saaVoittaa) {
        this.saaVoittaa = saaVoittaa;
    }

    public boolean isSaaVoittaa() {
        return saaVoittaa;
    }

}
