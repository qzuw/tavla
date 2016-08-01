/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla;

import java.util.Objects;

/**
 *
 * @author ttuotila
 */
public class Pelaaja {

    private String nimi;
    private boolean ihminen;
    private boolean musta;

    public Pelaaja(String nimi) {
        this.nimi = nimi;
        musta = false;
        ihminen = true;
    }

    public Pelaaja() {
        nimi = "Tietokone";
        ihminen = false;
        musta = true;
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

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setIhminen(boolean ihminen) {
        this.ihminen = ihminen;
    }

    public void setMusta(boolean musta) {
        this.musta = musta;
    }

//    @Override
//    public int hashCode() {
//        int hash = 7;
//        hash = 71 * hash + Objects.hashCode(this.nimi);
//        hash = 71 * hash + (this.ihminen ? 1 : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final Pelaaja other = (Pelaaja) obj;
//        if (this.ihminen != other.ihminen) {
//            return false;
//        }
//        if (!Objects.equals(this.nimi, other.nimi)) {
//            return false;
//        }
//        return true;
//    }
}
