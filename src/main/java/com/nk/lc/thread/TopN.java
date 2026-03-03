package com.nk.lc.thread;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @author nanke
 * @date 2026/3/3 下午11:23
 * 致终于来到这里的勇敢的人:
 * 永远不要放弃！永远不要对自己失望！永远不要逃走辜负了自己。
 * 永远不要哭啼！永远不要说再见！永远不要说慌来伤害目己。
 */
public class TopN {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int partNum = 10;
        int totalUser = 10000;
        int k = 10;
        // 获取总数据
        List<Integer> userIds = getUserIds(totalUser);
        // 切分小块
        List<List<Integer>> lists = splitList(userIds, partNum);
        // 初始化线程池
        ExecutorService executorService = Executors.newFixedThreadPool(partNum);
        // 提交任务
        List<Future<Map<Integer,Integer>>> futures = new ArrayList<>();
        for (List<Integer> list : lists) {
            Callable<Map<Integer,Integer>> task = () -> partMap(list);
            futures.add(executorService.submit(task));
        }
        // 合并
        Map<Integer, Integer> globalMap = new HashMap<>();
        for (Future<Map<Integer, Integer>> future : futures) {
            Map<Integer, Integer> partMap = future.get();
            for (Map.Entry<Integer, Integer> entry : partMap.entrySet()) {
                globalMap.merge(entry.getKey(),entry.getValue(),Integer::sum);
            }
        }
        // 最小堆排序
        Queue<Map.Entry<Integer, Integer>> queue = topK(globalMap, k);
        while (!queue.isEmpty()) {
            Map.Entry<Integer, Integer> entry = queue.poll();
            System.out.println(String.format("userId:%s, count:%s",entry.getKey(),entry.getValue()));
        }
        executorService.shutdown();
    }

    private static List<Integer> getUserIds(int total) {
        List<Integer> ans = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < total; i++) {
            ans.add(random.nextInt(1000));
        }
        return ans;
    }

    private static List<List<Integer>> splitList(List<Integer> userIds, int numPart) {
        List<List<Integer>> ans = new ArrayList<>();
        int size = userIds.size();
        int num = size / numPart;
        int start = 0;
        for (int i = 0; i < numPart; i++) {
            ans.add(new ArrayList<>(userIds.subList(start,start + num)));
            start = start + num;
        }
        return ans;
    }

    private static Map<Integer, Integer> partMap(List<Integer> partList) {
        Map<Integer, Integer> ans = new HashMap<>();
        for (Integer userId : partList) {
            ans.put(userId,ans.getOrDefault(userId,0) + 1);
        }
        return ans;
    }

    private static Queue<Map.Entry<Integer,Integer>> topK(Map<Integer,Integer> map, int k) {
        PriorityQueue<Map.Entry<Integer,Integer>> queue =
                new PriorityQueue<>(Comparator.comparing(Map.Entry::getValue));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (queue.size() < k) {
                queue.offer(entry);
            } else {
                if (queue.peek().getValue() < entry.getValue()) {
                    queue.poll();
                    queue.offer(entry);
                }
            }
        }
        return queue;
    }

}
