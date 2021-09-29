
package com.bewsoftware.tafe.java3.at2.three.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application
{
    public App()
    {
        // NoOp
    }

    private static Scene scene;

    public static void main(String[] args)
    {
        launch();
    }

    private static Parent loadFXML(String fxml) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    static void setRoot(String fxml) throws IOException
    {
        scene.setRoot(loadFXML(fxml));
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        scene = new Scene(loadFXML("MainWindow"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

}
