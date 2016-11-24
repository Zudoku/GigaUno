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

        alusta.pelaajaLaittaaKortin(new Kortti(KorttiVari.VIHREA, KorttiTyyppi.YKSI));
        assertTrue(alusta.voikoPelaajaLaittaaKortin(new Kortti(KorttiVari.ERIKOIS, KorttiTyyppi.NOSTAKAKSI)));

        alusta.pelaajaLaittaaKortin(new Kortti(KorttiVari.SININEN, KorttiTyyppi.YKSI));
        assertTrue(alusta.voikoPelaajaLaittaaKortin(new Kortti(KorttiVari.ERIKOIS, KorttiTyyppi.NOSTAKAKSI)));

        alusta.pelaajaLaittaaKortin(new Kortti(KorttiVari.PUNAINEN, KorttiTyyppi.YKSI));
        assertTrue(alusta.voikoPelaajaLaittaaKortin(new Kortti(KorttiVari.ERIKOIS, KorttiTyyppi.NOSTAKAKSI)));

        alusta.pelaajaLaittaaKortin(new Kortti(KorttiVari.KELTAINEN, KorttiTyyppi.YKSI));
        assertTrue(alusta.voikoPelaajaLaittaaKortin(new Kortti(KorttiVari.ERIKOIS, KorttiTyyppi.NOSTAKAKSI)));

        alusta.pelaajaLaittaaKortin(new Kortti(KorttiVari.ERIKOIS, KorttiTyyppi.HURRIKAANI));
        assertTrue(alusta.voikoPelaajaLaittaaKortin(new Kortti(KorttiVari.ERIKOIS, KorttiTyyppi.NOSTAKAKSI)));
    }

}
