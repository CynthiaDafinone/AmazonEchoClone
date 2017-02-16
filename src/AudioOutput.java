import java.io.File;
import java.io.ByteArrayOutputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

/**
 * Created by 650007903 on 15/02/2017.
 * Much of this code is based on David Wakeling's Workshops, modified to suit our purposes.
 */
public class AudioOutput {

    /**
     * Method to set up an AudioInputStream from a specified file
     * @param filename the filename
     * @return  the AudioInputStream
     */
    private static AudioInputStream setupStream(String filename ) {
        try {
            File file = new File(filename);
            AudioInputStream stm = AudioSystem.getAudioInputStream(file);
            return stm;
        } catch (Exception ex) {
            System.out.println(ex);
            System.exit(1);
            return null;
        }
    }


    /**
     * Method reads an AudioInputStream into a ByteArrayOutputStream
     * @param stm the AudioInputStream
     * @return the ByteArrayOutputStream
     */
    public static ByteArrayOutputStream readStream( AudioInputStream stm ) {
        try {
            AudioFormat           af  = stm.getFormat();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            int  bufferSize = (int) af.getSampleRate() * af.getFrameSize();
            byte buffer[]   = new byte[ bufferSize ];

            for (;;) {
                int n = stm.read( buffer, 0, buffer.length );
                if ( n > 0 ) {
                    bos.write( buffer, 0, n );
                } else {
                    break;
                }
            }

            return bos;
        } catch ( Exception ex ) {
            System.out.println( ex ); System.exit( 1 ); return null;
        }
    }

    /**
     * Method plays an audio stream
     * @param stm the AudioInputStream to play
     * @param bos the ByteArrayOutputStream
     */
    private static void playStream( AudioInputStream stm, ByteArrayOutputStream bos ) {
        try {
            AudioFormat    af   = stm.getFormat();
            byte[]         ba   = bos.toByteArray();
            DataLine.Info  info = new DataLine.Info( SourceDataLine.class, af );
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine( info );

            line.open( af );
            line.start();
            line.write( ba, 0, ba.length );
        } catch ( Exception ex ) {
            System.out.println( ex ); System.exit( 1 );
        }
    }

    /**
     * Method to play a sound from an AudioInputStream
     * @param stm the AudioInputStream to play from
     */
    static void playSound(AudioInputStream stm) {
        playStream( stm, readStream( stm ) );
    }


}
