/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sirenart.gigauno.logiikka.peli;

import com.sirenart.gigauno.logiikka.era.PeliAlustaTest;
import com.sirenart.gigauno.logiikka.kortit.KorttiTyyppi;
import java.util.Map;
import static org.junit.Assert.*;

/**
 * Created Nov 24, 2016
 * @author arska
 */
public class PeliAsetuksetTest {
    
    @org.junit.Test
    public void testAsetuksetInitialisoituu() {
        
        Map<KorttiTyyppi, Boolean> valinnat = PeliAlustaTest.getDefaultValinnat();
        int korttikerroin = 3;
        int aikaRajoitus = -1;
        int voittoRaja = 100;
        
        PeliAsetukset asetukset = new PeliAsetukset(valinnat, korttikerroin, aikaRajoitus, voittoRaja);
        assertEquals(asetukset.getErikoiskorttiValinnat(), valinnat);
        assertEquals(asetukset.getAikaRajoitus(), aikaRajoitus);
        assertEquals(asetukset.getKorttiKerroin(), korttikerroin);
        assertEquals(asetukset.getVoittoRaja(), voittoRaja);
        
        asetukset.setAikaRajoitus(3);
        asetukset.setErikoiskorttiValinnat(null);
        asetukset.setKorttiKerroin(7);
        asetukset.setVoittoRaja(200);
        
        assertEquals(asetukset.getAikaRajoitus(), 3);
        assertEquals(asetukset.getErikoiskorttiValinnat(), null);
        assertEquals(asetukset.getKorttiKerroin(), 7);
        assertEquals(asetukset.getVoittoRaja(), 200);
        assertNotNull(PeliAsetukset.getDefaultAsetukset());
        
    }
}
