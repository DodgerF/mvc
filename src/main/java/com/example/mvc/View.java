package com.example.mvc;

import controllers.HeightController;
import controllers.LengthController;
import controllers.RotateController;
import controllers.WidthController;
import event.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;

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
    private  Table table;
    private double centreX, centreY;

    private boolean isViewAbove;

    public View() {
        IEventBus eventBus = ServiceLocator.INSTANCE.getService(IEventBus.class);

        eventBus.subscribe(FieldHasBeenChangedEvent.HEIGHT_CHANGED, this::onHeightChanged);
        eventBus.subscribe(FieldHasBeenChangedEvent.WIDTH_CHANGED, this::onWidthChanged);
        eventBus.subscribe(FieldHasBeenChangedEvent.LENGTH_CHANGED, this::onLengthChanged);
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
            img.setImage(findImageByName("table.png"));
        }
        else {
            img.setImage(findImageByName("table_top.png"));
        }
        isViewAbove = !isViewAbove;

        if (isViewAbove)
            img.setFitHeight(table.getLength());
        else
            img.setFitHeight(table.getHeight());
        img.setFitWidth(table.getWidth());
        img.setRotate(table.getRotate());
    }

    private static String createPathToImage(String imgName){
        return String.format("%s\\src\\main\\resources\\images\\%s", new File("").getAbsolutePath(), imgName);
    }
    private static Image findImageByName(String imgName)
    {
        try
        {
            return new Image(new FileInputStream(createPathToImage(imgName)));
        }
        catch (Exception e)
        {
            return null;
        }
    }
    public void init(Table table, HeightController h, WidthController w, LengthController l, RotateController r){
        this.table = table;
        tableWidth.setText(table.getWidth().toString());
        tableHeight.setText(table.getHeight().toString());
        tableLength.setText(table.getLength().toString());
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

        tableLength.setOnAction(event -> {
            if (!l.checkValue(tableLength.getText())){
                tableLength.setText(table.getLength().toString());
            }
        });

        tableRotate.setOnAction(event -> {
            if (!r.checkValue(tableRotate.getText())) {
                tableRotate.setText(table.getRotate().toString());
            }
        });
        centreX = img.getX() + img.getFitWidth() / 2;
        centreY = img.getY() + img.getFitHeight() / 2;

        img.fitWidthProperty().addListener((observable, oldValue, newValue) -> {
            setImageToCenter();
        });

        img.fitHeightProperty().addListener((observable, oldValue, newValue) -> {
            setImageToCenter();
        });
    }
    private void setImageToCenter() {
        img.setX(centreX - img.getFitWidth()/2);
        img.setY(centreY - img.getFitHeight()/2);
    }

    private void onHeightChanged(Event event) {
        if (isViewAbove) return;
        img.setFitHeight(((FieldHasBeenChangedEvent) event).getValue());
    }
    private void onWidthChanged(Event event) {
        img.setFitWidth(((FieldHasBeenChangedEvent) event).getValue());
    }
    private void onRotateChanged(Event event) {
        img.setRotate(((FieldHasBeenChangedEvent)event).getValue());
    }
    private void onLengthChanged(Event event) {
        if (!isViewAbove) return;
        img.setFitHeight(((FieldHasBeenChangedEvent) event).getValue());
    }

}