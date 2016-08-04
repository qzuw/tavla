/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.logiikka;

import tutavla.tavla.ui.Kayttoliittyma;

/**
 *
 * @author ttuotila
 */
public class Sovelluslogiikka {

    private Pelilogiikka pelilogiikka;
    private Kayttoliittyma kali;

    public Sovelluslogiikka(Kayttoliittyma kali) {
        this.kali = kali;
    }

    public void kaynnista() {
        //kysy pelaajamaara
        //kysy nimet
        //pelilogiikka = new Pelilogiikka(...);
        
        pelilogiikka.asetaNappulat();
        pelilogiikka.kaynnistaPeli();
        
        //tätä täytyy vielä miettiä
    }

}
