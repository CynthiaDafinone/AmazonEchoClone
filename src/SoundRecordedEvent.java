import java.awt.event.ActionEvent;
import java.util.EventObject;

public class SoundRecordedEvent extends ActionEvent {
    public SoundRecordedEvent(Object source, int id, String command) {
        super(source, id, command);
    }
}
