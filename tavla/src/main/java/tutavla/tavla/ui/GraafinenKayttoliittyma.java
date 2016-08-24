/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import tutavla.tavla.logiikka.Sovelluslogiikka;

/**
 *
 * @author ttuotila
 */
public class GraafinenKayttoliittyma implements Runnable, Kayttoliittyma {

    private JFrame kehys;
    private JFrame kyselyikkuna;
    private Sovelluslogiikka svl;

    public GraafinenKayttoliittyma() {
        svl = new Sovelluslogiikka();
    }

    @Override
    public void kaynnista() {
        run();
    }

    @Override
    public void run() {
        kehys = new JFrame("Tavla");
        kehys.setPreferredSize(new Dimension(465, 390));
        // kehys.setPreferredSize(new Dimension(450, 360));

        kehys.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoPelilaudanKomponentit(kehys.getContentPane());

        kehys.pack();
        kehys.setVisible(true);

//        kyselyikkuna = new JFrame("Pelaajat");
//        kyselyikkuna.setPreferredSize(new Dimension(200, 100));
//
//        kyselyikkuna.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//        luoPelilaudanKomponentit(kyselyikkuna.getContentPane());
//
//        kyselyikkuna.pack();
//        kyselyikkuna.setVisible(true);
    }

    private void luoPelilaudanKomponentit(Container container) {
//        GridLayout layout = new GridLayout(3, 1);
//        container.setLayout(layout);
//
//        JLabel nimiTeksti = new JLabel("Pelaaja 1: ");
//        JTextField nimiKentta = new JTextField();
//        JLabel hetuTeksti = new JLabel("Hetu: ");
//        JTextField hetuKentta = new JTextField();
//
//        JButton lisaaNappi = new JButton("Lisää henkilö!");
        container.add(new Piirtoalusta(svl.pelitilanne()));
    }

    public JFrame getFrame() {
        return kehys;
    }

}
