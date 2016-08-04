/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.logiikka;

import tutavla.tavla.domain.Pelaaja;
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
        
        Pelaaja pelaaja1 = new Pelaaja();
        Pelaaja pelaaja2 = new Pelaaja();
        
        pelilogiikka = new Pelilogiikka(pelaaja1, pelaaja2);
        
        pelilogiikka.asetaNappulat();
        
        //pelisilmukka?
        
        //tätä täytyy vielä miettiä
    }

}
