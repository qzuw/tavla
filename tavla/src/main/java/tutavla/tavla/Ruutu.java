/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ttuotila
 */
public class Ruutu {

    List<Nappula> nappulat;
    
    public Ruutu() {
        nappulat = new ArrayList<>();
    }
    
    public void lisaaNappula(Nappula nappula){
        nappulat.add(nappula);
    }
    
    public Nappula otaNappula(){
        Nappula nappula = nappulat.get(0);
        nappulat.remove(0);
        return nappula;
    }
    
    public int nappuloidenMaara(){
        return nappulat.size();
    }
    
    public boolean isEmpty(){
        return nappulat.isEmpty();
    }
    
    public Pelaaja getPelaaja(){
        if (nappulat.isEmpty()){
            return null;
        }
        return nappulat.get(0).getPelaaja();
    }
}
