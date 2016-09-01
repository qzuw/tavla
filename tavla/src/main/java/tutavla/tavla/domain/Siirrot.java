/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.domain;

import java.util.*;

/**
 * Luokka sisältää nopat ja pelaajan nopista käyttöönsä saamat siirrot.
 *
 * @author ttuotila
 */
public class Siirrot {

    private ArrayList<Noppa> nopat;
    private ArrayList<Integer> siirrot;
    private Random random;

    /**
     * Luo Siirrot-olion.
     * 
     * @param random arvontaan käytettavä Random
     */
    public Siirrot(Random random) {
        siirrot = new ArrayList<>();
        nopat = new ArrayList<>();
        random = new Random();

        for (int i = 0; i < 2; i++) {
            nopat.add(new Noppa(random));
        }

    }

    /**
     * Poistaa vanhat siirrot, heittää noppia uudestaan ja tallettaa uudet
     * siirrot.
     */
    public void heitaNopat() {

        siirrot.clear();

        for (int i = 0; i < nopat.size(); i++) {
            nopat.get(i).heita();
            if (siirrot.contains(nopat.get(i).getArvo())) {
                for (int j = 0; j < 3; j++) {
                    siirrot.add(nopat.get(i).getArvo());
                }
            } else {
                siirrot.add(nopat.get(i).getArvo());
            }
        }
    }

    /**
     * Palauttaa listan siirtoja.
     * 
     * @return lista siirtoja
     */
    public ArrayList<Integer> haeSiirrot() {

        return siirrot;
    }

}
