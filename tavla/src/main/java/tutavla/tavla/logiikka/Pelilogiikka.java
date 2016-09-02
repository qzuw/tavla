/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.logiikka;

import java.util.ArrayList;
import java.util.Random;
import tutavla.tavla.domain.Lauta;
import tutavla.tavla.domain.Nappula;
import tutavla.tavla.domain.Noppa;
import tutavla.tavla.domain.Pelaaja;
import tutavla.tavla.domain.Siirrot;

/**
 * Luokka sisältää pelille keskeisen logiikan nappuloiden liikuttelusta yms.
 *
 * @author ttuotila@cs
 */
public class Pelilogiikka {

    private Lauta lauta;
        private Siirrot siirrot;
    private int lahtoruutu;


    /**
     * Luodaan uusi pelilogiikka.
     */
    public Pelilogiikka(Random random) {
        lauta = new Lauta();
                siirrot = new Siirrot(random);
                        lahtoruutu = -1;


    }

    /**
     * Alusta pelilaudan tilanne asettamalla nappulat ja määrittämällä pelaajien
     * maaliruudut.
     *
     * @param pelaaja1 pelaaja 1
     * @param pelaaja2 pelaaja 2
     */
    public void alustaPelitilanne(Pelaaja pelaaja1, Pelaaja pelaaja2) {
        pelaaja1.asetaMaali(0);
        pelaaja2.asetaMaali(25);
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
     * Heitä noppia.
     */
    public void heitaNopat() {
        siirrot.heitaNopat();
    }

    /**
     * Hae noppien arvoista saatavat siirrot.
     *
     * @return lista kokonaislukuja jotka ovat arvotut siirrot
     */
    public ArrayList<Integer> haeSiirrot() {
        return siirrot.haeSiirrot();
    }

    /**
     * Asetetaan siirrot.
     *
     * @param s siirroiksi asetettavat luvut
     */
    public void asetaSiirrot(ArrayList<Integer> s) {
        siirrot.asetaSiirrot(s);
    }

    /**
     * Aseta pelaajan siirron lähtoruutu.
     *
     * @param lahtoruutu lähtöruudun indeksi
     * @return true jos pelaajalla on ruudussa siirrettävissä oleva nappula
     */
    public boolean asetaLahtoruutu(int lahtoruutu, Pelaaja pelaaja) {
        if (pelaajaVoiSiirtaaRuuduista(pelaaja).contains((Integer) lahtoruutu)) {
            this.lahtoruutu = lahtoruutu;
            return true;
        }
        return false;
    }

    /**
     * Nollaa pelaajan siirron lähtöruudun.
     */
    public void nollaaLahtoruutu() {
        lahtoruutu = -1;
    }

    /**
     * Haetaan asetettu lähtöruutu.
     *
     * @return lähtöruudun indeksi
     */
    public int haeLahtoruutu() {
        return lahtoruutu;
    }

    /**
     * Haetaan mahdolliset kohderuudut pelaajan siirrolle.
     *
     * @return lista mahdollisia kohderuutuja
     */
    public ArrayList<Integer> pelaajaVoiSiirtaaRuutuihin(Pelaaja pelaaja) {
        return pelaajaVoiSiirtaaRuutuihin(pelaaja, lahtoruutu, siirrot.haeSiirrot());
    }

    /**
     * Voiko pelaaja siirtää nappulansa ruutuun.
     *
     * @param ruutu ruudun indeksi
     * @param pelaaja tarkastettava pelaaja
     * @return true jos pelaaja voi siirtää nappulansa ruutuun
     */
    public boolean ruutuunVoiSiirtya(int ruutu, Pelaaja pelaaja) {
        if (ruutu == pelaaja.haeMaali() && nappulatKotialueella(pelaaja)) {
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
        if ((lauta.ruutuPelaajalla(mista, pelaaja) || mista == Math.abs(pelaaja.haeMaali() - 25)) && this.ruutuunVoiSiirtya(minne, pelaaja)) {
            if (!lauta.ruutuPelaajalla(minne, pelaaja) && lauta.nappuloitaRuudussa(minne) == 1) {
                // tässä syödään vastustajan nappula
                lauta.siirraNappula(minne, pelaaja.haeMaali());
            }
            lauta.siirraPelaajanNappula(mista, minne, pelaaja);
        }

    }

    /**
     * Siirretään pelaajan nappulaa.
     *
     * @param minne kohderuudun indeksi
     */
    public void siirraNappulaa(int minne, Pelaaja pelaaja) {
        if (lahtoruutu > -1) {
            siirraNappulaa(pelaaja, lahtoruutu, minne);
            if (siirrot.haeSiirrot().contains((Integer) Math.abs(lahtoruutu - minne))) {
                siirrot.poistaSiirto((Integer) Math.abs(lahtoruutu - minne));
            } else if (nappulatKotialueella(pelaaja)) {
                siirrot.poistaSiirtoKotialueella((Integer) Math.abs(lahtoruutu - minne));
            }
            lahtoruutu = -1;
        }
    }

    /**
     * Tarkistaa eikö annettu voi Pelaaja siirtää mitään nappuloitaan.
     *
     * @return palauttaa true jos mitään nappuloita ei voi siirtää
     */
    public boolean eiVoiSiirtaa(Pelaaja pelaaja) {
        boolean voiSiirtaa = false;
        if (pelaaja.haeMaali() == 0) {
            voiSiirtaa = voikoPelaajaSiirtaa(pelaaja, -1);
        } else {
            voiSiirtaa = voikoPelaajaSiirtaa(pelaaja, 1);
        }
        return !voiSiirtaa;
    }

    private boolean voikoPelaajaSiirtaa(Pelaaja pelaaja, int suunta) {
        boolean voiSiirtaa = false;
        if (pelaajanNappulaMaara(Math.abs(pelaaja.haeMaali() - 25), pelaaja) > 0) {
            voiSiirtaa = voikoRuutuunSiirtyaSyotyNappula(pelaaja, suunta, voiSiirtaa);
        } else {
            voiSiirtaa = voikoSiirtaaLaudalla(pelaaja, suunta, voiSiirtaa);
        }
        return voiSiirtaa;
    }

    private boolean voikoSiirtaaLaudalla(Pelaaja pelaaja, int suunta, boolean voiSiirtaa) {
        if (!nappulatKotialueella(pelaaja)) {
            voiSiirtaa = voikoSiirtaaLaudallaNormaalisti(pelaaja, suunta, voiSiirtaa);
        } else if (nappulatKotialueella(pelaaja)) {
            voiSiirtaa = voikoSiirtaaKotialueella(pelaaja, voiSiirtaa);
        }
        return voiSiirtaa;
    }

    private boolean voikoSiirtaaKotialueella(Pelaaja pelaaja, boolean voiSiirtaa) {
        for (int ruutu : pelaajaVoiSiirtaaRuuduista(pelaaja)) {
            for (int kohderuutu : pelaajaVoiSiirtaaRuutuihin(pelaaja, ruutu, siirrot.haeSiirrot())) {
                for (int siirto : siirrot.haeSiirrot()) {
                    if (siirto >= ((Integer) Math.abs(ruutu - kohderuutu))) {
                        voiSiirtaa = true;
                        break;
                    }
                }
            }
        }
        return voiSiirtaa;
    }

    private boolean voikoSiirtaaLaudallaNormaalisti(Pelaaja pelaaja, int suunta, boolean voiSiirtaa) {
        for (int siirto : siirrot.haeSiirrot()) {
            for (int ruutu : pelaajaVoiSiirtaaRuuduista(pelaaja)) {
                if (pelaajaVoiSiirtaaRuutuihin(pelaaja, ruutu, siirrot.haeSiirrot()).contains(ruutu + suunta * siirto)) {
                    voiSiirtaa = true;
                    break;
                }
            }
        }
        return voiSiirtaa;
    }

    private boolean voikoRuutuunSiirtyaSyotyNappula(Pelaaja pelaaja, int suunta, boolean voiSiirtaa) {
        for (int siirto : siirrot.haeSiirrot()) {
            //System.out.println("siirto " + siirto);
            if (ruutuunVoiSiirtya((Math.abs(pelaaja.haeMaali() - 25) + suunta * siirto), pelaaja)) {
                voiSiirtaa = true;
                break;
            }
        }
        return voiSiirtaa;
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

        if (lauta.pelaajanNappuloitaRuudussa(pelaaja.haeMaali(), pelaaja) == 15) {
            return true;
        }

        return false;
    }

    /**
     * Mistä ruuduista pelaaja voi siirtää.
     *
     * @param pelaaja tarkistettava pelaaja
     * @return palautetaan lista ruutujen indeksejä joihin voi siirtää
     */
    public ArrayList<Integer> pelaajaVoiSiirtaaRuuduista(Pelaaja pelaaja) {
        ArrayList<Integer> lista = new ArrayList<>();

        int syotyjenNappuloidenRuutu = Math.abs(pelaaja.haeMaali() - 25);

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

        if (pelaaja.haeMaali() == 0) {
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
            } else if (kohderuutu == pelaaja.haeMaali()) {
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
        if (pelaaja.haeMaali() == 0) {
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
