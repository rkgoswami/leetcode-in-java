package com.rkg.leetcode;

/*
 * Problem: https://leetcode.com/problems/count-and-say/
 */

public class CountAndSay {
    public static void main(String[] args) {
        System.out.println("countAndSay(1): " + countAndSay(1));
        System.out.println("countAndSay(4): " + countAndSay(4));
        System.out.println("countAndSay(6): " + countAndSay(6));
    }
    public static String countAndSay(int n) {
        String result = "1";
        if(n == 1) {
            return result;
        } else {
            while (n > 1) {
                result = countAndSayHelper(result);
                n--;
            }
        }
        return result;
    }

    private static String countAndSayHelper(String s) {
        /*
            1. Set count to 0
            2. for each iteration over string from i = 0 to string length
                2.1 Increase count by 1
                2.2 If a[i] != a[i+1] || i == n - 1
                    2.2.1 Set value with result += count + '' + a[i]
                    2.2.2 Set count as 0
        */
        int count = 0;
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < s.length(); ++i) {
            count++;
            if (i == s.length() - 1 || s.charAt(i) != s.charAt(i + 1)) {
                result.append(count).append(s.charAt(i));
                count = 0;
            }
        }
        return result.toString();
    }

}