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
        ArrayList<Pelaaja> jarjestys = svl.getSiirtojarjestys();

        String s = "asdf";

        assertEquals(svl.getSiirtojarjestys().get(0).getNimi(), "Tietokone");
        svl.maaritaPelaaja(0, s);
        assertEquals(svl.getSiirtojarjestys().get(0).getNimi(), s);
        assertTrue(svl.getSiirtojarjestys().get(0).isIhminen());
    }

    @Test
    public void asetaVari() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        Pelaaja p = svl.getSiirtojarjestys().get(0);
        Pelaaja p2 = svl.getSiirtojarjestys().get(1);

        svl.asetaPelaajaMustaksi(p);

        assertTrue(svl.getSiirtojarjestys().get(0).isMusta());
        assertFalse(svl.getSiirtojarjestys().get(1).isMusta());

        svl.asetaPelaajaMustaksi(p2);

        assertTrue(svl.getSiirtojarjestys().get(1).isMusta());
        assertFalse(svl.getSiirtojarjestys().get(0).isMusta());
    }

    @Test
    public void setSiirtojarjestys() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        Pelaaja p1 = svl.getSiirtojarjestys().get(0);
        Pelaaja p2 = svl.getSiirtojarjestys().get(1);

        svl.setSiirtojarjestys(p2, p1);
        assertEquals(2, svl.getSiirtojarjestys().size());
    }

    @Test
    public void muutaSiirtojarjestys() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        Pelaaja p1 = svl.getSiirtojarjestys().get(0);
        Pelaaja p2 = svl.getSiirtojarjestys().get(1);

        assertEquals(svl.getSiirtojarjestys().get(0), p1);
        assertEquals(svl.getSiirtojarjestys().get(1), p2);
        svl.pelaajaSiirtaaEnsin(true, p2);
        assertEquals(svl.getSiirtojarjestys().get(1), p1);
        assertEquals(svl.getSiirtojarjestys().get(0), p2);
        svl.pelaajaSiirtaaEnsin(false, p2);
        assertEquals(svl.getSiirtojarjestys().get(0), p1);
        assertEquals(svl.getSiirtojarjestys().get(1), p2);

    }

    @Test
    public void muutaSiirtojarjestysIndeksilla1() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        Pelaaja p1 = svl.getSiirtojarjestys().get(0);
        Pelaaja p2 = svl.getSiirtojarjestys().get(1);

        assertEquals(svl.getSiirtojarjestys().get(0), p1);
        assertEquals(svl.getSiirtojarjestys().get(1), p2);
        svl.pelaajaSiirtaaEnsin(true, 1);
        assertEquals(svl.getSiirtojarjestys().get(1), p1);
        assertEquals(svl.getSiirtojarjestys().get(0), p2);

    }

    @Test
    public void muutaSiirtojarjestysIndeksilla2() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        Pelaaja p1 = svl.getSiirtojarjestys().get(0);
        Pelaaja p2 = svl.getSiirtojarjestys().get(1);

        assertEquals(svl.getSiirtojarjestys().get(0), p1);
        assertEquals(svl.getSiirtojarjestys().get(1), p2);
        svl.pelaajaSiirtaaEnsin(false, 0);
        assertEquals(svl.getSiirtojarjestys().get(0), p2);
        assertEquals(svl.getSiirtojarjestys().get(1), p1);

    }

    @Test
    public void kaynnistaTest() {
        Sovelluslogiikka svl = new Sovelluslogiikka();

        ArrayList<Pelaaja> jarjestys = svl.getSiirtojarjestys();

        // ei tätä voi oikein testata järkevästi
        assertTrue(jarjestys.size() == 2);
    }

//    @Test
//    public void siirtojarjestysTest(){
//        Sovelluslogiikka svl = new Sovelluslogiikka();
//        
//        ArrayList<Pelaaja> jarjestys = svl.getSiirtojarjestys();
//        
//        svl.setSiirtojarjestys(jarjestys.get(0), jarjestys.get(1));
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
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(24);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(0);
        assertFalse(svl.onkoJokuVoittanut());
        svl.asetaLahtoruutu(6);
        svl.heitaNopat();
        svl.siirraNappulaa(0);
        assertTrue(svl.onkoJokuVoittanut());

    }

    @Test
    public void tarkistaKukaVoitti() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        Pelaaja p = svl.getVuorossaOlevaPelaaja();

        svl.asetaLahtoruutu(24);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(24);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(0);
        svl.heitaNopat();
        assertEquals(svl.kukaVoitti(), p);
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
        assertEquals(6, svl.getLahtoruutu());
    }

    @Test
    public void nollaaLahtoruutu() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        svl.heitaNopat();

        svl.asetaLahtoruutu(6);
        svl.nollaaLahtoruutu();
        assertEquals(-1, svl.getLahtoruutu());
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

        assertTrue(svl.getSiirtojarjestys().get(0).isMusta());
    }

    @Test
    public void aloitusvarit2() {
        Sovelluslogiikka svl = new Sovelluslogiikka();

        assertFalse(svl.getSiirtojarjestys().get(1).isMusta());
    }

    @Test
    public void voikoSiirtaa() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        Pelaaja p = svl.getVuorossaOlevaPelaaja();

        svl.vaihdaVuoroa();
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(7);
        svl.siirraNappulaa(7);

        svl.vaihdaVuoroa();
        svl.asetaLahtoruutu(24);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(24);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(0);
        svl.heitaNopat();
        p.setMaali(25);
        assertFalse(svl.eiVoiSiirtaa());
    }

    @Test
    public void voikoSiirtaa2() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        Pelaaja p = svl.getVuorossaOlevaPelaaja();

        svl.vaihdaVuoroa();
        svl.asetaLahtoruutu(1);
        svl.siirraNappulaa(7);
        svl.siirraNappulaa(7);

        svl.vaihdaVuoroa();
        svl.asetaLahtoruutu(24);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(24);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(13);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(8);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(0);
        svl.asetaLahtoruutu(6);
        svl.siirraNappulaa(0);
        svl.heitaNopat();
        assertTrue(svl.eiVoiSiirtaa());
    }

    @Test
    public void voiSiirtaaRuuduista() {
        Sovelluslogiikka svl = new Sovelluslogiikka();
        Pelaaja p = svl.getVuorossaOlevaPelaaja();
        svl.heitaNopat();

        boolean tulos = true;

        ArrayList<Integer> l = new ArrayList<>(Arrays.asList(24, 13, 6, 8));

        if (!svl.pelaajaVoiSiirtaaRuuduista().containsAll(l)) {
            tulos = false;
        }
        assertTrue(tulos);
    }
}
