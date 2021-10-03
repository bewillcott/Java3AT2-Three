/*
 *  File Name:    SortingTask.java
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
 * Date: 30 Sept 2021
 * ****************************************************************
 */

package com.bewsoftware.tafe.java3.at2.three.gui.util;

import com.bewsoftware.tafe.java3.at2.three.common.Helper;
import com.bewsoftware.tafe.java3.at2.three.common.Ref;
import com.bewsoftware.tafe.java3.at2.three.common.Sorting;
import com.bewsoftware.tafe.java3.at2.three.gui.view.MainWindow;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;

import static java.lang.String.format;

/**
 * This class initiates the sorting process selected by the use.
 *
 * @author <a href="mailto:cancelled.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 1.0
 * @version 1.0
 */
public class SortingTask extends Task<Double>
{
    private final Ref<Boolean> cancelled = new Ref<>(false);

    private final Helper helper;

    private final Algorithm item;

    private final MainWindow mainWindow;

    /**
     * Initializes a new instance of the SortingTask class.
     *
     * @param item
     * @param helper
     * @param mainWindow
     */
    public SortingTask(Algorithm item, Helper helper, MainWindow mainWindow)
    {
        this.helper = helper;
        this.item = item;
        this.mainWindow = mainWindow;

        setOnCancelled((WorkerStateEvent t) ->
        {
            updateMessage("Cancel pending ...");
            cancelled.val = true;
        });
    }

    /**
     * This is the time consuming operation.
     *
     * @return total time taken by this operation
     */
    private Double timeConsumingOperation()
    {
        double rtn;

        if (item != null)
        {
            rtn = switch (item)
            {
                case ARRAYSORT ->
                    helper.sortIt(Sorting::arraySort, item.label, cancelled);

                case HEAPSORT ->
                    helper.sortIt(Sorting::heapSort, item.label, cancelled);

                case MERGESORT ->
                    helper.sortIt(Sorting::topDownMergeSort, item.label, cancelled);

                case QUICKSORT ->
                    helper.sortIt(Sorting::quickSort, item.label, cancelled);

                case UNSORTED ->
                    helper.generateIntArray();

                default ->
                    throw new AssertionError(item.name());
            };
        } else
        {
            rtn = 0;
        }

        return rtn;
    }

    @Override
    protected Double call() throws Exception
    {
        updateMessage("Processing...");
        updateProgress(-1, 1);

        // Start the time-consuming operation.
        double rtn = timeConsumingOperation();

        if (isCancelled())
        {
            updateProgress(0, 1);
            updateMessage("Cancelled!");

        } else
        {
            updateProgress(1, 1);
            updateMessage(format("%5.3f secs", rtn));
        }

        return rtn;
    }

    @Override
    protected void cancelled()
    {
        mainWindow.workerCancelled();
    }

    @Override
    protected void failed()
    {
        mainWindow.workerFailed();
    }

    @Override
    protected void succeeded()
    {
        mainWindow.workerSucceeded();
    }

}
