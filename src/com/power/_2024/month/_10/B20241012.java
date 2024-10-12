package com.power._2024.month._10;

import java.util.*;

public class B20241012 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] split = str.split(" ");

        Map<String, Integer> map = new HashMap<>();

        for (String s : split) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        Map<Integer, List<String>> map2 = new TreeMap<>((o1, o2) -> o2.compareTo(o1));

        map.forEach((k, v) -> {
            if (v >= 3) {
                List<String> orDefault = map2.getOrDefault(v, new ArrayList<>());
                orDefault.add(k);
                map2.put(v, orDefault);
            }
        });

        List<String> result = new ArrayList<>();
        map2.forEach((k, v) -> {
            Collections.sort(v);
            result.addAll(v);
        });
        for (String s : result) {
            System.out.println(s);
        }
    }
}
