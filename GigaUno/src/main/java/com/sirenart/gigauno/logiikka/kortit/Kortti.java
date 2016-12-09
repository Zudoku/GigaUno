/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sirenart.gigauno.logiikka.kortit;

/**
 * Kuvastaa yhtä uno korttia. Kortilla on väri ja tyyppi.
 *
 * @author arska
 */
public class Kortti {

    private KorttiVari vari;
    private KorttiTyyppi tyyppi;

    /**
     * Kuvastaa yhtä uno korttia. Kortilla on väri ja tyyppi.
     *
     * @param vari kortin väri
     * @param tyyppi kortin tyyppi
     */
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

    /**
     * Metodilla voi tarkistaa voiko korttia laittaa tämän kortin päälle. Kortin
     * voi laittaa jos väri tai tyyppi on sama tai kortti on erikoiskortti.
     *
     * @param kortti Kortti jonka haluat laittaa
     * @param korttejaLaitettu  kuinka monta korttia on laitettu
     * @return true jos voi laittaa, muulloin false
     */
    public boolean voikoLaittaaKortin(Kortti kortti, int korttejaLaitettu) {
        if (korttejaLaitettu >= 5) {
            return false;
        }

        if (kortti != null) {
            Kortti ylinKortti = this;

            if (kortti.getVari() == KorttiVari.ERIKOIS || kortti.getVari() == ylinKortti.getVari()
                    || ylinKortti.getVari() == KorttiVari.ERIKOIS) {
                //Värisääntö + erikoiskortti
                return true;
            } else if (kortti.getTyyppi() == ylinKortti.getTyyppi()) {
                //Sama tyyppi sääntö
                return true;
            }
        }
        return false;
    }

}
