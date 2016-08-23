package tutavla.tavla;

import tutavla.tavla.logiikka.Sovelluslogiikka;
import tutavla.tavla.ui.GraafinenKayttoliittyma;
import tutavla.tavla.ui.Kayttoliittyma;
import tutavla.tavla.ui.Tekstikayttoliittyma;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Main-luokka.
 * 
 * @author ttuotila@cs
 */
public class Main {

    /**
     * main-metodi.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Kayttoliittyma kali = new GraafinenKayttoliittyma();
        Kayttoliittyma kali = new Tekstikayttoliittyma();
        kali.kaynnista();
    }

}
