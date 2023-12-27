package event;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;

public class EventBusProvider implements IEventBus {

    private Group group = new Group();
    @Override
    public void fireEvent(Event event) {
        group.fireEvent(event);
    }

    @Override
    public <T extends Event> void subscribe(EventType<T> type, EventHandler<? super T> handler) {
        group.addEventHandler(type, handler);
    }

    @Override
    public <T extends Event> void unSubscribe(EventType<T> type, EventHandler<? super T> handler) {
        group.removeEventHandler(type, handler);
    }
}
