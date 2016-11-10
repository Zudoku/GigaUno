/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sirenart.gigauno.logiikka.era;

import com.sirenart.gigauno.logiikka.kortit.KorttiVari;
import com.sirenart.gigauno.logiikka.kortit.Kortti;
import com.sirenart.gigauno.logiikka.kortit.KorttiKasi;
import com.sirenart.gigauno.logiikka.kortit.KorttiPakka;
import com.sirenart.gigauno.logiikka.pelaaja.EraTiedot;
import com.sirenart.gigauno.logiikka.pelaaja.Pelaaja;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author arska
 */
public class Pelialusta {
    private final int aloitusKorttiMaara = 6;
    private final Random random = new Random();
    
    private boolean kaanteinenKierros; //Jos true, pelin suunta on päinvastainen
    
    private int nykyinenPelaajaIndeksi;
    
    private KorttiPakka nostoPakka;
    private KorttiPakka laittoPakka;
    
    private boolean eraLoppu; // Kertoo onko erä loppunut
    private List<Pelaaja> pelaajat;
    private Map<String, Kortti> aloitusKortit;
    
    public Pelialusta(List<Pelaaja> pelaajat, Map<Kortti, Boolean> erikoiskorttiValinnat, int korttiKerroin) {
        this.pelaajat = pelaajat;
        nykyinenPelaajaIndeksi = 0;
        eraLoppu = false;
        laittoPakka = new KorttiPakka();
        this.korttienAlustus(erikoiskorttiValinnat, korttiKerroin);
        this.pelaajienAlustus();
    }
    
    private void korttienAlustus(Map<Kortti, Boolean> erikoiskorttiValinnat, int korttiKerroin){
        List<Kortti> alustusPakka = new ArrayList<>();
        // Käydään kaikki kortit läpi
        for (Kortti kortti : Kortti.values()) {
            // Tarkastetaan kuuluuko kortti erikoiskortteihin
            if (erikoiskorttiValinnat.keySet().contains(kortti)){
                // Kortti on erikoiskortti, katsotaan onko se laitettu päälle
                if(erikoiskorttiValinnat.get(kortti)){
                    // Lisää kortti pakkaan
                    for(int i = 0; i < kortti.getPakassaKortteja() * korttiKerroin; i++){
                        alustusPakka.add(kortti.getCopy());
                    }
                }
            } else {
                // Kortti on siis tavallinen numerokortti
                // Lisää kortti pakkaan
                for(int i = 0; i < kortti.getPakassaKortteja() * korttiKerroin; i++){
                    // Joka värissä
                    alustusPakka.add(kortti.varita(KorttiVari.VIHREA));
                    alustusPakka.add(kortti.varita(KorttiVari.PUNAINEN));
                    alustusPakka.add(kortti.varita(KorttiVari.SININEN));
                    alustusPakka.add(kortti.varita(KorttiVari.KELTAINEN));
                }
            }
        }
        //Kaikki kortit on alustettu, laitetaan alustuspakka nostopakaksi 
        nostoPakka = new KorttiPakka(alustusPakka);
        // Sekoita kortit
        nostoPakka.sekoita();
    }
    
    private void pelaajienAlustus(){
        sekoitaJarjestys();
        jaaAloitusKortit();
        valitseAloittaja();
    }
    /**
     * Sekoita pelaajien järjestys
     */
    private void sekoitaJarjestys(){
        List<Integer> jarjestykset = new ArrayList<>();
        for(int i = 0; i < pelaajat.size(); i++){
            jarjestykset.add(i);
        }
        for(Pelaaja pelaaja : pelaajat){
            Integer indeksi = jarjestykset.get(random.nextInt(jarjestykset.size()));
            jarjestykset.remove(indeksi);
            pelaaja.setEraTiedot(new EraTiedot(indeksi, new KorttiKasi()));
        }
    }
    
    private void jaaAloitusKortit(){
        
    }
    /**
     * Arpoo vuoropäätös kortit (kuka aloittaa erän)
     */
    private void valitseAloittaja(){
        aloitusKortit = new HashMap<>();
        List<Integer> arvot = new ArrayList<>();
        for(int i = 0; i < pelaajat.size(); i++){
            Integer arvo = (Integer)random.nextInt(10);
            arvot.add(arvo);
            aloitusKortit.put(pelaajat.get(i).getNimimerkki(), Kortti.values()[arvo]);
        }
        nykyinenPelaajaIndeksi = arvot.indexOf(Collections.max(arvot));
        
    }
    
    
    public void aloitaEra(){
        
    }
    
}
