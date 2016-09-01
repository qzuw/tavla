/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.domain;

import java.util.Objects;

/**
 * Luokka määrittelee pelaajan tiedot.
 *
 * @author ttuotila
 */
public class Pelaaja {

    private String nimi;
    private boolean ihminen;
    private boolean musta;
    private int maali;

    /**
     * Luo uuden Pelaaja-olion.
     * 
     * @param nimi pelaajan nimi
     */
    public Pelaaja(String nimi) {
        this.nimi = nimi;
        musta = false;
        ihminen = true;
        maali = 0;
    }

    /**
     * Luo uuden Pelaaja-olion.
     */
    public Pelaaja() {
        nimi = "Tietokone";
        ihminen = false;
        musta = true;
        maali = 25;
    }

    /**
     * Hae Pelaajan nimi.
     * 
     * @return pelaajan nimi
     */
    public String haeNimi() {
        return nimi;
    }

    /**
     * Onko Pelaaja ihminen.
     * 
     * @return true jos pelaaja on ihminen
     */
    public boolean onkoIhminen() {
        return ihminen;
    }

    /**
     * Palauta true jos pelaajan nappuloiden väri on musta.
     * 
     * @return true jos pelaajan nappulat ovat mustia
     */
    public boolean onkoMusta() {
        return musta;
    }

    /**
     * Hae Pelaajan maaliruudun indeksi.
     * 
     * @return maaliruudun indeksi
     */
    public int haeMaali() {
        return maali;
    }

    /**
     * Aseta pelaajan nimi.
     * 
     * @param nimi pelaajan nimi
     */
    public void asetaNimi(String nimi) {
        this.nimi = nimi;
    }

    /**
     * Aseta pelaaja ihmiseksi.
     * 
     * @param ihminen true jos pelaaja on ihminen
     */
    public void asetaIhmiseksi(boolean ihminen) {
        this.ihminen = ihminen;
    }

    /**
     * Aseta pelaajan nappuloiden väri.
     * 
     * @param musta true jos pelaaja on musta
     */
    public void asetaMustaksi(boolean musta) {
        this.musta = musta;
    }

    /**
     * Aseta pelaajan maaliruudun indeksi.
     * 
     * @param maali maaliruudun indeksi, joko 0 tai 25
     */
    public void asetaMaali(int maali) {
        if (maali == 0 || maali == 25) {
            this.maali = maali;
        }
    }

    @Override
    public String toString() {
        String s = nimi + " (";
        if (musta) {
            s += "musta)";
        } else {
            s += "valkoinen)";
        }
        return s;
    }

}
