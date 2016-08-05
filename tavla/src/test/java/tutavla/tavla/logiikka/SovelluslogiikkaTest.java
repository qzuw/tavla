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
    public void muutaSiirtojarjestys() {
        Pelaaja p1 = new Pelaaja();
        Pelaaja p2 = new Pelaaja();
        Kayttoliittyma kali = new Tekstikayttoliittyma();
        Sovelluslogiikka svl = new Sovelluslogiikka(kali);

        svl.setSiirtojarjestys(p1, p2);
        assertEquals(svl.getSiirtojarjestys().get(0), p1);
        assertEquals(svl.getSiirtojarjestys().get(1), p2);
        svl.pelaajaSiirtaaEnsin(true, p2);
        assertEquals(svl.getSiirtojarjestys().get(1), p1);
        assertEquals(svl.getSiirtojarjestys().get(0), p2);
        svl.pelaajaSiirtaaEnsin(false, p2);
        assertEquals(svl.getSiirtojarjestys().get(0), p1);
        assertEquals(svl.getSiirtojarjestys().get(1), p2);

    }
}
