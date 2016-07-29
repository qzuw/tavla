/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla;

/**
 *
 * @author ttuotila
 */
public class Nappula {

    private Pelaaja pelaaja;

    public Nappula(Pelaaja pelaaja) {
        this.pelaaja = pelaaja;
    }

    public boolean onkoMusta() {
        return pelaaja.isMusta();
    }

    public Pelaaja getPelaaja() {
        return pelaaja;
    }
    
    
}
