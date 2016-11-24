/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sirenart.gigauno.logiikka.era;

import static com.sirenart.gigauno.logiikka.era.PeliAlustaTest.getDefaultValinnat;
import com.sirenart.gigauno.logiikka.kortit.Kortti;
import com.sirenart.gigauno.logiikka.kortit.KorttiTyyppi;
import com.sirenart.gigauno.logiikka.kortit.KorttiVari;
import com.sirenart.gigauno.logiikka.pelaaja.Pelaaja;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

/**
 * Created Nov 24, 2016
 * @author arska
 */
public class UnoTest {

    public UnoTest() {
        
    }
    
    @org.junit.Test
    public void testUnonHuuto() {
        List<Pelaaja> pelaajat = new ArrayList<>();
        pelaajat.add(new Pelaaja("Aatami"));
        pelaajat.add(new Pelaaja("Bertta"));

        Map<KorttiTyyppi, Boolean> erikoiskorttiValinnat = getDefaultValinnat();

        PeliAlusta alusta = new PeliAlusta(pelaajat, erikoiskorttiValinnat, 1);
        
        alusta.nykyinenPelaaja().getEraTiedot().getKortit().getKortit().clear();
        
        Kortti laitettuKortti = new Kortti(KorttiVari.ERIKOIS, KorttiTyyppi.NOSTAKAKSI);
        
        alusta.nykyinenPelaaja().getEraTiedot().getKortit().lisaa(laitettuKortti);
        assertTrue(alusta.nykyinenPelaaja().getEraTiedot().onkoPelaajallaUnoTila());
        assertTrue(alusta.pelaajaHuutaaUno());
        assertTrue(alusta.nykyinenPelaaja().getEraTiedot().isUnoHuudettu());
        
    }
    @org.junit.Test
    public void testUnonPaljastusJaPelinVoitto() {
        ArrayList<Pelaaja> pelaajat = new ArrayList<>();
        pelaajat.add(new Pelaaja("Aatami"));
        pelaajat.add(new Pelaaja("Bertta"));

        Map<KorttiTyyppi, Boolean> erikoiskorttiValinnat = getDefaultValinnat();

        PeliAlusta alusta = new PeliAlusta(pelaajat, erikoiskorttiValinnat, 1);
        
        alusta.nykyinenPelaaja().getEraTiedot().getKortit().getKortit().clear();
        alusta.seuraavaPelaaja().getEraTiedot().getKortit().getKortit().clear();
        
        alusta.seuraavaPelaaja().getEraTiedot().getKortit().lisaa(new Kortti(KorttiVari.ERIKOIS, KorttiTyyppi.NOSTAKAKSI));
        
        alusta.nykyinenPelaaja().getEraTiedot().getKortit().lisaa(new Kortti(KorttiVari.ERIKOIS, KorttiTyyppi.NOSTAKAKSI));
        alusta.nykyinenPelaaja().getEraTiedot().getKortit().lisaa(new Kortti(KorttiVari.ERIKOIS, KorttiTyyppi.NOSTAKAKSI));
        
        assertTrue(alusta.seuraavaPelaaja().getEraTiedot().onkoPelaajallaUnoTila());
        assertTrue(alusta.pelajaPaljastaaUnon(alusta.seuraavaPelaaja()));
        assertEquals(alusta.seuraavaPelaaja().getEraTiedot().getKortit().getKortit().size(), 3);
        
        assertTrue(alusta.pelaajaLaittaaKortin(alusta.nykyinenPelaaja().getEraTiedot().getKortit().getKortit().get(0)));
        assertTrue(alusta.pelaajaHuutaaUno());
        assertTrue(alusta.pelaajaLopettaaVuoron());
        assertFalse(alusta.pelajaPaljastaaUnon(alusta.seuraavaPelaaja()));
        
        
        alusta.pelaajaNostaaKortin();
        alusta.pelaajaLopettaaVuoron();
        
        assertTrue(alusta.pelaajaLaittaaKortin(alusta.nykyinenPelaaja().getEraTiedot().getKortit().getKortit().get(0)));
        assertTrue(alusta.isEraLoppu());
        assertEquals(alusta.nykyinenPelaaja(), alusta.getVoittaja());
        //assertEquals(pelaajat.get(0), alusta.getVoittaja());
        pelaajat.remove(alusta.getVoittaja());
        
        int pisteet = 0;
        for(Kortti kortti : pelaajat.get(0).getEraTiedot().getKortit().getKortit()){
            pisteet += kortti.getTyyppi().getPisteArvo();
        }
        assertEquals(alusta.getVoittaja().getPisteita(), pisteet);
        
    }
}
