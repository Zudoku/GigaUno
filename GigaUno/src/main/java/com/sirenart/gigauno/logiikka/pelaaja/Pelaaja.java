/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sirenart.gigauno.logiikka.pelaaja;

/**
 *
 * @author arska
 */
public class Pelaaja {

    private String nimimerkki; // Pelaajan nimimerkki
    private int pisteita; //Kuinka monta pistettä pelaajalla on
    private int eraVoittoja; // Kuinka monta erää pelaaja on voittanut
    private EraTiedot eraTiedot; //Pelaajan käynnissä olevan erän tiedot

    public Pelaaja(String nimimerkki) {
        this.nimimerkki = nimimerkki;
        this.pisteita = 0;
        this.eraVoittoja = 0;
    }

    public int getEraVoittoja() {
        return eraVoittoja;
    }

    public String getNimimerkki() {
        return nimimerkki;
    }

    public int getPisteita() {
        return pisteita;
    }

    public void setEraVoittoja(int eraVoittoja) {
        this.eraVoittoja = eraVoittoja;
    }

    public void setPisteita(int pisteita) {
        this.pisteita = pisteita;
    }

    public void setEraTiedot(EraTiedot eraTiedot) {
        this.eraTiedot = eraTiedot;
    }

    public EraTiedot getEraTiedot() {
        return eraTiedot;
    }

}
