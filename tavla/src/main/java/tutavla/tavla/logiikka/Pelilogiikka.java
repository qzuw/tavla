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
 * Luokka sisältää pelille keskeisen logiikan nappuloiden liikuttelusta yms.
 *
 * @author ttuotila@cs
 */
public class Pelilogiikka {

    private Lauta lauta;

    /**
     * Luodaan uusi pelilogiikka.
     */
    public Pelilogiikka() {
        lauta = new Lauta();
    }

    /**
     * Alusta pelilaudan tilanne asettamalla nappulat ja määrittämällä pelaajien
     * maaliruudut.
     *
     * @param pelaaja1 pelaaja 1
     * @param pelaaja2 pelaaja 2
     */
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

    /**
     * Aseta ruutuun tietty määrä nappuloita.
     *
     * @param pelaaja nappulat omistava pelaaja
     * @param maara nappuloiden määrä
     * @param ruutu ruudun indeksi
     */
    private void asetaNappuloitaRuutuun(Pelaaja pelaaja, int maara, int ruutu) {
        for (int i = 0; i < maara; i++) {
            lauta.asetaNappula(new Nappula(pelaaja), ruutu);
        }
    }

    /**
     * Hae ruudussa olevien nappuloiden määrä.
     *
     * @param ruutu ruudun indeksi
     * @return nappuloiden määrä
     */
    public int ruudunNappulaMaara(int ruutu) {
        return lauta.nappuloitaRuudussa(ruutu);
    }

    /**
     * Onko ruutu tyhjä.
     *
     * @param ruutu ruudun indeksi
     * @return true jos ruutu on tyhjä
     */
    public boolean ruutuOnTyhja(int ruutu) {
        return (lauta.nappuloitaRuudussa(ruutu) == 0);
    }

    /**
     * Onko ruutu pelaajan hallussa.
     *
     * @param ruutu ruudun indeksi
     * @param pelaaja tarkastettava pelaaja
     * @return true jos ruutu on pelaajan
     */
    public boolean ruutuOnPelaajan(int ruutu, Pelaaja pelaaja) {
        return lauta.ruutuPelaajalla(ruutu, pelaaja);
    }

    /**
     * Montako nappulaa pelaajalla on ruudussa.
     *
     * @param ruutu ruudun indeksi
     * @param pelaaja tarkastettava pelaaja
     * @return nappuloiden määrä
     */
    public int pelaajanNappulaMaara(int ruutu, Pelaaja pelaaja) {
        return lauta.pelaajanNappuloitaRuudussa(ruutu, pelaaja);
    }

    /**
     * Voiko pelaaja siirtää nappulansa ruutuun.
     *
     * @param ruutu ruudun indeksi
     * @param pelaaja tarkastettava pelaaja
     * @return true jos pelaaja voi siirtää nappulansa ruutuun
     */
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

    /**
     * Siirrä pelaajan nappula ruudusta toiseen.
     *
     * @param pelaaja pelaaja jonka nappulaa siirretään
     * @param mista lähtöruudun indeksi
     * @param minne lopetusruudun indeksi
     */
    public void siirraNappulaa(Pelaaja pelaaja, int mista, int minne) {
        if ((lauta.ruutuPelaajalla(mista, pelaaja) || mista == Math.abs(pelaaja.getMaali() - 25)) && this.ruutuunVoiSiirtya(minne, pelaaja)) {
            if (!lauta.ruutuPelaajalla(minne, pelaaja) && lauta.nappuloitaRuudussa(minne) == 1) {
                // tässä syödään vastustajan nappula
                lauta.siirraNappula(minne, pelaaja.getMaali());
            }
            lauta.siirraNappula(mista, minne);
        }

    }

    /**
     * Hae pelilauta.
     *
     * @return pelilauta
     */
    public Lauta pelitilanne() {
        return lauta;
    }

    /**
     * Tarkistetaan onko annettu pelaaja voittanut pelin.
     *
     * @param pelaaja tarkistettava pelaaja
     * @return true jos pelaaja on voittanut
     */
    public boolean onkoPelaajaVoittanut(Pelaaja pelaaja) {

        if (lauta.pelaajanNappuloitaRuudussa(pelaaja.getMaali(), pelaaja) == 15) {
            return true;
        }

        return false;
    }

    /**
     * Mistä ruuduista pelaaja voi siirtää.
     *
     * @param pelaaja tarksitettava pelaaja
     * @return palautetaan lista ruutujen indeksejä joihin voi siirtää
     */
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

    /**
     * Mihin ruutuihin pelaaja voi siirtää nappulan joka lähtee annetusta
     * ruudusta.
     *
     * @param pelaaja tarkistettava pelaaja
     * @param lahtoruutu lähtöruudun indeksi
     * @param siirrot lista käytettävissä olevista siirroista
     * @return lista mahdollisista kohderuuduista
     */
    public ArrayList<Integer> pelaajaVoiSiirtaaRuutuihin(Pelaaja pelaaja, int lahtoruutu, ArrayList<Integer> siirrot) {
        ArrayList<Integer> lista = new ArrayList<>();

        if (pelaaja.getMaali() == 0) {

            lista = siirrettavatRuudut(lahtoruutu, -1, pelaaja, siirrot);

        } else {

            lista = siirrettavatRuudut(lahtoruutu, 1, pelaaja, siirrot);
        }

        return lista;
    }

    private ArrayList<Integer> siirrettavatRuudut(int lahtoruutu, int kerroin, Pelaaja pelaaja, ArrayList<Integer> siirrot) {
        ArrayList<Integer> lista = new ArrayList<>();
        int i = lahtoruutu;
        int siirto = 0;
        while (true) {
            siirto++;
            int kohderuutu = lahtoruutu + kerroin * siirto;
            if (siirto > 6) {
                break;
            } else if (kohderuutu < 0 || kohderuutu > 25) {
                break;
            } else if (this.ruutuunVoiSiirtya(kohderuutu, pelaaja) && siirrot.contains(siirto)) {
                lista.add(kohderuutu);
            } else if (kohderuutu == pelaaja.getMaali()) {
                if (nappulatKotialueella(pelaaja) && !lista.contains(kohderuutu)) {
                    for (Integer s : siirrot) {
                        if (s >= siirto) {
                            lista.add(kohderuutu);
                        }
                    }
                }
                break;
            }
        }
        return lista;
    }

    /**
     * Ovatko pelaajan kaikki nappulat kotialueella.
     *
     * @param pelaaja tarkistettava pelaaja
     * @return true jos kaikki nappulat ovat pelaajan kotialueella
     */
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
