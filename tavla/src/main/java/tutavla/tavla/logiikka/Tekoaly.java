/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.logiikka;

import java.util.*;
import tutavla.tavla.domain.Lauta;
import tutavla.tavla.domain.Pelaaja;
import tutavla.tavla.domain.Siirto;

/**
 *
 * @author ttuotila
 */
public class Tekoaly {

    private Random random;

    public Tekoaly(Random random) {
        this.random = random;
    }

    /**
     * Arpoo tietokonepelaajalle nappuloiden värin valitsemalla satunnaisesti 
     * pelaajan
     * 
     * @param siirtojarjestys lista pelaajista
     * @return satunnaisesti valittu pelaaja
     */
    public Pelaaja valitseVari(ArrayList<Pelaaja> siirtojarjestys) {
        return siirtojarjestys.get(random.nextInt(siirtojarjestys.size()));
    }

    /**
     * Arpoo yhden siirron ja palauttaa sen.
     * 
     * @param tietokone
     * @param plk
     * @param siirrot lista noppien arvoja
     * @return 
     */
    public Siirto pelaa(Pelaaja tietokone, Pelilogiikka plk, ArrayList<Integer> siirrot) {
        ArrayList<Integer> lahtoruudut = plk.pelaajaVoiSiirtaaRuuduista(tietokone);

        int lahtoruutu = 0;
        int kohderuutu = 0;
        boolean syoVastustajanNappula = false;

        int limit = 1000;
        while (true) {

            lahtoruutu = lahtoruudut.get(random.nextInt(lahtoruudut.size()));
            ArrayList<Integer> kohderuudut = plk.pelaajaVoiSiirtaaRuutuihin(tietokone, lahtoruutu);

            if (kohderuudut.size() > 0) {
                kohderuutu = kohderuudut.get(random.nextInt(kohderuudut.size()));
                Integer siirto = Math.abs(kohderuutu - lahtoruutu);
                if (siirrot.contains(siirto) && kohderuutu != tietokone.getMaali()) {
                    syoVastustajanNappula = syodaankoTassaVastustajanNappula(kohderuutu, tietokone, plk, syoVastustajanNappula);
                    plk.siirraNappulaa(tietokone, lahtoruutu, kohderuutu);
                    siirrot.remove(siirto);
                    break;
                } else if (plk.nappulatKotialueella(tietokone)) {
                    int poistettava = -1;
                    poistettava = nappulaSilmukkaRefaktoroiNimiMyohemmin(siirrot, siirto, plk, tietokone, lahtoruutu, kohderuutu, poistettava);
                    if (poistettava >= 0) {
                        syoVastustajanNappula = syodaankoTassaVastustajanNappula(kohderuutu, tietokone, plk, syoVastustajanNappula);
                        siirrot.remove(poistettava);
                        break;
                    }
                }
            }

            limit--;
            if (limit < 0) {
                break;
            }
        }

        Siirto s = new Siirto(lahtoruutu, kohderuutu, syoVastustajanNappula, false);

        return s;
    }

    private int nappulaSilmukkaRefaktoroiNimiMyohemmin(ArrayList<Integer> siirrot, Integer siirto, Pelilogiikka plk, Pelaaja tietokone, int lahtoruutu, int kohderuutu, int poistettava) {
        for (Integer s : siirrot) {
            if (s > siirto) {
                plk.siirraNappulaa(tietokone, lahtoruutu, kohderuutu);
                poistettava = siirrot.indexOf(s);
                break;
            }
        }
        return poistettava;
    }

    private boolean syodaankoTassaVastustajanNappula(int kohderuutu, Pelaaja tietokone, Pelilogiikka plk, boolean syoVastustajanNappula) {
        if (kohderuutu != tietokone.getMaali()
                && !plk.ruutuOnTyhja(kohderuutu)
                && plk.pelaajanNappulaMaara(kohderuutu, tietokone) == 0) {
            syoVastustajanNappula = true;
        }
        return syoVastustajanNappula;
    }

//    private boolean syodaankoTassaSamallaVastustajanNappula(Pelilogiikka plk, int kohderuutu, Pelaaja tietokone, boolean syoVastustajanNappula) {
//        if (!plk.ruutuOnTyhja(kohderuutu) && plk.pelaajanNappulaMaara(kohderuutu, tietokone) == 0) {
//            syoVastustajanNappula = true;
//        }
//        return syoVastustajanNappula;
//    }
}
