/*
 *  File Name:    App.java
 *  Project Name: ConsoleApp
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
 * Date: 26 Sept 2021
 * ****************************************************************
 */

package com.bewsoftware.tafe.java3.at2.three.console;

import com.bewsoftware.tafe.java3.at2.three.common.Helper;
import com.bewsoftware.tafe.java3.at2.three.common.Sorting;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * App class description.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 1.0
 * @version 1.0
 */
public class App
{
    /// <summary>
    /// The array size
    /// </summary>
    private static final int ARRAY_SIZE = 10000000;

    /// <summary>
    /// The filename
    /// </summary>
    private static final String FILENAME = "sort_duration.csv";

    /// <summary>
    /// The maximum salary
    /// </summary>
    private static final int MAX_SALARY = 1000000;

    /// <summary>
    /// The minimum salary
    /// </summary>
    private static final int MIN_SALARY = 10000;

    /// <summary>
    /// The number of test runs
    /// </summary>
    private static final int NUM_OF_TEST_RUNS = 10;

    /// <summary>
    /// The random seed
    /// </summary>
    private static final int RANDOM_SEED = 1234;

    /// <summary>
    /// The list
    /// </summary>
    private static final List<Integer> list = new ArrayList<>(ARRAY_SIZE);

    /// <summary>
    /// Initializes a new instance of the <see cref="Program"/> class.
    /// </summary>
    protected App()
    {
    }

    /// <summary>
    /// The Main.
    /// </summary>
    public static void main(String[] args)
    {
        var helper = new Helper(list, Integer::compareTo, RANDOM_SEED, MAX_SALARY, ARRAY_SIZE);
        var rows = new CsvRow[]
        {
            new CsvRow("Arrays.sort"),
            new CsvRow("Heap Sort"),
            new CsvRow("Merge Sort"),
            new CsvRow("Top-down Merge Sort"),
            new CsvRow("Quick Sort")
        };

        var ls = new int[ARRAY_SIZE]; // List Sort
        var tdms = new int[ARRAY_SIZE]; // Top-down Merge Sort

        for (int i = 0; i < NUM_OF_TEST_RUNS; i++)
        {
            rows[0].add(helper.sortIt(Sorting::arraySort, "Arrays.sort [" + (i + 1) + "]", null));

            for (int j = 0; j < list.size(); j++)
            {
                ls[j] = list.get(j);
            }

            rows[1].add(helper.sortIt(Sorting::heapSort, "Heap Sort [" + (i + 1) + "]", null));
            rows[2].add(helper.sortIt(Sorting::mergeSort, "Merge Sort [" + (i + 1) + "]", null));
            rows[3].add(helper.sortIt(Sorting::topDownMergeSort, "Top-down Merge Sort [" + (i + 1) + "]", null));

            for (int j = 0; j < list.size(); j++)
            {
                tdms[j] = list.get(j);
            }

            rows[4].add(helper.sortIt(Sorting::quickSort, "Quick Sort [" + (i + 1) + "]", null));
            System.out.println("--------------------------------------------------------------");
        }

        System.out.format("Writing data to CSV filename: %s\n", FILENAME);

        if (writeCSVData(FILENAME, rows))
        {
            System.out.println("Data written.");
        } else
        {
            System.out.println("Failed to write data.");
        }

        System.out.format("\nAverage results over %d test runs:\n", NUM_OF_TEST_RUNS);
        System.out.format(" - Arrays.sort           : %3f\n", rows[0].getAvg());
        System.out.format(" - HeapSort            : %3f\n", rows[1].getAvg());
        System.out.format(" - MergeSort           : %3f\n", rows[2].getAvg());
        System.out.format(" - Top-down MergeSort  : %3f\n", rows[3].getAvg());
        System.out.format(" - QuickSort           : %3f\n", rows[4].getAvg());

        for (int i = 0; i < ls.length; i++)
        {
            if (ls[i] != tdms[i])
            {
                System.out.format("Top-down Merge Sort failed at [%d]\n", i);
            }
        }
    }

    /// <summary>
    /// Writes the CSV data.
    /// </summary>
    /// <param name="fileName">Name of the file.</param>
    /// <param name="rows">The rows.</param>
    /// <returns></returns>
    private static boolean writeCSVData(String fileName, CsvRow[] rows)
    {
        var rtn = false;

        if (rows[0] != null && fileName != null && !fileName.isBlank())
        {
            File file = new File(fileName);

            if (!file.exists())
            {
                try (FileWriter sw = new FileWriter(fileName))
                {
                    sw.write(rows[0].csvHeader() + "\n");
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }

            try (FileWriter sw = new FileWriter(fileName, true))
            {
                for (var row : rows)
                {
                    sw.write(row.toString() + "\n");
                }

                rtn = true;
            } catch (Exception ex)
            {
                System.out.format("ERROR: Failed to write to CSV file!\n%s\n", ex.getMessage());
            }
        }

        return rtn;
    }

/// <summary>
/// Contains data relevant to a single CSV row.
/// </summary>
/// <remarks>
/// The information is specific to a single sorting algorithm, and the
/// data collected over multiple test runs.
/// <para/>
/// The original idea for this class came from reading this site:<br/>
/// https://www.codeproject.com/articles/415732/reading-and-writing-csv-files-in-csharp
/// </remarks>
/// <seealso cref="System.Collections.Generic.List&lt;System.Double&gt;" />
    private static final class CsvRow extends ArrayList<Double>
    {
        private static final long serialVersionUID = -5577940667264457537L;

        private String algorithm;

        /// <summary>
        /// Initializes a new instance of the <see cref="CsvRow"/> class.
        /// </summary>
        /// <param name="algorithm">The algorithm.</param>
        public CsvRow(String algorithm)
        {
            setAlgorithm(algorithm);
        }

        /// <summary>
        /// Gets or sets the algorithm.
        /// </summary>
        public String getAlgorithm()
        {
            return algorithm;
        }

        public void setAlgorithm(String value)
        {
            algorithm = value;
        }

        /// <summary>
        /// Gets the average.
        /// </summary>
        /// <value>
        /// The average.
        /// </value>
        public double getAvg()
        {
            double total = 0;

//            total = this.stream().map(item -> item)
//                    .reduce(total, (accumulator, _item) -> accumulator + _item);
            total = this.stream().reduce(total, (accumulator, item) -> accumulator + item);

            return total / size();
        }

        /// <summary>
        /// Parses a CSV string.
        /// </summary>
        /// <param name="csvString">The CSV string.</param>
        /// <returns>A new <see cref="CsvRow"/>.</returns>
        public static CsvRow parseCSV(String csvString)
        {
            CsvRow rtn = null;

            if (csvString != null && !csvString.isBlank())
            {
                var data = csvString.split(",");

                if (data.length > 0)
                {
                    try
                    {
                        rtn = new CsvRow(data[0]);
                        rtn.algorithm = data[0];

                        for (var item : data)
                        {
                            rtn.add(Double.parseDouble(item));
                        }
                    } catch (NumberFormatException ex)
                    { // Quietly ignore it
                        rtn = null;
                    }
                }
            }

            return rtn;
        }

        /**
         * CSV file column headers.
         *
         * @return
         */
        public String csvHeader()
        {
            var sb = new StringBuilder("Sort Algorithm");

            for (int i = 0; i < this.size(); i++)
            {
                sb.append(",").append("Run #").append(i + 1);
            }

            return sb.toString();
        }

        @Override
        public String toString()
        {
            var sb = new StringBuilder(algorithm);

            this.forEach(obj -> sb.append(",").append(obj));

            return sb.toString();
        }
    }
}
