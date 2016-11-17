package com.sirenart.gigauno.logiikka.era;

import com.sirenart.gigauno.logiikka.kortit.Kortti;
import com.sirenart.gigauno.logiikka.kortit.KorttiVari;
import com.sirenart.gigauno.logiikka.kortit.KorttiTyyppi;
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
public class PeliAlusta {

    public static final int ALOITUS_KORTTI_MAARA = 6;
    private final Random random = new Random();

    
    
    
    private boolean kaanteinenKierros = false; //Jos true, pelin suunta on päinvastainen
    private boolean skipSeuraavaPelaaja = false; //Jos true, kun vuoro vaihtuu, vuoro vaihtuu kaksi kertaa
    
    
    
    private int nykyinenPelaajaIndeksi = 0; //Indeksi joka kertoo kenen pelaajan vuoro on nyt
    private boolean pelaajaSaaLopettaaVuoron = false; //Jos true, nykyinen pelaaja saa lopettaa vuoron

    private KorttiPakka nostoPakka; //Pakka josta nostetaan kortteja
    private KorttiPakka laittoPakka; // Pakka johon laitetaan kortit

    private List<Pelaaja> pelaajat; //Lista kaikista pelaajista
    private Map<Pelaaja, KorttiTyyppi> aloitusKortit; //Pelaajien aloituskortit

    private Pelaaja voittaja = null; // Kertoo voittajan kun erä on loppu, null jos erä ei ole loppu
    private boolean eraLoppu = false; // Kertoo onko erä loppunut

    public PeliAlusta(List<Pelaaja> pelaajat, Map<KorttiTyyppi, Boolean> erikoiskorttiValinnat, int korttiKerroin) {
        this.pelaajat = pelaajat;
        this.laittoPakka = new KorttiPakka();
        this.nostoPakka = new KorttiPakka();
        this.nostoPakka.alustus(erikoiskorttiValinnat, korttiKerroin);
        this.pelaajienAlustus();
        //Laitetaan laittopakkaan yksi kortti
        this.laittoPakka.lisaa(nostoPakka.nosta());
    }

    private void pelaajienAlustus() {
        sekoitaPelaajienJarjestys();
        jaaAloitusKortit();
        valitseAloittaja();
    }

    /**
     * Sekoita pelaajien järjestys
     */
    private void sekoitaPelaajienJarjestys() {
        List<Integer> jarjestykset = new ArrayList<>();
        for (int i = 0; i < pelaajat.size(); i++) {
            jarjestykset.add(i);
        }
        for (Pelaaja pelaaja : pelaajat) {
            Integer indeksi = jarjestykset.get(random.nextInt(jarjestykset.size()));
            jarjestykset.remove(indeksi);
            pelaaja.setEraTiedot(new EraTiedot(indeksi, new KorttiKasi()));
        }
    }

    /**
     * Alustaa pelaajien korttikädet korteilla
     */
    private void jaaAloitusKortit() {
        for (int i = 0; i < ALOITUS_KORTTI_MAARA; i++) {
            for (Pelaaja pelaaja : pelaajat) {
                Kortti nostettu = nostoPakka.nosta();
                pelaaja.getEraTiedot().getKortit().lisaa(nostettu);
            }
        }
    }

    /**
     * Arpoo vuoropäätös kortit (kuka aloittaa erän) ja laittaa muistiin kortit,
     * että ne voidaan näyttää jatkossa grafisesti pelaajalle
     */
    private void valitseAloittaja() {
        aloitusKortit = new HashMap<>();
        List<Integer> arvot = new ArrayList<>();
        for (int i = 0; i < pelaajat.size(); i++) {
            Integer arvo = (Integer) random.nextInt(10);
            arvot.add(arvo);
            //Talleta kortit graafista näyttöä varten
            aloitusKortit.put(pelaajat.get(i), KorttiTyyppi.values()[arvo]);
        }
        nykyinenPelaajaIndeksi = arvot.indexOf(Collections.max(arvot));

    }

    public Map<Pelaaja, KorttiTyyppi> getAloitusKortit() {
        return aloitusKortit;
    }

    public KorttiPakka getLaittoPakka() {
        return laittoPakka;
    }

    public KorttiPakka getNostoPakka() {
        return nostoPakka;
    }

    public void aloitaEra() {

    }

    public Pelaaja nykyinenPelaaja() {
        return pelaajat.get(nykyinenPelaajaIndeksi);
    }

    public Pelaaja seuraavaPelaaja() {
        int seuraavaIndeksi = nykyinenPelaajaIndeksi;
        if (kaanteinenKierros) {
            seuraavaIndeksi--;
            //Tarkistetaan ettei mene yli, jos menee, alkaa vuoro taas seuraavasta päästä
            if (seuraavaIndeksi < 0) {
                seuraavaIndeksi = pelaajat.size() - 1;
            }
        } else {
            seuraavaIndeksi++;
            //Tarkistetaan ettei mene yli, jos menee, alkaa vuoro taas seuraavasta päästä
            if (seuraavaIndeksi >= pelaajat.size()) {
                seuraavaIndeksi = 0;
            }
        }
        return pelaajat.get(seuraavaIndeksi);
    }

    public boolean pelaajaLaittaaKortin(Kortti kortti) {
        if (voikoPelaajaLaittaaKortin(kortti)) {
            laittoPakka.lisaa(kortti);
            pelaajaSaaLopettaaVuoron = true;
            if(kortti.getVari() == KorttiVari.ERIKOIS){
                pelaajaPelaaErikoisKortin(kortti);
            }
            tarkistaVoitto();
            return true;
        }
        return false;
    }
    
    private void tarkistaVoitto(){
        if(nykyinenPelaaja().getEraTiedot().getKortit().getKortit().isEmpty()){
            if(nykyinenPelaaja().getEraTiedot().isUnoHuudettu()){
                voittaja = nykyinenPelaaja();
                eraLoppu = true;
            } else {
                for(int i = 0; i < 2; i++){
                    nykyinenPelaaja().getEraTiedot().getKortit().lisaa(nostoPakka.nosta());
                }
            }
        }
    }
    
    private void pelaajaPelaaErikoisKortin(Kortti kortti){
        switch(kortti.getTyyppi()){
            case HURRIKAANI:
                
                break;
                
            case NOSTAKAKSI:
                //Seuraava pelaaja nostaa kaksi korttia
                for(int i = 0; i < 2; i++){
                    seuraavaPelaaja().getEraTiedot().getKortit().lisaa(nostoPakka.nosta());
                }
                break;
                
            case NOSTANELJA:
                //Seuraava pelaaja nostaa neljä korttia
                for(int i = 0; i < 4; i++){
                    seuraavaPelaaja().getEraTiedot().getKortit().lisaa(nostoPakka.nosta());
                }
                break;
                
            case OHITUS:
                skipSeuraavaPelaaja = true;
                break;
                
            case SUUNNANVAIHTO:
                kaanteinenKierros = !kaanteinenKierros;
                break;
                
            case VILLI:
                
                break;
                
            case VILLINOSTANELJA:
                
                break;
                
            default: 
                
                break;
        }
    }

    public boolean voikoPelaajaLaittaaKortin(Kortti kortti) {
        return laittoPakka.voikoLaittaaKortin(kortti);
    }

    public boolean pelaajaNostaaKortin() {
        Kortti nostettu = nostoPakka.nosta();
        if (nostettu != null) {
            nykyinenPelaaja().getEraTiedot().getKortit().lisaa(nostettu);
            pelaajaSaaLopettaaVuoron = true;
            return true;
        }
        return false;
    }

    public boolean pelaajaLopettaaVuoron() {
        //Tarkistetaan onko pelaaja ottanut kortin pakasta, tai laittanut kortin pakkaan.
        if (pelaajaSaaLopettaaVuoron) {
            if (kaanteinenKierros) {
                nykyinenPelaajaIndeksi--;
                //Tarkistetaan ettei mene yli, jos menee, alkaa vuoro taas seuraavasta päästä
                if (nykyinenPelaajaIndeksi < 0) {
                    nykyinenPelaajaIndeksi = pelaajat.size() - 1;
                }
            } else {
                nykyinenPelaajaIndeksi++;
                //Tarkistetaan ettei mene yli, jos menee, alkaa vuoro taas seuraavasta päästä
                if (nykyinenPelaajaIndeksi >= pelaajat.size()) {
                    nykyinenPelaajaIndeksi = 0;
                }
            }
            pelaajaSaaLopettaaVuoron = false;
            return true;

        }
        return false;
    }
    /**
     * Laskee pisteet erän lopussa
     * Palauttaa voittajan ansaitsemat pisteet
     */
    public int laskePisteet(){
        int pisteet = 0;
        for(Pelaaja pelaaja : pelaajat){
            if(pelaaja != voittaja && voittaja != null){
                for(Kortti kortti : pelaaja.getEraTiedot().getKortit().getKortit()){
                    pisteet += kortti.getTyyppi().getPisteArvo();
                }
            }
        }
        return pisteet;
    }
    
    public boolean isEraLoppu() {
        return eraLoppu;
    }

    public Pelaaja getVoittaja() {
        return voittaja;
    }

    public List<Pelaaja> getPelaajat() {
        return pelaajat;
    }

    public int getNykyinenPelaajaIndeksi() {
        return nykyinenPelaajaIndeksi;
    }

    public void setKaanteinenKierros(boolean kaanteinenKierros) {
        this.kaanteinenKierros = kaanteinenKierros;
    }

    public void setNykyinenPelaajaIndeksi(int nykyinenPelaajaIndeksi) {
        this.nykyinenPelaajaIndeksi = nykyinenPelaajaIndeksi;
    }

}
