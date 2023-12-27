package com.example.mvc;

import controllers.HeightController;
import controllers.WidthController;
import event.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class View implements IListener {
    @FXML
    private TextField widthText;
    @FXML
    private TextField heightText;
    @FXML
    private ImageView tableImage;


    public TextField getWidthText() {
        return widthText;
    }

    public TextField getHeightText() {
        return heightText;
    }

    public View() {
        IEventBus eventBus = ServiceLocator.INSTANCE.getService(IEventBus.class);

        eventBus.subscribe(FieldHasBeenChangedEvent.HEIGHT_CHANGED, this::onChangedHeight);
        eventBus.subscribe(FieldHasBeenChangedEvent.WIDTH_CHANGED, this::onChangedWidth);
    }

    public void init(Table table, HeightController h, WidthController w){
        tableImage.setScaleY(table.getHeight());
        tableImage.setScaleX(table.getWidth());

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

        widthText.setText(table.getWidth().toString());
        heightText.setText(table.getHeight().toString());
    }

    private void onChangedHeight(Event event) {
        tableImage.setScaleY(((FieldHasBeenChangedEvent) event).getValue());
    }
    private void onChangedWidth(Event event) {
        tableImage.setScaleX(((FieldHasBeenChangedEvent) event).getValue());
    }

}