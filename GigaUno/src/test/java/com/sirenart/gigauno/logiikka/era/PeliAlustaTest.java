/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sirenart.gigauno.logiikka.era;

import com.sirenart.gigauno.logiikka.kortit.Kortti;
import com.sirenart.gigauno.logiikka.kortit.KorttiTyyppi;
import com.sirenart.gigauno.logiikka.kortit.KorttiVari;
import com.sirenart.gigauno.logiikka.pelaaja.Pelaaja;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author arska
 */
public class PeliAlustaTest {

    public PeliAlustaTest() {

    }

    public static Map<KorttiTyyppi, Boolean> getDefaultValinnat() {
        Map<KorttiTyyppi, Boolean> erikoiskorttiValinnat = new HashMap<>();

        erikoiskorttiValinnat.put(KorttiTyyppi.HURRIKAANI, Boolean.TRUE);
        erikoiskorttiValinnat.put(KorttiTyyppi.NOSTAKAKSI, Boolean.TRUE);
        erikoiskorttiValinnat.put(KorttiTyyppi.NOSTANELJA, Boolean.TRUE);
        erikoiskorttiValinnat.put(KorttiTyyppi.OHITUS, Boolean.TRUE);
        erikoiskorttiValinnat.put(KorttiTyyppi.SUUNNANVAIHTO, Boolean.TRUE);
        erikoiskorttiValinnat.put(KorttiTyyppi.VILLI, Boolean.TRUE);
        erikoiskorttiValinnat.put(KorttiTyyppi.VILLINOSTANELJA, Boolean.TRUE);
        return erikoiskorttiValinnat;
    }

    @org.junit.Test
    public void testPelialustaInitialisoituu() {

        List<Pelaaja> pelaajat = new ArrayList<>();
        pelaajat.add(new Pelaaja("Aatami"));
        pelaajat.add(new Pelaaja("Bertta"));

        Map<KorttiTyyppi, Boolean> erikoiskorttiValinnat = getDefaultValinnat();

        PeliAlusta alusta = new PeliAlusta(pelaajat, erikoiskorttiValinnat, 1);
        //Varmistetaan, että laittopakassa on pelin alussa yksi kortti
        assertTrue(alusta.getLaittoPakka().jaljella() == 1);
        //Varmistetaan ettei nostopakka ole tyhjä
        assertTrue(alusta.getNostoPakka().jaljella() > 1);
        //Varmistetaan ettei ole voittajaa
        assertNull(alusta.getVoittaja());
        //Varmistetaan ettei ole loppunut erä heti luodessa
        assertFalse(alusta.isEraLoppu());
    }

    @org.junit.Test
    public void testPelaajillaOnKortit() {
        List<Pelaaja> pelaajat = new ArrayList<>();
        pelaajat.add(new Pelaaja("Aatami"));
        pelaajat.add(new Pelaaja("Bertta"));

        Map<KorttiTyyppi, Boolean> erikoiskorttiValinnat = getDefaultValinnat();

        PeliAlusta alusta = new PeliAlusta(pelaajat, erikoiskorttiValinnat, 1);

        for (Pelaaja pelaaja : alusta.getPelaajat()) {
            //Varmistetaan ettei ole tyhjä
            assertFalse(pelaaja.getEraTiedot().getKortit().getKortit().isEmpty());
            //Varmistetaan että on tietty määrä kortteja
            assertTrue(pelaaja.getEraTiedot().getKortit().getKortit().size() == PeliAlusta.ALOITUS_KORTTI_MAARA);
        }
    }

    @org.junit.Test
    public void testEiVoiVaihtaaVuoroaHeti() {
        List<Pelaaja> pelaajat = new ArrayList<>();
        pelaajat.add(new Pelaaja("Aatami"));
        pelaajat.add(new Pelaaja("Bertta"));

        Map<KorttiTyyppi, Boolean> erikoiskorttiValinnat = getDefaultValinnat();

        PeliAlusta alusta = new PeliAlusta(pelaajat, erikoiskorttiValinnat, 1);
        alusta.aloitaEra();
        //Varmistetaan ettei vuoron alussa voi vaihtaa heti vuoroa
        assertFalse(alusta.pelaajaLopettaaVuoron());
    }

    @org.junit.Test
    public void testVoiVaihtaaVuoronKortinLaitonJalkeen() {
        List<Pelaaja> pelaajat = new ArrayList<>();
        pelaajat.add(new Pelaaja("Aatami"));
        pelaajat.add(new Pelaaja("Bertta"));

        Map<KorttiTyyppi, Boolean> erikoiskorttiValinnat = getDefaultValinnat();

        PeliAlusta alusta = new PeliAlusta(pelaajat, erikoiskorttiValinnat, 1);
        alusta.aloitaEra();

        alusta.nykyinenPelaaja().getEraTiedot().getKortit().lisaa(new Kortti(KorttiVari.ERIKOIS, KorttiTyyppi.VILLI));
        alusta.pelaajaLaittaaKortin(new Kortti(KorttiVari.ERIKOIS, KorttiTyyppi.VILLI));
        //Varmistetaan että vuoron voi vaihtaa laitettuaan kortin pöytään
        assertTrue(alusta.pelaajaLopettaaVuoron());
    }

    @org.junit.Test
    public void testVoiVaihtaaVuoronKortinNostonJalkeen() {
        List<Pelaaja> pelaajat = new ArrayList<>();
        pelaajat.add(new Pelaaja("Aatami"));
        pelaajat.add(new Pelaaja("Bertta"));

        Map<KorttiTyyppi, Boolean> erikoiskorttiValinnat = getDefaultValinnat();

        PeliAlusta alusta = new PeliAlusta(pelaajat, erikoiskorttiValinnat, 1);
        alusta.aloitaEra();
        alusta.pelaajaNostaaKortin();
        //Varmistetaan että vuoron voi vaihtaa nostettuaan kortin pakasta
        assertTrue(alusta.pelaajaLopettaaVuoron());
    }

    @org.junit.Test
    public void testVuoroVaihtuuEteenpain() {
        List<Pelaaja> pelaajat = new ArrayList<>();
        pelaajat.add(new Pelaaja("Aatami"));
        pelaajat.add(new Pelaaja("Bertta"));

        Map<KorttiTyyppi, Boolean> erikoiskorttiValinnat = getDefaultValinnat();

        PeliAlusta alusta = new PeliAlusta(pelaajat, erikoiskorttiValinnat, 1);
        alusta.aloitaEra();
        alusta.setNykyinenPelaajaIndeksi(0);

        Pelaaja seuraavaPelaaja = alusta.seuraavaPelaaja();

        alusta.pelaajaNostaaKortin();
        alusta.pelaajaLopettaaVuoron();

        assertSame(seuraavaPelaaja, alusta.nykyinenPelaaja());
        assertEquals(1, alusta.getNykyinenPelaajaIndeksi());
    }

    @org.junit.Test
    public void testVuoroVaihtuuTaaksepain() {
        List<Pelaaja> pelaajat = new ArrayList<>();
        pelaajat.add(new Pelaaja("Aatami"));
        pelaajat.add(new Pelaaja("Bertta"));

        Map<KorttiTyyppi, Boolean> erikoiskorttiValinnat = getDefaultValinnat();

        PeliAlusta alusta = new PeliAlusta(pelaajat, erikoiskorttiValinnat, 1);
        alusta.aloitaEra();
        alusta.setKaanteinenKierros(true);
        alusta.setNykyinenPelaajaIndeksi(1);

        Pelaaja seuraavaPelaaja = alusta.seuraavaPelaaja();

        alusta.pelaajaNostaaKortin();
        alusta.pelaajaLopettaaVuoron();

        assertSame(seuraavaPelaaja, alusta.nykyinenPelaaja());
        assertEquals(0, alusta.getNykyinenPelaajaIndeksi());
    }

    @org.junit.Test
    public void testVuoroVaihtuuYmpari() {
        List<Pelaaja> pelaajat = new ArrayList<>();
        pelaajat.add(new Pelaaja("Aatami"));
        pelaajat.add(new Pelaaja("Bertta"));

        Map<KorttiTyyppi, Boolean> erikoiskorttiValinnat = getDefaultValinnat();

        PeliAlusta alusta = new PeliAlusta(pelaajat, erikoiskorttiValinnat, 1);
        alusta.aloitaEra();
        alusta.setKaanteinenKierros(true);
        alusta.setNykyinenPelaajaIndeksi(1);

        alusta.pelaajaNostaaKortin();
        alusta.pelaajaLopettaaVuoron();

        assertEquals(0, alusta.getNykyinenPelaajaIndeksi());

        alusta.pelaajaNostaaKortin();
        alusta.pelaajaLopettaaVuoron();

        assertEquals(1, alusta.getNykyinenPelaajaIndeksi());

        alusta.pelaajaNostaaKortin();
        alusta.pelaajaLopettaaVuoron();

        assertEquals(0, alusta.getNykyinenPelaajaIndeksi());
    }

}
