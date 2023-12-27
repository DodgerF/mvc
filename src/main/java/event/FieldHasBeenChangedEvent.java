package event;

import javafx.event.Event;
import javafx.event.EventType;

public class FieldHasBeenUpdatedEvent extends Event {
    private static final EventType<MyEvent> ANY = new EventType<>(Event.ANY);
    public static final EventType<MyEvent> HEIGHT_CHANGED = new EventType<>(ANY, "Height");
    public static final EventType<MyEvent> WIDTH_CHANGED = new EventType<>(ANY, "Width");
    private final double VALUE;

    public FieldHasBeenUpdatedEvent(EventType<FieldHasBeenUpdatedEvent> type, double v) {
        super(type);
        VALUE = v;
    }
    public double getValue() {
        return VALUE;
    }
}
