/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import tutavla.tavla.logiikka.Sovelluslogiikka;

/**
 * Kuunnellaan hiirtä.
 *
 * @author ttuotila
 */
public class HiirenKuuntelija implements MouseListener {

    Sovelluslogiikka sovelluslogiikka;
    GUILogiikka guilogiikka;
    Piirtoalusta piirtoalusta;
    JLabel siirrot;
    JLabel pelaaja;
    JLabel terveiset;

    /**
     * Luodaan Hiirenkuuntelija.
     *
     * @param guilogiikka GUILogiikka
     * @param piirtoalusta Piirtoalusta
     * @param siirrot JLabel jossa kerrotaan siirrot
     * @param pelaaja JLabel jossa näytetään pelaajan nimi
     * @param terveiset JLabel palautteen antamiseen pelaajalla
     */
    public HiirenKuuntelija(GUILogiikka guilogiikka, Piirtoalusta piirtoalusta, JLabel siirrot, JLabel pelaaja, JLabel terveiset) {
        this.piirtoalusta = piirtoalusta;
        this.sovelluslogiikka = guilogiikka.haeSovelluslogiikka();
        this.guilogiikka = guilogiikka;
        this.siirrot = siirrot;
        this.pelaaja = pelaaja;
        this.terveiset = terveiset;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (guilogiikka.pelaajaOnIhminen()) {
            int ruutu = selvitaRuutu(e.getX(), e.getY());

            toimintaKunRuutuaOnKlikattu(ruutu);
        }
    }

    private void toimintaKunRuutuaOnKlikattu(int ruutu) {
        if (ruutu > -1) {
            nappulanSiirtaminen(ruutu);
            if (sovelluslogiikka.onkoJokuVoittanut()) {
                terveiset.setText("Pelaaja " + sovelluslogiikka.kukaVoitti() + " on voittanut!");
                guilogiikka.voitto();
            } else if (sovelluslogiikka.eiVoiSiirtaa()) {
                terveiset.setText("Pelaaja " + sovelluslogiikka.haeVuorossaOlevaPelaaja() + " ei voi siirtää, vuoro vaihtuu!");
//                sovelluslogiikka.nollaaLahtoruutu();
//                sovelluslogiikka.vaihdaVuoroa();
                guilogiikka.vuoroVaihtuu();
                guilogiikka.vuoroNakyvyys(true);
                pelaaja.setText(sovelluslogiikka.haeVuorossaOlevaPelaaja().toString());
                siirrot.setText(sovelluslogiikka.haeSiirrot().toString());
            }
            piirtoalusta.repaint();
        }
    }

    private void nappulanSiirtaminen(int ruutu) {
        if (sovelluslogiikka.haeLahtoruutu() == -1) {
            sovelluslogiikka.asetaLahtoruutu(ruutu);
        } else if (!sovelluslogiikka.eiVoiSiirtaa() && sovelluslogiikka.pelaajaVoiSiirtaaRuutuihin().contains(ruutu)) {
            sovelluslogiikka.siirraNappulaa(ruutu);
            terveiset.setText("Siirrettiin nappulaa.");
            siirrot.setText(sovelluslogiikka.haeSiirrot().toString());
            vuoronVaihtuminen();
        } else {
            sovelluslogiikka.nollaaLahtoruutu();
        }
    }

    private void vuoronVaihtuminen() {
        if (sovelluslogiikka.haeSiirrot().isEmpty()) {
            terveiset.setText("Kaikki siirrot on käytetty, vuoro vaihtuu!");
            guilogiikka.vuoroVaihtuu();
            guilogiikka.vuoroNakyvyys(true);
            pelaaja.setText(sovelluslogiikka.haeVuorossaOlevaPelaaja().toString());
            siirrot.setText(sovelluslogiikka.haeSiirrot().toString());
        } else if (sovelluslogiikka.eiVoiSiirtaa()) {
            terveiset.setText(sovelluslogiikka.haeVuorossaOlevaPelaaja() + " ei voi siirtää, vuoro vaihtuu!");
            guilogiikka.vuoroVaihtuu();
            guilogiikka.vuoroNakyvyys(true);
            pelaaja.setText(sovelluslogiikka.haeVuorossaOlevaPelaaja().toString());
            siirrot.setText(sovelluslogiikka.haeSiirrot().toString());
        }
    }

    private int selvitaRuutu(int x, int y) {
        // vasen yläkulma 15, 15 -- y:15-165 -- 6* -> 13-18
        // vasen yläkulma 15, 15 -- y:15-165 -- +7* -- 6* -> 19-24
        // alarivi: y: 195-345
        int ruutu = -1;

        if (y >= 0 && y <= 165 && x >= 420 && sovelluslogiikka.haeVuorossaOlevaPelaaja().haeMaali() == 25) {
            ruutu = 25;
        } else if (y >= 196 && y <= 360 && x >= 420 && sovelluslogiikka.haeVuorossaOlevaPelaaja().haeMaali() == 0) {
            ruutu = 0;
        } else if (y >= 15 && y <= 165) {
            if (x >= 15 && x <= 195) {
                // 13-18
                ruutu = (x - 15) / 30 + 13;
            } else if (x >= 225 && x <= 405) {
                // 19-24
                ruutu = (x - 225) / 30 + 19;
            } else if (x >= 195 && x <= 225 && sovelluslogiikka.haeVuorossaOlevaPelaaja().haeMaali() == 25) {
                ruutu = 0;
            }
        } else if (y >= 195 && y <= 345) {
            if (x >= 15 && x <= 195) {
                // 7-12
                ruutu = 12 - (x - 15) / 30;

            } else if (x >= 225 && x <= 405) {
                // 1-6
                ruutu = 6 - (x - 225) / 30;
            } else if (x >= 195 && x <= 225 && sovelluslogiikka.haeVuorossaOlevaPelaaja().haeMaali() == 0) {
                ruutu = 25;
            }
        }

        return ruutu;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
