/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import tutavla.tavla.domain.Pelaaja;
import tutavla.tavla.logiikka.Sovelluslogiikka;

/**
 * Kyselykuuntelija kuuntelee kyselyikkunan syötettä.
 *
 * @author ttuotila
 */
public class VuoroKuuntelija implements ActionListener {

    private JLabel vuoroTeksti;
    private GUILogiikka guilogiikka;
    private JFrame vuoroikkuna;

    /**
     * Konstruktori.
     *
     * @param vuoroikkuna tarkkailtava ikkuna
     * @param guilogiikka GUILogiikka
     * @param vuoroTeksti ikkunan teksti
     */
    public VuoroKuuntelija(JFrame vuoroikkuna, GUILogiikka guilogiikka, JLabel vuoroTeksti) {
        this.vuoroTeksti = vuoroTeksti;
        this.guilogiikka = guilogiikka;
        this.vuoroikkuna = vuoroikkuna;
        vuoroikkuna.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (guilogiikka.lopetetaanko()) {
            guilogiikka.lopeta();
        }
        if (guilogiikka.onkoPeliKaynnissa()) {
            guilogiikka.vuoroNakyvyys(false);
        }
        guilogiikka.heitaNoppaa();
        guilogiikka.vuoroVaihtuu();
        if (!guilogiikka.pelaajaOnIhminen()) {
            guilogiikka.pelaaTietokone();
        }
        vuoroTeksti.setText(guilogiikka.haeVuoroteksti());

    }

}
