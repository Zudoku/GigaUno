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

    private boolean kaanteinenKierros; //Jos true, pelin suunta on päinvastainen

    private int nykyinenPelaajaIndeksi; //Indeksi joka kertoo kenen pelaajan vuoro on nyt
    private boolean pelaajaSaaLopettaaVuoron;

    private KorttiPakka nostoPakka;
    private KorttiPakka laittoPakka;

    private boolean eraLoppu; // Kertoo onko erä loppunut
    private List<Pelaaja> pelaajat; //Lista kaikista pelaajista
    private Map<Pelaaja, KorttiTyyppi> aloitusKortit; //Pelaajien aloituskortit

    private Pelaaja voittaja;

    public PeliAlusta(List<Pelaaja> pelaajat, Map<KorttiTyyppi, Boolean> erikoiskorttiValinnat, int korttiKerroin) {
        this.pelaajat = pelaajat;
        this.nykyinenPelaajaIndeksi = 0;
        this.kaanteinenKierros = false;
        this.eraLoppu = false;
        this.laittoPakka = new KorttiPakka();
        this.pelaajaSaaLopettaaVuoron = false;
        this.korttienAlustus(erikoiskorttiValinnat, korttiKerroin);
        this.pelaajienAlustus();
        //Laitetaan laittopakkaan yksi kortti
        this.laittoPakka.lisaa(nostoPakka.nosta());
    }

    private void korttienAlustus(Map<KorttiTyyppi, Boolean> erikoiskorttiValinnat, int korttiKerroin) {
        List<Kortti> alustusPakka = new ArrayList<>();
        // Käydään kaikki kortit läpi
        for (KorttiTyyppi korttiTyyppi : KorttiTyyppi.values()) {
            // Tarkastetaan kuuluuko kortti erikoiskortteihin
            if (erikoiskorttiValinnat.keySet().contains(korttiTyyppi)) {
                // Kortti on erikoiskortti, katsotaan onko se laitettu päälle
                if (erikoiskorttiValinnat.get(korttiTyyppi)) {
                    // Lisää kortti pakkaan
                    for (int i = 0; i < korttiTyyppi.getPakassaKortteja() * korttiKerroin; i++) {
                        alustusPakka.add(new Kortti(KorttiVari.ERIKOIS, korttiTyyppi));
                    }
                }
            } else {
                // Kortti on siis tavallinen numerokortti
                // Lisää kortti pakkaan
                for (int i = 0; i < korttiTyyppi.getPakassaKortteja() * korttiKerroin; i++) {
                    // Joka värissä
                    alustusPakka.add(new Kortti(KorttiVari.VIHREA, korttiTyyppi));

                }
            }
        }
        //Kaikki kortit on alustettu, laitetaan alustuspakka nostopakaksi 
        nostoPakka = new KorttiPakka(alustusPakka);
        // Sekoita kortit
        nostoPakka.sekoita();
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
            return true;
        }
        return false;
    }

    public boolean voikoPelaajaLaittaaKortin(Kortti kortti) {
        if (kortti != null) {
            Kortti ylinKortti = laittoPakka.ylin();
            if (kortti.getVari() == KorttiVari.ERIKOIS || kortti.getVari() == ylinKortti.getVari()) {
                return true;
            } else if (kortti.getTyyppi() == ylinKortti.getTyyppi()) {
                return true;
            }
        }
        return false;
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
