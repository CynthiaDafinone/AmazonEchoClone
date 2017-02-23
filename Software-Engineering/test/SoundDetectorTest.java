import be.tarsos.dsp.AudioEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import javax.sound.sampled.AudioInputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;
import java.awt.*;

/**
 * The test class for SoundDetector. Contains seven tests:
 * 
 * 1. Tests that the setupDection method works as intended **to finish**
 * 2. Tests that the readStream method works as intended 
 * 3. Tests that the questionMode method works as intended
 * 4. Tests that the listenMode method works as intended 
 * 5. Tests that the recordSound method works as intended
 * 6. Tests that the addActionListener method works as intended **to finish**
 * 7. Tests that the process method works as intended **to finish**
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
     * Test to check that the setUpDetection method works as intended
     */
    @Test
    public void testSetUpDetection() {
       // To be returned to 
    }

    /**
     * Test to check that the readStream method works as intended
     */
    @Test
    public void testReadStream() throws IOException{
        SoundDetector sd = new SoundDetector();  // Instance to test created
        sd.setUpDetection();
        ByteArrayOutputStream baos = sd.readStream(sd.ais);
        
        assertNotNull("Byte Array Output Stream can't be null", baos);
        
        (sd.ais).close(); //Clean up audio input stream
    }

    /**
     * Test to check that the questionMode method works as intended
     */
    @Test
    public void testQuestionMode() {
        SoundDetector sd = new SoundDetector();  // Instance to test created
        sd.questionMode();
        assertFalse("Value of bool questionMode should now be false",
                    sd.questionMode);
    }

    /**
     * Test to check that the listenMode method works as intended
     */
    @Test
    public void testListenMode() {
        SoundDetector sd = new SoundDetector();  // Instance to test created
        sd.listenMode();
        assertTrue("Value of bool questionMode should now be true",
                   sd.questionMode);
    }

    /**
     * Test to check that the recordSound method works as intended
     */
    @Test
    public void testRecordSound() throws IOException {
        SoundDetector sd = new SoundDetector();  // Instance to test created
        sd.setUpDetection();
        ByteArrayOutputStream baos2 = sd.readStream(sd.ais);
        SoundDetector.recordSound("testName", baos2);
        
        (sd.ais).close(); //Clean up audio input stream
       
        File s = new File("C:/Users/Lewis/Desktop/University/Degree/Year 2/Modules/Term 2/ECM2415 Software Engineering/Software Engineering/testName");
        assertTrue(s.isFile());  // Check output file created
    }

    /**
     * Test to check that the addActionListener method works as intended
     */
    @Test
    public void testAddActionListener() {
       //SoundDetector sd = new SoundDetector();  // Instance to test created
       //ActionListener mockListener;
       //int b = sd.listeners.size();
       //System.out.println(b);             // To be returned to
                                    //Need help creating mock ActionListener
       //sd.addActionListener(mockListener);
       //int a = sd.listeners.size();
       //System.out.println(a);  
    }

    /**
     * Test to check that the process method works as intended
     */
    @Test
    public void testProcess() {
            // To be returned to
            // Need help creating events to call and test the method
    }
    
}
