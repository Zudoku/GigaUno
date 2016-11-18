/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sirenart.gigauno.logiikka.kortit.pelaaja;

import com.sirenart.gigauno.logiikka.era.PeliAlusta;
import static com.sirenart.gigauno.logiikka.era.PeliAlustaTest.getDefaultValinnat;
import com.sirenart.gigauno.logiikka.kortit.Kortti;
import com.sirenart.gigauno.logiikka.kortit.KorttiKasi;
import com.sirenart.gigauno.logiikka.kortit.KorttiTyyppi;
import com.sirenart.gigauno.logiikka.kortit.KorttiVari;
import com.sirenart.gigauno.logiikka.pelaaja.EraTiedot;
import com.sirenart.gigauno.logiikka.pelaaja.Pelaaja;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created Nov 18, 2016
 * @author arska
 */
public class EratiedotTest {

    
    @org.junit.Test
    public void testUnoTila() {
        EraTiedot tiedot = new EraTiedot(1, new KorttiKasi());
        assertFalse(tiedot.onkoPelaajallaUnoTila());
        tiedot.getKortit().lisaa(new Kortti(KorttiVari.VIHREA, KorttiTyyppi.YKSI));
        assertTrue(tiedot.onkoPelaajallaUnoTila());
        tiedot.getKortit().lisaa(new Kortti(KorttiVari.VIHREA, KorttiTyyppi.KAKSI));
        assertFalse(tiedot.onkoPelaajallaUnoTila());
    }
    
    
}
