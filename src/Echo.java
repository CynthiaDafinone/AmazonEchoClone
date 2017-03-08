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
        detector.registerRecordingListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("soundDetected")) {
            // SoundRecordedEvent
            String str = SpeechToText.getTextFromAudio(FILENAME);
            System.out.println("Got a string as: " + str);
            // Checking that it is not returned as null
            if (str != null) {
                String result = Computational.getAnswer(str);
                System.out.println("Got an answer as: " + result);
                if (result == null) {
                    return;
                }
                TextToSpeech.convertStringToSpeech(result);
            }
        }
    }
}
