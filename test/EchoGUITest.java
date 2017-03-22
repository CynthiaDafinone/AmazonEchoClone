/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.AWTException;
import static java.awt.Color.cyan;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import static java.awt.event.MouseEvent.MOUSE_CLICKED;
import javax.swing.JButton;
import org.junit.After;
//import javax.swing.WindowConstants;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cynthia
 */
public class EchoGUITest {

    private static EchoGUI testgui;
    private SoundDetector sounddetector;
    private static Robot robot;
    private Thread detectorThread;
    
    
    
    //use reflection to access inner button classses
    
    
    
    
    public EchoGUITest() {
        sounddetector = new SoundDetector();
        testgui = new EchoGUI(sounddetector);
       // detectorThread = testgui.detectorThread;
        
        
        
    }
    
    @BeforeClass
    public static void setUpClass() throws AWTException {
        robot = new Robot();
        EchoGUITest guitest;
        
        
        //frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
       
       
       
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }

    /**
     * Test of addButtons method, of class EchoGUI.
     * 
     */
    public void click(JButton button){
        int x;
        int y;
        x = button.getX();
        y = button.getY();
        MouseEvent me = new MouseEvent(button,MouseEvent.MOUSE_CLICKED,0,0,x,y,1,false);
        button.dispatchEvent(me);
    }
    public void press(JButton button){
        int x;
        int y;
        x = button.getX();
        y = button.getY();
        MouseEvent me = new MouseEvent(button,MouseEvent.MOUSE_PRESSED,0,0,x,y,1,false);
        button.dispatchEvent(me);
    }
    public void release(JButton button){
        int x;
        int y;
        x = button.getX();
        y = button.getY();
        MouseEvent me = new MouseEvent(button,MouseEvent.MOUSE_RELEASED,0,0,x,y,1,false);
        button.dispatchEvent(me);
    
    
    }
    
    
    
    @Test 
    public void testPowerButton() throws InterruptedException{
       
        
        
        
        
        assertFalse(testgui.isPowered());
        click(testgui.btnPOW);
        /*checks the echo is turned on 
        after the power button is clicked */
        assertTrue(testgui.isPowered());
        assertTrue(sounddetector.running);
        Thread.sleep(2000);
        //testgui.detectorThread;
        boolean alive = testgui.detectorThread.isAlive();
        
        
        assertTrue(alive);
        
        
       
    }
       
    @Test
    public void testMuteButton() throws InterruptedException{
        
        
        
        //click(testgui.btnPOW);
        testgui.isPowered = true;
        testgui.isPressed = false;
       
       
        
        press(testgui.btnMUTE);
        //assertTrue(testgui.isPressed);
        boolean pressed = testgui.btnMUTE.getModel().isEnabled();
        assertTrue(pressed);
        System.out.println(pressed);
        assertEquals(testgui.flashCount,0);
        assertFalse(sounddetector.running);
        
        release(testgui.btnMUTE);
        
       //----------------------------------------------------
       //checks if thread has been started
       /* testgui.detectorThread.sleep(2000);
        boolean alive =  testgui.detectorThread.isAlive();
        assertTrue(alive);*/
        
        
    }
    
    @Test 
    public void testListenButton(){
        testgui.isPowered = true;
        testgui.isPressed = false;
       
       
        /*Unable to press buttons cant test functionality*/
        press(testgui.btnLIST);
        //assertTrue(testgui.isPressed);
        boolean pressed = testgui.btnLIST.getModel().isEnabled();
        assertTrue(pressed);
        System.out.println(pressed);
        //assertFalse(EchoTimer.shouldPlay);
       //makes sure the timer has stopped -- error
       
        
    
    }
    
    @After
    public void tearDown() {
       testgui = null;
       sounddetector = null;
    }
    
            
    @Test
    public void testAddButtons() {
        System.out.println("addButtons");
        
        int x;
        int y;
        JButton m;
        JButton p;
        JButton l;
        m = testgui.btnMUTE;
        p = testgui.btnPOW;
        l = testgui.btnLIST;
        
        assertNotNull(m);
        assertNotNull(p);
        assertNotNull(l);
        
        x = m.getX();
        y = m.getY();
        assertEquals(x,301);
        assertEquals(y,28);
        
        x = p.getX();
        y = p.getY();
        assertEquals(x,350);
        assertEquals(y,244);
        
        x = l.getX();
        y = l.getY();
        assertEquals(x,401);
        assertEquals(y,28);
        
    }

    /**
     * Test of changeColor method, of class EchoGUI.
     */
    /*@Test
    public void testChangeColor() {
        System.out.println("changeColor");
        String colour = "";
        EchoGUI instance = null;
        instance.changeColor(colour);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of Flash method, of class EchoGUI.
     */
   /* @Test
    public void testFlash() {
        System.out.println("Flash");
        EchoGUI instance = null;
        instance.Flash();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    } */
    @Test
    public void testIsPowered(){
        assertFalse(testgui.isPowered());
        click(testgui.btnPOW);
        assertTrue(testgui.isPowered());
    }

   
    
}


