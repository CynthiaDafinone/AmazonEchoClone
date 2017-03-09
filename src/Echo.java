import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main class for the Echo
 * In future will call GUI builder and handle some events
 */
public class Echo implements ActionListener {
    final String FILENAME = "temp.wav";
    final EchoGUI gui;
    final SoundDetector detector;

    public static void main(String[] args) {
        Echo e = new Echo();        
    }

    /**
     * Method to create an Echo, instantiating the SoundDetector and GUI
     */
    Echo() {
        detector = new SoundDetector();
        gui = new EchoGUI(detector);
        detector.registerRecordingListener(this);
    }

    /**
     * Method to check for any ActionEvents and perform an action based upon them
     * @param e the ActionEvent to check
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("soundDetected")) {
            // SoundRecordedEvent
            String str = SpeechToText.getTextFromAudio(FILENAME);
            System.out.println("Got a string as: " + str);
            // Checking that it is not returned as null
            if (str != null) {
                // If there was an error connecting to Microsoft
                if (str.equals("UnknownHostException")) {
                    AudioOutput.playSound(getClass().getClassLoader().getResourceAsStream("serverConnectionError.wav"));
                    return;
                }
                String result = Computational.getAnswer(str);
                System.out.println("Got an answer as: " + result);
                // If there was an error connecting to Wolfram
                if (result == null) {
                    AudioOutput.playSound(getClass().getClassLoader().getResourceAsStream("serverConnectionError.wav"));
                    return;
                }

                // Change into answer mode, say the answer
                gui.changeColor("Blue");
                detector.pauseForAnswer();
                TextToSpeech.convertStringToSpeech(result);
                detector.resumeAfterAnswer();


                // Next sprint we shall add Clip listeners to ensure this colour change is after the speech has finished
                gui.changeColor("Cyan");
            }
        }
    }
}
