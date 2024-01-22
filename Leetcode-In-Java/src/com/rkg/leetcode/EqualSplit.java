package com.rkg.leetcode;

import java.util.HashMap;
import java.util.Map;

/*
 * Given a string S, we can split S into 2 strings: S1 and S2.
 * Return the number of ways S can be split such that the number of unique characters between S1 and S2 are the same.
 * Ex-1:
 *  Input: "aaaa"
 *  Output: 3
 *  Explanation: we can get a - aaa, aa - aa, aaa - a
 *
 * Ex-2:
 *  Input: "ababa"
 *  Output: 2
 *  Explanation: ab - aba, aba - ba
 * */
public class EqualSplit {
    public static void main(String[] args) {
        System.out.println("Split count for 'aaaa': " + equalSplit("aaaa"));
        System.out.println("=============");
        System.out.println("Split count for 'bac': " + equalSplit("bac"));
        System.out.println("=============");
        System.out.println("Split count for 'ababa': " + equalSplit("ababa"));
        System.out.println("=============");
        System.out.println("Split count for 'aabbcc': " + equalSplit("aabbcc"));

    }
    private static int equalSplit(String str) {
        /*
        * Algo:
        *   1. Create a count map for characters in the string
        *   2. Create a visited map, which will be marked and increase value everytime we encounter that char.
        *   3. Based equality of countMap and visitedMap, we can increase the count of split.
        *   4. Finally return the split count.
        * */
        Map<Character, Integer> countMap = new HashMap<>();
        int uniqueChar = 0;
        for (int i = 0; i < str.length(); i++) {
            if (countMap.containsKey(str.charAt(i))) {
                countMap.put(str.charAt(i), countMap.get(str.charAt(i)) + 1);
            } else {
                uniqueChar++;
                countMap.put(str.charAt(i), 1);
            }
        }
        System.out.println(countMap);

        Map<Character, Integer> visitedMap = new HashMap<>();
        int splitCount = 0;
        int visitedUniqueChar = 0;
        for (int i = 0; i < str.length(); i++) {
            if (visitedMap.containsKey(str.charAt(i))) {
                visitedMap.put(str.charAt(i), visitedMap.get(str.charAt(i)) + 1);
            } else {
                visitedMap.put(str.charAt(i), 1);
                visitedUniqueChar++;
            }

            countMap.put(str.charAt(i), countMap.get(str.charAt(i)) - 1);

            if (countMap.get(str.charAt(i)) == 0) {
                uniqueChar--;
            }

            if (visitedUniqueChar == uniqueChar) {
                //System.out.println("splitCount++");
                splitCount++;
            }
        }
        return splitCount;
    }
}
