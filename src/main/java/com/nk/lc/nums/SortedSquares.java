package com.nk.lc.nums;

public class SortedSquares {

    public static void main(String[] args) {

        int[] nums = new int[]{-7,-4,1,0,3,8,10};

        int[] ans = sortSquares(nums);

        System.out.println(ans);
    }

    private static int[] sortSquares(int[] nums) {

        int left = 0;
        int right = nums.length - 1;
        int[] result = new int[nums.length];

        for (int i = nums.length - 1; i >= 0; i--) {

            int leftVal = nums[left];
            int rightVal = nums[right];

            int leftSquare = leftVal * leftVal;
            int rightSquare = rightVal * rightVal;

            if (leftSquare >= rightSquare) {
                result[i] = leftSquare;
                left ++;
            } else {
                result[i] = rightSquare;
                right--;
            }
        }
        return result;
    }


}
