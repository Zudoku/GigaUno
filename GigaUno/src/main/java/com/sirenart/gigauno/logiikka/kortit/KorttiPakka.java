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
public class KorttiPakka {
    
    private List<Kortti> kortit;

    public KorttiPakka(List<Kortti> kortit) {
        this.kortit = kortit;
    }

    public KorttiPakka() {
        kortit = new ArrayList<>();
    }
    
    public Kortti nosta(){
        if(kortit.size() > 0){
            Kortti nostettava = kortit.get(kortit.size() -1);
            kortit.remove(kortit.size() -1);
            return nostettava;
        }
        return null;
    }
    
    public Kortti ylin(){
        if(kortit.size() > 0){
            Kortti ylinKortti = kortit.get(kortit.size() -1);
            return ylinKortti;
        }
        return null;
    }
    
    public int jaljella(){
        return kortit.size();
    }
    
    public void sekoita(){
        
    }
    
    
    
}
