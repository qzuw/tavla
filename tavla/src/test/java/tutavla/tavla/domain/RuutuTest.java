/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.domain;

import tutavla.tavla.domain.Nappula;
import tutavla.tavla.domain.Ruutu;
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
public class RuutuTest {

    public RuutuTest() {
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
    public void lisaaNappula() {
        Ruutu ruutu = new Ruutu();
        Pelaaja pelaaja = new Pelaaja();

        ruutu.lisaaNappula(new Nappula(pelaaja));
        assertEquals(ruutu.nappuloidenMaara(), 1);
        ruutu.lisaaNappula(new Nappula(pelaaja));
        assertEquals(ruutu.nappuloidenMaara(), 2);

        Nappula nappula = new Nappula(pelaaja);
        ruutu.lisaaNappula(nappula);
        assertEquals(ruutu.nappuloidenMaara(), 3);
        ruutu.lisaaNappula(nappula);
        assertEquals(ruutu.nappuloidenMaara(), 3);
    }

    @Test
    public void poistaNappula() {
        Ruutu ruutu = new Ruutu();
        Pelaaja pelaaja = new Pelaaja();

        ruutu.lisaaNappula(new Nappula(pelaaja));
        ruutu.lisaaNappula(new Nappula(pelaaja));

        ruutu.otaNappula();
        assertEquals(ruutu.nappuloidenMaara(), 1);
        ruutu.otaNappula();
        assertTrue(ruutu.isEmpty());
        assertEquals(ruutu.nappuloidenMaara(), 0);
        assertEquals(ruutu.otaNappula(), null);
    }

    @Test
    public void tarkistaPelaaja() {
        Ruutu ruutu = new Ruutu();
        Pelaaja pelaaja = new Pelaaja();
        Pelaaja toinen = new Pelaaja();

        assertEquals(ruutu.getPelaaja(), null);
        ruutu.lisaaNappula(new Nappula(pelaaja));
        assertEquals(ruutu.getPelaaja(), pelaaja);
        ruutu.lisaaNappula(new Nappula(toinen));
        assertEquals(ruutu.getPelaaja(), pelaaja);
    }

    @Test
    public void pelaajanNappulamaara() {
        Ruutu ruutu = new Ruutu();
        Pelaaja pelaaja = new Pelaaja();
        Pelaaja toinen = new Pelaaja();

        ruutu.lisaaNappula(new Nappula(pelaaja));
        ruutu.lisaaNappula(new Nappula(pelaaja));
        ruutu.lisaaNappula(new Nappula(pelaaja));
        ruutu.lisaaNappula(new Nappula(pelaaja));
        ruutu.lisaaNappula(new Nappula(toinen));

        assertEquals(ruutu.pelaajanNappuloidenMaara(pelaaja), 4);
        assertEquals(ruutu.pelaajanNappuloidenMaara(toinen), 1);
        ruutu.lisaaNappula(new Nappula(pelaaja));
        ruutu.lisaaNappula(new Nappula(toinen));
        assertEquals(ruutu.pelaajanNappuloidenMaara(pelaaja), 5);
        assertEquals(ruutu.pelaajanNappuloidenMaara(toinen), 2);

    }

    @Test
    public void otaPelaajanNappula() {
        Ruutu ruutu = new Ruutu();
        Pelaaja pelaaja = new Pelaaja();
        Pelaaja toinen = new Pelaaja();

        assertEquals(ruutu.otaPelaajanNappula(pelaaja), null);

        ruutu.lisaaNappula(new Nappula(pelaaja));
        assertEquals(ruutu.otaPelaajanNappula(toinen), null);
        ruutu.lisaaNappula(new Nappula(toinen));

        assertEquals(ruutu.otaPelaajanNappula(toinen).haePelaaja(), toinen);
        assertEquals(ruutu.nappuloidenMaara(), 1);
    }

    @Test
    public void otaPelaajanNappulaEiOtaVaaraa() {
        Ruutu ruutu = new Ruutu();
        Pelaaja pelaaja = new Pelaaja();
        Pelaaja toinen = new Pelaaja();

        ruutu.lisaaNappula(new Nappula(pelaaja));

        assertEquals(ruutu.otaPelaajanNappula(toinen), null);
        assertEquals(ruutu.nappuloidenMaara(), 1);
    }

    @Test
    public void ruutuToString() {
        Ruutu ruutu = new Ruutu();

        assertEquals("[] ", ruutu.toString());
    }

    @Test
    public void ruutuToString2() {
        Ruutu ruutu = new Ruutu();
        Pelaaja pelaaja = new Pelaaja();
        ruutu.lisaaNappula(new Nappula(pelaaja));
        pelaaja.asetaMustaksi(true);

        assertEquals("M1 ", ruutu.toString());
    }

    @Test
    public void ruutuToString3() {
        Ruutu ruutu = new Ruutu();
        Pelaaja pelaaja = new Pelaaja();
        for (int i = 0; i < 10; i++) {
            ruutu.lisaaNappula(new Nappula(pelaaja));
        }
        pelaaja.asetaMustaksi(false);

        assertEquals("V10", ruutu.toString());
    }
}
