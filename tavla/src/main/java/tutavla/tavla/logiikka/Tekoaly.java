/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.logiikka;

import java.util.ArrayList;
import tutavla.tavla.domain.Pelaaja;
import tutavla.tavla.domain.Siirto;

/**
 * Rajapinta Tekoalylle.
 *
 * @author ttuotila
 */
public interface Tekoaly {

    /**
     * Valitsee tietokonepelaajalle nappuloiden värin.
     *
     * @param siirtojarjestys lista pelaajista
     * @return satunnaisesti valittu pelaaja
     */
    public Pelaaja valitseVari(ArrayList<Pelaaja> siirtojarjestys);

    /**
     * Valitsee yhden siirron ja palauttaa sen.
     *
     * @param tietokone siirtava pelaaja
     * @param plk pelilogiikka
     * @param siirrot lista noppien arvoja
     * @return palauttaa tekoälyn valitseman siirron
     */
    public Siirto pelaa(Pelaaja tietokone, Pelilogiikka plk, ArrayList<Integer> siirrot);

}
