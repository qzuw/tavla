/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ttuotila
 */
public class LautaTest {

    public LautaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void asetaNappula() {
        Lauta lauta = new Lauta();
        Pelaaja pelaaja = new Pelaaja();
        Nappula nappula = new Nappula(pelaaja);

        assertEquals(lauta.nappuloitaRuudussa(12), 0);
        lauta.asetaNappula(nappula, 12);
        assertEquals(lauta.nappuloitaRuudussa(12), 1);
    }

    @Test
    public void ruutuPelaajan() {
        Lauta lauta = new Lauta();
        Pelaaja pelaaja = new Pelaaja();
        Pelaaja toinen = new Pelaaja();
        Nappula nappula = new Nappula(pelaaja);

        assertFalse(lauta.ruutuPelaajalla(12, pelaaja));
        lauta.asetaNappula(nappula, 12);
        assertTrue(lauta.ruutuPelaajalla(12, pelaaja));
        assertFalse(lauta.ruutuPelaajalla(12, toinen));
        lauta.siirraNappula(12, 0);
        assertFalse(lauta.ruutuPelaajalla(12, pelaaja));
        assertFalse(lauta.ruutuPelaajalla(0, pelaaja));
        lauta.siirraNappula(0, 1);
        assertTrue(lauta.ruutuPelaajalla(1, pelaaja));
        assertFalse(lauta.ruutuPelaajalla(1, toinen));
        lauta.siirraNappula(1, 24);
        assertFalse(lauta.ruutuPelaajalla(1, pelaaja));
        assertTrue(lauta.ruutuPelaajalla(24, pelaaja));
        assertFalse(lauta.ruutuPelaajalla(24, toinen));
        lauta.siirraNappula(24, 25);
        assertFalse(lauta.ruutuPelaajalla(24, pelaaja));
        assertFalse(lauta.ruutuPelaajalla(25, pelaaja));
        assertFalse(lauta.ruutuPelaajalla(25, toinen));
        
    }
}
