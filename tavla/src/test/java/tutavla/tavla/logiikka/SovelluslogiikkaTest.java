/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.logiikka;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tutavla.tavla.domain.Pelaaja;
import tutavla.tavla.ui.Kayttoliittyma;
import tutavla.tavla.ui.Tekstikayttoliittyma;

/**
 *
 * @author ttuotila
 */
public class SovelluslogiikkaTest {

    public SovelluslogiikkaTest() {
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
    public void maaritaPelaaja() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        ArrayList<Pelaaja> jarjestys = svl.haeSiirtojarjestys();

        String s = "asdf";

        assertEquals(svl.haeSiirtojarjestys().get(0).haeNimi(), "Tietokone");
        svl.maaritaPelaaja(0, s);
        assertEquals(svl.haeSiirtojarjestys().get(0).haeNimi(), s);
        assertTrue(svl.haeSiirtojarjestys().get(0).onkoIhminen());
    }

    @Test
    public void asetaVari() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        Pelaaja p = svl.haeSiirtojarjestys().get(0);
        Pelaaja p2 = svl.haeSiirtojarjestys().get(1);

        svl.asetaPelaajaMustaksi(p);

        assertTrue(svl.haeSiirtojarjestys().get(0).onkoMusta());
        assertFalse(svl.haeSiirtojarjestys().get(1).onkoMusta());

        svl.asetaPelaajaMustaksi(p2);

        assertTrue(svl.haeSiirtojarjestys().get(1).onkoMusta());
        assertFalse(svl.haeSiirtojarjestys().get(0).onkoMusta());
    }

    @Test
    public void setSiirtojarjestys() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        Pelaaja p1 = svl.haeSiirtojarjestys().get(0);
        Pelaaja p2 = svl.haeSiirtojarjestys().get(1);

        svl.asetaSiirtojarjestys(p2, p1);
        assertEquals(2, svl.haeSiirtojarjestys().size());
    }

    @Test
    public void muutaSiirtojarjestys() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        Pelaaja p1 = svl.haeSiirtojarjestys().get(0);
        Pelaaja p2 = svl.haeSiirtojarjestys().get(1);

        assertEquals(svl.haeSiirtojarjestys().get(0), p1);
        assertEquals(svl.haeSiirtojarjestys().get(1), p2);
        svl.pelaajaSiirtaaEnsin(true, p2);
        assertEquals(svl.haeSiirtojarjestys().get(1), p1);
        assertEquals(svl.haeSiirtojarjestys().get(0), p2);
        svl.pelaajaSiirtaaEnsin(false, p2);
        assertEquals(svl.haeSiirtojarjestys().get(0), p1);
        assertEquals(svl.haeSiirtojarjestys().get(1), p2);

    }

    @Test
    public void muutaSiirtojarjestysIndeksilla1() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        Pelaaja p1 = svl.haeSiirtojarjestys().get(0);
        Pelaaja p2 = svl.haeSiirtojarjestys().get(1);

        assertEquals(svl.haeSiirtojarjestys().get(0), p1);
        assertEquals(svl.haeSiirtojarjestys().get(1), p2);
        svl.pelaajaSiirtaaEnsin(true, 1);
        assertEquals(svl.haeSiirtojarjestys().get(1), p1);
        assertEquals(svl.haeSiirtojarjestys().get(0), p2);

    }

    @Test
    public void muutaSiirtojarjestysIndeksilla2() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        Pelaaja p1 = svl.haeSiirtojarjestys().get(0);
        Pelaaja p2 = svl.haeSiirtojarjestys().get(1);

        assertEquals(svl.haeSiirtojarjestys().get(0), p1);
        assertEquals(svl.haeSiirtojarjestys().get(1), p2);
        svl.pelaajaSiirtaaEnsin(false, 0);
        assertEquals(svl.haeSiirtojarjestys().get(0), p2);
        assertEquals(svl.haeSiirtojarjestys().get(1), p1);

    }

    @Test
    public void kaynnistaTest() {
        Sovelluslogiikka svl = new Sovelluslogiikka();

        ArrayList<Pelaaja> jarjestys = svl.haeSiirtojarjestys();

        // ei tätä voi oikein testata järkevästi
        assertTrue(jarjestys.size() == 2);
    }

//    @Test
//    public void siirtojarjestysTest(){
//        Sovelluslogiikka svl = new Sovelluslogiikka();
//        
//        ArrayList<Pelaaja> jarjestys = svl.haeSiirtojarjestys();
//        
//        svl.asetaSiirtojarjestys(jarjestys.get(0), jarjestys.get(1));
//
//        
//        assertTrue(jarjestys.size() == 2);
//    }
    @Test
    public void heitaNopat() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        svl.heitaNopat();
        ArrayList<Integer> siirrot = svl.haeSiirrot();

        assertTrue(siirrot.size() == 2 || siirrot.size() == 4);
    }

    @Test
    public void aloittaessaEiOleVoittajaa() {
        Sovelluslogiikka svl = new Sovelluslogiikka();

        assertFalse(svl.onkoJokuVoittanut());
    }

    @Test
    public void aloittaessaEiOleVoittajaa2() {
        Sovelluslogiikka svl = new Sovelluslogiikka();

        assertEquals(svl.kukaVoitti(), null);
    }

    @Test
    public void tietokoneVoiPelataSiirronAlussa() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        svl.heitaNopat();

        assertFalse(svl.pelaaTietokone().eiVoiSiirtaa());
    }

    @Test
    public void tietokoneVoiPelataSiirronAlussa2() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        svl.vaihdaVuoroa();
        svl.heitaNopat();

        assertFalse(svl.pelaaTietokone().eiVoiSiirtaa());
    }

    @Test
    public void tarkistaOnkoVoittajaa() {
        Sovelluslogiikka svl = new Sovelluslogiikka();

        svl.asetaLahtoruutu(24);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(24);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(2);
        for (int i = 0; i < 14; i++) {
            svl.asetaLahtoruutu(2);
            svl.siirraNappulaa(0);
        }

        assertFalse(svl.onkoJokuVoittanut());
        svl.asetaLahtoruutu(6);
        svl.heitaNopat();
        svl.siirraNappulaa(0);
        assertTrue(svl.onkoJokuVoittanut());

    }

    @Test
    public void tarkistaKukaVoitti() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        Pelaaja p = svl.haeVuorossaOlevaPelaaja();

        svl.asetaLahtoruutu(24);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(24);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(2);
        for (int i = 0; i < 15; i++) {
            svl.asetaLahtoruutu(2);
            svl.siirraNappulaa(0);
        }

        svl.heitaNopat();
        assertEquals(p, svl.kukaVoitti());
    }

    @Test
    public void eiVoiSiirtaa() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        svl.heitaNopat();

        svl.asetaLahtoruutu(24);
        svl.siirraNappulaa(25);
        svl.asetaLahtoruutu(24);
        svl.siirraNappulaa(18);
        svl.vaihdaVuoroa();
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(20);
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(20);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(21);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(21);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(22);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(22);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(17);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(17);
        svl.siirraNappulaa(24);
        svl.asetaLahtoruutu(17);
        svl.siirraNappulaa(24);
        svl.heitaNopat();
        svl.vaihdaVuoroa();

        assertTrue(svl.eiVoiSiirtaa());
    }

    @Test
    public void eiVoiSiirtaa2() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        svl.heitaNopat();

        svl.vaihdaVuoroa();
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(0);
        svl.vaihdaVuoroa();
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(5);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(5);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(4);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(4);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(3);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(3);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(1);
        svl.heitaNopat();
        svl.vaihdaVuoroa();

        assertTrue(svl.eiVoiSiirtaa());
    }

    @Test
    public void voiSiirtaa() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        svl.heitaNopat();

        svl.asetaLahtoruutu(24);
        svl.siirraNappulaa(25);
        svl.asetaLahtoruutu(24);
        svl.siirraNappulaa(6);
        svl.vaihdaVuoroa();
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(20);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(21);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(22);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(24);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(17);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(17);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(17);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(17);
        svl.heitaNopat();
        svl.vaihdaVuoroa();

        assertFalse(svl.eiVoiSiirtaa());
    }

    @Test
    public void voiSiirtaa2() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        svl.heitaNopat();

        svl.vaihdaVuoroa();
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(0);
        svl.vaihdaVuoroa();
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(5);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(4);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(3);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(1);
        svl.heitaNopat();
        svl.vaihdaVuoroa();

        assertFalse(svl.eiVoiSiirtaa());
    }

    @Test
    public void asetaLahtoruutuPaluuarvo() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        svl.heitaNopat();

        assertFalse(svl.asetaLahtoruutu(2));
    }

    @Test
    public void asetaLahtoruutuPaluuarvo2() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        svl.heitaNopat();

        assertTrue(svl.asetaLahtoruutu(6));
    }

    @Test
    public void haeLahtoruutu() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        svl.heitaNopat();

        svl.asetaLahtoruutu(6);
        assertEquals(6, svl.haeLahtoruutu());
    }

    @Test
    public void nollaaLahtoruutu() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        svl.heitaNopat();

        svl.asetaLahtoruutu(6);
        svl.nollaaLahtoruutu();
        assertEquals(-1, svl.haeLahtoruutu());
    }

    @Test
    public void siirrotKotialueella() {
        Sovelluslogiikka svl = new Sovelluslogiikka();

        svl.vaihdaVuoroa();
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(2);
        svl.vaihdaVuoroa();
        svl.asetaLahtoruutu(24);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(24);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(6);
        svl.heitaNopat();
        assertFalse(svl.eiVoiSiirtaa());
    }

    @Test
    public void siirrotKotialueella2() {
        Sovelluslogiikka svl = new Sovelluslogiikka();

        svl.vaihdaVuoroa();
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(2);
        svl.vaihdaVuoroa();
        svl.asetaLahtoruutu(24);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(24);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(1);
        svl.heitaNopat();
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(0);

        assertTrue((svl.haeSiirrot().size() == 1 || svl.haeSiirrot().size() == 3));
    }

    @Test
    public void aloitusvarit() {
        Sovelluslogiikka svl = new Sovelluslogiikka();

        assertTrue(svl.haeSiirtojarjestys().get(0).onkoMusta());
    }

    @Test
    public void aloitusvarit2() {
        Sovelluslogiikka svl = new Sovelluslogiikka();

        assertFalse(svl.haeSiirtojarjestys().get(1).onkoMusta());
    }

    @Test
    public void voikoSiirtaa() {
        Sovelluslogiikka svl = new Sovelluslogiikka();

        svl.vaihdaVuoroa();
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(10);
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(10);

        svl.vaihdaVuoroa();
        svl.asetaLahtoruutu(24);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(24);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(2);
        for (int i = 0; i < 15; i++) {
            svl.asetaLahtoruutu(2);
            svl.siirraNappulaa(0);
        }
        svl.heitaNopat();
        svl.haeVuorossaOlevaPelaaja().asetaMaali(25);
        assertFalse(svl.eiVoiSiirtaa());
    }

    @Test
    public void voikoSiirtaa2() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        Pelaaja p = svl.haeVuorossaOlevaPelaaja();

        svl.vaihdaVuoroa();
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(7);
        svl.siirraNappulaa(7);

        svl.vaihdaVuoroa();
        svl.asetaLahtoruutu(24);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(24);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(2);
        for (int i = 0; i < 15; i++) {
            svl.asetaLahtoruutu(2);
            svl.siirraNappulaa(0);
        }
        svl.heitaNopat();
        assertTrue(svl.eiVoiSiirtaa());
    }

    @Test
    public void voiSiirtaaRuuduista() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        Pelaaja p = svl.haeVuorossaOlevaPelaaja();
        svl.heitaNopat();

        ArrayList<Integer> l = new ArrayList<>(Arrays.asList(24, 13, 6, 8));

        assertTrue(svl.pelaajaVoiSiirtaaRuuduista().containsAll(l));
    }

    @Test
    public void voiSiirtaaRuutuihin() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        Pelaaja p = svl.haeVuorossaOlevaPelaaja();
        svl.heitaNopat();

        svl.asetaLahtoruutu(24);
        ArrayList<Integer> s = new ArrayList<>(Arrays.asList(1, 2));
        svl.asetaSiirrot(s);

        ArrayList<Integer> l = new ArrayList<>(Arrays.asList(22, 23));

        assertTrue(svl.pelaajaVoiSiirtaaRuutuihin().containsAll(l));
    }

    @Test
    public void siirra() {
        Sovelluslogiikka svl = new Sovelluslogiikka();

        ArrayList<Integer> s = new ArrayList<>(Arrays.asList(1));

        svl.vaihdaVuoroa();
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(2);
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(2);
        svl.vaihdaVuoroa();
        svl.asetaLahtoruutu(24);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(24);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(1);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(1);
        svl.asetaSiirrot(s);

        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(0);

        assertTrue(svl.haeSiirrot().isEmpty());
    }

    @Test
    public void siirra2() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        svl.heitaNopat();
        ArrayList<Integer> s = new ArrayList<>(Arrays.asList(2));

        svl.vaihdaVuoroa();
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(23);
        svl.heitaNopat();
        svl.asetaSiirrot(s);
        svl.asetaLahtoruutu(23);
        svl.siirraNappulaa(25);

        assertTrue(svl.haeSiirrot().isEmpty());
    }

    @Test
    public void siirra3() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        svl.heitaNopat();
        ArrayList<Integer> s = new ArrayList<>(Arrays.asList(3));

        svl.vaihdaVuoroa();
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(17);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(17);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(17);
        svl.siirraNappulaa(23);
        svl.heitaNopat();
        svl.asetaSiirrot(s);
        svl.asetaLahtoruutu(23);
        svl.siirraNappulaa(25);

        assertTrue(svl.haeSiirrot().isEmpty());
    }

    @Test
    public void voikoSiirtaa3() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        svl.heitaNopat();
        ArrayList<Integer> s = new ArrayList<>(Arrays.asList(3));

        svl.vaihdaVuoroa();
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(17);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(17);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(17);
        svl.siirraNappulaa(23);
        svl.heitaNopat();
        svl.asetaSiirrot(s);

        assertFalse(svl.eiVoiSiirtaa());
    }

    @Test
    public void voikoSiirtaa4() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        svl.heitaNopat();
        // s pitäisi olla 2
        ArrayList<Integer> s = new ArrayList<>(Arrays.asList(1));

        svl.vaihdaVuoroa();
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(17);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(17);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(17);
//siirron pitäisi olla 4 ei 5
        svl.siirraNappulaa(5);
        svl.heitaNopat();
        svl.asetaSiirrot(s);

//        for (int i = 0; i < 25; i++) {
//            System.out.println(svl.pelitilanne().haeRuutu(i));
//        }
        assertTrue(svl.eiVoiSiirtaa());
    }

    @Test
    public void voikoSiirtaa5() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        svl.heitaNopat();
        ArrayList<Integer> s = new ArrayList<>(Arrays.asList(1));

        svl.vaihdaVuoroa();
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(12);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(19);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(17);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(17);
        svl.siirraNappulaa(23);
        svl.asetaLahtoruutu(17);
        svl.siirraNappulaa(5);
        svl.heitaNopat();
        svl.asetaSiirrot(s);

//        for (int i = 0; i < 25; i++) {
//            System.out.println(svl.pelitilanne().haeRuutu(i));
//        }
        assertTrue(svl.eiVoiSiirtaa());
    }
}
