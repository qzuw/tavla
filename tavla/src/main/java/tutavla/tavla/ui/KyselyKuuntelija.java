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
    private Sovelluslogiikka svl;
    private JFrame kyselyikkuna;

    /**
     * Konstruktori.
     * 
     * @param kyselyikkuna tarkkailtava ikkuna
     * @param svl sovelluslogiikka
     * @param kysymysTeksti ikkunan teksti
     * @param vastausKentta käyttäjän vastaus ikkunassa
     */
    public KyselyKuuntelija(JFrame kyselyikkuna, Sovelluslogiikka svl, JLabel kysymysTeksti, JTextField vastausKentta) {
        this.kysymysTeksti = kysymysTeksti;
        this.vastausKentta = vastausKentta;
        this.svl = svl;
        this.kyselyikkuna = kyselyikkuna;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (kysymysTeksti.getText().equals("Montako pelaajaa? (0-2)")) {
            try {
                int maara = Integer.parseInt(vastausKentta.getText());
                if (maara == 0) {
                    kyselyikkuna.setVisible(false);
                } else if (maara == 1) {
                    Pelaaja p = svl.getVuorossaOlevaPelaaja();
                    p.setIhminen(true);
                    kysymysTeksti.setText("Pelaajan nimi?");
                    vastausKentta.setText("");
                } else if (maara == 2) {
                    kysymysTeksti.setText("Ensimmäisen pelaajan nimi?");
                    vastausKentta.setText("");
                }
            } catch (Exception ex) {
            }
        }
    }

}
