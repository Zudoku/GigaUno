/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sirenart.gigauno.logiikka.kortit;

/**
 *
 * @author arska
 */
public enum Kortti {
    
    NOLLA(1,4),YKSI(2,4),
    KAKSI(3,4),KOLME(4,2),
    NELJA(5,2),VIISI(6,4),
    KUUSI(7,4),SEITSEMAN(8,4),
    KAHDEKSAN(9,4),YHDEKSAN(10,4),
    
    NOSTAKAKSI(11,2),
    NOSTANELJA(11,2),
    SUUNNANVAIHTO(11,2),
    HURRIKAANI(20,1),
    OHITUS(11,2),
    VILLI(11,2),
    VILLINOSTANELJA(15,2);
    
    Kortti(int pisteArvo, int pakassaKortteja){
        this.pisteArvo = pisteArvo;
        this.pakassaKortteja = pakassaKortteja;
    }
    private KorttiVari vari = KorttiVari.ERIKOIS; //Kortin väri on defaultisti ERIKOIS, muissa tapausessa se setataan
    private final int pisteArvo;
    private final int pakassaKortteja;

    public KorttiVari getVari() {
        return vari;
    }

    public int getPakassaKortteja() {
        return pakassaKortteja;
    }

    public int getPisteArvo() {
        return pisteArvo;
    }

    public void setVari(KorttiVari vari) {
        this.vari = vari;
    }
    
    public Kortti getCopy(){
        Kortti kopio = values()[ordinal()];
        kopio.setVari(this.getVari());
        return kopio;
    }
    
    public Kortti varita(KorttiVari uusiVari){
        Kortti varitetty = this.getCopy();
        varitetty.setVari(uusiVari);
        return varitetty;
    }
    
}
