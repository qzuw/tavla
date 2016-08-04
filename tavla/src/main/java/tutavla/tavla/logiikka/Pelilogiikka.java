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
    private Noppa noppa1;
    private Noppa noppa2;
    private ArrayList<Pelaaja> siirtojarjestys;
    // siirtojärjestys pitäisi olla svl, ei täällä
    // tällöin myös nopat pitäisi olla siellä

    public Pelilogiikka(Pelaaja pelaaja1, Pelaaja pelaaja2) {
        lauta = new Lauta();
        siirtojarjestys = new ArrayList<>();
        siirtojarjestys.add(pelaaja1);
        siirtojarjestys.add(pelaaja2);
        noppa1 = new Noppa();
        noppa2 = new Noppa();
    }

    public void pelaajaSiirtaaEnsin(boolean pelaajaEnsin, Pelaaja pelaaja) {
        if (!(siirtojarjestys.get(0).equals(pelaaja) == pelaajaEnsin)) {
            Pelaaja siirrettava = siirtojarjestys.get(0);
            siirtojarjestys.add(siirrettava);
            siirtojarjestys.remove(0);
        }
    }

    public void asetaNappulat() {
        asetaNappuloitaRuutuun(this.siirtojarjestys.get(0), 2, 24);
        asetaNappuloitaRuutuun(this.siirtojarjestys.get(0), 5, 13);
        asetaNappuloitaRuutuun(this.siirtojarjestys.get(0), 3, 8);
        asetaNappuloitaRuutuun(this.siirtojarjestys.get(0), 5, 6);
        asetaNappuloitaRuutuun(this.siirtojarjestys.get(1), 2, 1);
        asetaNappuloitaRuutuun(this.siirtojarjestys.get(1), 5, 12);
        asetaNappuloitaRuutuun(this.siirtojarjestys.get(1), 3, 17);
        asetaNappuloitaRuutuun(this.siirtojarjestys.get(1), 5, 19);
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

    public boolean ruutuOnPelaajan(int ruutu, Pelaaja pelaaja) {
        return lauta.ruutuPelaajalla(ruutu, pelaaja);
    }

    public boolean ruutuunVoiSiirtya(int ruutu, Pelaaja pelaaja) {
        if (ruutu == 0 || ruutu == 25) {
            return true;
        }
        if (this.ruutuOnPelaajan(ruutu, pelaaja)) {
            return true;
        }
        if (this.ruutuOnTyhja(ruutu)) {
            return true;
        }
        return false;
    }

}
