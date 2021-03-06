/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.domain;

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
public class SiirtoTest {

    public SiirtoTest() {
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
    public void siirtoSisaltaaLahtoruudun() {
        Siirto s = new Siirto(3, 4, false, false);

        assertEquals(s.haeLahto(), 3);
    }

    @Test
    public void siirtoSisaltaaMaaliruudun() {
        Siirto s = new Siirto(3, 4, false, false);

        assertEquals(s.haeMaali(), 4);
    }

    @Test
    public void siirtoSisaltaaTiedonSyomisesta() {
        Siirto s = new Siirto(3, 4, false, false);

        assertEquals(s.vastustajanNappulaSyoty(), false);
    }

    @Test
    public void mikaanSiirtoEiMahdollinen() {
        Siirto s = new Siirto(3, 4, false, true);

        assertEquals(s.eiVoiSiirtaa(), true);
    }

}
