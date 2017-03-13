import javax.sound.sampled.*;
import java.io.*;

class AudioOutput {
    /**
     * Method to play the sound from an AudioInputStream
     * @param ais the stream to play from
     */
    static void playSound (AudioInputStream ais) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        } catch (IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to play sound from an InputStream
     * @param is the InputStream to play from
     */
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

    /**
     * Method to play sound from a filepath
     * @param path the filepath to check for the audio in
     */
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

    static void playLooping(InputStream is) {
        try {
            BufferedInputStream bis = new BufferedInputStream(is);
            AudioInputStream ais = AudioSystem.getAudioInputStream(bis);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            while(EchoTimer.shouldPlay()) {
                Thread.sleep(10);
            }
            clip.stop();
        } catch (UnsupportedAudioFileException | LineUnavailableException | InterruptedException | IOException e) {
            System.out.println("There was an error outputting the audio.");
            e.printStackTrace();
            System.exit(1);
        }
    }
}