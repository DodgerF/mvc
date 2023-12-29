package com.example.mvc;

import controllers.HeightController;
import controllers.RotateController;
import controllers.WidthController;
import event.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class View {
    @FXML
    private TextField tableWidth;
    @FXML
    private TextField tableHeight;
    @FXML
    private TextField tableLength;
    @FXML
    private TextField tableRotate;
    @FXML
    private ImageView img;

    private double centreX, centreY;

    private boolean isViewAbove;

    public View() {
        IEventBus eventBus = ServiceLocator.INSTANCE.getService(IEventBus.class);

        eventBus.subscribe(FieldHasBeenChangedEvent.HEIGHT_CHANGED, this::onHeightChanged);
        eventBus.subscribe(FieldHasBeenChangedEvent.WIDTH_CHANGED, this::onWidthChanged);
        //eventBus.subscribe(FieldHasBeenChangedEvent.LENGTH_CHANGED, this:);
        eventBus.subscribe(FieldHasBeenChangedEvent.ROTATE_CHANGED, this::onRotateChanged);
    }

    public void onZoomIn() {
        var scale = img.getScaleX() + 0.1;
        img.setScaleX(scale);
        img.setScaleY(scale);
    }
    public void onZoomOut() {
        var scale = img.getScaleX() - 0.1;

        img.setScaleX(scale);
        img.setScaleY(scale);
    }

    public void onClick() {
        if (isViewAbove) {
            img.setImage(new Image("C:\\mvc\\src\\main\\resources\\images\\table.png"));
        }
        else {
            img.setImage(new Image("C:\\mvc\\src\\main\\resources\\images\\table_top.png"));
        }


        /*centreX = img.getX() + img.getFitWidth() / 2;
        centreY = img.getY() + img.getFitHeight() / 2;

        img.fitWidthProperty().addListener((observable, oldValue, newValue) -> {
            setImageToCenter();
        });

        img.fitHeightProperty().addListener((observable, oldValue, newValue) -> {
            setImageToCenter();
        });

        img.setFitHeight(table.getHeight());
        img.setFitWidth(table.getWidth());
        img.setRotate(table.getRotate());
        isViewAbove = !isViewAbove;*/
    }
    public void init(Table table, HeightController h, WidthController w, RotateController r){
        tableWidth.setText(table.getWidth().toString());
        tableHeight.setText(table.getHeight().toString());
        tableRotate.setText(table.getRotate().toString());

        tableWidth.setOnAction(event -> {
            if (!w.checkValue(tableWidth.getText())){
                tableWidth.setText(table.getWidth().toString());
            }
        });

        tableHeight.setOnAction(event -> {
            if (!h.checkValue(tableHeight.getText())){
                tableHeight.setText(table.getHeight().toString());
            }
        });
        tableRotate.setOnAction(event -> {
            if (!r.checkValue(tableRotate.getText())) {
                tableRotate.setText(table.getRotate().toString());
            }
        });

    }

    private void onHeightChanged(Event event) {
        img.setFitHeight(((FieldHasBeenChangedEvent) event).getValue());
    }
    private void onWidthChanged(Event event) {
        img.setFitWidth(((FieldHasBeenChangedEvent) event).getValue());
    }
    private void onRotateChanged(Event event) {
        img.setRotate(((FieldHasBeenChangedEvent)event).getValue());
    }

    private void setImageToCenter() {
        img.setX(centreX - img.getFitWidth()/2);
        img.setY(centreY - img.getFitHeight()/2);
    }
}