package com.example.mvc;

import controllers.HeightController;
import controllers.RotateController;
import controllers.WidthController;
import event.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class View {
    @FXML
    private TextField widthText;
    @FXML
    private TextField heightText;
    @FXML
    private TextField rotateText;
    @FXML
    private ImageView tableImage;


    public View() {
        IEventBus eventBus = ServiceLocator.INSTANCE.getService(IEventBus.class);

        eventBus.subscribe(FieldHasBeenChangedEvent.HEIGHT_CHANGED, this::onHeightChanged);
        eventBus.subscribe(FieldHasBeenChangedEvent.WIDTH_CHANGED, this::onWidthChanged);
        eventBus.subscribe(FieldHasBeenChangedEvent.ROTATE_CHANGED, this::onRotateChanged);
    }

    public void init(Table table, HeightController h, WidthController w, RotateController r){
        widthText.setText(table.getWidth().toString());
        heightText.setText(table.getHeight().toString());
        rotateText.setText(table.getRotate().toString());

        widthText.setOnAction(event -> {
            if (!w.checkValue(widthText.getText())){
                widthText.setText(table.getWidth().toString());
            }
        });

        heightText.setOnAction(event -> {
            if (!h.checkValue(heightText.getText())){
                heightText.setText(table.getHeight().toString());
            }
        });
        rotateText.setOnAction(event -> {
            if (!r.checkValue(rotateText.getText())) {
                rotateText.setText(table.getRotate().toString());
            }
        });

        tableImage.setScaleY(table.getHeight());
        tableImage.setScaleX(table.getWidth());
        tableImage.setRotate(table.getRotate());
    }

    private void onHeightChanged(Event event) {
        tableImage.setScaleY(((FieldHasBeenChangedEvent) event).getValue());
    }
    private void onWidthChanged(Event event) {
        tableImage.setScaleX(((FieldHasBeenChangedEvent) event).getValue());
    }
    private void onRotateChanged(Event event) {
        tableImage.setRotate(((FieldHasBeenChangedEvent)event).getValue());
    }

}