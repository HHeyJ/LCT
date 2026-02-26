package com.nk.lc.nums;

public class BinSearch {

    public static void main(String[] args) {

        int[] nums = new int[]{1,2,3,4,5,6,7,8,9};

        int i = binSearch(nums, 6);
        System.out.println(i);

    }

    private static int binSearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) left = mid + 1;
            if (nums[mid] > target) right = mid - 1;
        }
        return 0;
    }


}
