/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.logiikka;

import java.util.ArrayList;
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

}
