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
     * Test to check that the calibrateMic method works as intended
     */
    @Test
    public void testCalibrateMic() {
        
    }

    /**
     * Test of run method, of class SoundDetector.
     */
    @Test
    public void testRun() {
        
    }

    /**
     * Test of startRecording method, of class SoundDetector.
     */
    @Test(expected=NullPointerException.class)
    public void testStartRecording() {
        SoundDetector sd = new SoundDetector();
        
        sd.startRecording();
    }

    /**
     * Test of registerRecordingListener method, of class SoundDetector.
     */
    @Test
    public void testRegisterRecordingListener() {
        SoundDetector sd1 = new SoundDetector();
        ActionListener listener1;
        
        int before = (sd1.listeners).size();
      //  System.out.print(before);
       // sd1.registerRecordingListener();
        
    }

    /**
     * Test of unregisterRecordingListener method, of class SoundDetector.
     */
    @Test
    public void testUnregisterRecordingListener() {
        
    }

    /**
     * Test of enableMic method, of class SoundDetector.
     */
    @Test(expected=NullPointerException.class)
    public void testEnableMic() {
        SoundDetector sd = new SoundDetector();
        
        sd.enableMic();
    }

    /**
     * Test of pauseForAnswer method, of class SoundDetector.
     */
    @Test
    public void testPauseForAnswer() {
        SoundDetector sd = new SoundDetector();
        
        sd.pauseForAnswer();
        
        boolean bool1 = sd.canRecord;
        assertFalse("canRecord should now be set to false" , bool1);
    }

    /**
     * Test of resumeAfterAnswer method, of class SoundDetector.
     */
    @Test
    public void testResumeAfterAnswer() {
        SoundDetector sd = new SoundDetector();
        
        sd.resumeAfterAnswer();
        
        boolean bool1 = sd.canRecord;
        assertTrue("canRecord should now be set to true" , bool1);
    }

    /**
     * Test of disableMic method, of class SoundDetector.
     */
    @Test
    public void testDisableMic() {
        SoundDetector sd = new SoundDetector();
        
        sd.disableMic();
        
        boolean bool1 = sd.running;
        assertFalse("running should now be set to false" , bool1);
    }
    
}
