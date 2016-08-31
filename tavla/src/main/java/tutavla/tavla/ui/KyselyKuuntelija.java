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
    private GUILogiikka guilg;
    private JFrame kyselyikkuna;

    /**
     * Konstruktori.
     *
     * @param kyselyikkuna tarkkailtava ikkuna
     * @param guilg GUILogiikka
     * @param kysymysTeksti ikkunan teksti
     * @param vastausKentta käyttäjän vastaus ikkunassa
     */
    public KyselyKuuntelija(JFrame kyselyikkuna, GUILogiikka guilg, JLabel kysymysTeksti, JTextField vastausKentta) {
        this.kysymysTeksti = kysymysTeksti;
        this.vastausKentta = vastausKentta;
        this.guilg = guilg;
        this.kyselyikkuna = kyselyikkuna;
        kyselyikkuna.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (guilg.getPelaajamaara() < 0) {
            try {
                int maara = Integer.parseInt(vastausKentta.getText());
                if (maara >= 0 && maara <= 2) {
                    guilg.setPelaajamaara(maara);
                }
            } catch (Exception ex) {
            }
        } else if (guilg.kysyttavanPelaajanNro() > 0) {
            String nimi = vastausKentta.getText();
            guilg.asetaPelaajanNimi(nimi);
            System.out.println(guilg.getPelaajamaara() + " " + guilg.kysyttavanPelaajanNro());
            if (guilg.getPelaajamaara() <= guilg.kysyttavanPelaajanNro() - 1) {
                kyselyikkuna.setVisible(false);
                guilg.piirraPiirtoalusta();
            }
        }

        if (guilg.isTyhjennaVastaus()) {
            guilg.setTyhjennaVastaus(false);
            vastausKentta.setText("");
        }

        if (guilg.getPelaajamaara() == 0) {
            kyselyikkuna.setVisible(false);
        }
        kysymysTeksti.setText(guilg.getKyselyteksti());
    }

}
