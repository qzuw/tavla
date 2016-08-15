/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.domain;

import java.util.Objects;

/**
 *
 * @author ttuotila
 */
public class Pelaaja {

    private String nimi;
    private boolean ihminen;
    private boolean musta;
    private int maali;

    public Pelaaja(String nimi) {
        this.nimi = nimi;
        musta = false;
        ihminen = true;
        maali = 0;
    }

    public Pelaaja() {
        nimi = "Tietokone";
        ihminen = false;
        musta = true;
        maali = 25;
    }

    public String getNimi() {
        return nimi;
    }

    public boolean isIhminen() {
        return ihminen;
    }

    public boolean isMusta() {
        return musta;
    }

    public int getMaali() {
        return maali;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setIhminen(boolean ihminen) {
        this.ihminen = ihminen;
    }

    public void setMusta(boolean musta) {
        this.musta = musta;
    }

    public void setMaali(int maali) {
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
