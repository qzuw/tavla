/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.ui;

import tutavla.tavla.logiikka.Sovelluslogiikka;

/**
 * Käyttöliittymän toiminnan logiikkaa hallinnoiva luokka.
 *
 * @author ttuotila
 */
public class GUILogiikka {

    private Sovelluslogiikka svl;
    private int pelaajamaara;
    private boolean pelaajaOnIhminen;
    private Piirtoalusta pa;
    private int kysyttavaPelaaja;
    private String kyselyteksti;
    private boolean naytaKyselyikkuna;
    private boolean tyhjennaVastaus;

    /**
     * Konstruktori.
     *
     * @param svl sovelluslogiikka
     */
    public GUILogiikka(Sovelluslogiikka svl) {
        this.svl = svl;
        pelaajaOnIhminen = false;
        pelaajamaara = -1;
        kysyttavaPelaaja = 0;
        kyselyteksti = "Montako pelaajaa? (0-2)";
        naytaKyselyikkuna = true;
        tyhjennaVastaus = false;
    }

    /**
     * Palauta sovelluslogiikka.
     *
     * @return sovelluslogiikka
     */
    public Sovelluslogiikka getSovelluslogiikka() {
        return svl;
    }

    /**
     * Pitääkö kyselyikkuna näyttää.
     *
     * @return true jos ikkuna näytetään
     */
    public boolean naytaKyselyikkuna() {
        return naytaKyselyikkuna;
    }

    /**
     * Palauttaa tekstin kyselyikkunaa varten.
     *
     * @return teksti kyselyikkunalle
     */
    public String getKyselyteksti() {
        return kyselyteksti;
    }

    /**
     * Palautetaan pelaajien määrä.
     *
     * @return pelaajien määrä
     */
    public int getPelaajamaara() {
        return pelaajamaara;
    }

    /**
     * Asetetaan pelaajien määrä.
     *
     * @param pelaajamaara pelaajien määrä
     */
    public void setPelaajamaara(int pelaajamaara) {
        this.pelaajamaara = pelaajamaara;
        kysyttavaPelaaja++;
        kyselyteksti = "Pelaajan 1 nimi?";
        tyhjennaVastaus = true;
    }

    /**
     * Palauttaa pelaajan järjestysnumeron.
     *
     * @return pelaajan numero
     */
    public int kysyttavanPelaajanNro() {
        return kysyttavaPelaaja;
    }

    public void asetaPelaajanNimi(String nimi) {
        svl.getSiirtojarjestys().get(kysyttavaPelaaja - 1).setNimi(nimi);
        svl.getSiirtojarjestys().get(kysyttavaPelaaja - 1).setIhminen(true);
        kysyttavaPelaaja++;
        System.out.println("kys pel" + this.kysyttavaPelaaja);
        kyselyteksti = "Pelaajan " + kysyttavaPelaaja + " nimi?";
        tyhjennaVastaus = true;
    }

    public boolean isTyhjennaVastaus() {
        return tyhjennaVastaus;
    }

    public void setTyhjennaVastaus(boolean tyhjennaVastaus) {
        this.tyhjennaVastaus = tyhjennaVastaus;
    }

    /**
     * Tarkistetaan onko vuorossa oleva pelaaja ihminen.
     *
     * @return true jos vuorossa oleva pelaaja on ihminen
     */
    public boolean pelaajaOnIhminen() {
        return pelaajaOnIhminen;
    }

    /**
     * Lisätään luokalle piirtoalusta.
     *
     * @param pa piirtoalusta
     */
    public void lisaaPiirtoalusta(Piirtoalusta pa) {
        this.pa = pa;
    }

    public void piirraPiirtoalusta() {
        pa.repaint();
    }

}
