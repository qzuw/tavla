/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tutavla.tavla.logiikka.Sovelluslogiikka;

/**
 *
 * @author ttuotila
 */
public class PelaajaKuuntelija implements ActionListener {

    Sovelluslogiikka svl;

    public PelaajaKuuntelija(Sovelluslogiikka svl) {
        this.svl = svl;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }
}
