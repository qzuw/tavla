/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.domain;

/**
 * Luokka kapseloi yksittäiseen siirtoon liittyvät tiedot.
 *
 * @author ttuotila
 */
public class Siirto {

    private final int lahto;
    private final int maali;
    private final boolean syo;
    private final boolean eiVoi;

    /**
     * Luo Siirto-olion.
     * 
     * @param lahto siirron lähtöruudun indeksi
     * @param maali siirron lopetusruudun indeksi
     * @param syo syödäänkö siirrettäessä vastustajan nappula
     * @param eiVoi epäonnistuiko siirto
     */
    public Siirto(int lahto, int maali, boolean syo, boolean eiVoi) {
        this.lahto = lahto;
        this.maali = maali;
        this.syo = syo;
        this.eiVoi = eiVoi;
    }

    /**
     * Palauttaa lähtöruudun indeksin.
     * 
     * @return palauttaa lähtöruudun indeksin
     */
    public int haeLahto() {
        return lahto;
    }

    /**
     * Palauttaa siirron lopetusruudun indeksin.
     * 
     * @return palauttaa siirron lopetusruudun indeksin
     */
    public int haeMaali() {
        return maali;
    }

    /**
     * Palauttaa tiedon onko vastustajan nappula syöty siirtoa tehtäessä.
     * 
     * @return onko siirrettäessä syöty vastustajan nappula
     */
    public boolean vastustajanNappulaSyoty() {
        return syo;
    }

    /**
     * Palauttaa tiedon siirron epäonnistumisesta.
     * 
     * @return true jos siirto ei mahdollinen
     */
    public boolean eiVoiSiirtaa() {
        return eiVoi;
    }

}
