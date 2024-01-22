package com.rkg.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* Problem Statement:
*   There are some processes that need to be executed.
*   Amount of a load that process causes on a server that runs it, is being represented by a single integer.
*   Total load caused on a server is the sum of the loads of all the processes that run on that server.
*   You have at your disposal two servers, on which mentioned processes can be run.
*   Your goal is to distribute given processes between those two servers in the way that, absolute difference of their loads will be minimized.
*
*   Given an array of n integers, of which represents loads caused by successive processes,
*   return the minimum absolute difference of server loads.
*
*   Ex:
*       Input: [1, 2, 3, 4, 5]
*       Output: 1
*       Explanation:
*           We can distribute the processes with loads [1, 2, 4] to the first server and [3, 5] to the second one,
*           so that their total loads will be 7 and 8, respectively, and the difference of their loads will be equal to 1.
*
* */
public class MinimumAbsDiffServerLoad {
    public static void main(String[] args) {
        System.out.println("Min abs diff with loads [1,2,3,4,5] : " + minimumAbsDiffServerLoad(Arrays.asList(1,2,3,4,5)));
        System.out.println("Min abs diff with loads [1,8,7,2,5]: " + minimumAbsDiffServerLoad(Arrays.asList(1,8,7,2,5)));
        System.out.println("Min abs diff with loads [3,9,7,3]: " + minimumAbsDiffServerLoad(Arrays.asList(3,9,7,3)));
        //System.out.println("Min abs diff with loads [-36,36]: " + minimumAbsDiffServerLoad(Arrays.asList(-36,36)));
        //System.out.println("Min abs diff with loads [2,-1,0,4,-2,-9]: " + minimumAbsDiffServerLoad(Arrays.asList(2,-1,0,4,-2,-9)));
    }

    public static int minimumAbsDiffServerLoad(List<Integer> loads) {
        int n = loads.size();
        int sum = loads.stream().reduce(0, (a, b) -> a + b);
        int targetSum = Math.abs(sum/2);

        // create matrix of row = n, col = targetSum
        List<List<Integer>> matrix = createMatrix(n, targetSum);

        // dp logic
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= targetSum; j++) {
                Integer prevLoad = loads.get(i-1);
                if (prevLoad <= j) {
                    // find max of sum so far (matrix[i-1][j]
                    matrix.get(i).set(j, Math.max( matrix.get(i-1).get(j), prevLoad + matrix.get(i-1).get(j - prevLoad)));
                } else {
                    matrix.get(i).set(j, matrix.get(i-1).get(j));
                }
            }
            System.out.println("----------i="+ i +"---------");
            printMatrix(matrix);
        }

        System.out.println("----------final---------");
        printMatrix(matrix);
        Integer firstServerLoad = sum - matrix.get(n).get(targetSum);
        Integer secondServerLoad = matrix.get(n).get(targetSum);

        return Math.abs(firstServerLoad - secondServerLoad);
    }

    private static void printMatrix(List<List<Integer>> matrix) {
        matrix.forEach(System.out::println);
    }

    private static List<List<Integer>> createMatrix(int n, int m) {
        List<List<Integer>> matrix = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            List<Integer> rows = new ArrayList<>();
            for (int j = 0; j <= m; j++) {
                rows.add(0);
            }
            matrix.add(rows);
        }
        return matrix;
    }
}
