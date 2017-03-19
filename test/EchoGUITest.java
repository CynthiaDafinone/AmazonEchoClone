/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cynthia
 */
public class EchoGUITest {

    private static EchoGUI frame;
    private final SoundDetector sounddetector;
    private final  EchoGUI.PowerButton btnPOW;
    private final EchoGUI.MuteButton btnMUTE;
    private final EchoGUI.ListenButton btnLIST;
    private static Robot robot;
    private boolean isPowered;
    private boolean isPressed;
    private boolean listPressed;
    private int flashCount;
    
    
    
    //use reflection to access inner button classses
    
    
    
    
    public EchoGUITest() {
        
        //create guiframe mock object
        sounddetector = new SoundDetector();
        frame = new EchoGUI(sounddetector);
        
        //mock power button
        btnPOW = this.frame.new PowerButton();
        btnMUTE = this.frame.new MuteButton();
        btnLIST = this.frame.new ListenButton();
        frame.addButtons();
        
        
        
        
    }
    
    @BeforeClass
    public static void setUpClass() throws AWTException {
        EchoGUITest guitest = new EchoGUITest();
        robot = new Robot();
        EchoGUITest.frame.repaint();
       
       
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of addButtons method, of class EchoGUI.
     * 
     */
    public void click(){
        robot.mousePress(InputEvent.BUTTON1_MASK);
    }
    
    @Test 
    public void testPowerButton(){
         /*Checks if power button is off, 
        if its off it switches on the echo*/
         
         
    }
    
    @Test
    public void testMuteButton(){
        
    }
    
    @Test 
    public void testListenButton(){
    }
            
    @Test
    public void testAddButtons() {
        System.out.println("addButtons");
       
        EchoGUI instance = this.frame;
        //instance.addButtons();
        
        
        Rectangle muteBounds = btnMUTE.getBounds();
        Rectangle test1Bounds = new Rectangle(301, 28, 30, 15);
        assertEquals(muteBounds,test1Bounds);
        
        Rectangle powBounds = btnPOW.getBounds();
        Rectangle test2Bounds = new Rectangle(350, 244, 30, 30);
        assertEquals(powBounds,test2Bounds);
        
        Rectangle listBounds = btnLIST.getBounds();
        Rectangle test3Bounds = new Rectangle(401, 28, 30, 15);
        assertEquals(listBounds,test3Bounds);
        /* Why rectangles returning 0, add assert for boolean */
    }

    /**
     * Test of changeColor method, of class EchoGUI.
     */
    @Test
    public void testChangeColor() {
        System.out.println("changeColor");
        String colour = "";
        EchoGUI instance = null;
        instance.changeColor(colour);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Flash method, of class EchoGUI.
     */
    @Test
    public void testFlash() {
        System.out.println("Flash");
        EchoGUI instance = null;
        instance.Flash();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    @Test
    public void testIsPowered(){
        EchoGUI instance = this.frame;
        /*gui is open but on button is not clicked*/
        assertFalse(instance.isPowered());
        /*gui is open button has been clicked*/
        assertTrue(instance.isPowered());
    }
    
}


