/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.ui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import tutavla.tavla.logiikka.Sovelluslogiikka;

/**
 * Käyttöliittymän toiminnan logiikkaa hallinnoiva luokka.
 *
 * @author ttuotila
 */
public class GUILogiikka {

    private Sovelluslogiikka sovelluslogiikka;
    private int pelaajamaara;
    //private boolean pelaajaOnIhminen;
    private Piirtoalusta piirtoalusta;
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
    JLabel siirrot;
    JLabel pelaaja;

    /**
     * Konstruktori.
     *
     * @param svl sovelluslogiikka
     */
    public GUILogiikka(Sovelluslogiikka svl) {
        this.sovelluslogiikka = svl;
//        pelaajaOnIhminen = false;
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
    public Sovelluslogiikka haeSovelluslogiikka() {
        return sovelluslogiikka;
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
    public String haeKyselyteksti() {
        return kyselyteksti;
    }

    /**
     * Hae teksti vuoronvaihtoikkunaa varten.
     *
     * @return teksti joka vuoronvaihtoikkunan tulisi näyttää
     */
    public String haeVuoroteksti() {
        return vuoroteksti;
    }

    /**
     * Palautetaan pelaajien määrä.
     *
     * @return pelaajien määrä
     */
    public int haePelaajamaara() {
        return pelaajamaara;
    }

    /**
     * Asetetaan pelaajien määrä.
     *
     * @param pelaajamaara pelaajien määrä
     */
    public void asetaPelaajamaara(int pelaajamaara) {
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
        sovelluslogiikka.haeSiirtojarjestys().get(kysyttavaPelaaja - 1).asetaNimi(nimi);
        sovelluslogiikka.haeSiirtojarjestys().get(kysyttavaPelaaja - 1).asetaIhmiseksi(true);
        kysyttavaPelaaja++;
        kyselyteksti = "Pelaajan " + kysyttavaPelaaja + " nimi?";
        tyhjennaVastaus = true;
//        this.pelaajaOnIhminen = true;
    }

    /**
     * Tarkistetaan pitääkö vastauskenttä tyhjentää.
     *
     * @return true jos vastauskenttä pitää tyhjentää
     */
    public boolean tyhjennetaankoVastaus() {
        return tyhjennaVastaus;
    }

    /**
     * Asetetaan vastauskenttä tyhjennettäväksi.
     *
     * @param tyhjennaVastaus true jos vastauskenttä pitää tyhjentää
     */
    public void asetaTyhjennaVastaus(boolean tyhjennaVastaus) {
        this.tyhjennaVastaus = tyhjennaVastaus;
    }

    /**
     * Tarkistetaan onko vuorossa oleva pelaaja ihminen.
     *
     * @return true jos vuorossa oleva pelaaja on ihminen
     */
    public boolean pelaajaOnIhminen() {
//        return pelaajaOnIhminen;
        return sovelluslogiikka.haeVuorossaOlevaPelaaja().onkoIhminen();
    }

    /**
     * Lisätään luokalle piirtoalusta.
     *
     * @param pa piirtoalusta
     */
    public void lisaaPiirtoalusta(Piirtoalusta pa) {
        this.piirtoalusta = pa;
    }

    /**
     * Lisää käyttöliittymän siirroista ja vuorossa olevasta pelaajasta kertovat
     * elementit GUILogiikkaan.
     *
     * @param siirrot siirroista kertova JLabel
     * @param pelaaja vuorossa olevasta pelaajasta kertova JLabel
     */
    public void lisaaSiirrotJaPelaaja(JLabel siirrot, JLabel pelaaja) {
        this.siirrot = siirrot;
        this.pelaaja = pelaaja;
    }

    private void paivitaSiirrotJaPelaaja() {
        pelaaja.setText(sovelluslogiikka.haeVuorossaOlevaPelaaja().toString());
        siirrot.setText("Siirrot: " + sovelluslogiikka.haeSiirrot());
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
        piirtoalusta.repaint();
    }

    /**
     * Asetetaan kehyksen näkyvyys.
     *
     * @param naytetaanko true jos kehys halutaan näkyväksi
     */
    public void kehyksenNakyvyys(boolean naytetaanko) {
        paivitaSiirrotJaPelaaja();
        kehys.repaint();
        kehys.revalidate();
        kehys.setVisible(naytetaanko);
    }

    /**
     * Asetetaan kyselyikkunan näkyvyys.
     *
     * @param naytetaanko true jos kyselyikkuna halutaan näkyväksi
     */
    public void kyselyikkunanNakyvyys(boolean naytetaanko) {
        kyselyikkuna.setVisible(naytetaanko);
    }

    /**
     * Asetetaan vuoroikkunan näkyvyys.
     *
     * @param naytetaanko true jos vuoroikkuna halutaan näkyväksi
     */
    public void vuoroNakyvyys(boolean naytetaanko) {
        vuoroikkuna.setVisible(naytetaanko);
    }

    /**
     * Käynnistetään peli.
     */
    public void kaynnistaPeli() {
//        pelaajaOnIhminen = sovelluslogiikka.haeVuorossaOlevaPelaaja().onkoIhminen();
        paivitaSiirrotJaPelaaja();
        kehys.revalidate();
    }

    /**
     * Pelivuoro vaihtuu.
     */
    public void vuoroVaihtuu() {
        if (sovelluslogiikka.onkoJokuVoittanut()) {
            voitto();
        }

        if (!onkoPeliKaynnissa && !lopetetaan) {
            arvoAloittaja();
        } else if (!lopetetaan) {
            vuoroteksti = "Vuoro vaihtuu.";

            sovelluslogiikka.vaihdaVuoroa();
            sovelluslogiikka.nollaaLahtoruutu();
//            if (sovelluslogiikka.haeVuorossaOlevaPelaaja().onkoIhminen()) {
//                this.pelaajaOnIhminen = true;
//            } else {
//                this.pelaajaOnIhminen = false;
//            }
            sovelluslogiikka.heitaNopat();
        }
        if (sovelluslogiikka.onkoJokuVoittanut()) {
            voitto();
        }

        paivitaSiirrotJaPelaaja();
        kehys.revalidate();

    }

    private void arvoAloittaja() {
        int noppa1 = sovelluslogiikka.haeSiirrot().get(0);
        int noppa2 = sovelluslogiikka.haeSiirrot().get(1);
        vuoroteksti = sovelluslogiikka.haeSiirtojarjestys().get(0) + " heitti " + noppa1 + " ja " + sovelluslogiikka.haeSiirtojarjestys().get(1) + " heitti " + noppa2 + ".";
        if (noppa1 > noppa2) {
            kerroAloitusarvonnanVoittaja(0);
        } else if (noppa1 < noppa2) {
            kerroAloitusarvonnanVoittaja(1);
        } else {
            vuoroteksti += " Heitetään noppaa uudestaan.";
            sovelluslogiikka.heitaNopat();
        }
    }

    private void kerroAloitusarvonnanVoittaja(int pelaaja) {
        sovelluslogiikka.pelaajaSiirtaaEnsin(true, pelaaja);
        vuoroteksti += " " + sovelluslogiikka.haeVuorossaOlevaPelaaja().toString() + " liikkuu ensin.";
        this.onkoPeliKaynnissa = true;
    }

    /**
     * Pelaa näytä tietokonepelaajan vuoro.
     */
    public void pelaaTietokone() {
        if (lopetetaan) {
            lopeta();
        }
        while (!sovelluslogiikka.eiVoiSiirtaa() && sovelluslogiikka.haeSiirrot().size() > 0) {
            sovelluslogiikka.pelaaTietokone();
            paivitaSiirrotJaPelaaja();
            kehys.revalidate();
            piirtoalusta.repaint();
        }
        if (sovelluslogiikka.onkoJokuVoittanut()) {
            voitto();
        }
        paivitaSiirrotJaPelaaja();
        vuoroikkuna.setVisible(true);
        kehys.revalidate();
    }

    /**
     * Onko peli käynnissä.
     *
     * @return true jos peli on käynnistetty aiemmin
     */
    public boolean onkoPeliKaynnissa() {
        return onkoPeliKaynnissa;
    }

    /**
     * Heitetään noppia.
     */
    public void heitaNoppaa() {
        sovelluslogiikka.heitaNopat();
        paivitaSiirrotJaPelaaja();
    }

    /**
     * Ilmoitetaan voitosta.
     */
    public void voitto() {
        vuoroteksti = sovelluslogiikka.kukaVoitti().toString() + " on voittanut!";
        this.vuoroikkuna.setVisible(true);
        onkoPeliKaynnissa = false;
        lopetetaan = true;
    }

    /**
     * Onko peli päättynyt.
     *
     * @return true jos peli on päättynyt
     */
    public boolean lopetetaanko() {
        return this.lopetetaan;
    }

    /**
     * Lopetetaan ohjelman suoritus.
     */
    public void lopeta() {
        System.exit(0);
    }
}
