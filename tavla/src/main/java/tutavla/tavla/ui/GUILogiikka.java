/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.ui;

import javax.swing.JFrame;
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
    private String vuoroteksti;
    private boolean naytaKyselyikkuna;
    private boolean tyhjennaVastaus;
    private JFrame kehys;
    private JFrame kyselyikkuna;
    private JFrame vuoroikkuna;
    private boolean onkoPeliKaynnissa;
    private boolean lopetetaan;

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
        vuoroteksti = "Peli alkaa.";
        naytaKyselyikkuna = true;
        tyhjennaVastaus = false;
        onkoPeliKaynnissa = false;
        lopetetaan = false;
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

    public String getVuoroteksti() {
        return vuoroteksti;
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

    /**
     * Määritellään pelaajan nimi.
     *
     * @param nimi nimi pelaajalle
     */
    public void asetaPelaajanNimi(String nimi) {
        svl.getSiirtojarjestys().get(kysyttavaPelaaja - 1).setNimi(nimi);
        svl.getSiirtojarjestys().get(kysyttavaPelaaja - 1).setIhminen(true);
        kysyttavaPelaaja++;
        kyselyteksti = "Pelaajan " + kysyttavaPelaaja + " nimi?";
        tyhjennaVastaus = true;
        this.pelaajaOnIhminen = true;
    }

    /**
     * Tarkistetaan pitääkö vastauskenttä tyhjentää.
     *
     * @return true jos vastauskenttä pitää tyhjentää
     */
    public boolean isTyhjennaVastaus() {
        return tyhjennaVastaus;
    }

    /**
     * Asetetaan vastauskenttä tyhjennettäväksi.
     *
     * @param tyhjennaVastaus true jos vastauskenttä pitää tyhjentää
     */
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

    /**
     * Lisätään luokalle kehys.
     *
     * @param kehys piirtoalustan kehys
     */
    public void lisaaKehys(JFrame kehys) {
        this.kehys = kehys;
        kehys.setVisible(true);
        kehys.setVisible(false);
    }

    /**
     * Lisätään luokalle kyselyikkuna.
     *
     * @param kyselyikkuna ikkuna kyselyitä varten
     */
    public void lisaaKyselyikkuna(JFrame kyselyikkuna) {
        this.kyselyikkuna = kyselyikkuna;
        kyselyikkuna.setVisible(true);
    }

    /**
     * Lisätään luokalle vuoroikkuna.
     *
     * @param vuoroikkuna ikkuna vuoron vaihtumisesta ilmoittamiseen
     */
    public void lisaaVuoroikkuna(JFrame vuoroikkuna) {
        this.vuoroikkuna = vuoroikkuna;
        vuoroikkuna.setVisible(true);
        vuoroikkuna.setVisible(false);
    }

    /**
     * Päivitetään piirtoalusta.
     */
    public void piirraPiirtoalusta() {
        pa.repaint();
    }

    public void kehyksenNakyvyys(boolean naytetaanko) {
        kehys.repaint();
        kehys.setVisible(naytetaanko);
    }

    public void kyselyikkunanNakyvyys(boolean naytetaanko) {
        kyselyikkuna.setVisible(naytetaanko);
    }

    public void vuoroNakyvyys(boolean naytetaanko) {
        vuoroikkuna.setVisible(naytetaanko);
    }

    public void kaynnistaPeli() {
        svl.heitaNopat();
        pelaajaOnIhminen= svl.getVuorossaOlevaPelaaja().isIhminen();
    }

    public void vuoroVaihtuu() {
        if (!onkoPeliKaynnissa) {
            // System.out.println("sadf" + onkoPeliKaynnissa + " " + svl.haeSiirrot());
            int noppa1 = svl.haeSiirrot().get(0);
            int noppa2 = svl.haeSiirrot().get(1);
            vuoroteksti = svl.getSiirtojarjestys().get(0) + " heitti " + noppa1 + " ja " + svl.getSiirtojarjestys().get(1) + " heitti " + noppa2 + ".";
            if (noppa1 > noppa2) {
                svl.pelaajaSiirtaaEnsin(true, 0);
                vuoroteksti += " " + svl.getVuorossaOlevaPelaaja().toString() + " liikkuu ensin.";
                this.onkoPeliKaynnissa = true;
            } else if (noppa1 < noppa2) {
                svl.pelaajaSiirtaaEnsin(true, 1);
                vuoroteksti += " " + svl.getVuorossaOlevaPelaaja().toString() + " liikkuu ensin.";
                this.onkoPeliKaynnissa = true;
            } else {
                vuoroteksti += " Heitetään noppaa uudestaan.";
            }
        } else {
            vuoroteksti = "Vuoro vaihtuu.";
            svl.vaihdaVuoroa();
            this.vuoroteksti = "Vuoro vaihtuu.";
            this.vuoroikkuna.setVisible(true);
            svl.heitaNopat();
            if (svl.getVuorossaOlevaPelaaja().isIhminen()) {
                this.pelaajaOnIhminen = true;
            } else {
                this.pelaajaOnIhminen = false;
            }
        }

    }

    public boolean onkoPeliKaynnissa() {
        return onkoPeliKaynnissa;
    }

    public void heitaNoppaa() {
        svl.heitaNopat();
    }

    public void voitto() {
        vuoroteksti = svl.kukaVoitti().toString() + " on voittanut!";
        this.vuoroikkuna.setVisible(true);
        onkoPeliKaynnissa = false;
        lopetetaan = true;
    }

    public boolean lopetetaanko() {
        return this.lopetetaan;
    }

    public void lopeta() {
        System.exit(0);
    }
}
