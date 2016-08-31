/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.ui;

import tutavla.tavla.logiikka.Sovelluslogiikka;

/**
 * Käyttöliittymän toiminnan logiikkaa hallinnoiva luokka.
 *
 * @author ttuotila
 */
public class GUILogiikka {

    private Sovelluslogiikka svl;
    private int pelaajamaara;
    private boolean pelaajaOnIhminen;
    private Piirtoalusta pa;

    /**
     * Konstruktori.
     *
     * @param svl sovelluslogiikka
     */
    public GUILogiikka(Sovelluslogiikka svl) {
        this.svl = svl;
    }

    /**
     * Palauta sovelluslogiikka.
     *
     * @return sovelluslogiikka
     */
    public Sovelluslogiikka getSovelluslogiikka() {
        return svl;
    }

    /**
     * Tarkistetaan onko vuorossa oleva pelaaja ihminen.
     * 
     * @return true jos vuorossa oleva pelaaja on ihminen
     */
    public boolean isPelaajaOnIhminen() {
        return pelaajaOnIhminen;
    }

    /**
     * Lisätään luokalle piirtoalusta.
     * 
     * @param pa piirtoalusta
     */
    public void lisaaPiirtoalusta(Piirtoalusta pa) {
        this.pa = pa;
    }

    public void run() {

    }
}
