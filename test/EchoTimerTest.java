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

    }

    /**
     * Test to check that the shouldPlay method works as intended
     */
    @Test
    public void testShouldPlay() {
        EchoTimer et1 = new EchoTimer();
        
        boolean shouldPlayBool = et1.shouldPlay;
        assertTrue("shouldPlay should be true", shouldPlayBool);
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
