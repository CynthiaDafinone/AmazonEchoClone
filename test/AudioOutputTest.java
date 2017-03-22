

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
 * 
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
            
        AudioOutput ao = new AudioOutput();
        //playSound test with AudioInputStream
        URL url = this.getClass().getClassLoader()
                .getResource("audio_output_test.wav");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                        
        ao.playSound(audioIn);
            
        //playSound test with InputStream
        InputStream inStream=this.getClass().getClassLoader()
                .getResourceAsStream("audio_output_test.wav");
        ao.playSound(inStream);
            
        //playSound test with String path
            
        }
    
    @Test(expected=UnsupportedAudioFileException.class)
    public void testPlaySound2() throws UnsupportedAudioFileException, IOException,
            LineUnavailableException{
            
        AudioOutput ao = new AudioOutput();
        
        URL url = this.getClass().getClassLoader()
                .getResource("audio_output_test.mp3");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            
        ao.playSound(audioIn);
    }
    
    @Test(expected=NullPointerException.class)
    public void testPlaySound3() throws UnsupportedAudioFileException, IOException,
            LineUnavailableException{
            
        AudioOutput ao = new AudioOutput();
        
        URL url = this.getClass().getClassLoader()
                .getResource("not_a_file.wav");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            
        ao.playSound(audioIn);
    }
    
    @Test //(expected=UnsupportedAudioFileException.class)
    public void testPlaySound4() throws UnsupportedAudioFileException, IOException,
            LineUnavailableException{
            
        AudioOutput ao = new AudioOutput();
            
        InputStream inStream=this.getClass().getClassLoader()
                .getResourceAsStream("audio_output_test.wav");
        ao.playSound(inStream);
        
    }
    
    @Test
    public void testPlaySoundWithoutListeners() throws UnsupportedAudioFileException, 
            IOException, LineUnavailableException{
        
        AudioOutput ao = new AudioOutput();
        
        URL url = this.getClass().getClassLoader()
                .getResource("audio_output_test.wav");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
          
        ao.playSoundWithoutListeners(audioIn);
            
        //playSound test with InputStream
        InputStream inStream=this.getClass().getClassLoader()
                .getResourceAsStream("audio_output_test.wav");
        ao.playSoundWithoutListeners(inStream);
    }
    
    @Test(expected=UnsupportedAudioFileException.class)
    public void testPlaySoundWithoutListeners2() throws UnsupportedAudioFileException, 
            IOException, LineUnavailableException{
        
        AudioOutput ao = new AudioOutput();
        
        URL url = this.getClass().getClassLoader().getResource("audio_output_test.mp3");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            
        ao.playSoundWithoutListeners(audioIn);
        
    }
    
//    @Test(expected=LineUnavailableException.class)
//    public void testPlaySoundWithoutListeners3() throws UnsupportedAudioFileException, 
//            IOException, LineUnavailableException{
//        
//            AudioOutput ao = new AudioOutput();
//        
//            URL url = this.getClass().getClassLoader().getResource("");
//            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
//            
//            ao.playSoundWithoutListeners(audioIn);
//        
//    }
    
//    @Test
//    public void testAddLineListener() throws LineUnavailableException{
//        
//        }
    
//    @Test
//    public void testRemoveLineListener(){
//        
//        }
//    
    @Test
    public void testStopAudio() throws LineUnavailableException, UnsupportedAudioFileException,
            IOException{
        AudioOutput ao = new AudioOutput();
        //playSound test with AudioInputStream
        URL url = this.getClass().getClassLoader()
                .getResource("audio_output_test.wav");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                        
        ao.playSound(audioIn);
        ao.stopAudio();
    }

    @Test
    public void testPlayLooping() throws InterruptedException{
        AudioOutput ao = new AudioOutput();
        
        //No idea how to stop this when test is run
        new Thread() {
            @Override
            public void run() {
                ao.playLooping(getClass().getResourceAsStream("audio_output_test.wav"));
            }
        }.start();
        Thread.sleep(1000);
        ao.stopAudio();
    }
}