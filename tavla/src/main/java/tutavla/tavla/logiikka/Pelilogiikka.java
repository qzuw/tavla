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

    public void alustaPelitilanne(Pelaaja pelaaja1, Pelaaja pelaaja2) {
        pelaaja1.setMaali(0);
        pelaaja2.setMaali(25);
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
            if (!lauta.ruutuPelaajalla(minne, pelaaja) && lauta.nappuloitaRuudussa(minne) == 1) {
                lauta.siirraNappula(minne, pelaaja.getMaali());
            }
            lauta.siirraNappula(mista, minne);
        }

    }

    public Lauta pelitilanne() {
        return lauta;
    }

    public boolean onkoPelaajaVoittanut(Pelaaja pelaaja) {

        if (lauta.pelaajanNappuloitaRuudussa(pelaaja.getMaali(), pelaaja) == 15) {
            return true;
        }

        return false;
    }

    public ArrayList<Integer> pelaajaVoiSiirtaaRuuduista(Pelaaja pelaaja) {
        ArrayList<Integer> lista = new ArrayList<>();

        int syotyjenNappuloidenRuutu = Math.abs(pelaaja.getMaali() - 25);

        if (lauta.pelaajanNappuloitaRuudussa(syotyjenNappuloidenRuutu, pelaaja) > 0) {
            lista.add(syotyjenNappuloidenRuutu);
            return lista;
        }

        for (int i = 1; i < 25; i++) {
            if (lauta.ruutuPelaajalla(i, pelaaja)) {
                lista.add(i);
            }
        }

        return lista;
    }

    public ArrayList<Integer> pelaajaVoiSiirtaaRuutuihin(Pelaaja pelaaja, int lahtoruutu) {
        ArrayList<Integer> lista = new ArrayList<>();

        if (pelaaja.getMaali() == 0) {

            int i = lahtoruutu;
            int siirto = 0;
            while (true) {
                siirto++;
                int kohderuutu = lahtoruutu - siirto;
                if (siirto > 6) {
                    break;
                } else if (kohderuutu <= 0) {
                    kohderuutu = 0;
                    if (this.nappulatKotialueella(pelaaja) && !lista.contains(kohderuutu)) {
                        lista.add(kohderuutu);
                    }
                    break;
                } else if (this.ruutuunVoiSiirtya(kohderuutu, pelaaja)) {
                    lista.add(kohderuutu);
                }
            }

        } else {

            int i = lahtoruutu;
            int siirto = 0;
            while (true) {
                siirto++;
                int kohderuutu = siirto + lahtoruutu;
                if (siirto > 6) {
                    break;
                } else if (kohderuutu >= 25) {
                    kohderuutu = 25;
                    if (this.nappulatKotialueella(pelaaja) && !lista.contains(kohderuutu)) {
                        lista.add(kohderuutu);
                    }
                    break;
                } else if (this.ruutuunVoiSiirtya(kohderuutu, pelaaja)) {
                    lista.add(kohderuutu);
                }
            }
        }

        return lista;
    }

    public boolean nappulatKotialueella(Pelaaja pelaaja) {
        int maara = 0;
        if (pelaaja.getMaali() == 0) {
            for (int i = 0; i < 7; i++) {
                maara += lauta.pelaajanNappuloitaRuudussa(i, pelaaja);
            }
        } else {
            for (int i = 25; i > 18; i--) {
                maara += lauta.pelaajanNappuloitaRuudussa(i, pelaaja);
            }
        }
        if (maara == 15) {
            return true;
        }
        return false;
    }
}
