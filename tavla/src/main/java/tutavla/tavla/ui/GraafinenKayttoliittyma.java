/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
        kehys.setPreferredSize(new Dimension(470, 425));
        // kehys.setPreferredSize(new Dimension(450, 360));

        kehys.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        svl.heitaNopat();

        luoKomponentit(kehys.getContentPane());

        kehys.pack();
        kehys.setVisible(true);

//        kehys.update(kehys.getComponent(0).getGraphics());
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

    private void luoKomponentit(Container container) {
        BorderLayout layout = new BorderLayout(2, 1);
        container.setLayout(layout);

        container.add(new Piirtoalusta(svl));
        container.add(luoInfoikkuna(), BorderLayout.NORTH);
        container.add(luoPalauteikkuna(), BorderLayout.SOUTH);

        // tapahtumankuuntelija
//        luoPelilaudanKomponentit(kehys.getContentPane());
    }

    private JPanel luoInfoikkuna() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        JLabel pelaaja = new JLabel(svl.getVuorossaOlevaPelaaja().toString());
        JLabel siirrot = new JLabel("Siirrot: " + svl.haeSiirrot().toString());
        panel.add(pelaaja);
        panel.add(siirrot);

        return panel;
    }

    private JPanel luoPalauteikkuna() {
        JPanel panel = new JPanel(new GridLayout(1, 1));
        JLabel terveiset = new JLabel("Palautetta toiminnasta tänne.");
        panel.add(terveiset);

        return panel;
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
//        container.add(kehys)
        container.add(new Piirtoalusta(svl));
    }

    public JFrame getFrame() {
        return kehys;
    }

}
