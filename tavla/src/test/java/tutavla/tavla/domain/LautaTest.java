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

    @Test
    public void ruudunVari() {
        Lauta lauta = new Lauta();
        Pelaaja pelaaja = new Pelaaja();
        pelaaja.asetaMustaksi(true);

        lauta.asetaNappula(new Nappula(pelaaja), 1);
        assertTrue(lauta.ruudunVariMusta(1));
    }

    @Test
    public void ruudunVari2() {
        Lauta lauta = new Lauta();
        Pelaaja pelaaja = new Pelaaja();
        pelaaja.asetaMustaksi(false);

        lauta.asetaNappula(new Nappula(pelaaja), 1);
        assertFalse(lauta.ruudunVariMusta(1));
    }

    @Test
    public void ruudunVari3() {
        Lauta lauta = new Lauta();
        Pelaaja pelaaja = new Pelaaja();
        pelaaja.asetaMustaksi(true);

        assertFalse(lauta.ruudunVariMusta(1));
    }

    @Test
    public void haeRuutu() {
        Lauta lauta = new Lauta();

        Ruutu ruutu = lauta.haeRuutu(1);

        assertEquals(0, ruutu.nappuloidenMaara());
    }

    @Test
    public void syotyjaNappuloita() {
        Lauta lauta = new Lauta();

        assertEquals(0, lauta.syotyjaNappuloitaRuudussa(0));
    }

    @Test
    public void syotyjaNappuloita2() {
        Lauta lauta = new Lauta();

        assertEquals(0, lauta.syotyjaNappuloitaRuudussa(25));
    }

    @Test
    public void syotyjaNappuloita3() {
        Lauta lauta = new Lauta();
        Pelaaja p = new Pelaaja();
        Nappula n = new Nappula(p);
        lauta.asetaNappula(n, 0);
        p.asetaMaali(25);

        assertEquals(1, lauta.syotyjaNappuloitaRuudussa(0));
    }

    @Test
    public void syotyjaNappuloita4() {
        Lauta lauta = new Lauta();
        Pelaaja p = new Pelaaja();
        Nappula n = new Nappula(p);
        lauta.asetaNappula(n, 25);
        p.asetaMaali(0);

        assertEquals(1, lauta.syotyjaNappuloitaRuudussa(25));
    }

    @Test
    public void syotyjaNappuloita5() {
        Lauta lauta = new Lauta();
        Pelaaja p = new Pelaaja();
        Nappula n = new Nappula(p);
        Nappula n2 = new Nappula(p);
        lauta.asetaNappula(n, 25);
        lauta.asetaNappula(n2, 25);
        p.asetaMaali(0);

        assertEquals(2, lauta.syotyjaNappuloitaRuudussa(25));
    }

    @Test
    public void nappuloitaUlkona() {
        Lauta lauta = new Lauta();
        Pelaaja p = new Pelaaja();
        Nappula n = new Nappula(p);
        Nappula n2 = new Nappula(p);
        lauta.asetaNappula(n, 25);
        lauta.asetaNappula(n2, 25);
        p.asetaMaali(25);

        assertEquals(2, lauta.ulosPelattujaNappuloitaRuudussa(25));
    }

    @Test
    public void nappuloitaUlkona2() {
        Lauta lauta = new Lauta();
        Pelaaja p = new Pelaaja();
        Nappula n = new Nappula(p);
        lauta.asetaNappula(n, 0);
        p.asetaMaali(0);

        assertEquals(1, lauta.ulosPelattujaNappuloitaRuudussa(0));
    }

    @Test
    public void ulkonaMustiaNappuloita() {
        Lauta l = new Lauta();
        Pelaaja p = new Pelaaja();
        l.asetaNappula(new Nappula(p), 0);
        p.asetaMaali(0);
        p.asetaMustaksi(true);

        assertEquals(true, l.ulosPelatutNappulatMustia(0));
    }

    @Test
    public void ulkonaMustiaNappuloita2() {
        Lauta l = new Lauta();
        Pelaaja p = new Pelaaja();
        l.asetaNappula(new Nappula(p), 25);
        p.asetaMaali(25);
        p.asetaMustaksi(false);

        assertEquals(false, l.ulosPelatutNappulatMustia(25));
    }

    @Test
    public void syodytNappulatMustia() {
        Lauta l = new Lauta();
        Pelaaja p = new Pelaaja();
        l.asetaNappula(new Nappula(p), 25);
        p.asetaMaali(0);
        p.asetaMustaksi(false);

        assertFalse(l.syodytNappulatMustia(25));
    }

    @Test
    public void syodytNappulatMustia2() {
        Lauta l = new Lauta();
        Pelaaja p = new Pelaaja();
        l.asetaNappula(new Nappula(p), 25);
        p.asetaMaali(0);
        p.asetaMustaksi(true);

        assertTrue(l.syodytNappulatMustia(25));
    }

    @Test
    public void syodytNappulatMustia3() {
        Lauta l = new Lauta();
        Pelaaja p = new Pelaaja();
        l.asetaNappula(new Nappula(p), 0);
        p.asetaMaali(25);
        p.asetaMustaksi(true);

        assertTrue(l.syodytNappulatMustia(0));
    }

    @Test
    public void syodytNappulatMustia4() {
        Lauta l = new Lauta();

        assertFalse(l.syodytNappulatMustia(0));
    }
}
