/*
 *  File Name:    Sorting.java
 *  Project Name: Common
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

package com.bewsoftware.tafe.java3.at2.three.common;

import com.bewsoftware.tafe.java3.at2.three.common.BackgroundWorker.Parameters;
import java.util.Arrays;

/**
 * This class contains static methods designed to sort an array using a
 * {@link Comparator}.
 *
 * @author <a href="mailto:bwp.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 1.0
 * @version 1.0
 */
public class Sorting
{

    /**
     * Sorts the {@code listArray} using the
     * {@link Arrays#sort(int[]) algorithm.
     *
     * @param listArray the data to sort
     * @param bwp       not used
     */
    public static void arraySort(int[] listArray, Parameters bwp)
    {
        Arrays.sort(listArray);
    }

    /**
     * The Heap Sort algorithm.
     * <p>
     * O(n Log n)
     * <p>
     * This code was copied from:<br>
     * <a href="https://www.geeksforgeeks.org/heap-sort/">
     * https://www.geeksforgeeks.org/heap-sort/</a>
     * <br>and cleaned up.<br>
     * - Renamed parameter<br>
     * - Renamed variables<br>
     * - Replaced some code with method call
     *
     * @param listArray the data to sort
     * @param bwp       worker thread parameters
     */
    public static void heapSort(int[] listArray, Parameters bwp)
    {
        var numOfElements = listArray.length;

        // Build heap (rearrange array)
        for (int i = numOfElements / 2 - 1; i >= 0 && (bwp == null || !bwp.isCancelled()); i--)
        {
            heapify(listArray, numOfElements, i, bwp);
        }

        // One by one extract an element from heap
        for (int i = numOfElements - 1; i > 0 && (bwp == null || !bwp.isCancelled()); i--)
        {
            // Move current root to end
            // (BW) replaced with method call
            exchange(listArray, 0, i);

            // call max heapify on the reduced heap
            heapify(listArray, i, 0, bwp);
        }
    }

    /**
     * The Merge Sort algorithm.
     * <p>
     * Merge sort is based on the divide-and-conquer paradigm.
     * <p>
     * This code was copied from:<br>
     * <a href="https://www.csharpstar.com/merge-sort-csharp-program/">
     * https://www.csharpstar.com/merge-sort-csharp-program/</a>
     * <br>and cleaned up.
     *
     * @param listArray the data to sort
     * @param bwp       worker thread parameters
     */
    public static void mergeSort(int[] listArray, Parameters bwp)
    {
        mergeSortDivide(listArray, 0, listArray.length - 1, bwp);
    }

    /**
     * The Quick Sort algorithm.
     * <p>
     * Worst case: O(N²)<br>
     * Best case : O(N log N)
     * <p>
     * This code was copied from:<br>
     * <a href="http://anh.cs.luc.edu/170/notes/CSharpHtml/sorting.html">
     * http://anh.cs.luc.edu/170/notes/CSharpHtml/sorting.html</a>
     * <br>and cleaned up.
     *
     * @param listArray the data to sort
     * @param bwp       worker thread parameters
     */
    public static void quickSort(int[] listArray, Parameters bwp)
    {
        intArrayQuickSort(listArray, 0, listArray.length - 1, bwp);
    }

    /**
     * Top-down Merge Sort algorithm.
     * <p>
     * This is an implementation of the 'C-like code' here:<br>
     * <a href="https://en.wikipedia.org/wiki/Merge_sort">
     * https://en.wikipedia.org/wiki/Merge_sort</a>
     * <br>under the title: <b>Top-down implementation</b>.
     * <p>
     * I have made some logic changes, as I believe they have the sort from to
     * arrays around the wrong way.
     *
     * @param listArray the data to sort
     * @param bwp       worker thread parameters
     */
    public static void topDownMergeSort(int[] listArray, Parameters bwp)
    {
        var B = Arrays.copyOf(listArray, listArray.length);
        var A = Arrays.copyOf(listArray, listArray.length);

        // Sort data from B[] to A[]
        topDownSplitMerge(B, 0, B.length, A, bwp);

        System.arraycopy(A, 0, listArray, 0, listArray.length);
    }

    /**
     * Exchanges the specified data.
     * <p>
     * This code was copied from:<br>
     * <a href="http://anh.cs.luc.edu/170/notes/CSharpHtml/sorting.html">
     * http://anh.cs.luc.edu/170/notes/CSharpHtml/sorting.html</a>
     * <br>and cleaned up.<br>
     * - Renamed parameter
     *
     * @param list the data to sort
     * @param m    from index
     * @param n    to index
     */
    private static void exchange(int[] list, int m, int n)
    {
        var temporary = list[m];
        list[m] = list[n];
        list[n] = temporary;
    }

    /**
     * Heapifies the specified list.
     * <p>
     * This code was copied from:<br>
     * <a href="https://www.geeksforgeeks.org/heap-sort/">
     * https://www.geeksforgeeks.org/heap-sort/</a>
     * <br>and cleaned up.<br>
     * - Renamed parameters<br>
     * - Renamed variables<br>
     * - Replaced some code with method call
     *
     * @param list        the data to sort
     * @param sizeOfHeap
     * @param indexOfRoot
     * @param bwp         worker thread parameters
     */
    private static void heapify(int[] list, int sizeOfHeap,
            int indexOfRoot, Parameters bwp)
    {
        var indexOfLargest = indexOfRoot; // Initialize largest as root
        var leftIndex = 2 * indexOfRoot + 1; // left = 2*i + 1
        var rightIndex = 2 * indexOfRoot + 2; // right = 2*i + 2

        // If left child is larger than root
        if (leftIndex < sizeOfHeap && list[leftIndex] > list[indexOfLargest])
        {
            indexOfLargest = leftIndex;
        }

        // If right child is larger than largest so far
        if (rightIndex < sizeOfHeap && list[rightIndex] > list[indexOfLargest])
        {
            indexOfLargest = rightIndex;
        }

        // If largest is not root
        if (indexOfLargest != indexOfRoot && (bwp == null || !bwp.isCancelled()))
        {
            // (BW) Replaced code with method call
            exchange(list, indexOfRoot, indexOfLargest);

            // Recursively heapify the affected sub-tree
            heapify(list, sizeOfHeap, indexOfLargest, bwp);
        }
    }

    /**
     * Does a recursive quick sort.
     * <p>
     * This code was copied from:<br>
     * <a href="http://anh.cs.luc.edu/170/notes/CSharpHtml/sorting.html">
     * http://anh.cs.luc.edu/170/notes/CSharpHtml/sorting.html</a>
     * <br>and cleaned up.
     * - Renamed parameters
     *
     * @param list       the data to sort
     * @param leftIndex
     * @param rightIndex
     * @param bwp        worker thread parameters
     */
    private static void intArrayQuickSort(int[] list, int leftIndex,
            int rightIndex, Parameters bwp)
    {

        var i = leftIndex;
        var j = rightIndex;

        var x = list[(leftIndex + rightIndex) / 2];

        /*
         * find pivot item
         */
        while (bwp == null || !bwp.isCancelled())
        {
            while (list[i] < x)
            {
                i++;
            }
            while (x < list[j])
            {
                j--;
            }
            if (i <= j)
            {
                exchange(list, i, j);
                i++;
                j--;
            }
            if (i > j)
            {
                break;
            }
        }

        if (leftIndex < j)
        {
            intArrayQuickSort(list, leftIndex, j, bwp);
        }
        if (i < rightIndex)
        {
            intArrayQuickSort(list, i, rightIndex, bwp);
        }
    }

    /**
     * Sort each sub-array merging them back together.
     * <p>
     * First sub-array is {@code list[leftIndex..midIndex]} <br>
     * Second sub-array is {@code list[midIndex + leftIndex..rightIndex]} <br>
     * This code was copied from:<br>
     * <a href="https://www.geeksforgeeks.org/merge-sort/">
     * https://www.geeksforgeeks.org/merge-sort/</a>
     * <br>and cleaned up.
     *
     * @param list       the data to sort
     * @param leftIndex
     * @param midIndex
     * @param rightIndex
     * @param bwp        worker thread parameters
     */
    private static void mergeSortConquer(int[] list, int leftIndex, int midIndex,
            int rightIndex, Parameters bwp)
    {
        // Find sizes of two
        // sub-arrays to be merged
        var leftArraySize = midIndex - leftIndex + 1;
        var rightArraySize = rightIndex - midIndex;

        // Create temp arrays
        var leftArray = new int[leftArraySize];
        var rightArray = new int[rightArraySize];
        int leftArrayIndex;
        int rightArrayIndex;

        // Copy data to temp arrays
        for (leftArrayIndex = 0; leftArrayIndex < leftArraySize
                && (bwp == null || !bwp.isCancelled()); ++leftArrayIndex)
        {
            leftArray[leftArrayIndex] = list[leftIndex + leftArrayIndex];
        }

        for (rightArrayIndex = 0; rightArrayIndex < rightArraySize
                && (bwp == null || !bwp.isCancelled()); ++rightArrayIndex)
        {
            rightArray[rightArrayIndex] = list[midIndex + 1 + rightArrayIndex];
        }

        // Merge the temp arrays
        // Initial indexes of first
        // and second sub-arrays
        leftArrayIndex = 0;
        rightArrayIndex = 0;

        // Initial index of merged sub-array
        var listIndex = leftIndex;

        while (leftArrayIndex < leftArraySize && rightArrayIndex < rightArraySize
                && (bwp == null || !bwp.isCancelled()))
        {
            // Sort back into original list array
            if (leftArray[leftArrayIndex] <= rightArray[rightArrayIndex])
            {
                list[listIndex] = leftArray[leftArrayIndex];
                leftArrayIndex++;
            } else
            {
                list[listIndex] = rightArray[rightArrayIndex];
                rightArrayIndex++;
            }

            listIndex++;
        }

        // Copy remaining elements of leftArray, if any
        while (leftArrayIndex < leftArraySize && (bwp == null || !bwp.isCancelled()))
        {
            list[listIndex] = leftArray[leftArrayIndex];
            leftArrayIndex++;
            listIndex++;
        }

        // Copy remaining elements of rightArray, if any
        while (rightArrayIndex < rightArraySize && (bwp == null || !bwp.isCancelled()))
        {
            list[listIndex] = rightArray[rightArrayIndex];
            rightArrayIndex++;
            listIndex++;
        }
    }

    /**
     * Divide the list into working sub-lists.
     * <p>
     * First sub-array is {@code list[leftIndex..midIndex]} <br>
     * Second sub-array is {@code arr[midIndex+1..rightIndex]}
     * <p>
     * This code was copied from:<br>
     * <a href="https://www.geeksforgeeks.org/merge-sort/">
     * https://www.geeksforgeeks.org/merge-sort/</a>
     * <br>and cleaned up.
     *
     * @implNote
     * midIndex is currently implemented as:<br>
     * <code>
     * var midIndex = leftIndex + (rightIndex - leftIndex) / 2;
     * </code>
     *
     * @param list       the data to sort
     * @param leftIndex  index of the left
     * @param rightIndex index of the right
     * @param bwp        worker thread parameters
     */
    private static void mergeSortDivide(int[] list, int leftIndex,
            int rightIndex, Parameters bwp)
    {
        if (leftIndex < rightIndex)
        {
            // Find the middle point
            var midIndex = leftIndex + (rightIndex - leftIndex) / 2;

            // Sort first and
            // second halves
            mergeSortDivide(list, leftIndex, midIndex, bwp);
            mergeSortDivide(list, midIndex + 1, rightIndex, bwp);

            // Merge the sorted halves
            mergeSortConquer(list, leftIndex, midIndex, rightIndex, bwp);
        }
    }

    /**
     * Top down merge.
     * <p>
     * Left source half is {@code A[iBegin..iMiddle-1]} .<br>
     * Right source half is {@code A[iMiddle..iEnd-1]} .<br>
     * Result is {@code B[iBegin..iEnd-1]} .
     *
     * @param A       source data
     * @param iBegin  beginning index
     * @param iMiddle middle index
     * @param iEnd    ending index
     * @param B       target data
     * @param bwp     worker thread parameters
     */
    private static void topDownMerge(int[] A, int iBegin, int iMiddle, int iEnd, int[] B,
            Parameters bwp)
    {
        var i = iBegin;

        var j = iMiddle;

        for (int k = iBegin; k < iEnd && (bwp == null || !bwp.isCancelled()); k++)
        {
            if (i < iMiddle && (j >= iEnd || A[i] <= A[j]))
            {
                B[k] = A[i];
                i++;
            } else
            {
                B[k] = A[j];
                j++;
            }
        }
    }

    /**
     * Top down split merge.
     * <p>
     * Split A[] into 2 runs, sort both runs into B[], merge both runs from B[]
     * to A[].<br>
     * iBegin is inclusive; iEnd is exclusive.<br>
     * (A[iEnd] is not in the set).
     *
     * @param B      working copy
     * @param iBegin beginning index
     * @param iEnd   ending index
     * @param A      original data
     * @param bwp    worker thread parameters
     */
    private static void topDownSplitMerge(int[] B, int iBegin, int iEnd, int[] A,
            Parameters bwp)
    {
        if (iEnd - iBegin > 1 && (bwp == null || !bwp.isCancelled()))
        {
            var iMiddle = (iEnd + iBegin) / 2;

            topDownSplitMerge(A, iBegin, iMiddle, B, bwp);
            topDownSplitMerge(A, iMiddle, iEnd, B, bwp);

            topDownMerge(B, iBegin, iMiddle, iEnd, A, bwp);
        }
    }

    /**
     * Not intended to be instantiated.
     */
    private Sorting()
    {
    }

}
