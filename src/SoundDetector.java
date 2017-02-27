//import javafx.scene.media.MediaPlayer;
//
//import javax.sound.sampled.*;
//import java.awt.event.ActionListener;
//import java.io.*;
//import java.util.ArrayList;
//
///**
// * Created by 650007903 on 16/02/2017.
// */
//public class SoundDetector {
//    private static final int     TIMER           = 5;     /* secs */
//    private static final String  FILENAME        = "temp.wav";
//    private static final int     SAMPLE_RATE     = 16000; /* MHz  */
//    private static final int     SAMPLE_SIZE     = 16;    /* bits */
//    private static final int     SAMPLE_CHANNELS = 1;     /* mono */
//
//    ArrayList<ActionListener> listeners = new ArrayList<>();
//
//    AudioInputStream ais;
//    boolean micEnabled = true;
//
//    /* TODO: Currently this threshold is hardcoded and works for testing purposes only
//       We must add another function to detect the background noise and put this threshold
//       above it on order to make it work correctly */
//    private double threshold = -100;
//
//    void setUpDetection() {
//        try {
//            AudioFormat format = new AudioFormat(SAMPLE_RATE, SAMPLE_SIZE, SAMPLE_CHANNELS, true, true);
//            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
//            TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
//            line.open(format);
//            line.start();
//
//            ais = new AudioInputStream(line);
//
//        } catch (IllegalArgumentException e) {
//            System.out.println("There was an error detecting your microphone - are you sure it's plugged in?");
//            System.exit(1);
//        } catch (LineUnavailableException e) {
//            e.printStackTrace();
//            System.exit(1);
//        }
//    }
//
//    /**
//     * Method to convert an AudioInputStream into a ByteArrayOutputStream (read the stream)
//     * @param stm the AudioInputStream
//     * @return the ByteArrayOutputStream
//     */
//    ByteArrayOutputStream readStream(AudioInputStream stm) {
//        try {
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//
//            int  bufferSize = SAMPLE_RATE * stm.getFormat().getFrameSize();
//            byte buffer[]   = new byte[ bufferSize ];
//
//            int counter = TIMER;
//            while (counter > 0) {
//                counter--;
//                int n = stm.read( buffer, 0, buffer.length );
//                if ( n > 0 ) {
//                    bos.write( buffer, 0, n );
//                } else {
//                    break;
//                }
//            }
//            return bos;
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.exit(1);
//            return null;
//        }
//    }
//
//    boolean detectAudio(AudioInputStream stm) {
//        try {
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            int bufferSize = SAMPLE_SIZE * stm.getFormat().getFrameSize() / 2;
//            byte buffer[] = new byte[bufferSize];
//            while (true) {
//                int n = stm.read(buffer, 0, buffer.length);
//                if (n > 0) {
//                    bos.write(buffer, 0, n);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.exit(1);
//            return false;
//        }
//    }
//
//    /**
//     * Method to record sound and write to a file
//     * @param name the filename to write to
//     * @param bos the ByteArrayOutputStream to read from
//     */
//    static void recordSound(String name, ByteArrayOutputStream bos ) {
//
//
////        try {
////            AudioFormat af =
////                    new AudioFormat(SAMPLE_RATE
////                            , SAMPLE_SIZE
////                            , SAMPLE_CHANNELS
////                            , true /* signed */
////                            , true /* little-endian */
////                    );
////            byte[] ba = bos.toByteArray();
////            InputStream is = new ByteArrayInputStream(ba);
////            AudioInputStream ais = new AudioInputStream(is, af, ba.length);
////            File file = new File(name);
////            AudioSystem.write(ais, AudioFileFormat.Type.WAVE, file);
////        } catch (IOException e) {
////            e.printStackTrace();
////            System.out.println(e.getMessage());
////            System.exit(1);
////        }
//    }
//
//    /**
//     * Method to register as an action listener for when a sound has been recorded
//     * @param l The action listener to be registered
//     * @return True if successful
//     */
//    boolean addActionListener(ActionListener l) {
//        return listeners.add(l);
//    }
//
//
//    /**
//     * Method to disable the microphone
//     */
//    void disableMic() {
//        micEnabled = false;
//    }
//
//    /**
//     * Method to enable the microphone
//     */
//    void enableMic() {
//        micEnabled = true;
//    }
//}
