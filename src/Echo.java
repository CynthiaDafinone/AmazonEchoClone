import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main class for the Echo
 * In future will call GUI builder and handle some events
 */
public class Echo implements ActionListener {
    private final String FILENAME = "temp.wav";
    private final EchoGUI gui;
    private final SoundDetector detector;

    public static void main(String[] args) {
        Echo e = new Echo();        
    }

    /**
     * Method to create an Echo, instantiating the SoundDetector and GUI
     */
    private Echo() {
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
        
     //   gui.changeColor("Flash");
        
        if (e.getActionCommand().equals("soundDetected")) {
            // SoundRecordedEvent
            String str = SpeechToText.getTextFromAudio(FILENAME);
            System.out.println("Got a string as: " + str);
            // Checking that it is not returned as null
            if (str != null) {
                str = str.toLowerCase();
                // If there was an error connecting to Microsoft
                
                if (str.equals("UnknownHostException")) {
                    AudioOutput.playSound(getClass().getClassLoader().getResourceAsStream("serverConnectionError.wav"));
                    return;
                } else if (str.contains("timer")) {
                    EchoTimer.startTimer(str);
                    return;
                } else if (str.contains("stopwatch") || str.contains("stop watch")) {
                    if (str.contains("start")) {
                        EchoStopwatch.startStopwatch();
                        return;
                    }
                    // Space must be after stop to avoid 'stopwatch' triggering this
                    else if (str.contains("stop ")) {
                        EchoStopwatch.stopStopwatch();
                        return;
                    }
                }

                String result = Computational.getAnswer(str);
                System.out.println("Got an answer as: " + result);
                
                // If there was an error connecting to Wolfram
                if (result == null) {
                    detector.pauseForAnswer();
                    AudioOutput.playSound(getClass().getClassLoader().getResourceAsStream("serverConnectionError.wav"));
                    detector.resumeAfterAnswer();
                    return;
                }

                // Change into answer mode, say the answer 
                detector.pauseForAnswer();
                TextToSpeech.convertStringToSpeech(result);
                detector.resumeAfterAnswer();


                //need line listeners to make sure it doesn't record itself and it changes colors accurately
                gui.changeColor("Cyan");
            }
            
            else{
                AudioOutput.playSound(getClass().getClassLoader().getResourceAsStream("inputWasNull.wav"));
                gui.changeColor("Cyan");
                
            }
        }
    }
}
