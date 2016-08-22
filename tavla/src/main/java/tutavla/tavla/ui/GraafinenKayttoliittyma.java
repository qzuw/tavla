/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.ui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author ttuotila
 */
public class GraafinenKayttoliittyma implements Runnable, Kayttoliittyma {

    private JFrame frame;

    public GraafinenKayttoliittyma() {
    }

    @Override
    public void kaynnista() {
        run();
    }

    @Override
    public void run() {
        frame = new JFrame("Tavla");
        frame.setPreferredSize(new Dimension(350, 320));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container contentPane) {

    }

    public JFrame getFrame() {
        return frame;
    }

}
