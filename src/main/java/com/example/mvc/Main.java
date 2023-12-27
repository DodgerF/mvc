package com.example.mvc;

import controllers.HeightController;
import controllers.RotateController;
import controllers.WidthController;
import event.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private IEventBus eventBus;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        Table table = new Table();

        var h = new HeightController(table);
        var w = new WidthController(table);
        var r = new RotateController(table);

        ((View)fxmlLoader.getController()).init(table, h, w, r);
    }

    public static void main(String[] args) {
        ServiceLocator.INSTANCE.registerService(IEventBus.class, EventBusProvider.class);

        launch();
    }
}