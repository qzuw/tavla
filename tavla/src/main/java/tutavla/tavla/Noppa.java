/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla;

import java.util.Random;

/**
 *
 * @author ttuotila
 */
public class Noppa {
    private Random random;
    private int arvo;

    public Noppa() {
        random = new Random();
        this.arvo();
    }
    
    public void arvo() {
        arvo = random.nextInt(6) + 1;
    }

    public int getArvo() {
        return arvo;
    }
    
}
