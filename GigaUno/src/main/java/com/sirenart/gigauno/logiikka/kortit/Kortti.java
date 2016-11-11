/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sirenart.gigauno.logiikka.kortit;

/**
 * Created Nov 11, 2016
 * @author arska
 */
public class Kortti {
    private KorttiVari vari;
    private KorttiTyyppi tyyppi;

    public Kortti(KorttiVari vari, KorttiTyyppi tyyppi) {
        this.vari = vari;
        this.tyyppi = tyyppi;
    }

    public KorttiTyyppi getTyyppi() {
        return tyyppi;
    }

    public KorttiVari getVari() {
        return vari;
    }

    public void setVari(KorttiVari vari) {
        this.vari = vari;
    }
    
    

}
