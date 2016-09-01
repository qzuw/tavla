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
    Sovelluslogiikka sovelluslogiikka;

    /**
     * Luodaan piirtoalusta.
     *
     * @param sovelluslogiikka pelin Sovelluslogiikka
     */
    public Piirtoalusta(Sovelluslogiikka sovelluslogiikka) {
        super.setBackground(Color.WHITE);
        this.lauta = sovelluslogiikka.pelitilanne();
        this.sovelluslogiikka = sovelluslogiikka;
    }

    @Override
    protected void paintComponent(Graphics grafiikka) {
        super.paintComponent(grafiikka);

        piirraLauta(grafiikka);
        asetaNappulatRuutuihin(grafiikka);
        asetaSyodytNappulat(grafiikka, 0, -1);
        asetaSyodytNappulat(grafiikka, 25, 1);
        asetaUlosPelatutNappulat(grafiikka, 0, 1);
        asetaUlosPelatutNappulat(grafiikka, 25, -1);
        grafiikka.setColor(Color.BLACK);
    }

    private void asetaUlosPelatutNappulat(Graphics grafiikka, int ruutu, int suunta) {
        //Ulos pelatut nappulat 0, 1 -- 25, -1
        if (lauta.ulosPelattujaNappuloitaRuudussa(ruutu) > 0) {
            int y = 165 + suunta * 160; // 325;
            for (int i = 0; i < lauta.ulosPelattujaNappuloitaRuudussa(ruutu); i++) {
                if (i == 10) {
                    y += suunta * 155;
                } else if (i == 5) {
                    y += suunta * 155;
                }
                if (lauta.ulosPelatutNappulatMustia(ruutu)) {
                    piirraMustaNappula(grafiikka, 421, (y - suunta * i * 30));
                } else {
                    piirraValkoinenNappula(grafiikka, 421, (y - suunta * i * 30));
                }
            }
        }
    }

    private void asetaSyodytNappulat(Graphics grafiikka, int ruutu, int suunta) {
        // Syödyt nappulat 0, -1 -- 25 , 1
        if (lauta.syotyjaNappuloitaRuudussa(ruutu) > 0) {
            int y = 165 + suunta * 150;
            for (int i = 0; i < lauta.syotyjaNappuloitaRuudussa(ruutu); i++) {
                if (i == 5) {
                    y += suunta * 155;
                } else if (i == 10) {
                    y += suunta * 155;
                }
                if (lauta.syodytNappulatMustia(ruutu)) {
                    piirraMustaNappula(grafiikka, 195, (y - suunta * i * 30));
                } else {
                    piirraValkoinenNappula(grafiikka, 195, (y - suunta * i * 30));
                }
                if (sovelluslogiikka.haeLahtoruutu() == ruutu && i + 1 == lauta.syotyjaNappuloitaRuudussa(ruutu)) {
                    ympyroiNappula(grafiikka, 193, ((y + suunta * 2 - 2 - suunta * 2) - suunta * i * 30));
                }
            }
        }
    }

    private void asetaNappulatRuutuihin(Graphics grafiikka) {
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
                        if (sovelluslogiikka.haeLahtoruutu() == i && j + 1 == lauta.nappuloitaRuudussa(i)) {
                            ympyroiNappula(grafiikka, (373 - i * 30), ((y - 2) - j * 30));
                        }
                    } else {
                        if (lauta.ruudunVariMusta(i)) {
                            piirraMustaNappula(grafiikka, (405 - i * 30), (y - j * 30));
                        } else {
                            piirraValkoinenNappula(grafiikka, (405 - i * 30), (y - j * 30));
                        }
                        if (sovelluslogiikka.haeLahtoruutu() == i && j + 1 == lauta.nappuloitaRuudussa(i)) {
                            ympyroiNappula(grafiikka, (403 - i * 30), ((y - 2) - j * 30));
                        }
                    }
                }
            }
            int i2 = i + 12;
            if (lauta.nappuloitaRuudussa(i2) > 0) {
                for (int j = 0; j < lauta.nappuloitaRuudussa(i2); j++) {
                    int y = 15;
                    if (j > 4) {
                        y = -140;
                    } else if (j > 9) {
                        y = -295;
                    }
                    if (i2 > 18) {
                        if (lauta.ruudunVariMusta(i2)) {
                            piirraMustaNappula(grafiikka, (15 + (i2 - 12) * 30), (y + j * 30));
                        } else {
                            piirraValkoinenNappula(grafiikka, (15 + (i2 - 12) * 30), (y + j * 30));
                        }
                        if (sovelluslogiikka.haeLahtoruutu() == i2 && j + 1 == lauta.nappuloitaRuudussa(i2)) {
                            ympyroiNappula(grafiikka, (13 + (i2 - 12) * 30), ((y - 2) + j * 30));
                        }
                    } else {
                        if (lauta.ruudunVariMusta(i2)) {
                            piirraMustaNappula(grafiikka, (15 + (i2 - 13) * 30), (y + j * 30));
                        } else {
                            piirraValkoinenNappula(grafiikka, (15 + (i2 - 13) * 30), (y + j * 30));
                        }
                        if (sovelluslogiikka.haeLahtoruutu() == i2 && j + 1 == lauta.nappuloitaRuudussa(i2)) {
                            ympyroiNappula(grafiikka, (13 + (i2 - 13) * 30), ((y - 2) + j * 30));
                        }
                    }
                }
            }
        }
    }

    private void ympyroiNappula(Graphics grafiikka, int x, int y) {
        grafiikka.setColor(Color.RED);
        grafiikka.drawOval(x, y, 34, 34);
        grafiikka.setColor(Color.BLACK);
    }

    private void piirraLauta(Graphics grafiikka) {
        grafiikka.drawRect(0, 0, 420, 360);
        grafiikka.drawRect(15, 15, 180, 330);
        grafiikka.drawRect(225, 15, 180, 330);

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
