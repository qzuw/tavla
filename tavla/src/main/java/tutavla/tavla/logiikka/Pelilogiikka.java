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
    private Pelaaja pelaaja;
    private Pelaaja tietokone;
    private Noppa noppa1;
    private Noppa noppa2;
    private ArrayList<Pelaaja> siirtojarjestys;

    public Pelilogiikka() {
        lauta = new Lauta();
        pelaaja = new Pelaaja("");
        tietokone = new Pelaaja();
        siirtojarjestys = new ArrayList<>();
        siirtojarjestys.add(pelaaja);
        siirtojarjestys.add(tietokone);
        noppa1 = new Noppa();
        noppa2 = new Noppa();
    }

    public Pelilogiikka(String nimi) {
        lauta = new Lauta();
        pelaaja = new Pelaaja(nimi);
        tietokone = new Pelaaja();
        siirtojarjestys = new ArrayList<>();
        siirtojarjestys.add(pelaaja);
        siirtojarjestys.add(tietokone);
        noppa1 = new Noppa();
        noppa2 = new Noppa();
    }

    public void asetaPelaajaSiirtaaEnsin(boolean pelaajaEnsin) {
        if (!(siirtojarjestys.get(0).isIhminen() == pelaajaEnsin)){
            Pelaaja siirrettava = siirtojarjestys.get(0);
            siirtojarjestys.add(siirrettava);
            siirtojarjestys.remove(0);
        }
    }

    public void asetaNappulat() {
        asetaNappuloitaRuutuun(pelaaja, 2, 24);
        asetaNappuloitaRuutuun(pelaaja, 5, 13);
        asetaNappuloitaRuutuun(pelaaja, 3, 8);
        asetaNappuloitaRuutuun(pelaaja, 5, 6);
        asetaNappuloitaRuutuun(tietokone, 2, 1);
        asetaNappuloitaRuutuun(tietokone, 5, 12);
        asetaNappuloitaRuutuun(tietokone, 3, 17);
        asetaNappuloitaRuutuun(tietokone, 5, 19);
    }

    private void asetaNappuloitaRuutuun(Pelaaja pelaaja, int maara, int ruutu){
        for (int i = 0; i < maara; i++) {
            lauta.asetaNappula(new Nappula(pelaaja), ruutu);
        }        
    }
    
    public int ruudunNappulat(int ruutu) {
        return lauta.nappuloitaRuudussa(ruutu);
    }

    public boolean ruutuOnTyhja(int ruutu) {
        return (lauta.nappuloitaRuudussa(ruutu) == 0);
    }

    public boolean ruutuOnPelaajan(int ruutu) {
        return lauta.ruutuPelaajalla(ruutu, pelaaja);
    }

    public boolean ruutuOnTietokoneen(int ruutu) {
        return lauta.ruutuPelaajalla(ruutu, tietokone);
    }
}
