package org.example.p12945;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println(
                new Solution().solution(100)
        );
    }
}

class Solution {
    public int solution(int n) {
        return new Fibonacci().calc(n);
    }
}


class Fibonacci {
    Map<Integer, Integer> cache = new HashMap<>();

    int calc(int n) {
        if (cache.containsKey(n)) return cache.get(n);

        int rs;

        if (n <= 1) {
            rs = n;
        } else {
            rs = calc(n - 2) + calc(n - 1);
        }

        rs %= 1234567;

        cache.put(n, rs);

        return rs;
    }
}