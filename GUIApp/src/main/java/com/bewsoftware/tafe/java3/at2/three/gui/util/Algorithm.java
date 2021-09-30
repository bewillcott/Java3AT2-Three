/*
 *  File Name:    Algorithm.java
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Listing of all of the Sorting Algorithm and their
 * individual label entries to be used in the ListView.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 1.0
 * @version 1.0
 */
public enum Algorithm
{
    UNSORTED("= Unsorted ="),
    ARRAYSORT("Arrays.sort"),
    HEAPSORT("Heap Sort"),
    MERGESORT("Merge Sort"),
    QUICKSORT("Quick Sort");

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
