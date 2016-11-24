/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sirenart.gigauno.logiikka.peli;

import com.sirenart.gigauno.logiikka.pelaaja.Pelaaja;
import static org.junit.Assert.*;

/**
 * Created Nov 24, 2016
 * @author arska
 */
public class UnoPeliTest {
    
    @org.junit.Test
    public void testUnoPeliInitialisoituu(){
        UnoPeli peli = new UnoPeli();
        
        assertFalse(peli.isPelikesken());
        assertFalse(peli.isPelaajatLisatty());
        assertFalse(peli.isVoittajaLoytynyt());
        assertNull(peli.getVoittaja());
        assertFalse(peli.peliOhi());
        assertFalse(peli.isPelaajatLisatty());
        assertNull(peli.getPeliAsetukset());
        assertEquals(0, peli.getPelaajat().size());
        assertFalse(peli.aloitaPeli());
    }
    
    @org.junit.Test
    public void testPelaajienLisays(){
        UnoPeli peli = new UnoPeli();
        assertFalse(peli.paataPelaajienLisays());
        Pelaaja a = new Pelaaja("a");
        assertTrue(peli.lisaaPelaaja(a));
        assertFalse(peli.paataPelaajienLisays());
        
        Pelaaja aa = new Pelaaja("a");
        assertFalse(peli.lisaaPelaaja(aa));
        
        Pelaaja b = new Pelaaja("b");
        assertTrue(peli.lisaaPelaaja(b));
        assertTrue(peli.paataPelaajienLisays());
        Pelaaja c = new Pelaaja("c");
        assertFalse(peli.lisaaPelaaja(c));
        assertTrue(peli.isPelaajatLisatty());
        
    }
    
    @org.junit.Test
    public void testAsetuksienAsetus(){
        UnoPeli peli = new UnoPeli();
        Pelaaja a = new Pelaaja("a");
        Pelaaja b = new Pelaaja("b");
        peli.lisaaPelaaja(a);
        peli.lisaaPelaaja(b);
        peli.paataPelaajienLisays();
        
        
        peli.setPeliAsetukset(PeliAsetukset.getDefaultAsetukset());
        assertTrue(peli.aloitaPeli());
        assertTrue(peli.isPelikesken());
    }
    
    @org.junit.Test
    public void testEranAloitus(){
        UnoPeli peli = new UnoPeli();
        Pelaaja a = new Pelaaja("a");
        Pelaaja b = new Pelaaja("b");
        peli.lisaaPelaaja(a);
        peli.lisaaPelaaja(b);
        assertFalse(peli.seuraavaEra());
        peli.paataPelaajienLisays();
        assertFalse(peli.seuraavaEra());
        peli.setPeliAsetukset(PeliAsetukset.getDefaultAsetukset());
        peli.aloitaPeli();
        
        assertTrue(peli.seuraavaEra());
        assertNotNull(peli.getNykyinenEra());
    }

}
