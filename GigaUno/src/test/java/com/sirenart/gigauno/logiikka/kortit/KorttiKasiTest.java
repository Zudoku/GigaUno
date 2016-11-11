/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sirenart.gigauno.logiikka.kortit;

import static org.junit.Assert.*;

/**
 * Created Nov 11, 2016
 * @author arska
 */
public class KorttiKasiTest {
    
    
    @org.junit.Test
    public void testEiVoiPoistaaTyhjaaKorttia(){
        KorttiKasi pakka = new KorttiKasi();
        assertNull(pakka.poista(0));
        assertNull(pakka.poista(1));
    }
}
