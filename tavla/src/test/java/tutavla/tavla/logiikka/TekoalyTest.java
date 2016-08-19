/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.logiikka;

import java.util.ArrayList;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tutavla.tavla.domain.Pelaaja;
import tutavla.tavla.domain.Siirrot;
import tutavla.tavla.domain.Siirto;

/**
 *
 * @author ttuotila
 */
public class TekoalyTest {

    public TekoalyTest() {
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
    public void testaaSiirtojarjestyksenArpominen() {
        Random r = new Random();
        Tekoaly t = new Tekoaly(r);
        ArrayList<Pelaaja> lista = new ArrayList<>();
        lista.add(new Pelaaja());
        lista.add(new Pelaaja());

        Pelaaja p = t.valitseVari(lista);

        assertTrue(lista.contains(p));
    }

    @Test
    public void testaaEttaTekoalyTekeeJotainPelatessaan() {
        Random r = new Random();
        Tekoaly t = new Tekoaly(r);
        Pelilogiikka plk = new Pelilogiikka();
        Siirrot st = new Siirrot(r);

        Pelaaja p1 = new Pelaaja();
        Pelaaja p2 = new Pelaaja();
        p1.setMusta(true);
        p2.setMusta(false);

        plk.alustaPelitilanne(p1, p2);

        st.heitaNopat();

        Siirto s = t.pelaa(p2, plk, st.haeSiirrot());

        assertFalse(s.eiVoiSiirtaa());
    }

    @Test
    public void testaaEttaTekoalyTekeeJotainPelatessaan2() {
        Random r = new Random();
        Tekoaly t = new Tekoaly(r);
        Pelilogiikka plk = new Pelilogiikka();
        Siirrot st = new Siirrot(r);

        Pelaaja p1 = new Pelaaja();
        Pelaaja p2 = new Pelaaja();
        p1.setMusta(true);
        p2.setMusta(false);

        plk.alustaPelitilanne(p1, p2);

        plk.siirraNappulaa(p2, 1, 7);
        plk.siirraNappulaa(p2, 1, 0);
        plk.siirraNappulaa(p1, 6, 1);
        plk.siirraNappulaa(p1, 6, 1);
        plk.siirraNappulaa(p1, 6, 2);
        plk.siirraNappulaa(p1, 6, 2);
        plk.siirraNappulaa(p1, 6, 3);
        plk.siirraNappulaa(p1, 8, 3);
        plk.siirraNappulaa(p1, 8, 4);
        plk.siirraNappulaa(p1, 8, 4);
        plk.siirraNappulaa(p1, 13, 5);
        plk.siirraNappulaa(p1, 13, 5);
        plk.siirraNappulaa(p1, 13, 6);
        plk.siirraNappulaa(p1, 13, 6);
        plk.siirraNappulaa(p1, 13, 6);
        plk.siirraNappulaa(p1, 24, 6);
        plk.siirraNappulaa(p1, 24, 6);

        st.heitaNopat();

        Siirto s = t.pelaa(p2, plk, st.haeSiirrot());

        assertTrue(s.eiVoiSiirtaa());
    }

    @Test
    public void testaaEttaTekoalyTekeeJotainPelatessaan3() {
        Random r = new Random();
        Tekoaly t = new Tekoaly(r);
        Pelilogiikka plk = new Pelilogiikka();
        Siirrot st = new Siirrot(r);

        Pelaaja p1 = new Pelaaja();
        Pelaaja p2 = new Pelaaja();
        p1.setMusta(true);
        p2.setMusta(false);

        plk.alustaPelitilanne(p1, p2);

        plk.siirraNappulaa(p2, 1, 7);
        plk.siirraNappulaa(p2, 1, 0);
        plk.siirraNappulaa(p1, 6, 1);
        plk.siirraNappulaa(p1, 6, 1);
        plk.siirraNappulaa(p1, 6, 2);
        plk.siirraNappulaa(p1, 6, 2);
        plk.siirraNappulaa(p1, 6, 3);
        plk.siirraNappulaa(p1, 8, 3);
        plk.siirraNappulaa(p1, 8, 4);
        plk.siirraNappulaa(p1, 8, 4);
        plk.siirraNappulaa(p1, 13, 5);
        plk.siirraNappulaa(p1, 13, 5);
        plk.siirraNappulaa(p1, 13, 6);
        plk.siirraNappulaa(p1, 13, 6);
        plk.siirraNappulaa(p1, 13, 6);
        plk.siirraNappulaa(p1, 24, 6);
        plk.siirraNappulaa(p1, 24, 6);

        st.heitaNopat();

        Siirto s = t.pelaa(p1, plk, st.haeSiirrot());

        assertFalse(s.eiVoiSiirtaa());
    }

    @Test
    public void tekoalyHuomaaVastustajanNappulanSyonnin() {
        Random r = new Random();
        Tekoaly t = new Tekoaly(r);
        Pelilogiikka plk = new Pelilogiikka();
        Siirrot st = new Siirrot(r);

        Pelaaja p1 = new Pelaaja();
        Pelaaja p2 = new Pelaaja();
        p1.setMusta(true);
        p2.setMusta(false);

        plk.alustaPelitilanne(p1, p2);

        plk.siirraNappulaa(p2, 1, 7);
        plk.siirraNappulaa(p2, 1, 0);
        plk.siirraNappulaa(p1, 6, 1);
        plk.siirraNappulaa(p1, 6, 1);
        plk.siirraNappulaa(p1, 6, 1);
        plk.siirraNappulaa(p1, 6, 1);
        plk.siirraNappulaa(p1, 6, 1);
        plk.siirraNappulaa(p1, 8, 1);
        plk.siirraNappulaa(p1, 8, 1);
        plk.siirraNappulaa(p1, 8, 1);
        plk.siirraNappulaa(p1, 13, 1);
        plk.siirraNappulaa(p1, 13, 1);
        plk.siirraNappulaa(p1, 13, 1);
        plk.siirraNappulaa(p1, 13, 6);
        plk.siirraNappulaa(p1, 13, 5);
        plk.siirraNappulaa(p1, 24, 4);
        plk.siirraNappulaa(p1, 24, 3);
        plk.siirraNappulaa(p1, 5, 2);

        st.heitaNopat();

        Siirto s = t.pelaa(p2, plk, st.haeSiirrot());

        assertTrue(s.vastustajanNappulaSyoty());
    }

    @Test
    public void tekoalyHuomaaVastustajanNappulanSyomattomyyden() {
        Random r = new Random();
        Tekoaly t = new Tekoaly(r);
        Pelilogiikka plk = new Pelilogiikka();
        Siirrot st = new Siirrot(r);

        Pelaaja p1 = new Pelaaja();
        Pelaaja p2 = new Pelaaja();
        p1.setMusta(true);
        p2.setMusta(false);

        plk.alustaPelitilanne(p1, p2);

        plk.siirraNappulaa(p2, 1, 7);
        plk.siirraNappulaa(p2, 1, 7);
        plk.siirraNappulaa(p1, 6, 1);
        plk.siirraNappulaa(p1, 6, 1);
        plk.siirraNappulaa(p1, 6, 1);
        plk.siirraNappulaa(p1, 6, 1);
        plk.siirraNappulaa(p1, 6, 1);
        plk.siirraNappulaa(p1, 8, 1);
        plk.siirraNappulaa(p1, 8, 1);
        plk.siirraNappulaa(p1, 8, 1);
        plk.siirraNappulaa(p1, 13, 1);
        plk.siirraNappulaa(p1, 13, 1);
        plk.siirraNappulaa(p1, 13, 1);
        plk.siirraNappulaa(p1, 13, 1);
        plk.siirraNappulaa(p1, 13, 1);
        plk.siirraNappulaa(p1, 24, 1);
        plk.siirraNappulaa(p1, 24, 1);

        st.heitaNopat();

        Siirto s = t.pelaa(p2, plk, st.haeSiirrot());

        assertFalse(s.vastustajanNappulaSyoty());
    }

    @Test
    public void kotialueelleSiirtaminen() {
        Random r = new Random();
        Tekoaly t = new Tekoaly(r);
        Pelilogiikka plk = new Pelilogiikka();
        ArrayList<Integer> st = new ArrayList<>();

        Pelaaja p1 = new Pelaaja();
        Pelaaja p2 = new Pelaaja();
        p1.setMusta(true);
        p2.setMusta(false);

        plk.alustaPelitilanne(p1, p2);

        plk.siirraNappulaa(p2, 1, 11);
        plk.siirraNappulaa(p2, 1, 11);
        plk.siirraNappulaa(p1, 6, 1);
        plk.siirraNappulaa(p1, 6, 1);
        plk.siirraNappulaa(p1, 6, 1);
        plk.siirraNappulaa(p1, 6, 1);
        plk.siirraNappulaa(p1, 6, 1);
        plk.siirraNappulaa(p1, 8, 1);
        plk.siirraNappulaa(p1, 8, 1);
        plk.siirraNappulaa(p1, 8, 1);
        plk.siirraNappulaa(p1, 13, 1);
        plk.siirraNappulaa(p1, 13, 1);
        plk.siirraNappulaa(p1, 13, 1);
        plk.siirraNappulaa(p1, 13, 1);
        plk.siirraNappulaa(p1, 13, 1);
        plk.siirraNappulaa(p1, 24, 1);
        plk.siirraNappulaa(p1, 24, 8);

        st.add(2);

        Siirto s = t.pelaa(p1, plk, st);

        assertEquals(s.getMaali(), 6);
    }

    @Test
    public void kotialueeltaUlosSiirtaminen() {
        Random r = new Random();
        Tekoaly t = new Tekoaly(r);
        Pelilogiikka plk = new Pelilogiikka();
        ArrayList<Integer> st = new ArrayList<>();

        Pelaaja p1 = new Pelaaja();
        Pelaaja p2 = new Pelaaja();
        p1.setMusta(true);
        p2.setMusta(false);

        plk.alustaPelitilanne(p1, p2);

        plk.siirraNappulaa(p2, 1, 11);
        plk.siirraNappulaa(p2, 1, 11);
        plk.siirraNappulaa(p1, 6, 1);
        plk.siirraNappulaa(p1, 6, 1);
        plk.siirraNappulaa(p1, 6, 1);
        plk.siirraNappulaa(p1, 6, 1);
        plk.siirraNappulaa(p1, 6, 1);
        plk.siirraNappulaa(p1, 8, 1);
        plk.siirraNappulaa(p1, 8, 1);
        plk.siirraNappulaa(p1, 8, 1);
        plk.siirraNappulaa(p1, 13, 1);
        plk.siirraNappulaa(p1, 13, 1);
        plk.siirraNappulaa(p1, 13, 1);
        plk.siirraNappulaa(p1, 13, 1);
        plk.siirraNappulaa(p1, 13, 1);
        plk.siirraNappulaa(p1, 24, 1);
        plk.siirraNappulaa(p1, 24, 1);

        st.add(1);

        Siirto s = t.pelaa(p1, plk, st);

        assertEquals(s.getMaali(), 0);
        assertEquals(plk.ruudunNappulaMaara(0), 1);

        st.add(2);

        s = t.pelaa(p1, plk, st);

        assertEquals(s.getMaali(), 0);
        assertEquals(plk.ruudunNappulaMaara(0), 2);

    }
}
