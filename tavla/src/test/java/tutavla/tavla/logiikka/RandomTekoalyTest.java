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
public class RandomTekoalyTest {

    public RandomTekoalyTest() {
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
        RandomTekoaly t = new RandomTekoaly(r);
        ArrayList<Pelaaja> lista = new ArrayList<>();
        lista.add(new Pelaaja());
        lista.add(new Pelaaja());

        Pelaaja p = t.valitseVari(lista);

        assertTrue(lista.contains(p));
    }

    @Test
    public void testaaEttaTekoalyOsaaSiirtaaAloitustilanteessa() {
        Random r = new Random();
        RandomTekoaly t = new RandomTekoaly(r);
        Pelilogiikka plk = new Pelilogiikka();
        Siirrot st = new Siirrot(r);

        Pelaaja p1 = new Pelaaja();
        Pelaaja p2 = new Pelaaja();
        p1.asetaMustaksi(true);
        p2.asetaMustaksi(false);

        plk.alustaPelitilanne(p1, p2);

        st.heitaNopat();

        Siirto s = t.pelaa(p2, plk, st.haeSiirrot());

        assertFalse(s.eiVoiSiirtaa());
    }

    @Test
    public void testaaEttaTekoalyOsaaSiirtaaAloitustilanteessa2() {
        Random r = new Random();
        RandomTekoaly t = new RandomTekoaly(r);
        Pelilogiikka plk = new Pelilogiikka();
        Siirrot st = new Siirrot(r);

        Pelaaja p1 = new Pelaaja();
        Pelaaja p2 = new Pelaaja();
        p1.asetaMustaksi(true);
        p2.asetaMustaksi(false);

        plk.alustaPelitilanne(p1, p2);

        st.heitaNopat();

        Siirto s = t.pelaa(p1, plk, st.haeSiirrot());

        assertFalse(s.eiVoiSiirtaa());
    }

    @Test
    public void testaaEttaTekoalyEiVoiSiirtaa() {
        Random r = new Random();
        RandomTekoaly t = new RandomTekoaly(r);
        Pelilogiikka plk = new Pelilogiikka();
        Siirrot st = new Siirrot(r);

        Pelaaja p1 = new Pelaaja();
        Pelaaja p2 = new Pelaaja();
        p1.asetaMustaksi(true);
        p2.asetaMustaksi(false);

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
    public void testaaEttaTekoalyEiVoiSiirtaa2() {
        Random r = new Random();
        RandomTekoaly t = new RandomTekoaly(r);
        Pelilogiikka plk = new Pelilogiikka();
        Siirrot st = new Siirrot(r);

        Pelaaja p1 = new Pelaaja();
        Pelaaja p2 = new Pelaaja();
        p1.asetaMustaksi(true);
        p2.asetaMustaksi(false);
        p1.asetaMaali(0);

        plk.alustaPelitilanne(p1, p2);

        plk.siirraNappulaa(p1, 24, 25);
        plk.siirraNappulaa(p1, 24, 6);
        plk.siirraNappulaa(p2, 1, 20);
        plk.siirraNappulaa(p2, 1, 20);
        plk.siirraNappulaa(p2, 12, 21);
        plk.siirraNappulaa(p2, 12, 21);
        plk.siirraNappulaa(p2, 12, 22);
        plk.siirraNappulaa(p2, 12, 22);
        plk.siirraNappulaa(p2, 12, 23);
        plk.siirraNappulaa(p2, 17, 23);
        plk.siirraNappulaa(p2, 17, 24);
        plk.siirraNappulaa(p2, 17, 24);

        st.heitaNopat();

        Siirto s = t.pelaa(p1, plk, st.haeSiirrot());

        assertTrue(s.eiVoiSiirtaa());
    }

    @Test
    public void testaaEttaTekoalyTekeeJotainPelatessaan3() {
        Random r = new Random();
        RandomTekoaly t = new RandomTekoaly(r);
        Pelilogiikka plk = new Pelilogiikka();
        Siirrot st = new Siirrot(r);

        Pelaaja p1 = new Pelaaja();
        Pelaaja p2 = new Pelaaja();
        p1.asetaMustaksi(true);
        p2.asetaMustaksi(false);

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
        RandomTekoaly t = new RandomTekoaly(r);
        Pelilogiikka plk = new Pelilogiikka();

        Pelaaja p1 = new Pelaaja();
        Pelaaja p2 = new Pelaaja();
        p1.asetaMustaksi(true);
        p2.asetaMustaksi(false);
        p2.asetaMaali(25);

        plk.alustaPelitilanne(p1, p2);

        plk.siirraNappulaa(p2, 1, 7);
        plk.siirraNappulaa(p2, 1, 0);
        plk.siirraNappulaa(p1, 6, 5);
        plk.siirraNappulaa(p1, 6, 8);
        plk.siirraNappulaa(p1, 6, 8);
        plk.siirraNappulaa(p1, 6, 8);
        plk.siirraNappulaa(p1, 6, 8);
        plk.siirraNappulaa(p1, 13, 8);
        plk.siirraNappulaa(p1, 13, 8);
        plk.siirraNappulaa(p1, 13, 1);
        plk.siirraNappulaa(p1, 13, 6);
        plk.siirraNappulaa(p1, 24, 4);
        plk.siirraNappulaa(p1, 24, 3);

        ArrayList<Integer> st = new ArrayList<>();
        st.add(5);

        Siirto s = t.pelaa(p2, plk, st);

        assertTrue(s.vastustajanNappulaSyoty());
    }

    @Test
    public void tekoalyHuomaaVastustajanNappulanSyomattomyyden() {
        Random r = new Random();
        RandomTekoaly t = new RandomTekoaly(r);
        Pelilogiikka plk = new Pelilogiikka();
        Siirrot st = new Siirrot(r);

        Pelaaja p1 = new Pelaaja();
        Pelaaja p2 = new Pelaaja();
        p1.asetaMustaksi(true);
        p2.asetaMustaksi(false);

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
        RandomTekoaly t = new RandomTekoaly(r);
        Pelilogiikka plk = new Pelilogiikka();
        ArrayList<Integer> st = new ArrayList<>();

        Pelaaja p1 = new Pelaaja();
        Pelaaja p2 = new Pelaaja();
        p1.asetaMustaksi(true);
        p2.asetaMustaksi(false);

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

        assertEquals(s.haeMaali(), 6);
    }

    @Test
    public void kotialueeltaUlosSiirtaminen() {
        Random r = new Random();
        Sovelluslogiikka svl = new Sovelluslogiikka();
        Pelilogiikka plk = svl.haePelilogiikka();
        ArrayList<Integer> st = new ArrayList<>();
        svl.heitaNopat();

        Pelaaja p1 = svl.haeSiirtojarjestys().get(0);
        Pelaaja p2 = svl.haeSiirtojarjestys().get(1);
        p1.asetaMustaksi(true);
        p2.asetaMustaksi(false);

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
        svl.asetaSiirrot(st);

        Siirto s = svl.pelaaTietokone();

        assertEquals(0, s.haeMaali());
        assertEquals(1, plk.ruudunNappulaMaara(0));

        st.clear();
        st.add(2);
        svl.asetaSiirrot(st);

        s = svl.pelaaTietokone();

        assertEquals(0, s.haeMaali());
        assertEquals(2, plk.ruudunNappulaMaara(0));

    }

    @Test
    public void kotialueeltaUlosSiirtaminen2() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        Pelilogiikka plk = svl.haePelilogiikka();
        ArrayList<Integer> st = new ArrayList<>();
        svl.heitaNopat();

        Pelaaja p1 = svl.haeSiirtojarjestys().get(0);
        Pelaaja p2 = svl.haeSiirtojarjestys().get(1);
        p1.asetaMustaksi(true);
        p2.asetaMustaksi(false);

        plk.siirraNappulaa(p2, 12, 2);
        plk.siirraNappulaa(p2, 12, 2);
        plk.siirraNappulaa(p2, 12, 3);
        plk.siirraNappulaa(p2, 12, 3);
        plk.siirraNappulaa(p2, 12, 4);
        plk.siirraNappulaa(p2, 17, 4);
        plk.siirraNappulaa(p2, 17, 5);
        plk.siirraNappulaa(p2, 17, 5);
        plk.siirraNappulaa(p1, 8, 6);
        plk.siirraNappulaa(p1, 8, 6);
        plk.siirraNappulaa(p1, 8, 6);
        plk.siirraNappulaa(p1, 13, 6);
        plk.siirraNappulaa(p1, 13, 6);
        plk.siirraNappulaa(p1, 13, 6);
        plk.siirraNappulaa(p1, 13, 6);
        plk.siirraNappulaa(p1, 13, 6);
        plk.siirraNappulaa(p1, 24, 6);
        plk.siirraNappulaa(p1, 24, 6);

        st.add(1);
        svl.asetaSiirrot(st);

        Siirto s = svl.pelaaTietokone();

        assertTrue(s.eiVoiSiirtaa());

        st.clear();
        st.add(5);
        svl.asetaSiirrot(st);

        s = svl.pelaaTietokone();

        assertTrue(s.eiVoiSiirtaa());

        st.clear();
        st.add(6);
        svl.asetaSiirrot(st);

        s = svl.pelaaTietokone();

        assertFalse(s.eiVoiSiirtaa());
        assertEquals(0, s.haeMaali());
    }

    @Test
    public void kotialueeltaUlosSiirtaminen3() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        Pelilogiikka plk = svl.haePelilogiikka();
        ArrayList<Integer> st = new ArrayList<>();
        svl.heitaNopat();

        Pelaaja p1 = svl.haeSiirtojarjestys().get(0);
        Pelaaja p2 = svl.haeSiirtojarjestys().get(1);
        p1.asetaMustaksi(true);
        p2.asetaMustaksi(false);

        plk.siirraNappulaa(p2, 12, 2);
        plk.siirraNappulaa(p2, 12, 2);
        plk.siirraNappulaa(p2, 12, 3);
        plk.siirraNappulaa(p2, 12, 3);
        plk.siirraNappulaa(p2, 12, 4);
        plk.siirraNappulaa(p2, 17, 4);
        plk.siirraNappulaa(p2, 17, 6);
        plk.siirraNappulaa(p2, 17, 6);
        plk.siirraNappulaa(p1, 6, 5);
        plk.siirraNappulaa(p1, 6, 5);
        plk.siirraNappulaa(p1, 6, 5);
        plk.siirraNappulaa(p1, 6, 5);
        plk.siirraNappulaa(p1, 6, 5);
        plk.siirraNappulaa(p1, 8, 5);
        plk.siirraNappulaa(p1, 8, 5);
        plk.siirraNappulaa(p1, 8, 5);
        plk.siirraNappulaa(p1, 13, 5);
        plk.siirraNappulaa(p1, 13, 5);
        plk.siirraNappulaa(p1, 13, 5);
        plk.siirraNappulaa(p1, 13, 5);
        plk.siirraNappulaa(p1, 13, 5);
        plk.siirraNappulaa(p1, 24, 5);
        plk.siirraNappulaa(p1, 24, 5);

        st.clear();
        st.add(6);
        svl.asetaSiirrot(st);

        Siirto s = svl.pelaaTietokone();

        assertFalse(s.eiVoiSiirtaa());
        assertEquals(0, s.haeMaali());
    }
}
