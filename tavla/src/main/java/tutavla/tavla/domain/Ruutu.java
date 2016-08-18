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

    public Ruutu() {
        nappulat = new ArrayList<>();
    }

    public void lisaaNappula(Nappula nappula) {
        if (!nappulat.contains(nappula)) {
            nappulat.add(nappula);
        }
    }

    public Nappula otaNappula() {
        if (nappulat.size() < 1) {
            return null;
        }
        Nappula nappula = nappulat.get(0);
        nappulat.remove(0);
        return nappula;
    }

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

    public int nappuloidenMaara() {
        return nappulat.size();
    }

    public int pelaajanNappuloidenMaara(Pelaaja pelaaja) {
        int maara = 0;
        for (Nappula nappula : nappulat) {
            if (nappula.getPelaaja().equals(pelaaja)) {
                maara++;
            }
        }
        return maara;
    }

    public boolean isEmpty() {
        return nappulat.isEmpty();
    }

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
