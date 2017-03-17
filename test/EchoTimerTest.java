// TO BE FINISHED / WORK IN PROGRESS

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The test class for EchoTimer. Contains three tests:
 * 
 * 1. Tests that the startTimer method works as intended
 * 2. Tests that the shouldPlay method works as intended
 * 3. Tests that the stopPlaying method works as intended
 */
public class EchoTimerTest {
    
    public EchoTimerTest() {
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
     * Test to check that the startTimer method works as intended
     */
    @Test
    public void testStartTimer() {
        System.out.println("startTimer");
        String str = "";
        boolean expResult = false;
        boolean result = EchoTimer.startTimer(str);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test to check that the shouldPlay method works as intended
     */
    @Test
    public void testShouldPlay() {
        System.out.println("shouldPlay");
        boolean expResult = false;
        boolean result = EchoTimer.shouldPlay();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test to check that the stopPlaying method works as intended
     */
    @Test
    public void testStopPlaying() {
        System.out.println("stopPlaying");
        EchoTimer.stopPlaying();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
