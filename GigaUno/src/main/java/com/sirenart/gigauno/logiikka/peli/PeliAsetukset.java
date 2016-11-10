/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sirenart.gigauno.logiikka.peli;

import com.sirenart.gigauno.logiikka.kortit.Kortti;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author arska
 */
public class PeliAsetukset {
    private Map<Kortti, Boolean> erikoiskorttiValinnat;
    
    private int korttiKerroin;
    
    private long aikaRajoitus;
    
    private int voittoRaja;

    public PeliAsetukset(Map<Kortti, Boolean> erikoiskorttiValinnat, int korttiKerroin, long aikaRajoitus, int voittoRaja) {
        this.erikoiskorttiValinnat = erikoiskorttiValinnat;
        this.korttiKerroin = korttiKerroin;
        this.aikaRajoitus = aikaRajoitus;
        this.voittoRaja = voittoRaja;
    }

    public long getAikaRajoitus() {
        return aikaRajoitus;
    }

    public Map<Kortti, Boolean> getErikoiskorttiValinnat() {
        return erikoiskorttiValinnat;
    }

    public int getKorttiKerroin() {
        return korttiKerroin;
    }

    public int getVoittoRaja() {
        return voittoRaja;
    }
    
    
    
}
