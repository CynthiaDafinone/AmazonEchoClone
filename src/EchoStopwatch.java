import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;


public class EchoStopwatch {
    private static long pastTime = -1;

    static void startStopwatch() {
        pastTime = System.currentTimeMillis();
    }

    static boolean stopStopwatch() {
        if (pastTime == -1) {
            System.out.println("STOPWATCH :: There was no timer started, exiting!");
            return false;
        }
        long currentTime = System.currentTimeMillis();
        long timeDifference = currentTime - pastTime;

        System.out.println(pastTime);
        System.out.println(currentTime);
        System.out.println(timeDifference);


        long days = timeDifference / 86400000;
        long hours = timeDifference / 3600000;
        long minutes = timeDifference / 60000;
        long seconds = timeDifference / 1000;

        String response = "";
        if (days > 0) {
            response += days + " days, ";
        }
        if (hours > 0) {
            response += days + " hours, ";
        }
        if (minutes > 0) {
            response += minutes + " minutes, ";
        }
        if (seconds > 0) {
            response += seconds + " seconds, ";
        }
        System.out.println("STOPWATCH :: The resulting time was: " + response);

        AudioOutput.playSound(TextToSpeech.convertStringToSpeech(response));
        pastTime = -1;
        return true;
    }
}
