package com.nk.lc.nums;

/**
 * @author nanke
 * @date 2026/3/4 上午12:36
 * 致终于来到这里的勇敢的人:
 * 永远不要放弃！永远不要对自己失望！永远不要逃走辜负了自己。
 * 永远不要哭啼！永远不要说再见！永远不要说慌来伤害目己。
 */
public class BinSearchV2 {

    public static void main(String[] args) {

        int[] nums = new int[]{1,3,7,5,4,3,2};

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        System.out.println(nums[left]);
    }



}
