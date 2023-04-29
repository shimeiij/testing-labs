package org.testing.alg;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class AlgMain {
    public static void main(String[] args) {
        ClosedHashingProb<String> set = new ClosedHashingProb<>(30, ClosedHashingProb.Probe.QUAD);
        String[] keys = {
                "games", "blogs", "cartoons", "music", "books", "health", "dining",
                "sunday", "store", "classified", "mobile", "update", "services"
        };
        for (String key : keys) {
            set.insert(key);
        }
        System.out.println("keys=" + Arrays.toString(keys));
        set.printTable();
        System.out.println(Arrays.toString(set.getData()));
        set.remove("cartoons");
        try {
            set.insert("ff");
            System.out.println(set.find("cartoons"));
        } catch (NoSuchElementException | ArrayIndexOutOfBoundsException exception) {
            System.out.println(exception.toString());
        }
    }
}
