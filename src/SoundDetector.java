import javax.sound.sampled.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by 650007903 on 27/02/2017.
 */
public class SoundDetector implements Runnable {
    private static final int     TIMER           = 5;     /* secs */
    private static final String  FILENAME        = "temp.wav";

    // For now = 0.1, will change later to dynamically adapt
    private static volatile float THRESHOLD;
    private static final int     SAMPLE_RATE     = 16000; /* MHz  */
    private static final int     SAMPLE_SIZE     = 16;    /* bits */
    private static final int     SAMPLE_CHANNELS = 1;     /* mono */
    private AudioFormat format = new AudioFormat(SAMPLE_RATE, SAMPLE_SIZE, SAMPLE_CHANNELS, true, true);
    private AudioInputStream ais;
    private SoundDetectionThread soundDetector;

    private ArrayList<ActionListener> listeners= new ArrayList<>();

    private boolean running;
    private TargetDataLine line;


    /**
     * Method to calibrate the microphone's threshold to just above the background RMS level
     * @param detector the SoundDetectionThread used to check for incoming audio
     */
    private void calibrateMic(SoundDetectionThread detector) {
        // This is the lowest level possible
        THRESHOLD = -1f;
        while (detector.soundDetected()) {
            THRESHOLD += 0.05f;
        }
        THRESHOLD += 0.10f;
        System.out.println("Calibrated the threshold as " + THRESHOLD);
    }

    @Override
    /**
     * Run method of the thread, will listen for audio whilst in listening mode & record audio if it hears anything
     */
    public void run() {
        running = true;
        try {
            final int bufferSize = format.getFrameSize() * SAMPLE_RATE;

            line = AudioSystem.getTargetDataLine(format);
            line.open(format, bufferSize);
            line.start();

            ais = new AudioInputStream(line);
            soundDetector = new SoundDetectionThread(line, bufferSize);
            soundDetector.start();
            calibrateMic(soundDetector);
            System.out.println("Started silenceDetector");

            while (running) {
                try {
                    if (soundDetector.soundDetected()) {
                        System.out.println("Detected Audio, starting recording..");
                        startRecording();
                    }
                    Thread.currentThread().sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted exception - this shouldn't have happened.");
                    e.printStackTrace();
                    System.exit(1);
                }

            }

        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            line.close();
        }
    }

    /**
     * Method to record a given amount of audio (TIMER) and store it to a file (FILENAME) as a wave file.
     */
    private void startRecording() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            int bufferSize = SAMPLE_RATE * ais.getFormat().getFrameSize();
            byte buffer[] = new byte[bufferSize];

            int counter = TIMER;
            while (counter > 0 || soundDetector.soundDetected()) {
                counter--;
                int n = ais.read(buffer, 0, buffer.length);
                if (n > 0) {
                    bos.write(buffer, 0, n);
                } else {
                    break;
                }
            }

            byte[] ba = bos.toByteArray();
            InputStream is = new ByteArrayInputStream(ba);
            AudioInputStream ais = new AudioInputStream(is, format, ba.length);
            File file = new File(FILENAME);
            AudioSystem.write(ais, AudioFileFormat.Type.WAVE, file);

            System.out.println("Recorded a new audio file, notifying listeners..");
            SoundRecordedEvent event = new SoundRecordedEvent(this, 1, "soundDetected");
            for (ActionListener listener : listeners) {
                listener.actionPerformed(event);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Method to register as an EventListener for any new sound recordings
     * @param listener the EventListener to register
     * @return true if successful
     */
    boolean registerRecordingListener(ActionListener listener) {
        return listeners.add(listener);
    }

    /**
     * Method to unregister as an EventListener for any new sound recordings
     * @param listener the EventListener to unregister
     * @return true if successful
     */
    boolean unregisterRecordingListener(ActionListener listener) {
        return listeners.remove(listener);
    }

    void enableMic() {
        if (!soundDetector.isAlive()) {
            soundDetector = new SoundDetectionThread(line, ais.getFormat().getFrameSize() * SAMPLE_RATE);
        }
    }

    /**
     * Method should be called to disable the microphone's recording
     */
    void disableMic() {
        running = false;
    }

    private class SoundDetectionThread extends Thread {
        private TargetDataLine line;
        private int bufferSize;
        private float lastAmplitude;

        SoundDetectionThread(TargetDataLine line, int bufferSize) {
            this.line = line;
            this.bufferSize = bufferSize;
        }

        /**
         * Method to check if the microphone's audio input is above a threshold level
         * @return true if sound has been detected
         */
        boolean soundDetected() {
            return lastAmplitude > THRESHOLD;
        }

        @Override
        public void run() {
            while (running) {
                byte[] buf = new byte[bufferSize];
                float[] samples = new float[bufferSize / 2];
                while (running) {
                    int b = line.read(buf, 0, buf.length);
                    for (int i = 0, s = 0; i < b; ) {
                        int sample = 0;

                        // Converting the bytes to floats
                        sample |= buf[i++] << 8;
                        sample |= buf[i++] & 0xFF;

                        // Reducing it to the range -1 to +1
                        samples[s++] = sample / 32768f;
                    }

                    // Calculating the RMS amplitude
                    float rms = 0f;
                    float peak = 0f;
                    for (float sample : samples) {
                        float abs = Math.abs(sample);
                        if (abs > peak) {
                            peak = abs;
                        }
                        rms += sample * sample;
                    }
                    rms = (float) Math.sqrt(rms / samples.length);
                    lastAmplitude = rms;
                    try {
                        sleep(11);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted exception - this shouldn't have happened.");
                        e.printStackTrace();
                        System.exit(1);
                    }
                }
            }
        }
    }
}
