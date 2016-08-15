/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.domain;

/**
 *
 * @author ttuotila
 */
public class Siirto {

    private final int lahto;
    private final int maali;

    public Siirto(int lahto, int maali) {
        this.lahto = lahto;
        this.maali = maali;
    }

    public int getLahto() {
        return lahto;
    }

    public int getMaali() {
        return maali;
    }

}
