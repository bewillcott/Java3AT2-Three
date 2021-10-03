/*
 *  File Name:    SortingTest.java
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
 * Date: 3 Oct 2021
 * ****************************************************************
 */

package com.bewsoftware.tafe.java3.at2.three.common;

import java.util.Comparator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 */
public class SortingTest
{
    private static final Integer[] RAW_DATA =
    {
        23, 48, 1, 45, 25, 876, 468, 29, 472, 580, 375, 223, 51, 271, 388, 626
    };

    private static final Integer[] TEST_DATA =
    {
        1, 23, 25, 29, 45, 48, 51, 223, 271, 375, 388, 468, 472, 580, 626, 876
    };

    public SortingTest()
    {
    }

    /**
     * Test of arraySort method, of class Sorting.
     */
    @Test
    public void testArraySort()
    {
        System.out.println("arraySort");
        Integer[] listArray = RAW_DATA.clone();
        Comparator<Integer> comparator = Integer::compareTo;
        Ref<Boolean> cancelled = new Ref<>(false);
        Sorting.arraySort(listArray, comparator, cancelled);
        assertArrayEquals(TEST_DATA, listArray, "Sort failed");
    }

    /**
     * Test of heapSort method, of class Sorting.
     */
    @Test
    public void testHeapSort()
    {
        System.out.println("heapSort");
        Integer[] listArray = RAW_DATA.clone();
        Comparator<Integer> comparator = Integer::compareTo;
        Ref<Boolean> cancelled = new Ref<>(false);
        Sorting.heapSort(listArray, comparator, cancelled);
        assertArrayEquals(TEST_DATA, listArray, "Sort failed");
    }

    /**
     * Test of mergeSort method, of class Sorting.
     */
    @Test
    public void testMergeSort()
    {
        System.out.println("mergeSort");
        Integer[] listArray = RAW_DATA.clone();
        Comparator<Integer> comparator = Integer::compareTo;
        Ref<Boolean> cancelled = new Ref<>(false);
        Sorting.mergeSort(listArray, comparator, cancelled);
        assertArrayEquals(TEST_DATA, listArray, "Sort failed");
    }

    /**
     * Test of quickSort method, of class Sorting.
     */
    @Test
    public void testQuickSort()
    {
        System.out.println("quickSort");
        Integer[] listArray = RAW_DATA.clone();
        Comparator<Integer> comparator = Integer::compareTo;
        Ref<Boolean> cancelled = new Ref<>(false);
        Sorting.quickSort(listArray, comparator, cancelled);
        assertArrayEquals(TEST_DATA, listArray, "Sort failed");
    }

    /**
     * Test of topDownMergeSort method, of class Sorting.
     */
    @Test
    public void testTopDownMergeSort()
    {
        System.out.println("topDownMergeSort");
        Integer[] listArray = RAW_DATA.clone();
        Comparator<Integer> comparator = Integer::compareTo;
        Ref<Boolean> cancelled = new Ref<>(false);
        Sorting.topDownMergeSort(listArray, comparator, cancelled);
        assertArrayEquals(TEST_DATA, listArray, "Sort failed");
    }

}
