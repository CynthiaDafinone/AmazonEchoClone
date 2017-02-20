import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.SilenceDetector;
import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.beatroot.EventList;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;

import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.EventListener;

/**
 * Created by 650007903 on 16/02/2017.
 * Class uses the TarsosDSP Library to detect any sounds and deals with this
 */
public class SoundDetector implements AudioProcessor {
    private static final int     TIMER           = 5;     /* secs */
    private static final String  FILENAME        = "temp.wav";
    private static final int     SAMPLE_RATE     = 16000; /* MHz  */
    private static final int     SAMPLE_SIZE     = 16;    /* bits */
    private static final int     SAMPLE_CHANNELS = 1;     /* mono */

    private ArrayList<ActionListener> listeners = new ArrayList<>();

    private SilenceDetector silenceDetector;
    private AudioInputStream ais;
    private AudioDispatcher dispatcher;

    /* TODO: Currently this threshold is hardcoded and works for testing purposes only
       We must add another function to detect the background noise and put this threshold
       above it on order to make it work correctly */
    private double threshold = -100;
    // double threshold = SilenceDetector.DEFAULT_SILENCE_THRESHOLD;

    void setUpDetection() {
        try {
            int bufferSize = 512;

            AudioFormat format = new AudioFormat(SAMPLE_RATE, SAMPLE_SIZE, SAMPLE_CHANNELS, true, true);
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();

            ais = new AudioInputStream(line);
            JVMAudioInputStream stream = new JVMAudioInputStream(ais);
            dispatcher = new AudioDispatcher(stream, bufferSize, 0);


            silenceDetector = new SilenceDetector(threshold, false);
            dispatcher.addAudioProcessor(silenceDetector);
            dispatcher.addAudioProcessor(this);

            new Thread(dispatcher, "Audio Dispatcher").start();

        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Method to convert an AudioInputStream into a ByteArrayOutputStream (read the stream)
     * @param stm the AudioInputStream
     * @return the ByteArrayOutputStream
     */
    private ByteArrayOutputStream readStream(AudioInputStream stm ) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            int  bufferSize = SAMPLE_RATE * stm.getFormat().getFrameSize();
            byte buffer[]   = new byte[ bufferSize ];

            int counter = TIMER;
            while (counter > 0 && silenceDetector.currentSPL() > threshold) {
                counter--;
                int n = stm.read( buffer, 0, buffer.length );
                if ( n > 0 ) {
                    bos.write( buffer, 0, n );
                } else {
                    break;
                }
            }

            return bos;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
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

    boolean addActionListener(ActionListener l) {
        return listeners.add(l);
    }


    @Override
    public boolean process(AudioEvent audioEvent) {
        if (silenceDetector.currentSPL() > threshold) {
            recordSound(FILENAME, readStream(ais));
        }
        SoundRecordedEvent e = new SoundRecordedEvent(this, 1, "soundDetected");
        for (ActionListener l : listeners) {
            l.actionPerformed(e);
        }
        return true;
    }

    @Override
    public void processingFinished() {

    }



}
