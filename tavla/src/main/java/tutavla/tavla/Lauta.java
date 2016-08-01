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
    private List<Ruutu> ruudut;

    public Lauta() {
        ruudut = new ArrayList<>();
        // 0 ja 25 erikoistapauksia
        for (int i = 0; i < 26; i++) {
            ruudut.add(new Ruutu());
        }
    }
    
    public boolean ruutuPelaajalla(int ruutu, Pelaaja pelaaja){
        boolean pelaajalla = false;
        if (!ruudut.get(ruutu).isEmpty() && ruutu != 0 && ruutu != 25){
            pelaajalla = (ruudut.get(ruutu).getPelaaja().equals(pelaaja));
        }
        return pelaajalla;
    }
    
    public int nappuloitaRuudussa(int ruutu){
        return ruudut.get(ruutu).nappuloidenMaara();
    }
    
    public void asetaNappula(Nappula nappula, int ruutu){
        ruudut.get(ruutu).lisaaNappula(nappula);
    }

    public void siirraNappula(int mista, int minne){
        ruudut.get(minne).lisaaNappula(ruudut.get(mista).otaNappula());
    }
    
}
