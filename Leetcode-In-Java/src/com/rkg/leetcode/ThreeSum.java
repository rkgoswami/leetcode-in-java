package com.rkg.leetcode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
    Problem: Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
        such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
        Notice that the solution set must not contain duplicate triplets.
     Example 1:
         Input: nums = [-1,0,1,2,-1,-4]
         Output: [[-1,-1,2],[-1,0,1]]
         Explanation:
             nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
             nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
             nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
         The distinct triplets are [-1,0,1] and [-1,-1,2].
         Notice that the order of the output and the order of the triplets does not matter.
     Example 2:
         Input: nums = [0,1,1]
         Output: []
         Explanation: The only possible triplet does not sum up to 0.
     Example 3:
         Input: nums = [0,0,0]
         Output: [[0,0,0]]
         Explanation: The only possible triplet sums up to 0.

     Constraints:
         3 <= nums.length <= 3000
         -105 <= nums[i] <= 105

    Inspiration: Think of solving for pair and restructure the solution for triplet.
        - For each item try to find a pair which has sum as expectedSum - current item.
        - Note expectedSum in this problem is 0

    Solution:
        1. Sort the given array elements in either ascending order.
        2. Iterate over the array elements from i = 0 to n - 1, where n = size of the array.
        3. For each iteration of 'i', keep a variable, localSum = expectedSum - nums[i]
            (i.e. -nums[i] as expectedSum is 0)
        4. Use 2 pointer method to find the pair to the right of 'i'
            4.1 Set first pointer j = i + 1 and second pointer k = n - 1
            4.2 While j < k, check if nums[j] + nums[k] == localSum
                4.2.1 If Yes, we found on triplet. Add the triplet as {nums[i], nums[j], nums[k]}
                    and update j = j + 1 and k = k - 1
                4.2.2 If No, move j to right if nums[j] + nums[k] < 0 else move k to left.
            Note: To avoid duplicate triplets, skip following iterations
                a. nums[i] == nums[i-1]
                b. nums[j] == nums[j-1]
            c. nums[k] == nums[k+1]
        5. Return the list of triplets.
 */

public class ThreeSum {
    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
        System.out.println(threeSum(new int[]{0,1,1}));
        System.out.println(threeSum(new int[]{0,0,0}));
        System.out.println(threeSum(new int[]{0,0,0,0}));
        System.out.println(threeSum(new int[]{0,0,0,0,0,0}));
        System.out.println(threeSum(new int[]{0,0,0,-1, -1, 1, 1, 1, -1}));
        System.out.println(threeSum(new int[]{-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6}));
        System.out.println(threeSum(new int[]{1,-1,-1,0}));

    }

    // -4, -1, -1, 0, 1, 2
    // [-1, 0, 1], [-1, -1, 2]

    static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> triplets = new ArrayList<>();

        int n = nums.length;
        // sort
        nums = Arrays.stream(nums).sorted().toArray();
        for (int i = 0; i < n - 1; i++) {
            int j = i + 1;
            int k = n - 1;
            int localSum = -nums[i];
            // Skip duplicate for i
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            while( j < k ) {
                if (nums[j] + nums[k] == localSum) {
                    // found
                    triplets.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    // skip the duplicates for k
                    while (j < k && nums[j] == nums[j + 1]) {
                        j = j + 1;
                    }
                    // skip the duplicates for k
                    while (j < k && nums[k] == nums[k - 1]) {
                        k = k - 1;
                    }
                    // update j, k
                    j = j + 1;
                    k = k - 1;
                } else if (nums[j] + nums[k] < localSum){
                    // update j
                    j = j + 1;
                } else {
                    // update k
                    k = k - 1;
                }
            }
        }
        return triplets;
    }
}
