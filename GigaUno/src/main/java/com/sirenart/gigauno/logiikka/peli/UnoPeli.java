/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sirenart.gigauno.logiikka.peli;

import com.sirenart.gigauno.logiikka.era.PeliAlusta;
import com.sirenart.gigauno.logiikka.pelaaja.Pelaaja;
import java.util.List;

/**
 *
 * @author arska
 */
public class UnoPeli {
    
    private boolean pelikesken = false;
    
    private boolean pelaajatLisatty;
    private PeliAsetukset peliAsetukset;
    
    private List<Pelaaja> pelaajat;
    private PeliAlusta nykyinenEra;

    public UnoPeli() {
        this.pelaajatLisatty = false;
        this.peliAsetukset = null;
    }
    
    public boolean paataPelaajienLisays(){
        if(pelaajat.size() < 2) {
            return false;
        }
        this.pelaajatLisatty = true;
        return true;
    }
    
    public boolean lisaaPelaaja(Pelaaja pelaaja){
        if(pelaajatLisatty){
            return false;
        }
        pelaajat.add(pelaaja);
        // TODO: Lisää checkkaus ettei voi lisätä samannimistä pelaajaa
        return true;
    }

    public void setPeliAsetukset(PeliAsetukset peliAsetukset) {
        this.peliAsetukset = peliAsetukset;
    }
    
    
    
    public void aloitaPeli(){
        if(pelaajatLisatty && peliAsetukset != null){
            pelikesken = true;
        }
    }
    
    
    
    
}
