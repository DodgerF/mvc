package com.example.mvc;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class View {
    @FXML
    private TextField widthText;
    @FXML
    private TextField heightText;

    public TextField getWidthText() {
        return widthText;
    }

    public TextField getHeightText() {
        return heightText;
    }


}