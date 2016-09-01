/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.domain;

import java.util.Random;

/**
 * Luokka määrittelee yksittäisen nopan.
 *
 * @author ttuotila
 */
public class Noppa {

    private Random random;
    private int arvo;

    /**
     * Luo Noppa-olion.
     * 
     * @param r Random
     */
    public Noppa(Random r) {
        random = r;
        this.heita();
    }

    /**
     * Heitä nopalle uusi arvo.
     */
    public void heita() {
        arvo = random.nextInt(6) + 1;
    }

    /**
     * Hae nopan tämänhetkinen arvo.
     * 
     * @return nopan tämänhetkinen arvo
     */
    public int haeArvo() {
        return arvo;
    }

}
