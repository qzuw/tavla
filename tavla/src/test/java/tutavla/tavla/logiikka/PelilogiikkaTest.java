/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tutavla.tavla.domain.Pelaaja;

/**
 *
 * @author ttuotila@cs
 */
public class PelilogiikkaTest {

    public PelilogiikkaTest() {
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
    public void asetaNappulatLaudalle() {
        Pelaaja pelaaja1 = new Pelaaja();
        Pelaaja pelaaja2 = new Pelaaja();
        Pelilogiikka logiikka = new Pelilogiikka();

        pelaaja2.setMaali(0);
        assertEquals(pelaaja2.getMaali(), 0);

        logiikka.alustaPelitilanne(pelaaja1, pelaaja2);
        assertFalse(logiikka.ruutuOnTyhja(1));
        assertEquals(logiikka.ruudunNappulaMaara(1), 2);
        assertTrue(logiikka.ruutuOnTyhja(2));
        assertTrue(logiikka.ruutuOnTyhja(3));
        assertTrue(logiikka.ruutuOnTyhja(4));
        assertTrue(logiikka.ruutuOnTyhja(5));
        assertFalse(logiikka.ruutuOnTyhja(6));
        assertEquals(logiikka.ruudunNappulaMaara(6), 5);
        assertTrue(logiikka.ruutuOnTyhja(7));
        assertFalse(logiikka.ruutuOnTyhja(8));
        assertEquals(logiikka.ruudunNappulaMaara(8), 3);
        assertTrue(logiikka.ruutuOnTyhja(9));
        assertTrue(logiikka.ruutuOnTyhja(10));
        assertTrue(logiikka.ruutuOnTyhja(11));
        assertFalse(logiikka.ruutuOnTyhja(12));
        assertEquals(logiikka.ruudunNappulaMaara(12), 5);
        assertFalse(logiikka.ruutuOnTyhja(13));
        assertEquals(logiikka.ruudunNappulaMaara(13), 5);
        assertTrue(logiikka.ruutuOnTyhja(14));
        assertTrue(logiikka.ruutuOnTyhja(15));
        assertTrue(logiikka.ruutuOnTyhja(16));
        assertFalse(logiikka.ruutuOnTyhja(17));
        assertEquals(logiikka.ruudunNappulaMaara(17), 3);
        assertTrue(logiikka.ruutuOnTyhja(18));
        assertFalse(logiikka.ruutuOnTyhja(19));
        assertEquals(logiikka.ruudunNappulaMaara(19), 5);
        assertTrue(logiikka.ruutuOnTyhja(20));
        assertTrue(logiikka.ruutuOnTyhja(21));
        assertTrue(logiikka.ruutuOnTyhja(22));
        assertTrue(logiikka.ruutuOnTyhja(23));
        assertFalse(logiikka.ruutuOnTyhja(24));
        assertEquals(logiikka.ruudunNappulaMaara(24), 2);

        assertEquals(pelaaja1.getMaali(), 0);
        assertEquals(pelaaja2.getMaali(), 25);
    }

    @Test
    public void voikoSiirtaa() {
        Pelaaja pelaaja1 = new Pelaaja();
        Pelaaja pelaaja2 = new Pelaaja();
        Pelilogiikka l = new Pelilogiikka();

        l.alustaPelitilanne(pelaaja1, pelaaja2);

        for (int i = 0; i < 26; i++) {
            if (l.ruutuOnPelaajan(i, pelaaja2)) {
                assertTrue(l.ruutuunVoiSiirtya(i, pelaaja2));
                assertFalse(l.ruutuunVoiSiirtya(i, pelaaja1));
            }
            if (l.ruutuOnPelaajan(i, pelaaja1)) {
                assertFalse(l.ruutuunVoiSiirtya(i, pelaaja2));
                assertTrue(l.ruutuunVoiSiirtya(i, pelaaja1));
            }
            if (l.ruutuOnTyhja(i)) {
                assertTrue(l.ruutuunVoiSiirtya(i, pelaaja2));
                assertTrue(l.ruutuunVoiSiirtya(i, pelaaja1));
            }
        }

    }

    @Test
    public void voikoSiirtaaSiirronJalkeen() {
        Pelaaja pelaaja1 = new Pelaaja();
        Pelaaja pelaaja2 = new Pelaaja();
        Pelilogiikka l = new Pelilogiikka();

        l.alustaPelitilanne(pelaaja1, pelaaja2);

        l.siirraNappulaa(pelaaja2, 12, 14);
        assertTrue(l.ruutuunVoiSiirtya(12, pelaaja2));
        assertFalse(l.ruutuunVoiSiirtya(12, pelaaja1));
        assertEquals(l.ruudunNappulaMaara(12), 4);
        assertTrue(l.ruutuOnPelaajan(14, pelaaja2));
        assertTrue(l.ruutuunVoiSiirtya(14, pelaaja2));
        assertTrue(l.ruutuunVoiSiirtya(14, pelaaja1));
        assertEquals(l.ruudunNappulaMaara(14), 1);

    }

    @Test
    public void pelaajanNappuloitaRuudussa() {
        Pelaaja pelaaja = new Pelaaja();
        Pelaaja toinen = new Pelaaja();
        Pelilogiikka plk = new Pelilogiikka();

        plk.alustaPelitilanne(pelaaja, toinen);

        assertEquals(0, plk.pelaajanNappulaMaara(0, pelaaja));
        plk.siirraNappulaa(pelaaja, 24, 2);
        plk.siirraNappulaa(pelaaja, 24, 2);
        plk.siirraNappulaa(pelaaja, 13, 2);
        plk.siirraNappulaa(pelaaja, 13, 2);
        plk.siirraNappulaa(pelaaja, 8, 2);
        plk.siirraNappulaa(pelaaja, 8, 2);
        plk.siirraNappulaa(pelaaja, 8, 2);
        plk.siirraNappulaa(pelaaja, 13, 2);
        plk.siirraNappulaa(pelaaja, 2, 0);
        plk.siirraNappulaa(pelaaja, 13, 2);
        plk.siirraNappulaa(pelaaja, 2, 0);
        assertEquals(2, plk.pelaajanNappulaMaara(0, pelaaja));
        plk.siirraNappulaa(toinen, 12, 0);
        plk.siirraNappulaa(toinen, 12, 0);
        assertEquals(2, plk.pelaajanNappulaMaara(0, pelaaja));
        plk.siirraNappulaa(pelaaja, 13, 2);
        plk.siirraNappulaa(pelaaja, 2, 0);
        assertEquals(3, plk.pelaajanNappulaMaara(0, pelaaja));
    }

    @Test
    public void onkoVoittanut() {
        Pelaaja pelaaja = new Pelaaja();
        Pelaaja toinen = new Pelaaja();
        Pelilogiikka plk = new Pelilogiikka();
        plk.alustaPelitilanne(pelaaja, toinen);

        plk.siirraNappulaa(pelaaja, 24, 2);
        plk.siirraNappulaa(pelaaja, 24, 2);
        plk.siirraNappulaa(pelaaja, 13, 2);
        plk.siirraNappulaa(pelaaja, 13, 2);
        plk.siirraNappulaa(pelaaja, 13, 2);
        plk.siirraNappulaa(pelaaja, 13, 2);
        plk.siirraNappulaa(pelaaja, 13, 2);
        plk.siirraNappulaa(pelaaja, 8, 2);
        plk.siirraNappulaa(pelaaja, 8, 2);
        plk.siirraNappulaa(pelaaja, 8, 2);
        plk.siirraNappulaa(pelaaja, 6, 2);
        plk.siirraNappulaa(pelaaja, 6, 2);
        plk.siirraNappulaa(pelaaja, 6, 2);
        plk.siirraNappulaa(pelaaja, 6, 2);
        for (int i = 0; i < 14; i++) {
            plk.siirraNappulaa(pelaaja, 2, 0);
        }
        assertFalse(plk.onkoPelaajaVoittanut(pelaaja));
        plk.siirraNappulaa(pelaaja, 6, 0);
        assertFalse(plk.onkoPelaajaVoittanut(toinen));
        assertTrue(plk.onkoPelaajaVoittanut(pelaaja));
    }

    @Test
    public void nappulatKotialueella() {
        Pelaaja pelaaja = new Pelaaja();
        Pelaaja toinen = new Pelaaja();
        Pelilogiikka plk = new Pelilogiikka();
        plk.alustaPelitilanne(pelaaja, toinen);
        pelaaja.setMaali(0);

        plk.siirraNappulaa(toinen, 1, 11);
        plk.siirraNappulaa(toinen, 1, 11);
        plk.siirraNappulaa(pelaaja, 24, 1);
        plk.siirraNappulaa(pelaaja, 24, 1);
        plk.siirraNappulaa(pelaaja, 13, 2);
        plk.siirraNappulaa(pelaaja, 13, 2);
        plk.siirraNappulaa(pelaaja, 13, 3);
        plk.siirraNappulaa(pelaaja, 13, 4);
        plk.siirraNappulaa(pelaaja, 13, 5);
        plk.siirraNappulaa(pelaaja, 8, 6);
        plk.siirraNappulaa(pelaaja, 8, 6);
        assertFalse(plk.nappulatKotialueella(pelaaja));
        plk.siirraNappulaa(pelaaja, 8, 6);
        assertTrue(plk.nappulatKotialueella(pelaaja));
    }

    @Test
    public void nappulatKotialueella2() {
        Pelaaja pelaaja = new Pelaaja();
        Pelaaja toinen = new Pelaaja();
        Pelilogiikka plk = new Pelilogiikka();
        plk.alustaPelitilanne(pelaaja, toinen);
        pelaaja.setMaali(0);

        plk.siirraNappulaa(pelaaja, 24, 2);
        plk.siirraNappulaa(pelaaja, 24, 2);
        plk.siirraNappulaa(toinen, 1, 24);
        plk.siirraNappulaa(toinen, 1, 24);
        plk.siirraNappulaa(toinen, 17, 24);
        plk.siirraNappulaa(toinen, 17, 24);
        plk.siirraNappulaa(toinen, 17, 24);
        plk.siirraNappulaa(toinen, 12, 25);
        plk.siirraNappulaa(toinen, 12, 24);
        plk.siirraNappulaa(toinen, 12, 24);
        plk.siirraNappulaa(toinen, 12, 24);
        assertFalse(plk.nappulatKotialueella(toinen));
        plk.siirraNappulaa(toinen, 12, 22);
        assertTrue(plk.nappulatKotialueella(toinen));
    }

    @Test
    public void syominenLaudallePalatessa() {
        Pelaaja pelaaja = new Pelaaja();
        Pelaaja toinen = new Pelaaja();
        Pelilogiikka plk = new Pelilogiikka();
        plk.alustaPelitilanne(pelaaja, toinen);

        plk.siirraNappulaa(toinen, 1, 2);
        plk.siirraNappulaa(pelaaja, 24, 23);
        plk.siirraNappulaa(pelaaja, 6, 2);
        plk.siirraNappulaa(toinen, 0, 2);

        assertEquals(1, plk.pelaajanNappulaMaara(25, pelaaja));
    }

    @Test
    public void syominenLaudallePalatessa2() {
        Pelaaja pelaaja = new Pelaaja();
        Pelaaja toinen = new Pelaaja();
        Pelilogiikka plk = new Pelilogiikka();
        plk.alustaPelitilanne(pelaaja, toinen);

        plk.siirraNappulaa(toinen, 1, 2);
        plk.siirraNappulaa(toinen, 19, 20);
        plk.siirraNappulaa(toinen, 19, 21);
        plk.siirraNappulaa(toinen, 19, 22);
        plk.siirraNappulaa(pelaaja, 24, 23);
        plk.siirraNappulaa(pelaaja, 6, 2);
        plk.siirraNappulaa(toinen, 0, 2);
        plk.siirraNappulaa(pelaaja, 25, 22);

        assertEquals(1, plk.pelaajanNappulaMaara(0, toinen));
    }

}
