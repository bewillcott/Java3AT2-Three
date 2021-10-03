/*
 *  File Name:    App.java
 *  Project Name: GUIApp
 *
 *  Copyright (c) 2021 Bradley Willcott
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * ****************************************************************
 * Name: Bradley Willcott
 * ID:   M198449
 * Date: 29 Sept 2021
 * ****************************************************************
 */

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
