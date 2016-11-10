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
    private KorttiKasi kortit;

    public EraTiedot(int pelaajaIndeksi, KorttiKasi kortit) {
        this.pelaajaIndeksi = pelaajaIndeksi;
        this.unoHuudettu = false;
        this.kortit = kortit;
    }

    public void setUnoHuudettu(boolean unoHuudettu) {
        this.unoHuudettu = unoHuudettu;
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
    
    public boolean onkoPelaajallaUnoTila(){
        return (kortit.getKortit().size() == 1);
    }
    
    
    
    
    
    
}
