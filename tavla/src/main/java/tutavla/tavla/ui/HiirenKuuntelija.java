/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import tutavla.tavla.logiikka.Sovelluslogiikka;

/**
 *
 * @author ttuotila
 */
public class HiirenKuuntelija implements MouseListener {

    Sovelluslogiikka svl;
    Piirtoalusta pa;
    JLabel siirrot;
    JLabel pelaaja;
    JLabel terveiset;

    public HiirenKuuntelija(Sovelluslogiikka svl, Piirtoalusta pa, JLabel siirrot, JLabel pelaaja, JLabel terveiset) {
        this.pa = pa;
        this.svl = svl;
        this.siirrot = siirrot;
        this.pelaaja = pelaaja;
        this.terveiset = terveiset;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        System.out.println("huhuu!" + e.getPoint().toString());

        int ruutu = selvitaRuutu(e.getX(), e.getY());

        if (ruutu > -1) {
            if (svl.getLahtoruutu() == -1) {
                svl.asetaLahtoruutu(ruutu);
            } else if (!svl.eiVoiSiirtaa() && svl.pelaajaVoiSiirtaaRuutuihin().contains(ruutu)) {
                svl.siirraNappulaa(ruutu);
            } else {
                svl.nollaaLahtoruutu();
            }
            pa.repaint();
        }
    }

    private int selvitaRuutu(int x, int y) {
        // vasen yläkulma 15, 15 -- y:15-165 -- 6* -> 13-18
        // vasen yläkulma 15, 15 -- y:15-165 -- +7* -- 6* -> 19-24
        // alarivi: y: 195-345
        int ruutu = -1;

        if (y >= 15 && y <= 165) {
            if (x >= 15 && x <= 195) {
                // 13-18
                ruutu = (x - 15) / 30 + 13;
            } else if (x >= 225 && x <= 405) {
                // 19-24
                ruutu = (x - 225) / 30 + 19;
            }
        } else if (y >= 195 && y <= 345) {
            if (x >= 15 && x <= 195) {
                // 7-12
                ruutu = 12 - (x - 15) / 30;

            } else if (x >= 225 && x <= 405) {
                // 1-6
                ruutu = 6 - (x - 225) / 30;
            }
        }
        return ruutu;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
