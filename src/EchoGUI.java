import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.util.concurrent.TimeUnit;

public class EchoGUI extends JFrame {

    JFrame frame = new JFrame();
    final PowerButton btnPOW = new PowerButton("POW");
    final MuteButton btnMUTE = new MuteButton("MUTE");
    final ListenButton btnLIST = new ListenButton("LIST");
    final private SoundDetector detector;
    private Thread detectorThread;
    boolean isPowered = false;
    boolean isPressed = false;
    ScheduledExecutorService executorService;
    int flashCount = 0;


    /*
    * Power button
     */
    private class PowerButton extends JButton {
        

        PowerButton(String s) {
            setBorder(null);
            addMouseListener(new MouseAdapter() {

                public void mouseClicked(MouseEvent me) {
                    //runs this if echo is turned off and turns it on
                    if (isPowered == false) {
                        System.out.println("TURNING ON");
                        isPowered = true;

                        detectorThread = new Thread(detector);
                        detectorThread.start();

                        AudioOutput.playSound(getClass().getResourceAsStream("newStartSound.wav"));

                        changeColor("flash");


                        
                                  
                    } //runs this if echo is turned on and turns it off
                    else {
                        executorService.shutdown();
                        flashCount = 0;
                        System.out.println("TURNING OFF");
                        isPowered = false;
                        changeColor("Off");
                        AudioOutput.playSound(getClass().getResourceAsStream("newOffSound.wav"));


                        try {
                            detector.disableMic();
                            detectorThread.join();
                        } catch (InterruptedException e) {
                            // Should not be called
                            System.exit(1);
                        }
                    }
                }
            });
        }
    }


    /*
    * Mute Button
     */
    private class MuteButton extends JButton {

        MuteButton(String s) {
            setIcon(new ImageIcon("mute.png"));
            setBorder(null);
            addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent me) {
                    if (isPowered) {

                        //Holds previous value after being switched off and on
                        if (isPressed) {
                            executorService.shutdown();
                             flashCount = 0;
                            System.out.println("Microphone activated");
                            AudioOutput.playSound(getClass().getResourceAsStream("unmuted.wav"));
                            isPressed = false;
                            detector.enableMic();
                            detectorThread = new Thread(detector);
                            detectorThread.start();
                            changeColor("flash");

                        } else {
                            executorService.shutdown();
                        flashCount = 0;
                            isPressed = true;
                            changeColor("Blue");
                            AudioOutput.playSound(getClass().getResourceAsStream("muted.wav"));
                            detector.disableMic();
                            try {
                                detectorThread.join();
                            } catch (InterruptedException e) {
                                System.out.println("InterruptedException - this should not have happened.");
                                e.printStackTrace();
                                System.exit(1);
                            } finally {
                                System.out.println("Disabled all microphone input.");
                            }
                            //STOP AUDIO INPUT
                        }
                    }
                }
            });
        }
    }


    /*
  * Force Listen button
     */
    private class ListenButton extends JButton {

        ListenButton(String s) {
            setBorder(null);
            addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent me) {
                    if (isPowered) {
                        System.out.println("Echo is listening");
                        changeColor("Cyan");

                        //this button should probably do something
                    }
                }
            });
        }
    }

    public void reportError() {
        //javascript style error message telling user server is down
    }

    //adds the three buttons onto the conent pane
    public void addButtons() {
        btnMUTE.setOpaque(false);
        btnMUTE.setContentAreaFilled(false);
        btnMUTE.setBorderPainted(false);
        btnMUTE.setBounds(301, 28, 30, 15);
        frame.add(btnMUTE);

        btnPOW.setOpaque(false);
        btnPOW.setContentAreaFilled(false);
        btnPOW.setBorderPainted(false);
        btnPOW.setBounds(350, 244, 30, 30);
        frame.add(btnPOW);

        btnLIST.setOpaque(false);
        btnLIST.setContentAreaFilled(false);
        btnLIST.setBorderPainted(false);
        btnLIST.setBounds(401, 28, 30, 15);
        frame.add(btnLIST);
    }

    public void changeColor(String color) {
        // options for color are Blue, Cyan, and Off
        if (color.equals("flash")) {
            executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    Flash();
                }
            }, 0, 1, TimeUnit.SECONDS);
        } else {
            frame.setContentPane(new JLabel(new ImageIcon("resources/echo" + color + ".png")));
            frame.setLayout(null);
            frame.pack();
            addButtons();
        }
    }

    
    

    public void Flash() {
        if (flashCount % 2 == 0) {
           
            frame.setContentPane(new JLabel(new ImageIcon("resources/echoCyanFlash.png")));
            frame.setLayout(null);
            frame.pack();
            addButtons();
            flashCount++;
        } else {
            frame.setContentPane(new JLabel(new ImageIcon("resources/echoCyanFlash2.png")));
             
            frame.setLayout(null);
            frame.pack();
            addButtons();
            flashCount++;
        }
    }

    //constructs frame and sets other things up
    public EchoGUI(SoundDetector detector) {
        this.detector = detector;
        frame.setTitle("The Amazon Echo");
        changeColor("Off");
        frame.setLayout(null);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(738, 622);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
