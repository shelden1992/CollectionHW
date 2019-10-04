package org.courses;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(4, 5, -6, 4, 5, 3, 4, 2, 4, 5, 7);
        Map<Integer, Integer> hashMap = new TreeMap<>();
        integers.forEach(number -> {
            if (!hashMap.containsKey(number)) {
                hashMap.put(number, 1);
            } else {
                hashMap.put(number, hashMap.get(number) + 1);
            }
        });
        Set<Map.Entry<Integer, Integer>> entries = hashMap.entrySet();
        for (Map.Entry<Integer, Integer> entr : entries
        ) {
            System.out.println(entr.getKey() + " " + entr.getValue());

        }
    }
}
