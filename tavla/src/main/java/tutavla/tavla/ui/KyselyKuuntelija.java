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
public class KyselyKuuntelija implements ActionListener {

    private JLabel kysymysTeksti;
    private JTextField vastausKentta;
    private GUILogiikka guilogiikka;
    private JFrame kyselyikkuna;

    /**
     * Konstruktori.
     *
     * @param kyselyikkuna tarkkailtava ikkuna
     * @param guilogiikka GUILogiikka
     * @param kysymysTeksti ikkunan teksti
     * @param vastausKentta käyttäjän vastaus ikkunassa
     */
    public KyselyKuuntelija(JFrame kyselyikkuna, GUILogiikka guilogiikka, JLabel kysymysTeksti, JTextField vastausKentta) {
        this.kysymysTeksti = kysymysTeksti;
        this.vastausKentta = vastausKentta;
        this.guilogiikka = guilogiikka;
        this.kyselyikkuna = kyselyikkuna;
        kyselyikkuna.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (guilogiikka.haePelaajamaara() < 0) {
            try {
                int maara = Integer.parseInt(vastausKentta.getText());
                if (maara >= 0 && maara <= 2) {
                    guilogiikka.asetaPelaajamaara(maara);
                }
            } catch (Exception ex) {
            }
        } else if (guilogiikka.kysyttavanPelaajanNro() > 0) {
            String nimi = vastausKentta.getText();
            guilogiikka.asetaPelaajanNimi(nimi);
            if (guilogiikka.haePelaajamaara() <= guilogiikka.kysyttavanPelaajanNro() - 1) {
                kyselyikkuna.setVisible(false);
                guilogiikka.kyselyikkunanNakyvyys(false);
                guilogiikka.kehyksenNakyvyys(true);
                guilogiikka.vuoroNakyvyys(true);
            }
        }

        if (guilogiikka.tyhjennetaankoVastaus()) {
            guilogiikka.asetaTyhjennaVastaus(false);
            vastausKentta.setText("");
        }

        if (guilogiikka.haePelaajamaara() == 0) {
            guilogiikka.kyselyikkunanNakyvyys(false);
            guilogiikka.kehyksenNakyvyys(true);
            guilogiikka.vuoroNakyvyys(true);
        }
        kysymysTeksti.setText(guilogiikka.haeKyselyteksti());
    }

}
