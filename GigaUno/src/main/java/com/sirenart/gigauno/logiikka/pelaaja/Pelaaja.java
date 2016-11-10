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
    private int pisteitä; //Kuinka monta pistettä pelaajalla on
    private int eraVoittoja; // Kuinka monta erää pelaaja on voittanut
    private EraTiedot eraTiedot; //Pelaajan käynnissä olevan erän tiedot

    public Pelaaja(String nimimerkki) {
        this.nimimerkki = nimimerkki;
        this.pisteitä = 0;
        this.eraVoittoja = 0;
    }

    public int getEraVoittoja() {
        return eraVoittoja;
    }

    public String getNimimerkki() {
        return nimimerkki;
    }

    public int getPisteitä() {
        return pisteitä;
    }

    public void setEraVoittoja(int eraVoittoja) {
        this.eraVoittoja = eraVoittoja;
    }

    public void setPisteitä(int pisteitä) {
        this.pisteitä = pisteitä;
    }

    public void setEraTiedot(EraTiedot eraTiedot) {
        this.eraTiedot = eraTiedot;
    }

    public EraTiedot getEraTiedot() {
        return eraTiedot;
    }
    
    
    
    
    
    
}
