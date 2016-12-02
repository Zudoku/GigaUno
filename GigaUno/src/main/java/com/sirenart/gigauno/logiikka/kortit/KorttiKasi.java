package com.sirenart.gigauno.logiikka.kortit;

import java.util.ArrayList;
import java.util.List;

/**
 * Lista käden nykyisistä korteista.
 *
 * @author arska
 */
public class KorttiKasi {

    /**
     * Lista käden nykyisistä korteista.
     */
    private List<Kortti> kortit;

    /**
     * Korttikäsi on pelaajan erän kortit. Käteen voi lisätä kortteja ja sieltä
     * voi poistaa kortteja. alustaa kortit tyhjäksi.
     */
    public KorttiKasi() {
        kortit = new ArrayList<>();
    }

    /**
     * Lisää annetun kortin käteen.
     * 
     * @param kortti kortti jonka haluat lisätä
     */
    public void lisaa(Kortti kortti) {
        if (kortti != null) {
            kortit.add(kortti);
        }
    }

    /**
     * Poistaa kortin kädestä annetusta indeksistä.
     * 
     * @param index indeksi josta halutaan poistaa kortti
     * @return Poistettu kortti, null jos indeksi on huono
     */
    public Kortti poista(int index) {
        if (kortit.size() > 0 && kortit.size() > index) {
            Kortti poistettu = kortit.get(index);
            kortit.remove(index);
            return poistettu;
        }
        return null;
    }

    public List<Kortti> getKortit() {
        return kortit;
    }

}
