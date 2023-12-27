package com.example.mvc;

import event.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private IEventBus eventBus;
    private IListener listener;

    @Override
    public void start(Stage stage) throws IOException {
        listener = ServiceLocator.INSTANCE.getService(IListener.class);
        eventBus = ServiceLocator.INSTANCE.getService(IEventBus.class);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        scene.setOnMouseClicked(event -> {
            eventBus.fireEvent(new MyEvent(MyEvent.FIELD_CHANGED, "x: " + event.getX()));
            eventBus.fireEvent(new MyEvent(MyEvent.TEST, "y: " + event.getY()));
        });
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        //Table table = new Table();

    }

    public static void main(String[] args) {
        ServiceLocator.INSTANCE.registerService(IListener.class, Listener.class);
        ServiceLocator.INSTANCE.registerService(IEventBus.class, EventBusProvider.class);

        launch();
    }
}