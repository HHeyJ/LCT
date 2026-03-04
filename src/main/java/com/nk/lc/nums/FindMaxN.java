package com.nk.lc.nums;

import java.util.Comparator;
import java.util.PriorityQueue;

public class FindMaxN {

    public static void main(String[] args) {

        int[] nums = new int[]{1,2,3,4,5,6,7,8,9,10};

        findMaxThree(nums);

    }


    private static void findMaxN(int[] nums,int n) {
        // 最小堆实现(优先队列)
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparing(Integer::valueOf));
        for (int i = 0; i < nums.length; i++) {
            if (queue.size() < n) {
                queue.offer(nums[i]);
            } else {
                if (nums[i] > queue.peek()) {
                    queue.poll();
                    queue.offer(nums[i]);
                }
            }
        }

        for (Integer i : queue) {
            System.out.println(i);
        }
    }

    private static void findMaxThree(int[] nums) {
        // a b c
        int a = Integer.MIN_VALUE;
        int b = Integer.MIN_VALUE;
        int c = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num > a) {
                c = b;
                b = a;
                a = num;
            } else if (num > b) {
                c = b;
                b = num;
            } else if (num > c) {
                c = num;
            }
        }

        System.out.println(a + "_" + b + "_" + c);
    }



}
