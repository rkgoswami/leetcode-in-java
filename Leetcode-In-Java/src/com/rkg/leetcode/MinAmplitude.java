package com.rkg.leetcode;


import java.util.Arrays;
import java.util.List;

/*
* Given an Array A, find the minimum amplitude you can get after changing up to 3 elements.
* Amplitude is the range of the array (basically difference between largest and smallest element).
*
* Ex-1:
*   Input: [-1, 3, -1, 8, 5, 4]
*   Output: 2
*   Explanation: we can change -1, -1, 8 to 3, 4 or 5
*
* Ex-2:
*   Input: [10, 10, 3, 4, 10]
*   Output: 0
*   Explanation: change 3 and 4 to 10
* */
public class MinAmplitude {
    public static void main(String[] args) {
        System.out.println("Min Amplitude for [-1, 3, -1, 8, 5, 4]: " + minAmplitude(Arrays.asList(-1, 3, -1, 8, 5, 4)));
        System.out.println("Min Amplitude for [10, 10, 3, 4, 10]: " +minAmplitude(Arrays.asList(10, 10, 3, 4, 10)));
    }

    public static Integer minAmplitude(List<Integer> arr) {
        Integer minAmplitude = Integer.MAX_VALUE;
        arr.sort((a,b) -> a - b);

        Integer start = 3, end = arr.size() - 1;
        while (start >= 0) {
            Integer localMin = getMinAmplitude(arr, start, end);
            if (minAmplitude > localMin) {
                minAmplitude = localMin;
            }
            System.out.println("LocalMin: "+ localMin + " minAmplitude: "+ minAmplitude);
            --start;
            --end;
        }

        return minAmplitude;
    }

    public static Integer getMinAmplitude(List<Integer> arr, Integer start, Integer end) {
        return arr.get(end) - arr.get(start);
    }
}
