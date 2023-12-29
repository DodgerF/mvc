package event;

import javafx.event.Event;
import javafx.event.EventType;

public class FieldHasBeenChangedEvent extends Event {
    private static final EventType<FieldHasBeenChangedEvent> ANY = new EventType<>(Event.ANY);
    public static final EventType<FieldHasBeenChangedEvent> HEIGHT_CHANGED = new EventType<>(ANY, "Height");
    public static final EventType<FieldHasBeenChangedEvent> WIDTH_CHANGED = new EventType<>(ANY, "Width");
    public static final EventType<FieldHasBeenChangedEvent> LENGTH_CHANGED = new EventType<>(ANY, "Length");
    public static final EventType<FieldHasBeenChangedEvent> ROTATE_CHANGED = new EventType<>(ANY, "Rotate");
    private final double VALUE;

    public FieldHasBeenChangedEvent(EventType<FieldHasBeenChangedEvent> type, double v) {
        super(type);
        VALUE = v;
    }
    public Double getValue() {
        return VALUE;
    }
}
