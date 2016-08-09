/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.domain;

import tutavla.tavla.domain.Nappula;
import tutavla.tavla.domain.Lauta;
import tutavla.tavla.domain.Pelaaja;
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

        assertEquals(lauta.nappuloitaRuudussa(0), 0);
        lauta.asetaNappula(nappula, 0);
        assertEquals(lauta.nappuloitaRuudussa(0), 1);

        assertEquals(lauta.nappuloitaRuudussa(25), 0);
        lauta.asetaNappula(nappula, 25);
        assertEquals(lauta.nappuloitaRuudussa(25), 1);
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

    @Test
    public void siirraNappulaa() {
        Lauta lauta = new Lauta();
        Pelaaja pelaaja = new Pelaaja();
        Pelaaja toinen = new Pelaaja();
        Nappula nappula1 = new Nappula(pelaaja);
        Nappula nappula2 = new Nappula(pelaaja);
        Nappula nappula3 = new Nappula(pelaaja);

        lauta.asetaNappula(nappula1, 5);
        lauta.asetaNappula(nappula2, 5);
        lauta.asetaNappula(nappula3, 5);

        assertEquals(lauta.nappuloitaRuudussa(5), 3);
        lauta.siirraNappula(5, 7);
        assertEquals(lauta.nappuloitaRuudussa(5), 2);
        assertEquals(lauta.nappuloitaRuudussa(7), 1);
        lauta.siirraNappula(5, 8);
        assertEquals(lauta.nappuloitaRuudussa(5), 1);
        assertEquals(lauta.nappuloitaRuudussa(7), 1);
        assertEquals(lauta.nappuloitaRuudussa(8), 1);
        lauta.siirraNappula(7, 8);
        assertEquals(lauta.nappuloitaRuudussa(5), 1);
        assertEquals(lauta.nappuloitaRuudussa(7), 0);
        assertEquals(lauta.nappuloitaRuudussa(8), 2);
    }

    @Test
    public void pelaajanNappuloitaRuudussa() {
        Lauta lauta = new Lauta();
        Pelaaja pelaaja = new Pelaaja();
        Pelaaja toinen = new Pelaaja();

        assertEquals(lauta.pelaajanNappuloitaRuudussa(6, pelaaja), 0);
        lauta.asetaNappula(new Nappula(pelaaja), 6);
        lauta.asetaNappula(new Nappula(pelaaja), 6);
        assertEquals(lauta.pelaajanNappuloitaRuudussa(6, pelaaja), 2);
        lauta.asetaNappula(new Nappula(toinen), 6);
        assertEquals(lauta.pelaajanNappuloitaRuudussa(6, pelaaja), 2);
        lauta.asetaNappula(new Nappula(pelaaja), 6);
        assertEquals(lauta.pelaajanNappuloitaRuudussa(6, pelaaja), 3);

    }
}
