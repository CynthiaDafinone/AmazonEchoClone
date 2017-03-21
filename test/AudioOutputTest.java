

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.File;
import javax.sound.sampled.*;
import java.io.*;
import java.net.URL;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 */
public class AudioOutputTest {
    
    public AudioOutputTest() {
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
    public void testPlaySound() throws UnsupportedAudioFileException, IOException,
            LineUnavailableException{
       
        
            
        }
    @Test
    public void testPlaySoundWithoutListeners(){
        
    }
    @Test
    public void testAddLineListener() throws LineUnavailableException{
        
        }
    
    @Test
    public void testRemoveLineListener(){
        
        }
    
    @Test
    public void testStopAudio(){
        
        }
    
    @Test
    public void testPlaySound2(){
        //
    }
    
    @Test
    public void testPlaySoundWithoutListeners2(){
        
    }
    
    @Test
    public void testPlaySound3(){
       AudioOutput.playSound("audio_output_test.wav");
    }
    
    @Test
    public void testPlayLooping(){
        
    }
}
