/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sirenart.gigauno.logiikka.peli;

import com.sirenart.gigauno.logiikka.kortit.KorttiTyyppi;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author arska
 */
public class PeliAsetukset {
    private Map<KorttiTyyppi, Boolean> erikoiskorttiValinnat;
    
    private int korttiKerroin;
    
    private long aikaRajoitus;
    
    private int voittoRaja;

    public PeliAsetukset(Map<KorttiTyyppi, Boolean> erikoiskorttiValinnat, int korttiKerroin, long aikaRajoitus, int voittoRaja) {
        this.erikoiskorttiValinnat = erikoiskorttiValinnat;
        this.korttiKerroin = korttiKerroin;
        this.aikaRajoitus = aikaRajoitus;
        this.voittoRaja = voittoRaja;
    }

    public long getAikaRajoitus() {
        return aikaRajoitus;
    }

    public Map<KorttiTyyppi, Boolean> getErikoiskorttiValinnat() {
        return erikoiskorttiValinnat;
    }

    public int getKorttiKerroin() {
        return korttiKerroin;
    }

    public int getVoittoRaja() {
        return voittoRaja;
    }
    
    public static final PeliAsetukset getDefaultAsetukset(){
        Map<KorttiTyyppi, Boolean> erikoiskorttiValinnat = new HashMap<>();
        
        erikoiskorttiValinnat.put(KorttiTyyppi.HURRIKAANI, Boolean.TRUE);
        erikoiskorttiValinnat.put(KorttiTyyppi.NOSTAKAKSI, Boolean.TRUE);
        erikoiskorttiValinnat.put(KorttiTyyppi.NOSTANELJA, Boolean.TRUE);
        erikoiskorttiValinnat.put(KorttiTyyppi.OHITUS, Boolean.TRUE);
        erikoiskorttiValinnat.put(KorttiTyyppi.SUUNNANVAIHTO, Boolean.TRUE);
        erikoiskorttiValinnat.put(KorttiTyyppi.VILLI, Boolean.TRUE);
        erikoiskorttiValinnat.put(KorttiTyyppi.VILLINOSTANELJA, Boolean.TRUE);
        
        return new PeliAsetukset(erikoiskorttiValinnat, 2, -1, 500);
    }

    public void setAikaRajoitus(long aikaRajoitus) {
        this.aikaRajoitus = aikaRajoitus;
    }

    public void setErikoiskorttiValinnat(Map<KorttiTyyppi, Boolean> erikoiskorttiValinnat) {
        this.erikoiskorttiValinnat = erikoiskorttiValinnat;
    }

    public void setKorttiKerroin(int korttiKerroin) {
        this.korttiKerroin = korttiKerroin;
    }

    public void setVoittoRaja(int voittoRaja) {
        this.voittoRaja = voittoRaja;
    }
    
}
