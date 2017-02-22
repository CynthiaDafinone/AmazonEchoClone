import javax.sound.sampled.AudioInputStream;
import javax.xml.bind.SchemaOutputResolver;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main class for the Echo
 * In future will call GUI builder and handle some events
 */
public class Echo implements ActionListener {
    final String FILENAME = "temp.wav";

    public static void main(String[] args) {
        Echo e = new Echo();        
    }
    
    Echo() {
        SoundDetector detector = new SoundDetector();
        EchoGUI gui = new EchoGUI(detector);
        detector.setUpDetection();
        detector.addActionListener(this);
    }   

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("soundDetected")) {
            System.out.println("called event");
            // SoundRecordedEvent
            String str = SpeechToText.getTextFromAudio(FILENAME);
            // Checking that it is not returned as null
            if (str != null) {
                String result = Computational.getAnswer(str);
                TextToSpeech.convertStringToSpeech(result);
            }
        }
    }
}
