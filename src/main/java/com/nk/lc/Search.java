package com.nk.lc;

public class Search {

    public static void main(String[] args) {

        int[] nums = new int[]{1,2,3,4,5,6,7,8};
        int target = 8;

        int search = search(nums, target);
        System.out.println(search);


    }

    private static int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {

            int mid = (right + left) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] < target) left = mid + 1;
            if (nums[mid] > target) right = mid - 1;
        }

        return -1;
    }




}
