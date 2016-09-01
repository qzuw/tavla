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
    JLabel siirrot;
    JLabel pelaaja;

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

    public void lisaaSiirtotJaPelaaja(JLabel siirrot, JLabel pelaaja) {
        this.siirrot = siirrot;
        this.pelaaja = pelaaja;
    }

    private void paivitaSiirrotJaPelaaja() {
        pelaaja.setText(svl.getVuorossaOlevaPelaaja().toString());
        siirrot.setText("Siirrot: " + svl.haeSiirrot());
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
        pelaajaOnIhminen = svl.getVuorossaOlevaPelaaja().isIhminen();
        paivitaSiirrotJaPelaaja();
        kehys.revalidate();
    }

    /**
     * Pelivuoro vaihtuu.
     */
    public void vuoroVaihtuu() {
        if (svl.onkoJokuVoittanut()) {
            voitto();
        }

        if (!onkoPeliKaynnissa && !lopetetaan) {
            // System.out.println("sadf" + onkoPeliKaynnissa + " " + svl.haeSiirrot());
            int noppa1 = svl.haeSiirrot().get(0);
            int noppa2 = svl.haeSiirrot().get(1);
            vuoroteksti = svl.getSiirtojarjestys().get(0) + " heitti " + noppa1 + " ja " + svl.getSiirtojarjestys().get(1) + " heitti " + noppa2 + ".";
            if (noppa1 > noppa2) {
                svl.pelaajaSiirtaaEnsin(true, 0);
                vuoroteksti += " " + svl.getVuorossaOlevaPelaaja().toString() + " liikkuu ensin.";
                this.onkoPeliKaynnissa = true;
                if (svl.getVuorossaOlevaPelaaja().isIhminen()) {
                    this.pelaajaOnIhminen = true;
                } else {
                    this.pelaajaOnIhminen = false;
                }
            } else if (noppa1 < noppa2) {
                svl.pelaajaSiirtaaEnsin(true, 1);
                vuoroteksti += " " + svl.getVuorossaOlevaPelaaja().toString() + " liikkuu ensin.";
                this.onkoPeliKaynnissa = true;
                if (svl.getVuorossaOlevaPelaaja().isIhminen()) {
                    this.pelaajaOnIhminen = true;
                } else {
                    this.pelaajaOnIhminen = false;
                }
            } else {
                vuoroteksti += " Heitetään noppaa uudestaan.";
                svl.heitaNopat();
            }
        } else if (!lopetetaan) {
            vuoroteksti = "Vuoro vaihtuu.";
            svl.vaihdaVuoroa();
            svl.nollaaLahtoruutu();
//            this.vuoroikkuna.setVisible(true);
            if (svl.getVuorossaOlevaPelaaja().isIhminen()) {
                this.pelaajaOnIhminen = true;
            } else {
                this.pelaajaOnIhminen = false;
            }
            svl.heitaNopat();
        }
        if (svl.onkoJokuVoittanut()) {
            voitto();
        }

        paivitaSiirrotJaPelaaja();
        kehys.revalidate();

    }

    public void pelaaTietokone() {
        while (!svl.eiVoiSiirtaa() && svl.haeSiirrot().size() > 0) {
            svl.pelaaTietokone();
            paivitaSiirrotJaPelaaja();
            kehys.revalidate();
            pa.repaint();
            for (int i = 0; i < 10000; i++) {
                i++;
                i += 0;
            }
        }
        if (svl.onkoJokuVoittanut()) {
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
        svl.heitaNopat();
        paivitaSiirrotJaPelaaja();
    }

    /**
     * Ilmoitetaan voitosta.
     */
    public void voitto() {
        vuoroteksti = svl.kukaVoitti().toString() + " on voittanut!";
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
