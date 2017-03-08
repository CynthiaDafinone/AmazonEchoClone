// TO BE FINISHED / WORK IN PROGRESS

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The test class for TextToSpeech. Contains three tests:
 * 
 * 1. Tests that the synthesizeSpeech method works as intended
 * 2. Tests that the writeData method works as intended
 * 3. Tests that the convertStringToSpeech method works as intended
 */
public class TextToSpeechTest {
    
    public TextToSpeechTest() {
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
     * Test to check that the synthesizeSpeech method works as intended TO BE FINISHED
     */
    @Test
    public void testSynthesizeSpeech() {
        System.out.println("synthesizeSpeech");
        String token = "";
        String text = "";
        String lang = "";
        String gender = "";
        String format = "";
        byte[] expResult = null;
        byte[] result = TextToSpeech.synthesizeSpeech(token, text, lang, gender, format);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test to check that the writeData method works as intended TO BE FINISHED
     */
    @Test
    public void testWriteData() {
        System.out.println("writeData");
        byte[] buffer = null;
        String name = "";
        TextToSpeech.writeData(buffer, name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test to check that the convertStringToSpeech method works as intended TO BE FINISHED
     */
    @Test
    public void testConvertStringToSpeech() {
        System.out.println("convertStringToSpeech");
        String text = "";
        String expResult = "";
        String result = TextToSpeech.convertStringToSpeech(text);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
