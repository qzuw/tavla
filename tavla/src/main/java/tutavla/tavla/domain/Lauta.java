/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Luokka määrittelee pelilaudan.
 *
 * @author ttuotila
 */
public class Lauta {

    private List<Ruutu> ruudut;

    /**
     * Luodaan tyhjä Lauta-olio.
     */
    public Lauta() {
        ruudut = new ArrayList<>();
        // 0 ja 25 erikoistapauksia
        for (int i = 0; i < 26; i++) {
            ruudut.add(new Ruutu());
        }
    }

    /**
     * Onko tietty Ruutu tietyn Pelaajan hallussa.
     *
     * @param ruutu tarkistettavan ruudun indeksi
     * @param pelaaja tarkistettava pelaaja
     * @return true jos ruutu on pelaajan hallussa
     */
    public boolean ruutuPelaajalla(int ruutu, Pelaaja pelaaja) {
        boolean pelaajalla = false;
        if (!ruudut.get(ruutu).isEmpty() && ruutu != 0 && ruutu != 25) {
            pelaajalla = (ruudut.get(ruutu).getPelaaja().equals(pelaaja));
        }
        return pelaajalla;
    }

    /**
     * Ruudun nappuloiden väri.
     *
     * @param ruutu tarkistettavan ruudun indeksi
     * @return true jos nappulat mustia
     */
    public boolean ruudunVariMusta(int ruutu) {
        if (!(ruudut.get(ruutu).nappuloidenMaara() > 0)) {
            return false;
        }
        return ruudut.get(ruutu).nappulat.get(0).getPelaaja().isMusta();
    }

    /**
     * Ruudusssa olevien nappuloiden määrä.
     *
     * @param ruutu ruudun indeksi
     * @return nappuloiden määrä
     */
    public int nappuloitaRuudussa(int ruutu) {
        return ruudut.get(ruutu).nappuloidenMaara();
    }

    /**
     * Ruudussa olevien tietyn pelaajan nappuloiden määrä.
     *
     * @param ruutu ruudun indeksi
     * @param pelaaja tarkistettava pelaaja
     * @return ruudussa olevien pelaajan nappuloiden määrä
     */
    public int pelaajanNappuloitaRuudussa(int ruutu, Pelaaja pelaaja) {
        return ruudut.get(ruutu).pelaajanNappuloidenMaara(pelaaja);
    }

    /**
     * Aseta nappula ruutuun.
     *
     * @param nappula asetetttava nappula
     * @param ruutu asetettavan ruudun indeksi
     */
    public void asetaNappula(Nappula nappula, int ruutu) {
        ruudut.get(ruutu).lisaaNappula(nappula);
    }

    /**
     * Siirrä yksi nappula ruudusta toiseen.
     *
     * @param mista lähtöruudun indeksi
     * @param minne lopetusruudun indeksi
     */
    public void siirraNappula(int mista, int minne) {
        ruudut.get(minne).lisaaNappula(ruudut.get(mista).otaNappula());
    }

    /**
     * Hae pelilaudan ruutu indeksillä.
     *
     * @param ruutu ruudun indeksi
     * @return palauttaa indeksin mukaisen Ruudun
     */
    public Ruutu haeRuutu(int ruutu) {
        return ruudut.get(ruutu);
    }

//    @Override
//    public String toString() {
//        String s = "";
//        for (int i = 13; i <= 24; i++) {
//            s += i + "  ";
//        }
//
//        s += "\n";
//
//        for (int i = 13; i <= 24; i++) {
//            s += ruudut.get(i) + " ";
//        }
//
//        s += "\n";
//        s += "\n";
//
//        for (int i = 12; i >= 1; i--) {
//            s += ruudut.get(i) + " ";
//        }
//        s += "\n";
//        for (int i = 12; i >= 1; i--) {
//            s += i + "  ";
//            if (i < 10) {
//                s += " ";
//            }
//        }
//        s += "\n";
//
//        return s;
//    }
}
