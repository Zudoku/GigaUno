/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sirenart.gigauno.logiikka.kortit;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arska
 */
public class KorttiKasi {
    
    private List<Kortti> kortit;

    public KorttiKasi() {
        kortit = new ArrayList<>();
    }
    
    public void lisaa(Kortti kortti){
        kortit.add(kortti);
    }
    
    public Kortti poista(int index){
        if(kortit.size() > 0 && kortit.size() > index){
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
