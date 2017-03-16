// TO BE FINISHED / WORK IN PROGRESS

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The test class for SpeechToText. Contains three tests:
 * 
 * 1. Tests that the recognizeSpeech method works as intended !!NOT WORKING
 * 2. Tests that the readData method works as intended
 * 3. Tests that the getTextFromAudio method works as intended
 */
public class SpeechToTextTest {
    
    public SpeechToTextTest() {
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
     * Test to check that the recognizeSpeech method works as intended
     */
    @Test
    public void testRecognizeSpeech() throws Exception {  
        final String tokenTest = HTTPConnect.renewAccessToken();
        String nameTest = "christmas.wav";
        byte[] bodyTest = SpeechToText.readData(nameTest);
        String result = SpeechToText.recognizeSpeech(tokenTest, bodyTest);
        System.out.print(result);
    }

     /**
     * Test to check that the readData method works as intended
     */
    @Test
    public void testReadData() {
        String nameTest = "christmas.wav";
        
        byte[] output = SpeechToText.readData(nameTest);

        assertNotNull(output);
        

    }

     /**
     * Test to check that the getTextFromAudio method works as intended
     */
    @Test
    public void testGetTextFromAudio() {
//        System.out.println("getTextFromAudio");
//        String filename = "";
//        String expResult = "";
//        String result = SpeechToText.getTextFromAudio(filename);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
