import java.awt.event.ActionEvent;

class SoundRecordedEvent extends ActionEvent {
    SoundRecordedEvent(Object source, int id, String command) {
        super(source, id, command);
    }
}
