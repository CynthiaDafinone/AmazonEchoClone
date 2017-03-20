/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lewis
 */
public class SoundRecordedEventTest {
    
    public SoundRecordedEventTest() {
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

    @Test
    public void testSomeMethod() {
        SoundDetector sd = new SoundDetector();
        
        SoundRecordedEvent event1 = new SoundRecordedEvent(sd, 1, "soundDetected");
        assertNotNull("event1 should not be null", event1);
    }
    
}
