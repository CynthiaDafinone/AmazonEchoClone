import java.io.*;
import javax.sound.sampled.*;

/**
 * Created by 650007903 on 15/02/2017.
 * Much of this code is based on David Wakeling's Workshops, modified to suit our purposes.
 */
class AudioInput {
    private static final int     TIMER           = 5;     /* secs */
    private static final int     SAMPLE_RATE     = 16000; /* MHz  */
    private static final int     SAMPLE_SIZE     = 16;    /* bits */
    private static final int     SAMPLE_CHANNELS = 1;     /* mono */

    /**
     * Method to set up an audio stream from the default microphone
     * @return an AudioInputStream leading to the microphone
     */
    private static AudioInputStream setupStream() {
        try {
            AudioFormat af =
                    new AudioFormat( SAMPLE_RATE
                            , SAMPLE_SIZE
                            , SAMPLE_CHANNELS
                            , true /* signed */
                            , true /* little-endian */
                    );
            DataLine.Info    info = new DataLine.Info( TargetDataLine.class, af );
            TargetDataLine   line = (TargetDataLine) AudioSystem.getLine( info );
            AudioInputStream stm  = new AudioInputStream( line );
            line.open( af );
            line.start();
            return stm;
        } catch ( Exception ex ) {
            System.out.println( ex ); System.exit( 1 ); return null;
        }
    }

    /**
     * Method to convert an AudioInputStream into a ByteArrayOutputStream (read the stream)
     * @param stm the AudioInputStream
     * @return the ByteArrayOutputStream
     */
    private static ByteArrayOutputStream readStream( AudioInputStream stm ) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            int  bufferSize = SAMPLE_RATE * stm.getFormat().getFrameSize();
            byte buffer[]   = new byte[ bufferSize ];

            for ( int counter = TIMER; counter > 0; counter-- ) {
                int n = stm.read( buffer, 0, buffer.length );
                if ( n > 0 ) {
                    System.out.println();
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
     * Method to record sound and write to a file
     * @param name the filename to write to
     * @param bos the ByteArrayOutputStream to read from
     */
    private static void recordSound(String name, ByteArrayOutputStream bos ) {
        try {
            AudioFormat af =
                    new AudioFormat(SAMPLE_RATE
                            , SAMPLE_SIZE
                            , SAMPLE_CHANNELS
                            , true /* signed */
                            , true /* little-endian */
                    );
            byte[] ba = bos.toByteArray();
            InputStream is = new ByteArrayInputStream(ba);
            AudioInputStream ais = new AudioInputStream(is, af, ba.length);
            File file = new File(name);
            AudioSystem.write(ais, AudioFileFormat.Type.WAVE, file);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Method to record audio and write to a file
     * @param name the filename to write to
     */
    static void recordAudio(String name) {
        ByteArrayOutputStream in = readStream(setupStream());
        recordSound(name, in);
    }
}
