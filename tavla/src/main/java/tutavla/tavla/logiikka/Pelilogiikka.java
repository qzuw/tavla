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
    private Pelaaja pelaaja1;
    private Pelaaja pelaaja2;
    private Noppa noppa1;
    private Noppa noppa2;
    private ArrayList<Pelaaja> siirtojarjestys;

    public Pelilogiikka() {
        lauta = new Lauta();
        pelaaja1 = new Pelaaja();
        pelaaja2 = new Pelaaja();
        siirtojarjestys = new ArrayList<>();
        siirtojarjestys.add(pelaaja1);
        siirtojarjestys.add(pelaaja2);
        noppa1 = new Noppa();
        noppa2 = new Noppa();
    }

    public Pelilogiikka(String nimi) {
        lauta = new Lauta();
        pelaaja1 = new Pelaaja(nimi);
        pelaaja2 = new Pelaaja();
        siirtojarjestys = new ArrayList<>();
        siirtojarjestys.add(pelaaja1);
        siirtojarjestys.add(pelaaja2);
        noppa1 = new Noppa();
        noppa2 = new Noppa();
    }

    public Pelilogiikka(String nimi1, String nimi2) {
        lauta = new Lauta();
        pelaaja1 = new Pelaaja(nimi1);
        pelaaja2 = new Pelaaja(nimi2);
        siirtojarjestys = new ArrayList<>();
        siirtojarjestys.add(pelaaja1);
        siirtojarjestys.add(pelaaja2);
        noppa1 = new Noppa();
        noppa2 = new Noppa();
    }

    public void asetaPelaajaSiirtaaEnsin(boolean pelaajaEnsin) {
        //tämä pitää ajatella uudelleen
        if (!(siirtojarjestys.get(0).isIhminen() == pelaajaEnsin)) {
            Pelaaja siirrettava = siirtojarjestys.get(0);
            siirtojarjestys.add(siirrettava);
            siirtojarjestys.remove(0);
        }
    }

    public void asetaNappulat() {
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

    public int ruudunNappulat(int ruutu) {
        return lauta.nappuloitaRuudussa(ruutu);
    }

    public boolean ruutuOnTyhja(int ruutu) {
        return (lauta.nappuloitaRuudussa(ruutu) == 0);
    }

    public boolean ruutuOnPelaajan(int ruutu) {
        //tämäkin pitää vielä miettiä
        return lauta.ruutuPelaajalla(ruutu, pelaaja1);
    }

    public boolean ruutuOnTietokoneen(int ruutu) {
        //tämäkin pitää vielä miettiä
        return lauta.ruutuPelaajalla(ruutu, pelaaja2);
    }

    public void kaynnistaPeli() {

    }
}
