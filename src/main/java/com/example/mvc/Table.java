package com.example.mvc;

import event.FieldHasBeenChangedEvent;
import event.IEventBus;
import event.ServiceLocator;

public class Table {
    private Double height;
    private Double width;
    public Table() {
        height = 1.0;
        width = 1.0;
    }

    public Double getHeight() {
        return height;
    }
    public Double getWidth() {
        return width;
    }
    public void setHeight(double h) {
        height = h;

        var eventBus = ServiceLocator.INSTANCE.getService(IEventBus.class);
        eventBus.fireEvent(new FieldHasBeenChangedEvent(FieldHasBeenChangedEvent.HEIGHT_CHANGED, height));
    }
    public void setWidth(double w) {
        width = w;

        var eventBus = ServiceLocator.INSTANCE.getService(IEventBus.class);
        eventBus.fireEvent(new FieldHasBeenChangedEvent(FieldHasBeenChangedEvent.WIDTH_CHANGED, width));
    }
}
