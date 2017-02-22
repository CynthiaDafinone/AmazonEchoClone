import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class EchoGUI extends JFrame {

    JFrame frame = new JFrame();

    final PowerButton btnPOW = new PowerButton("POW");
    final MuteButton btnMUTE = new MuteButton("MUTE");
    final ListenButton btnLIST = new ListenButton("LIST");

    private AudioInputStream soundName;
    private Clip startingSound;
    boolean isPowered = false;
    boolean isPressed = false;
    

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

                        frame.setContentPane(new JLabel(new ImageIcon("../resources/echoBlue.png")));
                        frame.setLayout(null);
                        frame.pack();

                        //  startingSound.setFramePosition(0);
                        //  startingSound.start();
                        addButtons();

                    } //runs this if echo is turned on and turns it off
                    else {
                        System.out.println("TURNING OFF");
                        isPowered = false;

                        frame.setContentPane(new JLabel(new ImageIcon("../resources/echoOff.png")));
                        frame.setLayout(null);
                        frame.pack();

                        addButtons();
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
                            System.out.println("Microphone activated");
                            isPressed = false;
                            String[] args = {};
                            // Sound4.main(args);

                        } else {
                            System.out.println("Microphone muted");
                            isPressed = true;
                            String[] args = {};
                            // Sound3.main(args);

                            frame.setContentPane(new JLabel(new ImageIcon("../resources/echoBlue.png")));
                            frame.setLayout(null);
                            frame.pack();

                            addButtons();

                            //Stop any audio input
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

                        frame.setContentPane(new JLabel(new ImageIcon("../resources/echoCyan.png")));
                        frame.setLayout(null);
                        frame.pack();

                        addButtons();

                        //Forces Echo into listening mode, lights glow CYAN
                    }
                }
            });
        }
    }

    //adds the buttons back after background image is changed()
    public void addButtons() {
        //location and size of button - need to make transparent
        btnMUTE.setBounds(50, 120, 53, 30);
        (btnMUTE).setText("MUTE");
        frame.add(btnMUTE);
        btnPOW.setBounds(50, 180, 53, 30);
        (btnPOW).setText("POWER");
        frame.add(btnPOW);
        btnLIST.setBounds(50, 240, 53, 30);
        (btnLIST).setText("LIST");
        frame.add(btnLIST);
    }

    //initialize the gui
    public EchoGUI() {

        //unfinished 
        // try {
        //     soundName = AudioSystem.getAudioInputStream(new File("../resources/startSound.wav"));

        //     startingSound = AudioSystem.getClip();
        //     startingSound.open(soundName);
        // } catch (Exception e) {
        //     System.out.println("Failure to load sound");
        // }

        frame.setTitle("The Amazon Echo");
        frame.setContentPane(new JLabel(new ImageIcon("../resources/echoOff.png")));
        frame.setLayout(null);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(738, 622);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        addButtons();

    }

    //sets up frame and give certain values
    // public static void main(String[] argv) {
    //     new EchoGUI();
    // }
}