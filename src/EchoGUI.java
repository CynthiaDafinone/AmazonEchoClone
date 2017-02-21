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

    final PowerButton power = new PowerButton("POW");
    JFrame frame = new JFrame();
    private AudioInputStream soundName;
    private Clip startingSound;
    private Boolean powerMode = false;

    ////incomplete code for implementaion of power button
    private class PowerButton extends JButton {
        
        PowerButton(String s) {
            setBorder(null);
            addMouseListener(new MouseAdapter() {

                public void mouseClicked(MouseEvent me) {
                    //runs this if echo is turned off and turns it on
                    if (powerMode == false) {
                        System.out.println("TURNING ON");
                        powerMode = true;
                        
                        frame.setContentPane(new JLabel(new ImageIcon("../resources/echoBlue.png")));
                        frame.setLayout(null);
                        frame.pack();

                       // startingSound.setFramePosition(0);
                       //-Enginee startingSound.start();

                        addButtons();

                    } //runs this if echo is turned on and turns it off
                    else {
                        System.out.println("TURNING OFF");
                        powerMode = false;
                        
                        frame.setContentPane(new JLabel(new ImageIcon("../resources/echoOff.png")));
                        frame.setLayout(null);
                        frame.pack();
                        
                        addButtons();
                    }
                }
            });
        }
    }

    public void addButtons() {
        //location of button - need to make transparent
        power.setBounds(210, 260, 40, 40);
        frame.add(power);
    }


    public EchoGUI() {
        
        //loads a wav file
        try {
            soundName = AudioSystem.getAudioInputStream(new File("../resources/startSound.wav"));

            startingSound = AudioSystem.getClip();
            startingSound.open(soundName);
        } catch (Exception e) {
            System.out.println("Failure to load sound");
        }

        frame.setTitle("The Amazon Echo");
        frame.setContentPane(new JLabel(new ImageIcon("../resources/echoOff.png")));
        frame.setLayout(null);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(770, 620);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.setVisible(true);

        addButtons();

    }

    //sets up frame and give certain values
    public static void main(String[] argv) {
        new EchoGUI();
    }
}
