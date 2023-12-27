package controllers;

import com.example.mvc.Table;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class HeightController {
    @FXML
    private TextField field;
    public HeightController(TextField f, Table table) {
        field = f;
    }

}
