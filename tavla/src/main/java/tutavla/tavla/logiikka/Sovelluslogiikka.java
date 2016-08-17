/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.logiikka;

import java.util.ArrayList;
import java.util.Random;
import tutavla.tavla.domain.Lauta;
import tutavla.tavla.domain.Noppa;
import tutavla.tavla.domain.Pelaaja;
import tutavla.tavla.domain.Siirto;
import tutavla.tavla.ui.Kayttoliittyma;

/**
 *
 * @author ttuotila
 */
public class Sovelluslogiikka {

    private Pelilogiikka pelilogiikka;
    private ArrayList<Pelaaja> siirtojarjestys;
    private ArrayList<Noppa> nopat;
    private ArrayList<Integer> siirrot;
    private Tekoaly tekoaly;

    public Sovelluslogiikka() {
        siirtojarjestys = new ArrayList<>();
        siirrot = new ArrayList<>();
        nopat = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            nopat.add(new Noppa(random));
        }

        tekoaly = new Tekoaly(random);

        Pelaaja pelaaja1 = new Pelaaja();
        Pelaaja pelaaja2 = new Pelaaja();
        this.setSiirtojarjestys(pelaaja1, pelaaja2);

        pelilogiikka = new Pelilogiikka();

        pelilogiikka.alustaPelitilanne(siirtojarjestys.get(0), siirtojarjestys.get(1));
    }

    public void maaritaPelaaja(int pelaaja, String nimi) {
        try {
            siirtojarjestys.get(pelaaja).setIhminen(true);
            siirtojarjestys.get(pelaaja).setNimi(nimi);
        } catch (Exception e) {
        }
    }

    public void pelaajaSiirtaaEnsin(boolean pelaajaEnsin, Pelaaja pelaaja) {
        if (!(siirtojarjestys.get(0).equals(pelaaja) == pelaajaEnsin)) {
            siirtojarjestys.add(siirtojarjestys.get(0));
            siirtojarjestys.remove(0);
        }
    }

    public void pelaajaSiirtaaEnsin(boolean pelaajaEnsin, int pelaajaIndex) {
        if ((pelaajaIndex != 0) == pelaajaEnsin) {
            siirtojarjestys.add(siirtojarjestys.get(0));
            siirtojarjestys.remove(0);
        }
    }

    public void asetaPelaajaMustaksi(Pelaaja pelaaja) {
        for (Pelaaja p : siirtojarjestys) {
            if (p.equals(pelaaja)) {
                p.setMusta(true);
            } else {
                p.setMusta(false);
            }
        }
    }

    private void setSiirtojarjestys(Pelaaja pelaaja1, Pelaaja pelaaja2) {
        siirtojarjestys.clear();
        siirtojarjestys.add(pelaaja1);
        siirtojarjestys.add(pelaaja2);
    }

    public ArrayList<Pelaaja> getSiirtojarjestys() {
        return siirtojarjestys;
    }

    /**
     * Poistaa vanhat siirrot, heittää noppia uudestaan ja tallettaa uudet
     * siirrot
     */
    public void heitaNopat() {

        siirrot.clear();

        for (int i = 0; i < 2; i++) {
            nopat.get(i).heita();
            if (siirrot.contains(nopat.get(i).getArvo())) {
                for (int j = 0; j < 3; j++) {
                    siirrot.add(nopat.get(i).getArvo());
                }
            } else {
                siirrot.add(nopat.get(i).getArvo());
            }
        }
    }

    public ArrayList<Integer> haeSiirrot() {

        return siirrot;
    }

    public void tietokoneValitseeVarin() {
        this.asetaPelaajaMustaksi(tekoaly.valitseVari(siirtojarjestys));
    }

    /**
     * Antaa tietokoneen pelattavaksi yhden siirron ja palauttaa Siirto-oliona
     * tämän tuloksen.
     *
     * @param tietokone Pelaaja jonka nappuloilla tietokone pelaa siirron
     * @return valittu siirto
     */
    public Siirto pelaaTietokone(Pelaaja tietokone) {
        Siirto siirto = new Siirto(0, 0, false, false);
        if (!this.eiVoiSiirtaa(tietokone)) {
            siirto = tekoaly.pelaa(tietokone, pelilogiikka, siirrot);
        } else {
            siirto = new Siirto(0, 0, false, true);
        }
        return siirto;
    }

    /**
     * Tarkistaa voiko annettu Pelaaja siirtää mitään nappuloitaan
     *
     * @param pelaaja tarkistettava pelaaja
     * @return palauttaa true jos mitään nappuloita ei voi siirtää
     */
    public boolean eiVoiSiirtaa(Pelaaja pelaaja) {
        boolean voiSiirtaa = false;

        if (pelaaja.getMaali() == 0) {
            voiSiirtaa = voikoPelaajaSiirtaa(pelaaja, -1);
        } else {
            voiSiirtaa = voikoPelaajaSiirtaa(pelaaja, 1);
        }
        return !voiSiirtaa;
    }

    private boolean voikoPelaajaSiirtaa(Pelaaja pelaaja, int suunta) {
        boolean voiSiirtaa = false;
        if (pelilogiikka.pelaajanNappulaMaara(Math.abs(pelaaja.getMaali() - 25), pelaaja) > 0) {
            for (int siirto : siirrot) {
                if (pelilogiikka.ruutuunVoiSiirtya((Math.abs(pelaaja.getMaali() - 25) + suunta * siirto), pelaaja)) {
                    voiSiirtaa = true;
                    break;
                }
            }
        } else {
            for (int siirto : siirrot) {
                for (int ruutu : pelilogiikka.pelaajaVoiSiirtaaRuuduista(pelaaja)) {
                    if (pelilogiikka.pelaajaVoiSiirtaaRuutuihin(pelaaja, ruutu).contains(ruutu + suunta * siirto)) {
                        voiSiirtaa = true;
                        break;
                    }
                }
            }
        }
        return voiSiirtaa;
    }

    public Lauta pelitilanne() {
        return pelilogiikka.pelitilanne();
    }

    /**
     * Käy Pelaajat läpi ja tarkistaa onko joku voittanut
     *
     * @return true jos joku on voittanut
     */
    public boolean onkoJokuVoittanut() {

        for (Pelaaja pelaaja : siirtojarjestys) {
            if (pelilogiikka.onkoPelaajaVoittanut(pelaaja)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Palauttaa pelin voittaneen Pelaajan
     *
     * @return pelin voittanut pelaaja
     */
    public Pelaaja kukaVoitti() {

        for (Pelaaja pelaaja : siirtojarjestys) {
            if (pelilogiikka.onkoPelaajaVoittanut(pelaaja)) {
                return pelaaja;
            }
        }

        return null;
    }

}
