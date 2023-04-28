package org.testing.alg;

import java.util.Arrays;

public class AlgMain {
    public static void main(String[] args) {
        ClosedHashingProb<String> set = new ClosedHashingProb<>(29, ClosedHashingProb.Probe.DOUBLE);
        String[] keys = {
                "games", "blogs", "cartoons", "music", "books", "health", "dining",
                "sunday", "store", "classified", "mobile", "update", "services"
        };
        for (String key : keys) {
            set.insert(key);
        }
        System.out.println("keys=" + Arrays.toString(keys));
        set.printTable();
//        System.out.println(set.find("aaa"));
    }
}
