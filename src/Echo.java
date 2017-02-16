public class Echo {
    public static void main(String[] args) {
        final String FILENAME = "temp.wav";

        AudioInput.recordAudio(FILENAME);
        System.out.println(SpeechRecognition.getTextFromAudio(FILENAME));
    }
}
