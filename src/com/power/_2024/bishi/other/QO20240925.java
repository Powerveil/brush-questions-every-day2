package com.power._2024.bishi.other;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 2024-09-25 chengzhang 下午笔试
 */
public class QO20240925 {
    public static void main(String[] args) {
        short s = 1;

//        System.out.println(Arrays.toString(test01(16, new int[]{10, 15, 30, 1, 19})));
//        System.out.println(Arrays.toString(test011(16, new int[]{10, 15, 30, 1, 19})));

//        System.out.println(Arrays.toString(test01(10, new int[]{5, 15, 30, 1, 9})));
        System.out.println(Arrays.toString(test011(10, new int[]{5, 15, 30, 9, 1})));

//        for (int i = 0; i < 20; i++) {
//            System.out.println(Arrays.toString(test011(20, new int[]{10, 15, 30, 1, 19})));
//        }
        test02();

//        test03();
    }

    /**
     * 第一题 两数只和（要求时间复杂度为O(n)）
     *
     * @param target 输入的目标值
     * @param nums   输入数组 (不重复元素，并且不能有target一半的数)
     * @return 返回数据，1.保证顺序
     */
    public static int[] test01(int target, int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int index = 0; index < nums.length; index++) {
            map.put(nums[index], index);
            if (map.containsKey(target - nums[index])) {
                return new int[]{Math.min(map.get(target - nums[index]), index), Math.max(map.get(target - nums[index]), index)};
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 第一题 两数只和（要求时间复杂度为O(n)）
     *
     * @param target 输入的目标值
     * @param nums   输入数组 (不重复元素)
     * @return 返回数据，1.保证顺序 2.不重复元素
     */
    public static int[] test011(int target, int[] nums) {
        int[] result = new int[]{-1, -1};
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        for (int index = 0; index < nums.length; index++) {
            map.put(nums[index], index);
        }

        map.keySet().stream().anyMatch(k -> {
            Integer index = map.get(k);
            map.remove(k);
            if (map.containsKey(target - k)) {
                result[0] = Math.min(index, map.get(target - k));
                result[1] = Math.max(index, map.get(target - k));
                return true;
            }
            return false;
        });

        return result;
    }

    /**
     * 第二题 一百个线程争抢一个资源不超卖
     */
    public static void test02() {
        int size = 150;
        for (int i = 0; i < size; i++) {
            new Thread(new MyThread()).start();
        }
    }

    private static class MyThread implements Runnable {
        /**
         * 票的数量
         */
        private static int count = 100;

        @Override
        public void run() {
            synchronized (MyThread.class) {
                if (count == 0) {
                    return;
                }
                System.out.println(Thread.currentThread().getName() + ":" + count--);
            }
        }
    }

    /**
     * 测试ConcurrentHashMap是否是有顺序的 结论无序，看似有序是因为hash桶的位置
     */
    private static void test03() {

//        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> map = new ConcurrentHashMap<>();

        int[] nums = new int[]{13, 23, 543643, 1, 2, 3, 4, 9, 5};
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

//        map.keySet().stream().forEach(k -> {
//            System.out.println(k + ":" + map.get(k));
//        });

        map.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });
    }
}
