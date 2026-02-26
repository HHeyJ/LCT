package com.nk.lc.nums;

public class MinSubArrayLen {

    public static void main(String[] args) {

        int[] nums = new int[]{2,3,1,2,4,3};

        int i = minSubArrayLen(nums, 7);
        System.out.println(i);

    }


    private static int minSubArrayLen(int[] nums, int sum) {

        int left = 0;
        int right = 0;
        int ans = Integer.MAX_VALUE;

        while (left < nums.length && right < nums.length) {

            int total = 0;
            for (int i = left; i <= right; i++) {
                total += nums[i];
            }

            if (total >= sum) {
                ans = Math.min(ans,right - left + 1);
                left++;
            } else {
                right++;
            }

        }
        return ans;
    }

}
