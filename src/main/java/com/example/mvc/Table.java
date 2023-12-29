package com.example.mvc;

import event.FieldHasBeenChangedEvent;
import event.IEventBus;
import event.ServiceLocator;

public class Table {
    private Double height;
    private Double width;
    private Double length;
    private Double rotate;
    public Table() {
        height = 90.0;
        width = 120.0;
        length = 80.0;
        rotate = 0.0;
    }

    public Double getHeight() {
        return height;
    }
    public Double getWidth() {
        return width;
    }
    public Double getRotate() {
        return rotate;
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

    public void setRotate(double r) {
        rotate = r;

        var eventBus = ServiceLocator.INSTANCE.getService(IEventBus.class);
        eventBus.fireEvent(new FieldHasBeenChangedEvent(FieldHasBeenChangedEvent.ROTATE_CHANGED, rotate));
    }
}
