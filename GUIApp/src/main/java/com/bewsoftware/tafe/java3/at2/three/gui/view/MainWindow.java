/*
 *  File Name:    MainWindow.java
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

package com.bewsoftware.tafe.java3.at2.three.gui.view;

import com.bewsoftware.tafe.java3.at2.three.common.Helper;
import com.bewsoftware.tafe.java3.at2.three.gui.util.Algorithm;
import com.bewsoftware.tafe.java3.at2.three.gui.util.SortingTask;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static javafx.scene.control.Alert.AlertType.ERROR;

/**
 * FXML Controller class
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 */
public class MainWindow
{

    /// </summary>
    private final int ARRAY_SIZE = 10000000;

    private final int MAX_SALARY = 10000000;

    private final int MIN_SALARY = 10000;

    private final int RANDOM_SEED = 1234;

    @FXML
    private ComboBox<Algorithm> algorithmComboBox;

    @FXML
    private Button cancelButton;

    @FXML
    private Label durationLabel;

    private final Helper helper;

    private final List<Integer> list = new ArrayList<>();

    @FXML
    private URL location;

    @FXML
    private ResourceBundle resources;

    @FXML
    private ListView<Integer> salaryListBox;

    @FXML
    private ProgressBar sortingProgressBar;

    private SortingTask worker;

    /**
     * Initializes a new instance of the MainWindow class.
     */
    public MainWindow()
    {
        helper = new Helper(list, Integer::compareTo, RANDOM_SEED, MAX_SALARY, ARRAY_SIZE);
    }

    /**
     * Call this method to start the task.
     *
     * @param event
     */
    public void startWorker(ActionEvent event)
    {
        Algorithm item = algorithmComboBox.getSelectionModel().getSelectedItem();

        worker = new SortingTask(item, helper, this);
        salaryListBox.getItems().clear();

        // automatically update the progressBar using worker's progress Property
        sortingProgressBar.progressProperty().bind(worker.progressProperty());

        // update the displayField whenever the value of worker changes:
        durationLabel.textProperty().bind(worker.messageProperty());

        Thread tasker = new Thread(worker);
        tasker.setDaemon(true);
        tasker.start();
    }

    /**
     * Call this method when Stop button is pressed.
     *
     * @param event
     */
    public void stopWorker(ActionEvent event)
    {
        worker.cancel();
    }

    /**
     * Called by worker task if completed successfully.
     */
    public void workerSucceeded()
    {
        salaryListBox.getItems().addAll(list);
    }

    /**
     * Called by worker task if process cancelled.
     */
    public void workerCancelled()
    {
//        new Alert(INFORMATION, "Operation was canceled").showAndWait();
    }

    /**
     * Called by worker task if process failed.
     */
    public void workerFailed()
    {
        new Alert(ERROR, "Operation failed to complete").showAndWait();
    }

    @FXML
    private void initialize()
    {
        // Setup combobox
        algorithmComboBox.getItems().addAll(Algorithm.values());
        algorithmComboBox.setOnAction(this::startWorker);
        algorithmComboBox.getSelectionModel().selectFirst();

        cancelButton.setOnAction(this::stopWorker);
        startWorker(null);
    }

}
