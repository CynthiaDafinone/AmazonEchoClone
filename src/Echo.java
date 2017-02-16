/**
 * Main class for the Echo
 * In future will call GUI builder and handle some events
 */
public class Echo {
    public static void main(String[] args) {
        final String FILENAME = "temp.wav";
        AudioInput.recordAudio(FILENAME);
        System.out.println(SpeechToText.getTextFromAudio(FILENAME));
    }
}
