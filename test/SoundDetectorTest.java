// TO BE FINISHED / WORK IN PROGRESS

import java.awt.event.ActionListener;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The test class for SoundDetector, & SoundDetectionThread its nested class . 
 * Contains 12 tests:
 * 
 * 1. Tests that the calibrateMic method works as intended
 * 2. Tests that the B method works as intended
 * 3. Tests that the C method works as intended
 * 4. Tests that the A method works as intended
 * 5. Tests that the B method works as intended
 * 6. Tests that the C method works as intended
 * 7. Tests that the A method works as intended
 * 8. Tests that the B method works as intended
 * 9. Tests that the C method works as intended
 * 10. Tests that the A method works as intended
 * 11. Tests that the B method works as intended
 * 12. Tests that the C method works as intended
 */
public class SoundDetectorTest {
    
    public SoundDetectorTest() {
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
     * Test of calibrateMic method, of class SoundDetector.
     */
    @Test
    public void testCalibrateMic() {
        System.out.println("calibrateMic");
        SoundDetector.SoundDetectionThread detector = null;
        SoundDetector instance = new SoundDetector();
        instance.calibrateMic(detector);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of run method, of class SoundDetector.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        SoundDetector instance = new SoundDetector();
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startRecording method, of class SoundDetector.
     */
    @Test
    public void testStartRecording() {
        System.out.println("startRecording");
        SoundDetector instance = new SoundDetector();
        instance.startRecording();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerRecordingListener method, of class SoundDetector.
     */
    @Test
    public void testRegisterRecordingListener() {
        System.out.println("registerRecordingListener");
        ActionListener listener = null;
        SoundDetector instance = new SoundDetector();
        boolean expResult = false;
        boolean result = instance.registerRecordingListener(listener);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unregisterRecordingListener method, of class SoundDetector.
     */
    @Test
    public void testUnregisterRecordingListener() {
        System.out.println("unregisterRecordingListener");
        ActionListener listener = null;
        SoundDetector instance = new SoundDetector();
        boolean expResult = false;
        boolean result = instance.unregisterRecordingListener(listener);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enableMic method, of class SoundDetector.
     */
    @Test
    public void testEnableMic() {
        System.out.println("enableMic");
        SoundDetector instance = new SoundDetector();
        instance.enableMic();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pauseForAnswer method, of class SoundDetector.
     */
    @Test
    public void testPauseForAnswer() {
        System.out.println("pauseForAnswer");
        SoundDetector instance = new SoundDetector();
        instance.pauseForAnswer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resumeAfterAnswer method, of class SoundDetector.
     */
    @Test
    public void testResumeAfterAnswer() {
        System.out.println("resumeAfterAnswer");
        SoundDetector instance = new SoundDetector();
        instance.resumeAfterAnswer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of disableMic method, of class SoundDetector.
     */
    @Test
    public void testDisableMic() {
        System.out.println("disableMic");
        SoundDetector instance = new SoundDetector();
        instance.disableMic();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
