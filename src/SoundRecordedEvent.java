import java.awt.event.ActionEvent;
import java.util.EventObject;

class SoundRecordedEvent extends ActionEvent {
    SoundRecordedEvent(Object source, int id, String command) {
        super(source, id, command);
    }
}
