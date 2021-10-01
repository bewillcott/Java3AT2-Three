
package com.bewsoftware.tafe.java3.at2.three.gui;

import com.bewsoftware.tafe.java3.at2.three.gui.view.RootLayoutController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

/**
 * JavaFX App
 */
public class App extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    private Stage primaryStage;

    private BorderPane rootLayout;

    public Stage getPrimaryStage()
    {
        return primaryStage;
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout()
    {
        try
        {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            RootLayoutController rootLayoutController = loader.getController();
            rootLayoutController.setApp(this);

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    /**
     * Shows the MainWindow inside the root layout.
     */
    public void showMainWindow()
    {
        try
        {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/MainWindow.fxml"));
            GridPane mainWindow = (GridPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(mainWindow);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Java3 AT2 Three");

        initRootLayout();

        showMainWindow();
    }

}
