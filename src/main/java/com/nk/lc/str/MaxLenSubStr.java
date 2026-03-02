package com.nk.lc.str;

public class MaxLenSubStr {

    public static void main(String[] args) {

        String s1 = "ABCDF";
        String s2 = "CBCD";

        int i = maxLenSubStr(s1, s2);
        System.out.println(i);

    }

    private static int maxLenSubStr(String s1, String s2) {

        int[][] dp = new int[s1.length() + 1][ s2.length() + 1];

        int maxLen = 0;
        int index = 0;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    if (dp[i][j] > maxLen) {
                        maxLen = dp[i][j];
                        index = i;
                    }
                }
            }
        }
        System.out.println(s1.substring(index - maxLen,index));
        return maxLen;
    }


}
