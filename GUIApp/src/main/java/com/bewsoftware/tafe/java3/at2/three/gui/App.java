
package com.bewsoftware.tafe.java3.at2.three.gui;

import com.bewsoftware.tafe.java3.at2.three.gui.view.MainWindow;
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

    /**
     * The data as an observable list of Persons.
     */
//    private final ObservableList<Person> personData = FXCollections.observableArrayList();

    private Stage primaryStage;

    private BorderPane rootLayout;

    /**
     * Constructor
     */
//    public MainApp()
//    {
//        // Add some sample data
//        personData.add(new Person("Hans", "Muster"));
//        personData.add(new Person("Ruth", "Mueller"));
//        personData.add(new Person("Heinz", "Kurz"));
//        personData.add(new Person("Cornelia", "Meier"));
//        personData.add(new Person("Werner", "Meyer"));
//        personData.add(new Person("Lydia", "Kunz"));
//        personData.add(new Person("Anna", "Best"));
//        personData.add(new Person("Stefan", "Meier"));
//        personData.add(new Person("Martin", "Mueller"));
//    }

    /**
     * Returns the data as an observable list of Persons.
     *
     * @return
     */
//    public ObservableList<Person> getPersonData()
//    {
//        return personData;
//    }

    /**
     * Returns the main stage.
     *
     * @return
     */
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
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     *
     * @param person the person object to be edited
     *
     * @return true if the user clicked OK, false otherwise.
     */
//    public boolean showPersonEditDialog(Person person)
//    {
//        try
//        {
//            // Load the fxml file and create a new stage for the popup dialog.
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
//            AnchorPane page = (AnchorPane) loader.load();
//
//            // Create the dialog Stage.
//            Stage dialogStage = new Stage();
//            dialogStage.setTitle("Edit Person");
//            dialogStage.initModality(Modality.WINDOW_MODAL);
//            dialogStage.initOwner(primaryStage);
//            Scene scene = new Scene(page);
//            dialogStage.setScene(scene);
//            dialogStage.setResizable(false);
//
//            // Set the person into the controller.
//            PersonEditDialogController controller = loader.getController();
//            controller.setDialogStage(dialogStage);
//            controller.setPerson(person);
//
//            // Show the dialog and wait until the user closes it
//            dialogStage.showAndWait();
//
//            return controller.isOkClicked();
//        } catch (IOException e)
//        {
//            e.printStackTrace();
//            return false;
//        }
//    }

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

            // Give the controller access to the main app.
            MainWindow controller = loader.getController();
            controller.init();
            controller.setApp(this);

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
