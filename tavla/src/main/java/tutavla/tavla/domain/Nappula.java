/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.domain;

/**
 * Luokka määrittelee yksittäisen pelinappulan.
 *
 * @author ttuotila
 */
public class Nappula {

    private Pelaaja pelaaja;

    /**
     * Luo Nappula-olion.
     * 
     * @param pelaaja nappulan omistava pelaaja
     */
    public Nappula(Pelaaja pelaaja) {
        this.pelaaja = pelaaja;
    }

    /**
     * Hae nappulan omistava Pelaaja.
     * 
     * @return palauttaa nappulan omistajan
     */
    public Pelaaja haePelaaja() {
        return pelaaja;
    }

}
