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
import tutavla.tavla.logiikka.Sovelluslogiikka;

/**
 * Piirtoalusta joka luo pelilaudan ja piirtää pelitilanteen mukaisesti nappulat
 * sille.
 *
 * @author ttuotila
 */
public class Piirtoalusta extends JPanel {

    Lauta lauta;
    Sovelluslogiikka svl;

    /**
     * Luodaan piirtoalusta.
     *
     * @param svl pelin Sovelluslogiikka
     */
    public Piirtoalusta(Sovelluslogiikka svl) {
        super.setBackground(Color.WHITE);
        this.lauta = svl.pelitilanne();
        this.svl = svl;
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
//        svl.vaihdaVuoroa();
//        svl.asetaLahtoruutu(0);
//        lauta.siirraNappula(8, 0);
//        lauta.siirraNappula(8, 0);
//        lauta.siirraNappula(8, 0);
//        lauta.siirraNappula(6, 0);
//        lauta.siirraNappula(6, 0);
//        lauta.siirraNappula(6, 0);
//        lauta.siirraNappula(6, 0);
//        lauta.siirraNappula(6, 0);
//        lauta.siirraNappula(17, 0);
//        lauta.siirraNappula(17, 0);
//        lauta.siirraNappula(17, 0);
//        lauta.siirraNappula(19, 0);
//        lauta.siirraNappula(19, 0);
//        lauta.siirraNappula(19, 0);
//        lauta.siirraNappula(19, 0);
//        lauta.siirraNappula(19, 0);
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
                    int y = 315;
                    if (j > 4) {
                        y = 470;
                    } else if (j > 9) {
                        y = 625;
                    }
                    if (i > 6) {
                        if (lauta.ruudunVariMusta(i)) {
                            piirraMustaNappula(grafiikka, (375 - i * 30), (y - j * 30));
                        } else {
                            piirraValkoinenNappula(grafiikka, (375 - i * 30), (y - j * 30));
                        }
                        if (svl.getLahtoruutu() == i && j + 1 == lauta.nappuloitaRuudussa(i)) {
                            grafiikka.setColor(Color.RED);
                            grafiikka.drawOval((373 - i * 30), ((y - 2) - j * 30), 34, 34);
                            grafiikka.setColor(Color.BLACK);
                        }
                    } else {
                        if (lauta.ruudunVariMusta(i)) {
                            piirraMustaNappula(grafiikka, (405 - i * 30), (y - j * 30));
                        } else {
                            piirraValkoinenNappula(grafiikka, (405 - i * 30), (y - j * 30));
                        }
                        if (svl.getLahtoruutu() == i && j + 1 == lauta.nappuloitaRuudussa(i)) {
                            grafiikka.setColor(Color.RED);
                            grafiikka.drawOval((403 - i * 30), ((y - 2) - j * 30), 34, 34);
                            grafiikka.setColor(Color.BLACK);
                        }
                    }

                }
            }

        }

        // ylärivin nappulat, silmukan voisi yhdistää ylläolevaan jos olisi tosi
        // fiksu eikä menisi sekaisin numeroista.
        for (int i = 13; i < 25; i++) {
            if (lauta.nappuloitaRuudussa(i) > 0) {
                for (int j = 0; j < lauta.nappuloitaRuudussa(i); j++) {
                    int y = 15;
                    if (j > 4) {
                        y = -140;
                    } else if (j > 9) {
                        y = -295;
                    }
                    if (i > 18) {
                        if (lauta.ruudunVariMusta(i)) {
                            piirraMustaNappula(grafiikka, (15 + (i - 12) * 30), (y + j * 30));
                        } else {
                            piirraValkoinenNappula(grafiikka, (15 + (i - 12) * 30), (y + j * 30));
                        }
                        if (svl.getLahtoruutu() == i && j + 1 == lauta.nappuloitaRuudussa(i)) {
                            grafiikka.setColor(Color.RED);
                            grafiikka.drawOval((13 + (i - 12) * 30), ((y - 2) + j * 30), 34, 34);
                            grafiikka.setColor(Color.BLACK);
                        }
                    } else {
                        if (lauta.ruudunVariMusta(i)) {
                            piirraMustaNappula(grafiikka, (15 + (i - 13) * 30), (y + j * 30));
                        } else {
                            piirraValkoinenNappula(grafiikka, (15 + (i - 13) * 30), (y + j * 30));
                        }
                        if (svl.getLahtoruutu() == i && j + 1 == lauta.nappuloitaRuudussa(i)) {
                            grafiikka.setColor(Color.RED);
                            grafiikka.drawOval((13 + (i - 13) * 30), ((y - 2) + j * 30), 34, 34);
                            grafiikka.setColor(Color.BLACK);
                        }
                    }

                }
            }

        }

        // Syödyt nappulat
        if (lauta.syotyjaNappuloitaRuudussa(0) > 0) {
            for (int i = 0; i < lauta.syotyjaNappuloitaRuudussa(0); i++) {
                int y = 15;
                if (i > 4) {
                    y = -140;
                } else if (i > 9) {
                    y = -295;
                }
                if (lauta.syodytNappulatMustia(0)) {
                    piirraMustaNappula(grafiikka, 195, (y + i * 30));
                } else {
                    piirraValkoinenNappula(grafiikka, 195, (y + i * 30));
                }
                if (svl.getLahtoruutu() == 0 && i + 1 == lauta.syotyjaNappuloitaRuudussa(0)) {
                    grafiikka.setColor(Color.RED);
                    grafiikka.drawOval(193, ((y - 2) + i * 30), 34, 34);
                    grafiikka.setColor(Color.BLACK);
                }
            }
        }
        if (lauta.syotyjaNappuloitaRuudussa(25) > 0) {
            for (int i = 0; i < lauta.syotyjaNappuloitaRuudussa(25); i++) {
                int y = 315;
                if (i > 4) {
                    y = 470;
                } else if (i > 9) {
                    y = 625;
                }
                if (lauta.syodytNappulatMustia(25)) {
                    piirraMustaNappula(grafiikka, 195, (y - i * 30));
                } else {
                    piirraValkoinenNappula(grafiikka, 195, (y - i * 30));
                }
                if (svl.getLahtoruutu() == 25 && i + 1 == lauta.syotyjaNappuloitaRuudussa(25)) {
                    grafiikka.setColor(Color.RED);
                    grafiikka.drawOval(193, ((y - 2) + i * 30), 34, 34);
                    grafiikka.setColor(Color.BLACK);
                }
            }
        }

        //Ulos pelatut nappulat
        if (lauta.ulosPelattujaNappuloitaRuudussa(0) > 0) {
            for (int i = 0; i < lauta.ulosPelattujaNappuloitaRuudussa(0); i++) {
                int y = 325;
                if (i > 4) {
                    y = 480;
                } else if (i > 9) {
                    y = 635;
                }
                if (lauta.ulosPelatutNappulatMustia(0)) {
                    piirraMustaNappula(grafiikka, 421, (y - i * 30));
                } else {
                    piirraValkoinenNappula(grafiikka, 421, (y - i * 30));
                }
            }
        }
        if (lauta.ulosPelattujaNappuloitaRuudussa(25) > 0) {
            for (int i = 0; i < lauta.ulosPelattujaNappuloitaRuudussa(25); i++) {
                int y = 5;
                if (i > 4) {
                    y = -150;
                } else if (i > 9) {
                    y = -305;
                }
                if (lauta.ulosPelatutNappulatMustia(25)) {
                    piirraMustaNappula(grafiikka, 421, (y + i * 30));
                } else {
                    piirraValkoinenNappula(grafiikka, 421, (y + i * 30));
                }
            }
        }
        grafiikka.setColor(Color.BLACK);
    }

    private void piirraMustaNappula(Graphics grafiikka, int x, int y) {
        grafiikka.setColor(Color.BLACK);
        grafiikka.fillOval(x, y, 30, 30);
        grafiikka.setColor(Color.WHITE);
        grafiikka.drawOval(x, y, 30, 30);
    }

    private void piirraValkoinenNappula(Graphics grafiikka, int x, int y) {
        grafiikka.setColor(Color.WHITE);
        grafiikka.fillOval(x, y, 30, 30);
        grafiikka.setColor(Color.BLACK);
        grafiikka.drawOval(x, y, 30, 30);
    }
}
