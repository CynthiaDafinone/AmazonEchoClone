import javax.sound.sampled.*;
import java.io.*;

class AudioOutput {
    static void playSound (AudioInputStream ais) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        } catch (IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    static void playSound(InputStream is) {
        try {
            BufferedInputStream bis = new BufferedInputStream(is);
            AudioInputStream ais = AudioSystem.getAudioInputStream(bis);
            playSound(ais);
        } catch (UnsupportedAudioFileException | IOException e) {
            System.out.println("There was an error outputting the audio.");
            e.printStackTrace();
            System.exit(1);
        }
    }

    static void playSound(String path) {
        try {
            File file = new File(path);
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            playSound(ais);
        } catch (FileNotFoundException e ){
            System.out.println("The file given was not found.");
            e.printStackTrace();
            System.exit(1);
        } catch (UnsupportedAudioFileException e) {
            System.out.println("The filetype found was not supported.");
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            System.out.println("There was an IOException");
            e.printStackTrace();
            System.exit(1);
        }
    }
}