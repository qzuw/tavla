/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.ui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tutavla.tavla.domain.Lauta;

/**
 * Piirtoalusta joka luo pelilaudan ja piirtää pelitilanteen mukaisesti nappulat
 * sille.
 *
 * @author ttuotila
 */
public class Piirtoalusta extends JPanel {

    Lauta lauta;

    /**
     * Luodaan piirtoalusta.
     *
     * @param pelitilanne Lauta
     */
    public Piirtoalusta(Lauta pelitilanne) {
        super.setBackground(Color.WHITE);
        this.lauta = pelitilanne;
    }

    @Override
    protected void paintComponent(Graphics grafiikka) {
        super.paintComponent(grafiikka);

        grafiikka.drawRect(0, 0, 420, 360);
        grafiikka.drawRect(15, 15, 180, 330);
        grafiikka.drawRect(225, 15, 180, 330);

        // testaukseen
//        lauta.siirraNappula(6, 0);
//        lauta.siirraNappula(6, 25);
//        lauta.siirraNappula(19, 0);
//        lauta.siirraNappula(19, 25);

        // pelilaudan kuviointi
        for (int i = 0; i < 13; i++) {
            if (i != 6) {
                int[] taulukkoX = {(15 + 30 * i), (30 + 30 * i), (45 + 30 * i)};
                int[] taulukkoY = {15, 165, 15};
                int[] taulukkoX2 = {(15 + 30 * i), (30 + 30 * i), (45 + 30 * i)};
                int[] taulukkoY2 = {345, 195, 345};

                if ((i % 2 == 0 && i < 6) || (i % 2 == 1 && i > 6)) {
                    grafiikka.fillPolygon(taulukkoX, taulukkoY, 3);
                    grafiikka.drawPolygon(taulukkoX2, taulukkoY2, 3);
                } else {
                    grafiikka.drawPolygon(taulukkoX, taulukkoY, 3);
                    grafiikka.fillPolygon(taulukkoX2, taulukkoY2, 3);
                }

            }
        }

        //alarivin nappulat
        for (int i = 1; i < 13; i++) {
            if (lauta.nappuloitaRuudussa(i) > 0) {
                for (int j = 0; j < lauta.nappuloitaRuudussa(i); j++) {
                    if (i > 6) {
                        if (lauta.ruudunVariMusta(i)) {
                            grafiikka.fillOval((375 - i * 30), (315 - j * 30), 30, 30);
                        } else {
                            grafiikka.drawOval((375 - i * 30), (315 - j * 30), 30, 30);
                        }
                    } else if (lauta.ruudunVariMusta(i)) {
                        grafiikka.fillOval((405 - i * 30), (315 - j * 30), 30, 30);
                    } else {
                        grafiikka.drawOval((405 - i * 30), (315 - j * 30), 30, 30);
                    }

                }
            }

        }

        // ylärivin nappulat
        for (int i = 13; i < 25; i++) {
            if (lauta.nappuloitaRuudussa(i) > 0) {
                for (int j = 0; j < lauta.nappuloitaRuudussa(i); j++) {
                    if (i > 18) {
                        if (lauta.ruudunVariMusta(i)) {
                            grafiikka.fillOval((15 + (i - 12) * 30), (15 + j * 30), 30, 30);
                        } else {
                            grafiikka.drawOval((15 + (i - 12) * 30), (15 + j * 30), 30, 30);
                        }
                    } else if (lauta.ruudunVariMusta(i)) {
                        grafiikka.fillOval((15 + (i - 13) * 30), (15 + j * 30), 30, 30);
                    } else {
                        grafiikka.drawOval((15 + (i - 13) * 30), (15 + j * 30), 30, 30);
                    }

                }
            }

        }

        // Syödyt nappulat
        if (lauta.syotyjaNappuloitaRuudussa(0) > 0) {
            for (int i = 0; i < lauta.syotyjaNappuloitaRuudussa(0); i++) {
                if (lauta.syodytNappulatMustia(0)) {
                    grafiikka.fillOval(195, (15 + i * 30), 30, 30);
                } else {
                    grafiikka.drawOval(195, (15 + i * 30), 30, 30);
                }
            }
        }
        if (lauta.syotyjaNappuloitaRuudussa(25) > 0) {
            for (int i = 0; i < lauta.syotyjaNappuloitaRuudussa(25); i++) {
                if (lauta.syodytNappulatMustia(25)) {
                    grafiikka.fillOval(195, (315 - i * 30), 30, 30);
                } else {
                    grafiikka.drawOval(195, (315 - i * 30), 30, 30);
                }
            }
        }

        //Ulos pelatut nappulat
        if (lauta.ulosPelattujaNappuloitaRuudussa(0) > 0) {
            for (int i = 0; i < lauta.ulosPelattujaNappuloitaRuudussa(0); i++) {
                if (lauta.ulosPelatutNappulatMustia(0)) {
                    grafiikka.fillOval(421, (330 - i * 30), 30, 30);
                } else {
                    grafiikka.drawOval(421, (330 - i * 30), 30, 30);
                }
            }
        }
        if (lauta.ulosPelattujaNappuloitaRuudussa(25) > 0) {
            for (int i = 0; i < lauta.ulosPelattujaNappuloitaRuudussa(25); i++) {
                if (lauta.ulosPelatutNappulatMustia(25)) {
                    grafiikka.fillOval(421, (0 + i * 30), 30, 30);
                } else {
                    grafiikka.drawOval(421, (0 + i * 30), 30, 30);
                }
            }
        }
    }
}
