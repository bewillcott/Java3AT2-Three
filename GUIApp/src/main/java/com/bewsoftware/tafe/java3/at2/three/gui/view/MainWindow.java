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
import com.bewsoftware.tafe.java3.at2.three.gui.App;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * FXML Controller class
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 */
public class MainWindow
{

    /// <summary>
    /// The array size.
    /// </summary>
    private final int ARRAY_SIZE = 1000000;

    /// <summary>
    /// The maximum salary.
    /// </summary>
    private final int MAX_SALARY = 10000000;

    /// <summary>
    /// The minimum salary.
    /// </summary>
    private final int MIN_SALARY = 10000;

    /// <summary>
    /// The random seed.
    /// </summary>
    private final int RANDOM_SEED = 1234;

    /// <summary>
    /// The helper.
    /// </summary>
    private final Helper helper;

    /// <summary>
    /// The list.
    /// </summary>
    private final ObservableList<Integer> list = FXCollections.observableArrayList();

    @FXML
    private ComboBox<Algorithm> algorithmComboBox;

    @FXML
    private Button cancelButton;

    @FXML
    private Label durationLabel;

    @FXML
    private ProgressBar sortingProgressBar;

    @FXML
    private ListView<Integer> salaryListBox;

    // Reference to the main application.
    private App app;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param app
     */
    public void setApp(App app)
    {
        this.app = app;

        // Add observable list data to the table
//        personTable.setItems(mainApp.getPersonData());
    }

    /// <summary>
    /// The worker
    /// </summary>
//    private final BackgroundWorker worker = new ();
    /// <summary>
    /// Initializes a new instance of the <see cref="MainWindow"/> class.
    /// </summary>
    public MainWindow()
    {
//        InitializeComponent();
        helper = new Helper(list, RANDOM_SEED, MAX_SALARY, ARRAY_SIZE);

        // Setup the worker
//        worker.DoWork += Worker_DoWork;
//        worker.RunWorkerCompleted += Worker_RunWorkerCompleted;
//        worker.WorkerSupportsCancellation = true;
    }

    public void init()
    {
        // Setup combobox
        algorithmComboBox.getItems().addAll(Algorithm.values());
        algorithmComboBox.getSelectionModel().selectFirst();
    }

    /// <summary>
    /// The AlgorithmComboBox_SelectionChanged.
    /// </summary>
    /// <param name="sender">The sender<see cref="Object"/>.</param>
    /// <param name="e">The e<see cref="SelectionChangedEventArgs"/>.</param>
    @FXML
    private void algorithmComboBox_SelectionChanged()
    {
        var item = algorithmComboBox.getSelectionModel().getSelectedItem();

        salaryListBox.setItems(null);
        durationLabel.setText("0.000 seconds");
        sortingProgressBar.setProgress(-1);

//        worker.RunWorkerAsync(item);
//
//        e.Handled = true;
    }

    /// <summary>
    /// Handles the Click event of the CancelButton control.
    /// </summary>
    /// <param name="sender">The source of the event.</param>
    /// <param name="e">The <see cref="System.Windows.RoutedEventArgs"/> instance containing the event data.</param>
    /// <returns></returns>
    @FXML
    private void CancelButton_Click()
    {
//        worker.CancelAsync();
    }

    /// <summary>
    /// Times the consuming operation.
    /// </summary>
    /// <param name="bw">The bw.</param>
    /// <param name="item">The item.</param>
    /// <returns></returns>
    private Object TimeConsumingOperation()
    {
        var rtn = 0.000;

//        if (null != item)
//        {
//            switch (item)
//            {
//                case ArraySort ->
//                    rtn = helper.sortIt(Sorting::arraySort, item.label, bw);
//                case HeapSort ->
//
//                    rtn = helper.sortIt(Sorting::heapSort, item.label, bw);
//
//                case MergeSort ->
//
//                    rtn = helper.sortIt(Sorting::mergeSort, item.label, bw);
//
//                case QuickSort ->
//
//                    rtn = helper.sortIt(Sorting::quickSort, item.label, bw);
//
//                case Unsorted ->
//                    helper.generateIntArray();
//                default ->
//                {
//                }
//            }
//        }
        return rtn;
    }

    /// <summary>
    /// Handles the DoWork event of the Worker control.
    /// </summary>
    /// <remarks>
    /// Initial code copied from:
    /// https://docs.microsoft.com/en-us/dotnet/desktop/winforms/controls/how-to-run-an-operation-in-the-background?view=netframeworkdesktop-4.8
    /// </remarks>
    /// <param name="sender">The source of the event.</param>
    /// <param name="e">The <see cref="DoWorkEventArgs"/> instance containing the event data.</param>
    private void Worker_DoWork()
    {
        // Do not access the form's BackgroundWorker reference directly.
        // Instead, use the reference provided by the sender parameter.
//        var bw = sender as BackgroundWorker;
//
//        // Extract the argument.
//        var item = e.Argument as Algorithms;
//
//        // Start the time-consuming operation.
//        e.Result = TimeConsumingOperation(bw, item);
//
//        // If the operation was canceled by the user,
//        // set the DoWorkEventArgs.Cancel property to true.
//        if (bw.CancellationPending)
//        {
//            e.Cancel = true;
//        }
    }

    /// <summary>
    /// Handles the RunWorkerCompleted event of the Worker control.
    /// </summary>
    /// <remarks>
    /// Initial code copied from:
    /// https://docs.microsoft.com/en-us/dotnet/desktop/winforms/controls/how-to-run-an-operation-in-the-background?view=netframeworkdesktop-4.8
    /// </remarks>
    /// <param name="sender">The source of the event.</param>
    /// <param name="e">The <see cref="RunWorkerCompletedEventArgs"/> instance containing the event data.</param>
    private void Worker_RunWorkerCompleted()
    {
//        SortingProgressBar.IsIndeterminate = false;
//
//        if (e.Cancelled)
//        {
//            // The user canceled the operation.
//            MessageBox.Show("Operation was canceled");
//        } else if (e.Error != null)
//        {
//            // There was an error during the operation.
//            var msg = $
//            "An error occurred: {e.Error.Message}";
//                MessageBox.Show(msg);
//        } else
//        {
//            // The operation completed normally.
//            DurationTextBox.Text = e.Result != null ? ((double) e.Result).ToString("F3") + @" seconds" : @"0.000 seconds";
//            SalaryListBox.ItemsSource = list;
//        }
    }

    /**
     * Listing of all of the Sorting Algorithm and their
     * individual label entries to be used in the ListView.
     */
    public enum Algorithm
    {
        Unsorted("=Unsorted="),
        ArraySort("Arrays.sort"),
        HeapSort("Heap Sort"),
        MergeSort("Merge Sort"),
        QuickSort("Quick Sort");

        private static final Map<String, Algorithm> BY_LABEL = new HashMap<>();

        private static final List<String> LABELS = new ArrayList<>();

        static
        {
            for (Algorithm value : values())
            {
                BY_LABEL.put(value.label, value);
                LABELS.add(value.label);
            }
        }

        public static String[] labels()
        {
            return LABELS.toArray(new String[LABELS.size()]);
        }

        public static List<String> labelsList()
        {
            return new ArrayList<>(LABELS);
        }

        public static Algorithm valueOfLabel(String label)
        {
            return BY_LABEL.get(label);
        }

        public final String label;

        Algorithm(String label)
        {
            this.label = label;
        }

        @Override
        public String toString()
        {
            return label;
        }
    }
}
