/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.domain;

import java.util.ArrayList;
import java.util.Random;
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
public class SiirrotTest {

    public SiirrotTest() {
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
    public void heitaNopat() {
        Random r = new Random();
        Siirrot s = new Siirrot(r);

        s.heitaNopat();
        s.heitaNopat();
        s.heitaNopat();

        assertTrue(s.haeSiirrot().size() == 2 || s.haeSiirrot().size() == 4);
        if (s.haeSiirrot().size() == 4) {
            assertTrue(s.haeSiirrot().get(0) == s.haeSiirrot().get(1));
            assertTrue(s.haeSiirrot().get(0) == s.haeSiirrot().get(2));
            assertTrue(s.haeSiirrot().get(0) == s.haeSiirrot().get(3));
        }
    }

    @Test
    public void heitaNopatMontaKertaa() {
        Random r = new Random();
        Siirrot s = new Siirrot(r);

        for (int i = 0; i < 100; i++) {
            s.heitaNopat();
            assertTrue(s.haeSiirrot().size() == 2 || s.haeSiirrot().size() == 4);
        }
    }

    @Test
    public void asetaSiirrot() {
        Random r = new Random();
        Siirrot s = new Siirrot(r);

        ArrayList<Integer> sl = new ArrayList<>();
        sl.add(1);
        s.asetaSiirrot(sl);
        assertEquals(1, s.haeSiirrot().size());
        assertEquals(1, (int) s.haeSiirrot().get(0));
    }
}
