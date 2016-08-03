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
public class Logiikka {

    private Lauta lauta;
    private Pelaaja pelaaja;
    private Pelaaja tietokone;
    private Noppa noppa1;
    private Noppa noppa2;
    private ArrayList<Pelaaja> siirtojarjestys;

    public Logiikka() {
        lauta = new Lauta();
        pelaaja = new Pelaaja("");
        tietokone = new Pelaaja();
        siirtojarjestys = new ArrayList<>();
        siirtojarjestys.add(pelaaja);
        siirtojarjestys.add(tietokone);
        noppa1 = new Noppa();
        noppa2 = new Noppa();
    }

    public Logiikka(String nimi) {
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
        for (int i = 0; i < 2; i++) {
            lauta.asetaNappula(new Nappula(pelaaja), 24);
        }
        for (int i = 0; i < 5; i++) {
            lauta.asetaNappula(new Nappula(pelaaja), 13);
        }
        for (int i = 0; i < 3; i++) {
            lauta.asetaNappula(new Nappula(pelaaja), 8);
        }
        for (int i = 0; i < 5; i++) {
            lauta.asetaNappula(new Nappula(pelaaja), 6);
        }
        for (int i = 0; i < 2; i++) {
            lauta.asetaNappula(new Nappula(tietokone), 1);
        }
        for (int i = 0; i < 5; i++) {
            lauta.asetaNappula(new Nappula(tietokone), 12);
        }
        for (int i = 0; i < 3; i++) {
            lauta.asetaNappula(new Nappula(tietokone), 17);
        }
        for (int i = 0; i < 5; i++) {
            lauta.asetaNappula(new Nappula(tietokone), 19);
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
