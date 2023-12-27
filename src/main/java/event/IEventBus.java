package event;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

public interface IEventBus {
    void fireEvent(Event event);
    <T extends Event> void subscribe(EventType<T> type, EventHandler<? super T> handler);
    <T extends Event> void unSubscribe(EventType<T> type, EventHandler<? super T> handler);
}
