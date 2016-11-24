/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sirenart.gigauno.logiikka.era;

import static com.sirenart.gigauno.logiikka.era.PeliAlustaTest.getDefaultValinnat;
import com.sirenart.gigauno.logiikka.kortit.Kortti;
import com.sirenart.gigauno.logiikka.kortit.KorttiPakka;
import com.sirenart.gigauno.logiikka.kortit.KorttiTyyppi;
import com.sirenart.gigauno.logiikka.kortit.KorttiVari;
import com.sirenart.gigauno.logiikka.pelaaja.Pelaaja;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

/**
 * Created Nov 18, 2016
 *
 * @author arska
 */
public class KortinLaittoTest {

    @org.junit.Test
    public void testVoiLaittaaErikoiskortinAina() {
        List<Pelaaja> pelaajat = new ArrayList<>();
        pelaajat.add(new Pelaaja("Aatami"));
        pelaajat.add(new Pelaaja("Bertta"));

        Map<KorttiTyyppi, Boolean> erikoiskorttiValinnat = getDefaultValinnat();

        PeliAlusta alusta = new PeliAlusta(pelaajat, erikoiskorttiValinnat, 1);
        
        Kortti laitettuKortti = new Kortti(KorttiVari.VIHREA, KorttiTyyppi.YKSI);
        Kortti erikoisKortti = new Kortti(KorttiVari.ERIKOIS, KorttiTyyppi.NOSTAKAKSI);
        
        alusta.nykyinenPelaaja().getEraTiedot().getKortit().lisaa(laitettuKortti);
        alusta.nykyinenPelaaja().getEraTiedot().getKortit().lisaa(erikoisKortti);
        
        alusta.pelaajaLaittaaKortin(laitettuKortti);
        assertTrue(alusta.voikoPelaajaLaittaaKortin(erikoisKortti));
        
        laitettuKortti = new Kortti(KorttiVari.SININEN, KorttiTyyppi.YKSI);
        alusta.nykyinenPelaaja().getEraTiedot().getKortit().lisaa(laitettuKortti);
        alusta.nykyinenPelaaja().getEraTiedot().getKortit().lisaa(erikoisKortti);

        alusta.pelaajaLaittaaKortin(laitettuKortti);
        assertTrue(alusta.voikoPelaajaLaittaaKortin(erikoisKortti));
        
        laitettuKortti = new Kortti(KorttiVari.PUNAINEN, KorttiTyyppi.YKSI);
        alusta.nykyinenPelaaja().getEraTiedot().getKortit().lisaa(laitettuKortti);
        alusta.nykyinenPelaaja().getEraTiedot().getKortit().lisaa(erikoisKortti);

        alusta.pelaajaLaittaaKortin(laitettuKortti);
        assertTrue(alusta.voikoPelaajaLaittaaKortin(erikoisKortti));
        
        laitettuKortti = new Kortti(KorttiVari.KELTAINEN, KorttiTyyppi.YKSI);
        alusta.nykyinenPelaaja().getEraTiedot().getKortit().lisaa(laitettuKortti);
        alusta.nykyinenPelaaja().getEraTiedot().getKortit().lisaa(erikoisKortti);

        alusta.pelaajaLaittaaKortin(laitettuKortti);
        assertTrue(alusta.voikoPelaajaLaittaaKortin(erikoisKortti));
        
        laitettuKortti = new Kortti(KorttiVari.ERIKOIS, KorttiTyyppi.VILLINOSTANELJA);
        alusta.nykyinenPelaaja().getEraTiedot().getKortit().lisaa(laitettuKortti);
        alusta.nykyinenPelaaja().getEraTiedot().getKortit().lisaa(erikoisKortti);

        alusta.pelaajaLaittaaKortin(laitettuKortti);
        assertTrue(alusta.voikoPelaajaLaittaaKortin(erikoisKortti));
    }
    
    @org.junit.Test
    public void testKortinLaittoMeneeKutenPitaa() {
        List<Pelaaja> pelaajat = new ArrayList<>();
        pelaajat.add(new Pelaaja("Aatami"));
        pelaajat.add(new Pelaaja("Bertta"));

        Map<KorttiTyyppi, Boolean> erikoiskorttiValinnat = getDefaultValinnat();

        PeliAlusta alusta = new PeliAlusta(pelaajat, erikoiskorttiValinnat, 1);
        
        
        KorttiPakka pakka = alusta.getLaittoPakka();
        int initialSize = pakka.getKortit().size();
        
        Kortti laitettuKortti = new Kortti(KorttiVari.ERIKOIS, KorttiTyyppi.NOSTAKAKSI);
        alusta.nykyinenPelaaja().getEraTiedot().getKortit().lisaa(laitettuKortti);
        
        assertTrue(alusta.pelaajaLaittaaKortin(laitettuKortti));
        assertEquals(initialSize +1, pakka.getKortit().size());
        
        laitettuKortti = new Kortti(KorttiVari.VIHREA, KorttiTyyppi.YKSI);
        alusta.nykyinenPelaaja().getEraTiedot().getKortit().lisaa(laitettuKortti);
        
        alusta.pelaajaLaittaaKortin(laitettuKortti);
        
        laitettuKortti = new Kortti(KorttiVari.SININEN, KorttiTyyppi.KOLME);
        alusta.nykyinenPelaaja().getEraTiedot().getKortit().lisaa(laitettuKortti);
        
        assertFalse(alusta.voikoPelaajaLaittaaKortin(laitettuKortti));
        
        laitettuKortti = new Kortti(KorttiVari.SININEN, KorttiTyyppi.KOLME);
        alusta.nykyinenPelaaja().getEraTiedot().getKortit().lisaa(laitettuKortti);
        
        assertFalse(alusta.pelaajaLaittaaKortin(laitettuKortti));
    }
    
    @org.junit.Test
    public void testErikoiskorttienEffektit() {
        List<Pelaaja> pelaajat = new ArrayList<>();
        pelaajat.add(new Pelaaja("Aatami"));
        pelaajat.add(new Pelaaja("Bertta"));


        Map<KorttiTyyppi, Boolean> erikoiskorttiValinnat = getDefaultValinnat();

        PeliAlusta alusta = new PeliAlusta(pelaajat, erikoiskorttiValinnat, 1);
        int initialCardCount = alusta.seuraavaPelaaja().getEraTiedot().getKortit().getKortit().size();
        
        Kortti laitettuKortti = new Kortti(KorttiVari.ERIKOIS, KorttiTyyppi.NOSTAKAKSI);
        alusta.nykyinenPelaaja().getEraTiedot().getKortit().lisaa(laitettuKortti);
        alusta.pelaajaLaittaaKortin(laitettuKortti);
        
        assertEquals(initialCardCount + 2, alusta.seuraavaPelaaja().getEraTiedot().getKortit().getKortit().size());
        
        initialCardCount = alusta.seuraavaPelaaja().getEraTiedot().getKortit().getKortit().size();
        
        laitettuKortti = new Kortti(KorttiVari.ERIKOIS, KorttiTyyppi.NOSTANELJA);
        alusta.nykyinenPelaaja().getEraTiedot().getKortit().lisaa(laitettuKortti);
        alusta.pelaajaLaittaaKortin(laitettuKortti);
        
        assertEquals(initialCardCount + 4, alusta.seuraavaPelaaja().getEraTiedot().getKortit().getKortit().size());
        
        
        boolean nykyinenKaanteinenKierrosArvo = alusta.isKaanteinenKierros();
        
        laitettuKortti = new Kortti(KorttiVari.ERIKOIS, KorttiTyyppi.SUUNNANVAIHTO);
        alusta.nykyinenPelaaja().getEraTiedot().getKortit().lisaa(laitettuKortti);
        alusta.pelaajaLaittaaKortin(laitettuKortti);
        assertEquals(nykyinenKaanteinenKierrosArvo, !alusta.isKaanteinenKierros());
    }
    
    

}
