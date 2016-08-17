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
    private final boolean syo;
    private final boolean eiVoi;

    public Siirto(int lahto, int maali, boolean syo, boolean eiVoi) {
        this.lahto = lahto;
        this.maali = maali;
        this.syo = syo;
        this.eiVoi = eiVoi;
    }

    public int getLahto() {
        return lahto;
    }

    public int getMaali() {
        return maali;
    }

    public boolean vastustajanNappulaSyoty() {
        return syo;
    }

    public boolean eiVoiSiirtaa() {
        return eiVoi;
    }

}
