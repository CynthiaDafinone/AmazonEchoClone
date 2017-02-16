import java.awt.event.ActionEvent;
import java.util.EventObject;

/**
 * Created by User on 16/02/2017.
 */
public class SoundRecordedEvent extends ActionEvent {

    public SoundRecordedEvent(Object source, int id, String command) {
        super(source, id, command);
    }
}
