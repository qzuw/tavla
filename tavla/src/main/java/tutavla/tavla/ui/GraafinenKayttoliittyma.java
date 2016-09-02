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
    private JFrame vuoroikkuna;
    private Sovelluslogiikka sovelluslogiikka;
    private GUILogiikka guilogiikka;
    private JLabel siirrot;
    private JLabel pelaaja;
    private JLabel terveiset;

    public GraafinenKayttoliittyma() {
        sovelluslogiikka = new Sovelluslogiikka();
        guilogiikka = new GUILogiikka(sovelluslogiikka);
    }

    @Override
    public void kaynnista() {
        run();
    }

    @Override
    public void run() {
        kehys = new JFrame("Tavla");
        kehys.setPreferredSize(new Dimension(470, 425));

        kehys.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        sovelluslogiikka.heitaNopat();

        luoKomponentit(kehys.getContentPane());

        kehys.pack();
        guilogiikka.lisaaKehys(kehys);

        kyselyikkuna = new JFrame("Tavla");
        kyselyikkuna.setPreferredSize(new Dimension(250, 150));

        kyselyikkuna.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKyselyikkuna(kyselyikkuna.getContentPane());

        kyselyikkuna.pack();
        guilogiikka.lisaaKyselyikkuna(kyselyikkuna);

        vuoroikkuna = new JFrame("Tavla");
        vuoroikkuna.setPreferredSize(new Dimension(500, 150));

        vuoroikkuna.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoVuoroikkuna(vuoroikkuna.getContentPane());

        vuoroikkuna.pack();
        guilogiikka.lisaaVuoroikkuna(vuoroikkuna);
    }

    private void luoKomponentit(Container container) {
        BorderLayout layout = new BorderLayout(2, 1);
        container.setLayout(layout);

        Piirtoalusta piirtoalusta = new Piirtoalusta(sovelluslogiikka);
        container.add(luoInfoikkuna(), BorderLayout.NORTH);
        container.add(luoPalauteikkuna(), BorderLayout.SOUTH);
        piirtoalusta.addMouseListener(new HiirenKuuntelija(guilogiikka, piirtoalusta, siirrot, pelaaja, terveiset));
        container.add(piirtoalusta);
        guilogiikka.lisaaPiirtoalusta(piirtoalusta);

    }

    private JPanel luoInfoikkuna() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        pelaaja = new JLabel(sovelluslogiikka.haeVuorossaOlevaPelaaja().toString());
        siirrot = new JLabel("Siirrot: ");
        panel.add(pelaaja);
        panel.add(siirrot);
        guilogiikka.lisaaSiirrotJaPelaaja(siirrot, pelaaja);

        return panel;
    }

    private JPanel luoPalauteikkuna() {
        JPanel panel = new JPanel(new GridLayout(1, 1));
        terveiset = new JLabel("Palautetta toiminnasta tänne.");
        panel.add(terveiset);

        return panel;
    }

    private void luoKyselyikkuna(Container container) {
        GridLayout layout = new GridLayout(3, 1);
        container.setLayout(layout);

        JLabel kysymysTeksti = new JLabel("Montako pelaajaa? (0-2)");
        JTextField vastausKentta = new JTextField();
        JButton vastaaNappi = new JButton("OK");
        container.add(kysymysTeksti);
        container.add(vastausKentta);
        container.add(vastaaNappi);

        KyselyKuuntelija kyselykuuntelija = new KyselyKuuntelija(kyselyikkuna, guilogiikka, kysymysTeksti, vastausKentta);
        vastaaNappi.addActionListener(kyselykuuntelija);
    }

    private void luoVuoroikkuna(Container container) {
        GridLayout layout = new GridLayout(2, 1);
        container.setLayout(layout);

        JLabel vuoroTeksti = new JLabel("Peli alkaa.");
        JButton okNappi = new JButton("OK");
        container.add(vuoroTeksti);
        container.add(okNappi);

        VuoroKuuntelija vuorokuuntelija = new VuoroKuuntelija(vuoroikkuna, guilogiikka, vuoroTeksti);
        okNappi.addActionListener(vuorokuuntelija);
    }

    private void luoPelilaudanKomponentit(Container container) {
        container.add(new Piirtoalusta(sovelluslogiikka));
    }

    public JFrame getFrame() {
        return kehys;
    }

}
