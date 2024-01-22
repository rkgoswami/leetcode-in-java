package com.rkg.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntersectionOfTwoArrays {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(intersection(new int[]{1,2}, new int[] {2,3,4,2})));
    }
    public static int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> list2 = IntStream.of(nums2)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));

        List<Integer> result = new ArrayList();
        for(int i=0; i<nums1.length; ++i) {
            if(list2.contains(nums1[i]) && !result.contains(nums1[i])) {
                //System.out.println("i : "+ i + " :: nums1[i] : " + nums1[i]);
                result.add(nums1[i]);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}
