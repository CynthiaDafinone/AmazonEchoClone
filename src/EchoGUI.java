
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class EchoGUI extends JFrame {

    private JFrame frame = new JFrame();
    private final PowerButton btnPOW = new PowerButton();
    private final MuteButton btnMUTE = new MuteButton();
    private final ListenButton btnLIST = new ListenButton();
    private final SoundDetector detector;
    private Thread detectorThread;
    private boolean isPowered = false;
    private boolean isPressed = false;
    private boolean listPressed = false;
    private  ScheduledExecutorService executorService;
    private int flashCount = 0;


    /*
    * Power button
     */
    private class PowerButton extends JButton {
        PowerButton() {
            setBorder(null);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    //runs this if echo is turned off and turns it on
                    if (!isPowered) {
                        System.out.println("TURNING ON");
                        isPowered = true;

                        detectorThread = new Thread(detector);
                        detectorThread.start();

                        AudioOutput.playSound(getClass().getClassLoader().getResourceAsStream("newStartSound.wav"));
                        changeColor("Cyan");


                        
                                  
                    } //runs this if echo is turned on and turns it off
                    else {
                        if (executorService != null) {
                            executorService.shutdown();
                        }
                        flashCount = 0;
                        System.out.println("TURNING OFF");
                        isPowered = false;
                        changeColor("Off");
                        AudioOutput.playSound(getClass().getClassLoader().getResourceAsStream("newOffSound.wav"));

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

        MuteButton() {
            setIcon(new ImageIcon("mute.png"));
            setBorder(null);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    if (isPowered) {

                        //Holds previous value after being switched off and on
                        if (isPressed) {
                            flashCount = 0;
                            System.out.println("Microphone activated");
                            AudioOutput.playSound(getClass().getClassLoader().getResourceAsStream("unmuted.wav"));
                            isPressed = false;
                            detector.enableMic();
                            detectorThread = new Thread(detector);
                            detectorThread.start();

                        } else {
                        flashCount = 0;
                            isPressed = true;
                            AudioOutput.playSound(getClass().getClassLoader().getResourceAsStream("muted.wav"));
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


    /**
     * Template for action button - functionality to be implemented in sprint 3
     */
    private class ListenButton extends JButton {

        ListenButton() {
            setBorder(null);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    if (isPowered) {
                        if (listPressed) {
                            System.out.println("Echo is listening");
                            changeColor("Flash");
                            listPressed = false;

                            //this button should probably do something
                        } else {

                            changeColor("Cyan");
                            listPressed = true;
                        }
                    }
                }
            });
        }
    }

    /**
     * Temporary method to report any errors
     */
    public void reportError() {
        //javascript style error message telling user server is down
    }

    //adds the three buttons onto the conent pane
    void addButtons() {
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

    /**
     * Method to change the color of the Echo's light
     * @param color the color to change it to - (Blue/Cyan/Off/Flash)
     */
    void changeColor(String color) {
        // options for color are Blue, Cyan, Flash and Off
        if (executorService != null) {
            executorService.shutdown();
        }
        if (color.equals("Flash")) {
            executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    Flash();
                }
            }, 0, 1, TimeUnit.SECONDS);
        } else {
            frame.setContentPane(new JLabel(new ImageIcon(getClass().getResource("echo" + color + ".png"))));
//            frame.setContentPane(new JLabel(new ImageIcon("resources/echo" + color + ".png")));
            frame.setLayout(null);
            frame.pack();
            addButtons();
        }
    }


    /**
     * Method to have the lights atop the echo to flash
     */
    void Flash() {
        if (flashCount % 2 == 0) {
            frame.setContentPane(new JLabel(new ImageIcon(getClass().getResource("echoCyanFlash.png"))));

//            frame.setContentPane(new JLabel(new ImageIcon("resources/echoCyanFlash.png")));
            frame.setLayout(null);
            frame.pack();
            addButtons();
            flashCount++;
        } else {
            frame.setContentPane(new JLabel(new ImageIcon(getClass().getResource("echoCyanFlash2.png"))));
//            frame.setContentPane(new JLabel(new ImageIcon("resources/echoCyanFlash2.png")));
            frame.setLayout(null);
            frame.pack();
            addButtons();
            flashCount++;
        }
    }

    /**
     * Constructor to set up the GUI
     * @param detector the SoundDetector to interact with when muting, etc.
     */
    EchoGUI(SoundDetector detector) {
        this.detector = detector;
        frame.setTitle("The Amazon Echo");
        changeColor("Off");
        frame.setLayout(null);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(738, 622);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
