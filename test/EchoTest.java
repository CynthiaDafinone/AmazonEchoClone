import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.event.ActionEvent;

import static org.junit.Assert.*;

public class EchoTest {
    static Echo echo;

    @BeforeClass
    public static void setUp() {
        echo = new Echo();
    }

    @Test
    public void testEcho() {
        assertNotNull(echo.detector);
        assertNotNull(echo.FILENAME);
        assertNotNull(echo.gui);
    }

    @Test
    public void testActionPerformed() {
        ActionEvent event = new ActionEvent(this, 1, "soundDetected");
        echo.gui.setPowered(true);

        echo.FILENAME = getClass().getResource("timer_test.wav").getPath();
        System.out.println(echo.FILENAME);
        echo.actionPerformed(event);

        echo.FILENAME = getClass().getResource("news_test.wav").getPath();
        echo.actionPerformed(event);

        echo.FILENAME = getClass().getResource("start_stopwatch_test.wav").getPath();
        echo.actionPerformed(event);

        echo.FILENAME = getClass().getResource("stop_stopwatch_test.wav").getPath();
        echo.actionPerformed(event);

    }
}
