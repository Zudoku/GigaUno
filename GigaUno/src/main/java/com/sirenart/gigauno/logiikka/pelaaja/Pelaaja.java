package com.sirenart.gigauno.logiikka.pelaaja;

/**
 *
 * Pelaaja luokka mallintaa yhtä pelaajaa. Se pitää kirjaa pisteistä ja sillä on
 * EraTiedot luokka jossa on pelaajan nykyisen erän tiedot (kortit jne)
 *
 * @author arska
 */
public class Pelaaja {

    /**
     * Pelaajan nimimerkki
     */
    private String nimimerkki;
    /**
     * Kuinka monta pistettä pelaajalla on
     */
    private int pisteita;
    /**
     * Kuinka monta erää pelaaja on voittanut
     */
    private int eraVoittoja;
    /**
     * Pelaajan käynnissä olevan erän tiedot
     */
    private EraTiedot eraTiedot;

    /**
     * Pelaaja luokka mallintaa yhtä pelaajaa. Se pitää kirjaa pisteistä ja
     * sillä on EraTiedot luokka jossa on pelaajan nykyisen erän tiedot (kortit
     * jne)
     *
     * @param nimimerkki Pelaajan nimi
     */
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
