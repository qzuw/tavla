/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.logiikka;

import java.util.ArrayList;
import tutavla.tavla.domain.Lauta;
import tutavla.tavla.domain.Nappula;
import tutavla.tavla.domain.Noppa;
import tutavla.tavla.domain.Pelaaja;

/**
 *
 * @author ttuotila@cs
 */
public class Pelilogiikka {
    
    private Lauta lauta;

    public Pelilogiikka() {
        lauta = new Lauta();
    }
    
    public void asetaNappulat(Pelaaja pelaaja1, Pelaaja pelaaja2) {
        asetaNappuloitaRuutuun(pelaaja1, 2, 24);
        asetaNappuloitaRuutuun(pelaaja1, 5, 13);
        asetaNappuloitaRuutuun(pelaaja1, 3, 8);
        asetaNappuloitaRuutuun(pelaaja1, 5, 6);
        asetaNappuloitaRuutuun(pelaaja2, 2, 1);
        asetaNappuloitaRuutuun(pelaaja2, 5, 12);
        asetaNappuloitaRuutuun(pelaaja2, 3, 17);
        asetaNappuloitaRuutuun(pelaaja2, 5, 19);
    }
    
    private void asetaNappuloitaRuutuun(Pelaaja pelaaja, int maara, int ruutu) {
        for (int i = 0; i < maara; i++) {
            lauta.asetaNappula(new Nappula(pelaaja), ruutu);
        }
    }
    
    public int ruudunNappulaMaara(int ruutu) {
        return lauta.nappuloitaRuudussa(ruutu);
    }
    
    public boolean ruutuOnTyhja(int ruutu) {
        return (lauta.nappuloitaRuudussa(ruutu) == 0);
    }
    
    public boolean ruutuOnPelaajan(int ruutu, Pelaaja pelaaja) {
        return lauta.ruutuPelaajalla(ruutu, pelaaja);
    }
    
    public int pelaajanNappulaMaara(int ruutu, Pelaaja pelaaja) {
        return lauta.pelaajanNappuloitaRuudussa(ruutu, pelaaja);
    }
    
    public boolean ruutuunVoiSiirtya(int ruutu, Pelaaja pelaaja) {
        if (ruutu == pelaaja.getMaali()) {
            return true;
        }
        if (this.ruudunNappulaMaara(ruutu) < 2) {
            return true;
        }
        if (this.ruutuOnPelaajan(ruutu, pelaaja)) {
            return true;
        }
        return false;
    }
    
    public void siirraNappulaa(Pelaaja pelaaja, int mista, int minne) {
        if (lauta.ruutuPelaajalla(mista, pelaaja) && this.ruutuunVoiSiirtya(minne, pelaaja)) {
            lauta.siirraNappula(mista, minne);
        }
        
    }
    
}
