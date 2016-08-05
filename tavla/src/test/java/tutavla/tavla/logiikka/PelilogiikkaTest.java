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

        logiikka.asetaNappulat(pelaaja1, pelaaja2);
        assertFalse(logiikka.ruutuOnTyhja(1));
        assertEquals(logiikka.ruudunNappulat(1), 2);
        assertTrue(logiikka.ruutuOnTyhja(2));
        assertTrue(logiikka.ruutuOnTyhja(3));
        assertTrue(logiikka.ruutuOnTyhja(4));
        assertTrue(logiikka.ruutuOnTyhja(5));
        assertFalse(logiikka.ruutuOnTyhja(6));
        assertEquals(logiikka.ruudunNappulat(6), 5);
        assertTrue(logiikka.ruutuOnTyhja(7));
        assertFalse(logiikka.ruutuOnTyhja(8));
        assertEquals(logiikka.ruudunNappulat(8), 3);
        assertTrue(logiikka.ruutuOnTyhja(9));
        assertTrue(logiikka.ruutuOnTyhja(10));
        assertTrue(logiikka.ruutuOnTyhja(11));
        assertFalse(logiikka.ruutuOnTyhja(12));
        assertEquals(logiikka.ruudunNappulat(12), 5);
        assertFalse(logiikka.ruutuOnTyhja(13));
        assertEquals(logiikka.ruudunNappulat(13), 5);
        assertTrue(logiikka.ruutuOnTyhja(14));
        assertTrue(logiikka.ruutuOnTyhja(15));
        assertTrue(logiikka.ruutuOnTyhja(16));
        assertFalse(logiikka.ruutuOnTyhja(17));
        assertEquals(logiikka.ruudunNappulat(17), 3);
        assertTrue(logiikka.ruutuOnTyhja(18));
        assertFalse(logiikka.ruutuOnTyhja(19));
        assertEquals(logiikka.ruudunNappulat(19), 5);
        assertTrue(logiikka.ruutuOnTyhja(20));
        assertTrue(logiikka.ruutuOnTyhja(21));
        assertTrue(logiikka.ruutuOnTyhja(22));
        assertTrue(logiikka.ruutuOnTyhja(23));
        assertFalse(logiikka.ruutuOnTyhja(24));
        assertEquals(logiikka.ruudunNappulat(24), 2);
    }

    @Test
    public void voikoSiirtaa() {
        Pelaaja pelaaja1 = new Pelaaja();
        Pelaaja pelaaja2 = new Pelaaja();
        Pelilogiikka l = new Pelilogiikka();

        l.asetaNappulat(pelaaja1, pelaaja2);

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

        l.asetaNappulat(pelaaja1, pelaaja2);

        l.siirraNappulaa(pelaaja2, 12, 14);
        assertTrue(l.ruutuunVoiSiirtya(12, pelaaja2));
        assertFalse(l.ruutuunVoiSiirtya(12, pelaaja1));
        assertEquals(l.ruudunNappulat(12), 4);
        assertTrue(l.ruutuOnPelaajan(14, pelaaja2));
        assertTrue(l.ruutuunVoiSiirtya(14, pelaaja2));
        assertTrue(l.ruutuunVoiSiirtya(14, pelaaja1));
        assertEquals(l.ruudunNappulat(14), 1);

    }
}
