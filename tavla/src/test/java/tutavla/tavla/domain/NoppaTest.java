/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.domain;

import java.util.Random;
import tutavla.tavla.domain.Noppa;
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
public class NoppaTest {

    public NoppaTest() {
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
    public void arvot1taiEnemman() {
        Random r = new Random();
        Noppa noppa = new Noppa(r);

        for (int i = 0; i < 60; i++) {
            noppa.arvo();
            int luku = noppa.getArvo();
            assertTrue(luku >= 1);
        }
    }

    @Test
    public void arvot6taiAlle() {
        Random r = new Random();
        Noppa noppa = new Noppa(r);

        for (int i = 0; i < 60; i++) {
            noppa.arvo();
            int luku = noppa.getArvo();
            assertTrue(luku <= 6);
        }
    }
    
    @Test
    public void konstruktoriLuoArvon(){
        Random r = new Random();
        Noppa noppa = new Noppa(r);
        assertTrue(noppa.getArvo()<7);
        assertTrue(noppa.getArvo()>0);
    }
}
