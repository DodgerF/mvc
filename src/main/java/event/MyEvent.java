package event;

import javafx.event.Event;
import javafx.event.EventType;

public class MyEvent extends Event {
    private static final EventType<MyEvent> ANY = new EventType<>(Event.ANY);
    public static final EventType<MyEvent> FIELD_CHANGED = new EventType<>(ANY, "field_change");
    public static final EventType<MyEvent> TEST = new EventType<>(ANY, "test");
    public final String string;
    public MyEvent(EventType<MyEvent> type, String sting) {
        super(type);
        this.string = sting;
    }
}
