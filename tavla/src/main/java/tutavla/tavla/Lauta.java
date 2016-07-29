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
public class Lauta {
    private List<List<Nappula>> ruudut;

    public Lauta() {
        ruudut = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            ruudut.add(new ArrayList<Nappula>());
        }
    }
    
    public boolean ruutuPelaajalla(int ruutu, Pelaaja pelaaja){
        boolean pelaajalla = false;
        if (!ruudut.get(ruutu).isEmpty()){
            pelaajalla = (ruudut.get(ruutu).get(0).getPelaaja().equals(pelaaja));
        }
        return pelaajalla;
    }
    
    public int nappuloitaRuudussa(int ruutu){
        return ruudut.get(ruutu).size();
    }
    
    public void asetaNappula(Nappula nappula, int ruutu){
        ruudut.get(ruutu).add(nappula);
    }

    public void siirraNappula(int mista, int minne){
        ruudut.get(minne).add(ruudut.get(mista).get(0));
        ruudut.get(mista).remove(0);
    }
    
}
