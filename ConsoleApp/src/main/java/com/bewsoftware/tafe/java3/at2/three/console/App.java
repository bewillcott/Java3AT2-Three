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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import static java.text.NumberFormat.getIntegerInstance;

/**
 * This is the Console Application for my Java3 AT2 Three assignment.
 * <p>
 * It is essentially for the testing of the sort algorithms, and for producing
 * test results which are stored in a CSV file for later use.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 1.0
 * @version 1.0
 */
public class App
{
    /**
     * The array size.
     */
    private static final int ARRAY_SIZE = 1000000;

    /**
     * The output CSV filename.
     */
    private static final String FILENAME = "sort_duration.csv";

    /**
     * Largest Integer value.
     */
    private static final int MAX_INTEGER = 10000000;

    /**
     * The number of test runs.
     */
    private static final int NUM_OF_TEST_RUNS = 10;

    /**
     * The seed for the Random number generator.
     */
    private static final int RANDOM_SEED = 1234;

    /**
     * The list of numbers to be sorted.
     */
    private static final List<Integer> list = new ArrayList<>(ARRAY_SIZE);

    /**
     * Initializes a new instance of the App class.
     */
    protected App()
    {
    }

    /**
     * The program entry point.
     */
    public static void main(String[] args)
    {
        var helper = new Helper(list, Integer::compareTo, RANDOM_SEED, MAX_INTEGER, ARRAY_SIZE);
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

        System.out.format("\nWriting data to CSV filename: %s\n", FILENAME);

        if (writeCSVData(FILENAME, rows))
        {
            System.out.println("Data written.");
        } else
        {
            System.out.println("Failed to write data.");
        }

        NumberFormat nf = getIntegerInstance();

        System.out.format("\nAverage results over %d test runs of %s Integers:\n", NUM_OF_TEST_RUNS, nf.format(ARRAY_SIZE));
        System.out.format(" - Arrays.sort           : %3f\n", rows[0].getAvg());
        System.out.format(" - HeapSort              : %3f\n", rows[1].getAvg());
        System.out.format(" - MergeSort             : %3f\n", rows[2].getAvg());
        System.out.format(" - Top-down MergeSort    : %3f\n", rows[3].getAvg());
        System.out.format(" - QuickSort             : %3f\n", rows[4].getAvg());

        for (int i = 0; i < ls.length; i++)
        {
            if (ls[i] != tdms[i])
            {
                System.out.format("Top-down Merge Sort failed at [%d]\n", i);
            }
        }
    }

    /**
     * Writes the CSV data.
     *
     * @param fileName name of the output CSV file
     * @param rows     the data to output
     *
     * @return {@code true} if successful.
     */
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

    /**
     * Contains data relevant to a single CSV row.
     * <p>
     * The information is specific to a single sorting algorithm, and the
     * data collected over multiple test runs.
     * <p>
     * The original idea for this class came from reading this
     * article/blog:<br/>
     * <a href="https://www.codeproject.com/articles/415732/reading-and-writing-csv-files-in-csharp">
     * reading-and-writing-csv-files-in-csharp</a>
     * <p>
     */
    private static final class CsvRow extends ArrayList<Double>
    {
        private static final long serialVersionUID = -5577940667264457537L;

        private String algorithm;

        /**
         * Initializes a new instance of the CsvRow class.
         *
         * @param algorithm used to sort array
         */
        public CsvRow(String algorithm)
        {
            setAlgorithm(algorithm);
        }

        /**
         * Gets the algorithm.
         *
         * @return
         */
        public String getAlgorithm()
        {
            return algorithm;
        }

        /**
         * Sets the algorithm.
         *
         * @param value
         */
        public void setAlgorithm(String value)
        {
            algorithm = value;
        }

        /**
         * Gets the average.
         *
         * @return the average
         */
        public double getAvg()
        {
            double total = 0;

            total = this.stream().reduce(total, (accumulator, item) -> accumulator + item);

            return total / size();
        }

        /**
         * Parses a CSV string.
         *
         * @param csvString the string to parse
         *
         * @return a new CsvRow populated with information from the csvString.
         */
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
