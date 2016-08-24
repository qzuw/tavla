/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Luokka määrittelee yksittäisen ruudun ja sen sisällön.
 *
 * @author ttuotila
 */
public class Ruutu {

    List<Nappula> nappulat;

    /**
     * Luodaan Ruutu-olio.
     */
    public Ruutu() {
        nappulat = new ArrayList<>();
    }

    /**
     * Lisätään ruutuun nappula.
     * 
     * @param nappula ruutuun lisättävä nappula
     */
    public void lisaaNappula(Nappula nappula) {
        if (!nappulat.contains(nappula)) {
            nappulat.add(nappula);
        }
    }

    /**
     * Otetaan ruudusta nappula.
     * 
     * @return palautetaan otettu nappula.
     */
    public Nappula otaNappula() {
        if (nappulat.size() < 1) {
            return null;
        }
        Nappula nappula = nappulat.get(0);
        nappulat.remove(0);
        return nappula;
    }

    /**
     * Otetaan ruudusta tietyn pelaajan nappula.
     * 
     * @param pelaaja Pelaaja jonka nappula otetaan
     * @return palautetaan otettu nappula
     */
    public Nappula otaPelaajanNappula(Pelaaja pelaaja) {
        if (nappulat.size() < 1) {
            return null;
        }

        int poistettava = -1;
        for (int i = 0; i < nappulat.size(); i++) {
            if (nappulat.get(i).getPelaaja().equals(pelaaja)) {
                poistettava = i;
                break;
            }
        }
        if (poistettava < 0) {
            return null;
        }

        Nappula nappula = nappulat.get(poistettava);
        nappulat.remove(poistettava);
        return nappula;
    }

    /**
     * Haetaan ruudussa olevien nappuloiden määrä.
     * 
     * @return nappuloiden määrä ruudussa
     */
    public int nappuloidenMaara() {
        return nappulat.size();
    }

//    /**
//     * Haetaan lista ruudussa olevista nappuloista.
//     * 
//     * @return lista nappuloita
//     */
//    public List<Nappula> ruudunNappulat() {
//        return nappulat;
//    }

    /**
     * Haetaan Ruudussa olevien tietyn pelaajan nappuloiden määrä.
     * 
     * @param pelaaja pelaaja jonka nappuloiden määrä halutaan tietää
     * @return pelaajan nappuloiden määrä ruudussa
     */
    public int pelaajanNappuloidenMaara(Pelaaja pelaaja) {
        int maara = 0;
        for (Nappula nappula : nappulat) {
            if (nappula.getPelaaja().equals(pelaaja)) {
                maara++;
            }
        }
        return maara;
    }

    /**
     * Onko Ruutu tyhjä?
     * 
     * @return true jos ruutu on tyhjä
     */
    public boolean isEmpty() {
        return nappulat.isEmpty();
    }

    /**
     * Haetaan millä Pelaajalla on nappuloita Ruudussa.
     * 
     * @return pelaaja jolla on nappuloita ruudussa
     */
    public Pelaaja getPelaaja() {
        if (nappulat.isEmpty()) {
            return null;
        }
        return nappulat.get(0).getPelaaja();
    }

    @Override
    public String toString() {
        String s = "[] ";

        if (!this.isEmpty()) {
            if (this.nappulat.get(0).getPelaaja().isMusta()) {
                s = "M";
            } else {
                s = "V";
            }
            s += this.nappuloidenMaara();
            if (this.nappuloidenMaara() < 10) {
                s += " ";
            }
        }

        return s;
    }
}
