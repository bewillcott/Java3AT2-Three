/*
 *  File Name:    Helper.java
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
 * Date: 28 Sept 2021
 * ****************************************************************
 */

package com.bewsoftware.tafe.java3.at2.three.common;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.BiConsumer;
import org.apache.commons.lang3.time.StopWatch;

/**
 * Helper class description.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 1.0
 * @version 1.0
 */
public class Helper
{
    /// <summary>
    /// Defines the list.
    /// </summary>
    private final List<Integer> list;

    /// <summary>
    /// Defines the max.
    /// </summary>
    private final int max;

    /// <summary>
    /// Defines the seed.
    /// </summary>
    private final int seed;

    private final int arraySize;

    /// <summary>
    /// Defines the watch.
    /// </summary>
    private final StopWatch watch;

    /// <summary>
    /// Initializes a new instance of the <see cref="Helper"/> class.
    /// </summary>
    /// <param name="list">The list<see cref="int[]"/>.</param>
    /// <param name="seed">The seed<see cref="int"/>.</param>
    /// <param name="min">The min<see cref="int"/>.</param>
    /// <param name="max">The max<see cref="int"/>.</param>
    public Helper(List<Integer> list, int seed, int max, int arraySize)
    {
        this.list = Objects.requireNonNull(list, "List must not be null!");
        this.seed = seed;
        this.max = max;
        this.arraySize = arraySize;
        watch = new StopWatch();
    }

    /// <summary>
    /// Generate an int array of random numbers.
    /// </summary>
    public double generateIntArray()
    {
        if (seed != -1)
        {
            var r = new Random(seed);
            list.clear();

            for (int i = 0; i < arraySize; i++)
            {
                list.add(r.nextInt(max) + 1);
            }
        }

        return 0;
    }

    /// <summary>
    /// Sorts it.
    /// </summary>
    /// <param name="methodName">Name of the method.</param>
    /// <param name="text">The text.</param>
    /// <param name="bw">Worker thread.</param>
    /// <returns>The elapsed time.</returns>
    public double sortIt(BiConsumer<int[], Ref<Boolean>> methodName, String text, Ref<Boolean> cancelled)
    {
        System.out.format("Processing %s => ", text);
        double elapsedTime;  // time in second, accurate to about milliseconds
        generateIntArray();

        if (methodName != null)
        {
            // create working copy
            var listArray = new int[list.size()];

            for (int i = 0; i < list.size(); i++)
            {
                listArray[i] = list.get(i);
            }

            watch.reset();
            watch.start();
            methodName.accept(listArray, cancelled);
            watch.stop();

            // Copy sorted array back over the 'list'
            list.clear();

            for (int i = 0; i < list.size(); i++)
            {
                list.add(listArray[i]);
            }
        } else
        {
            watch.reset();
            watch.start();
//  TODO:          list.sort(c);
            watch.stop();
        }

        elapsedTime = watch.getTime() / 1000.0;
        System.out.format("%3f\n", elapsedTime);

        return elapsedTime;
    }
}
