/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.logiikka;

import java.util.ArrayList;
import tutavla.tavla.domain.Lauta;
import tutavla.tavla.domain.Noppa;
import tutavla.tavla.domain.Pelaaja;
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

    public Sovelluslogiikka() {
        siirtojarjestys = new ArrayList<>();
        siirrot = new ArrayList<>();
        nopat = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            nopat.add(new Noppa());
        }

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
            Pelaaja siirrettava = siirtojarjestys.get(0);
            siirtojarjestys.add(siirrettava);
            siirtojarjestys.remove(0);
        }
    }

    public void pelaajaSiirtaaEnsin(boolean pelaajaEnsin, int pelaajaIndex) {
        if (!(pelaajaIndex != 0) == pelaajaEnsin) {
            Pelaaja siirrettava = siirtojarjestys.get(0);
            siirtojarjestys.add(siirrettava);
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

    public void heitaNopat() {

        for (int i = 0; i < 2; i++) {
            nopat.get(i).arvo();
            if (siirrot.contains(nopat.get(i).getArvo())) {
                for (int j = 0; j < 3; j++) {
                    siirrot.add(nopat.get(i).getArvo());
                }
            } else {
                siirrot.add(nopat.get(i).getArvo());
            }
        }
    }

    public void poistaSiirrot() {
        siirrot.clear();
    }

    public ArrayList<Integer> haeSiirrot() {

        return siirrot;
    }

    public void tietokoneValitseeVarin() {
        // rand
    }

    public void pelaaTietokone() {
// tänne logiikka mahtavalle tekoälylle        
    }

    public Lauta pelitilanne() {
        return pelilogiikka.pelitilanne();
    }

    public boolean onkoJokuVoittanut() {

        for (Pelaaja pelaaja : siirtojarjestys) {
            if (pelilogiikka.onkoPelaajaVoittanut(pelaaja)) {
                return true;
            }
        }

        return false;
    }

    public Pelaaja kukaVoitti() {

        for (Pelaaja pelaaja : siirtojarjestys) {
            if (pelilogiikka.onkoPelaajaVoittanut(pelaaja)) {
                return pelaaja;
            }
        }

        return null;
    }

}
