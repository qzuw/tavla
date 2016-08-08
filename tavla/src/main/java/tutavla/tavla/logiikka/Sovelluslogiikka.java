/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.logiikka;

import java.util.ArrayList;
import tutavla.tavla.domain.Noppa;
import tutavla.tavla.domain.Pelaaja;
import tutavla.tavla.ui.Kayttoliittyma;

/**
 *
 * @author ttuotila
 */
public class Sovelluslogiikka {

    private Pelilogiikka pelilogiikka;
    private Kayttoliittyma kali;
    private ArrayList<Pelaaja> siirtojarjestys;
    private Noppa noppa1;
    private Noppa noppa2;

    public Sovelluslogiikka(Kayttoliittyma kali) {
        this.kali = kali;
        siirtojarjestys = new ArrayList<>();
        noppa1 = new Noppa();
        noppa2 = new Noppa();

    }

    public void pelaajaSiirtaaEnsin(boolean pelaajaEnsin, Pelaaja pelaaja) {
        if (!(siirtojarjestys.get(0).equals(pelaaja) == pelaajaEnsin)) {
            Pelaaja siirrettava = siirtojarjestys.get(0);
            siirtojarjestys.add(siirrettava);
            siirtojarjestys.remove(0);
        }
    }

    public void setSiirtojarjestys(Pelaaja pelaaja1, Pelaaja pelaaja2) {
        siirtojarjestys.add(pelaaja1);
        siirtojarjestys.add(pelaaja2);
    }

    public ArrayList<Pelaaja> getSiirtojarjestys() {
        return siirtojarjestys;
    }

    public void kaynnista() {
        
        Pelaaja pelaaja1 = new Pelaaja();
        Pelaaja pelaaja2 = new Pelaaja();
        this.setSiirtojarjestys(pelaaja1, pelaaja2);

        pelilogiikka = new Pelilogiikka();

        pelilogiikka.asetaNappulat(siirtojarjestys.get(0), siirtojarjestys.get(1));

    }

}
