/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla;

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
public class PelaajaTest {

    public PelaajaTest() {
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
    public void pelaajallaOnNimi() {
        Pelaaja pelaaja = new Pelaaja("asdf");

        String nimi = pelaaja.getNimi();

        assertEquals("asdf", nimi);
    }

    @Test
    public void tietokoneOnTietokone() {
        Pelaaja pelaaja = new Pelaaja();
        boolean onkoTietokone = !pelaaja.isIhminen();

        assertTrue(onkoTietokone);
    }

    @Test
    public void ihminenOnIhminen() {
        Pelaaja pelaaja = new Pelaaja("asdf");
        boolean onkoTietokone = !pelaaja.isIhminen();

        assertFalse(onkoTietokone);
    }

    @Test
    public void pelaajaEqualsPelaaja() {
        Pelaaja pelaaja = new Pelaaja();
        Pelaaja kopio = pelaaja;

        boolean pelaajaOnSama = pelaaja.equals(kopio);

        assertTrue(pelaajaOnSama);
    }

    @Test
    public void pelaajaNotEqualsPelaaja() {
        Pelaaja pelaaja = new Pelaaja("adsf");
        Pelaaja toinen = new Pelaaja("sdfg");
        Pelaaja tietokone = new Pelaaja();
        Pelaaja toinenTietokone = new Pelaaja();

        boolean pelaajaOnSama = pelaaja.equals(toinen);
        boolean tietokoneOnSama = pelaaja.equals(toinen);

        assertFalse(pelaajaOnSama);
        assertFalse(tietokoneOnSama);
    }

}
