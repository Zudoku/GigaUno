/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sirenart.gigauno.logiikka.kortit;

import com.sirenart.gigauno.logiikka.era.PeliAlusta;
import static com.sirenart.gigauno.logiikka.era.PeliAlustaTest.getDefaultValinnat;
import com.sirenart.gigauno.logiikka.pelaaja.Pelaaja;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
/**
 * Created Nov 11, 2016
 * @author arska
 */
public class KorttiPakkaTest {
    
    
    @org.junit.Test
    public void testTyhjaPakkaPalauttaaNull(){
        KorttiPakka pakka = new KorttiPakka();
        assertNull(pakka.ylin());
        assertNull(pakka.nosta());
    }
    
    @org.junit.Test
    public void testPakkaSekoittuu(){
        List<Pelaaja> pelaajat = new ArrayList<>();
        pelaajat.add(new Pelaaja("Aatami"));
        pelaajat.add(new Pelaaja("Bertta"));

        Map<KorttiTyyppi, Boolean> erikoiskorttiValinnat = getDefaultValinnat();
        
        PeliAlusta alusta = new PeliAlusta(pelaajat, erikoiskorttiValinnat, 1);
        alusta.aloitaEra();
        
        List<Kortti> ennenSekoitusta = new ArrayList<>(alusta.getNostoPakka().getKortit());
        alusta.getNostoPakka().sekoita();
        assertNotEquals(ennenSekoitusta, alusta.getNostoPakka().getKortit());
    }
    
}
