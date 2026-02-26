package com.nk.lc.nums;

public class RemoveElement {

    public static void main(String[] args) {

        int[] nums = new int[]{1,2,3,4,5,5,7,8,9};

        int i = removeElement(nums, 5);

        System.out.println(i);
    }

    /**
     * 快慢指针
     */
    private static int removeElement(int[] nums, int element) {
        // 快指针
        int slow = 0;
        // 初始长度
        int len = nums.length;
        for (int fast = 0; fast < nums.length; fast++) {
            int val = nums[fast];
            // 快指针覆盖慢指针
            if (val != element) {
                nums[slow] = val;
                slow++;
            } else {
                // 长度-1
                len--;
            }
        }
        return len;
    }

    /**
     * 暴力
     */
    private static int removeElement1(int[] nums, int element) {
        // 初始长度
        int len = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == element) {
                for (int j = i; j < nums.length - 1; j++) {
                    nums[j] = nums[j+1];
                }
                // 退回一次，防止连续重复
                len--;
                i--;
            }
        }
        return len;
    }

}
