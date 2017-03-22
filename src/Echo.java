import com.sun.org.apache.xpath.internal.SourceTree;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main class for the Echo
 * In future will call GUI builder and handle some events
 */
public class Echo implements ActionListener, LineListener {
    String FILENAME = "temp.wav";
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
        AudioOutput.addLineListener(this);
    }

    /**
     * Method to check for any ActionEvents and perform an action based upon them
     * @param e the ActionEvent to check
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("soundDetected") && gui.isPowered()) {
            // SoundRecordedEvent
            String str = SpeechToText.getTextFromAudio(FILENAME);
            System.out.println("Got a string as: " + str);
            // Checking that it is not returned as null
            if (str != null) {
                str = str.toLowerCase();
                // If there was an error connecting to Microsoft
                if (str.contains("alexa")){
                    if (str.equals("UnknownHostException")) {
                        AudioOutput.playSoundWithoutListeners(getClass().getClassLoader().getResourceAsStream("serverConnectionError.wav"));
                        return;
                    } else if (str.contains("timer")) {
                        EchoTimer.startTimer(str);
                        return;
                    } else if(str.contains("news")){
                        News.playNews();
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
                }
                
                str = str.replaceFirst("alexa", "");
                String result = Computational.getAnswer(str);
                System.out.println("Got an answer as: " + result);
                
                // If there was an error connecting to Wolfram
                if (result == null) {
                    AudioOutput.playSoundWithoutListeners(getClass().getClassLoader().getResourceAsStream("serverConnectionError.wav"));
                    return;
                }

                // Change into answer mode, say the answer 
                TextToSpeech.convertStringToSpeech(result);
            }
            
            else{
                AudioOutput.playSoundWithoutListeners(getClass().getClassLoader().getResourceAsStream("inputWasNull.wav"));
                gui.changeColor("Cyan");
                
            }
        }
    }

    @Override
    public void update(LineEvent event) {
        if (!gui.isPowered()) {
            return;
        }
        boolean shouldChangeColour = false;
        if (event.getSource() == AudioOutput.clip) {
            shouldChangeColour = true;
        }

        if (event.getType() == LineEvent.Type.START) {
            detector.pauseForAnswer();
            System.out.println("Paused audio detection");
            if (shouldChangeColour) {
                gui.changeColor("Blue");
            }
        }
        if (event.getType() == LineEvent.Type.STOP) {
            if (shouldChangeColour) {
                gui.changeColor("Cyan");
            }
            detector.resumeAfterAnswer();
            System.out.println("Resumed audio detection");
        }
    }
}
