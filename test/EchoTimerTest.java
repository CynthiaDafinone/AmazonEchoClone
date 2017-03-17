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
//        System.out.println("startTimer");
//        String str = "";
//        boolean expResult = false;
//        boolean result = EchoTimer.startTimer(str);
//       assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test to check that the shouldPlay method works as intended
     */
    @Test
    public void testShouldPlay() {
        EchoTimer et1 = new EchoTimer();
        
        boolean shouldPlayBool = et1.shouldPlay;
        assertNotNull("shouldPlay shouldn't be null", shouldPlayBool);
    }

    /**
     * Test to check that the stopPlaying method works as intended
     */
    @Test
    public void testStopPlaying() {
        EchoTimer et = new EchoTimer();
        
        boolean shouldPlayBool1 = et.shouldPlay;
        assertTrue("shouldPlay should have value true", shouldPlayBool1);

        et.stopPlaying();
        
        boolean shouldPlayBool2 = et.shouldPlay;
        assertFalse("shouldPlay should have value false", shouldPlayBool2);
    }
    
}
