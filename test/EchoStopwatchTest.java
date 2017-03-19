// TO BE FINISHED / WORK IN PROGRESS

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The test class for EchoTimer. Contains two tests:
 * 
 * 1. Tests that the startStopwatch method works as intended
 * 2. Tests that the stopStopwatch method works as intended
 */
public class EchoStopwatchTest {
    
    public EchoStopwatchTest() {
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

    /**
     * Test to check that the startStopwatch method works as intended
     */
    @Test
    public void testStartStopwatch() {
        EchoStopwatch esw = new EchoStopwatch();
        
        long before = esw.pastTime;
        esw.startStopwatch();
        long after = esw.pastTime;
        
        assertTrue("pastTime should change value after stopwatch started", before != after);
    }

    /**
     * Test to check that the stopStopwatch method works as intended
     */
    @Test
    public void testStopStopwatch() {
        EchoStopwatch esw1 = new EchoStopwatch();
      //  System.out.println(esw1.pastTime);
       // boolean bool1 = esw1.stopStopwatch();
        //assertFalse("bool1 should be false as pastTime == -1", bool1);
       // System.out.println(bool1);
    }
    
}
