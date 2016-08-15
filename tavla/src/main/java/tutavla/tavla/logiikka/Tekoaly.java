/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.logiikka;

import java.util.*;
import java.util.Random;
import tutavla.tavla.domain.Lauta;
import tutavla.tavla.domain.Pelaaja;

/**
 *
 * @author ttuotila
 */
public class Tekoaly {
    private Random random;

    public Tekoaly(Random random) {
        this.random = random;
    }
    
    public Pelaaja valitseVari(ArrayList<Pelaaja> siirtojarjestys){
        return siirtojarjestys.get(random.nextInt(1));
    }
    
    public void pelaa(Pelaaja tietokone, Pelilogiikka plk, ArrayList<Integer> siirrot){
        
    }
}
