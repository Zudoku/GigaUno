/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sirenart.gigauno.logiikka.peli;

import com.sirenart.gigauno.logiikka.era.PeliAlusta;
import com.sirenart.gigauno.logiikka.pelaaja.Pelaaja;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arska
 */
public class UnoPeli {

    private boolean pelikesken = false;
    private boolean voittajaLoytynyt = false;
    private Pelaaja voittaja = null;

    private boolean pelaajatLisatty;
    private PeliAsetukset peliAsetukset;

    private List<Pelaaja> pelaajat;
    private PeliAlusta nykyinenEra;

    /**
     * UnoPeli on luokka joka kuvaa koko Uno peliä. 
     * UnoPelillä on PeliAlusta joka kuvaa nykyistä erää ja tiedot pelaajista ja onko peli kesken vai ei.
     */
    public UnoPeli() {
        this.pelaajatLisatty = false;
        this.peliAsetukset = null;
        this.pelaajat = new ArrayList<>();
    }
    /**
     * Päätä Pelaajien lisäysvaihe. 
     * Metodi palauttaa booleanin siitä onnistuiko siirtyminen seuraavaan pelin vaiheeseen vai ei.
     * Yksi ehdoista on se, että pelissä on ainakin kaksi pelaajaa.
     * @return boolean joka kertoo onnistuiko tilan päättäminen
     */
    public boolean paataPelaajienLisays() {
        if (pelaajat.size() < 2) {
            return false;
        }
        this.pelaajatLisatty = true;
        return true;
    }
    /**
     * Metodi jolla voi lisätä peliin yhden pelaajan. 
     * Pelaajan lisäys voi epäonnistua jos pelaajien lisäysvaihe on ohi 
     * tai samanniminen pelaaja on jo olemassa tai jos nimimerkki on tyhjä.
     * @param pelaaja Pelaaja jonka haluat lisätä peliin
     * @return false jos epäonnistuu, muulloin true.
     */
    public boolean lisaaPelaaja(Pelaaja pelaaja) {
        if (pelaajatLisatty || pelaaja.getNimimerkki() == null || pelaaja.getNimimerkki().isEmpty()) {
            return false;
        }
        for(Pelaaja p : pelaajat) {
            if(p.getNimimerkki().equals(pelaaja.getNimimerkki())) { 
                return false;
            }
        }
        pelaajat.add(pelaaja);
        return true;
    }

    public void setPeliAsetukset(PeliAsetukset peliAsetukset) {
        this.peliAsetukset = peliAsetukset;
    }
    /**
     * Metodi jota kutsutaan kun halutaan aloittaa peli.
     * @return false jos aloitus epäonnistuu, muulloin true
     */
    public boolean aloitaPeli() {
        if (pelaajatLisatty && peliAsetukset != null) {
            pelikesken = true;
            return true;
        }
        return false;
    }
    /**
     * Metodi jota kutsutaan kun halutaan aloittaa seuraava erä.
     * @return false jos erän aloittaminen ei onnistu, muulloin true
     */
    public boolean seuraavaEra(){
        tarkistaVoitto();
        if(nykyinenEra == null || nykyinenEra.isEraLoppu() ){
            if(!pelikesken) {
                return false;
            }
            nykyinenEra = new PeliAlusta(pelaajat, peliAsetukset.getErikoiskorttiValinnat(), peliAsetukset.getKorttiKerroin());
            return true;
        }
        return false;
    }
    /**
     * Metodi joka palauttaa onko koko peli ohi eli onko voittaja löytynyt.
     * @return true jos peli on ohi, muulloin false
     */
    public boolean peliOhi(){
        tarkistaVoitto();
        if(!pelikesken && voittajaLoytynyt){
            return true;
        }
        return false;
    }
    /**
     * Tarkistaa onko kukaan voittanut peliä.
     */
    private void tarkistaVoitto(){
        for(Pelaaja pelaaja : pelaajat) {
            if(pelaaja.getPisteita() >= peliAsetukset.getVoittoRaja()){
                pelikesken = false;
                voittajaLoytynyt = true;
                voittaja = pelaaja;
                break;
            }
        }
    }

    public List<Pelaaja> getPelaajat() {
        return pelaajat;
    }

    public PeliAsetukset getPeliAsetukset() {
        return peliAsetukset;
    }

    public PeliAlusta getNykyinenEra() {
        return nykyinenEra;
    }
    
    
    
    

}
