package event;

import javafx.event.Event;

public class Listener implements IListener {
    public Listener() {
        IEventBus eventBus = ServiceLocator.INSTANCE.getService(IEventBus.class);

        eventBus.subscribe(MyEvent.FIELD_CHANGED, this::onAction);
        eventBus.subscribe(MyEvent.TEST, this::onAction);
    }
    @Override
    public void onAction(Event event) {
        if (event.getEventType() == MyEvent.FIELD_CHANGED)
            System.out.println(((MyEvent)event).string);
        else {
            System.out.println(event.getEventType());
        }
    }
}
