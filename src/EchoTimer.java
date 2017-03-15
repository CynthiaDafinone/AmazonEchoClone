import java.util.Timer;
import java.util.TimerTask;

public class EchoTimer {
    private volatile static boolean shouldPlay = true;
    private static boolean isPlaying = false;

    static boolean startTimer(String str) {
        if (str == null || !str.contains("minutes") && !str.contains("hours") && !str.contains("seconds")) {
            return false;
        }

        long time = 0;
        str = str.replaceAll("[^A-Za-z0-9 ]", "");
        String[] strings = str.split(" ");

        for (int i = 1; i < strings.length; i++) {
            try {
                if (strings[i].equals("seconds") || strings[i].equals("second")) {
                    time += Integer.parseInt(strings[i-1]) *  1000;
                }
                if (strings[i].equals("minutes") || strings[i].equals("minute")) {
                    time += Integer.parseInt(strings[i-1]) * 60000;
                }
                if (strings[i].equals("hours") || strings[i].equals("hour")) {
                    time += Integer.parseInt(strings[i-1]) * 3600000;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        Timer t = new Timer();
        System.out.println("TIMER SET FOR: " + time + " MILLISECONDS");
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                isPlaying = true;
                shouldPlay = true;
                AudioOutput.playLooping(getClass().getResourceAsStream("alarm.wav"));
                isPlaying = false;
            }
        }, time);
        return true;
    }

    static boolean shouldPlay() {
        return shouldPlay;
    }

    static void stopPlaying() {
        shouldPlay = false;
    }
}
