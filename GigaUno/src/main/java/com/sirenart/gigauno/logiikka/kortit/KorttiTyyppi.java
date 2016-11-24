package com.sirenart.gigauno.logiikka.kortit;

/**
 *
 * @author arska
 */
public enum KorttiTyyppi {

    NOLLA(1, 4), YKSI(2, 4),
    KAKSI(3, 4), KOLME(4, 4),
    NELJA(5, 2), VIISI(6, 4),
    KUUSI(7, 4), SEITSEMAN(8, 4),
    KAHDEKSAN(9, 4), YHDEKSAN(10, 4),
    NOSTAKAKSI(11, 2),
    NOSTANELJA(11, 2),
    SUUNNANVAIHTO(11, 2),
    HURRIKAANI(20, 1),
    OHITUS(11, 2),
    VILLI(11, 2),
    VILLINOSTANELJA(15, 2);

    /**
     * Kuvastaa kortin tyyppiä.
     *
     * @param pisteArvo Kuinka monta pistettä kortti antaa pelin lopussa.
     * @param pakassaKortteja Kuinka monta korttia on pakassa ennen
     * korttikerrointa.
     */
    KorttiTyyppi(int pisteArvo, int pakassaKortteja) {
        this.pisteArvo = pisteArvo;
        this.pakassaKortteja = pakassaKortteja;
    }
    private final int pisteArvo;
    private final int pakassaKortteja;

    public int getPakassaKortteja() {
        return pakassaKortteja;
    }

    public int getPisteArvo() {
        return pisteArvo;
    }

}
