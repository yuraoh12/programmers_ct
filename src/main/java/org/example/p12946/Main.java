package org.example.p12946;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(
                Arrays.deepToString(new Solution().solution(4))
        );
    }
}

class Solution {
    public static boolean isDebug = false;

    public int[][] solution(int n) {
        return new Hanoi(1, 3, n).toArray();
    }
}

class Hanoi {
    private final List<int[]> paths = new ArrayList<>();
    private int calculateCallCount = 0;

    public Hanoi(int from, int to, int n) {
        calculate(from, to, n, 1);
    }

    // from : 시작점
    // to : 목적지
    // n : 옮길 원판 개수
    // empty : from 도 아니고 to 도 아닌 기둥 번호
    private void calculate(int from, int to, int n, int depth) {
        calculateCallCount++;
        String debugLineHead = "\t".repeat(depth - 1) + String.format("%03d : (%d) => (%d), %d개 옮기기", calculateCallCount, from, to, n);

        if (Solution.isDebug) System.out.println(debugLineHead + " - 시작");

        if (n == 1) {
            addPath(from, to);

            if (Solution.isDebug) System.out.println(debugLineHead + " - 끝(addPath)");
            return;
        }

        int empty = 6 - from - to;

        calculate(from, empty, n - 1, depth + 1);
        calculate(from, to, 1, depth + 1);
        calculate(empty, to, n - 1, depth + 1);

        if (Solution.isDebug) System.out.println(debugLineHead + " - 끝");
    }

    private void addPath(int from, int to) {
        paths.add(new int[]{from, to});
    }

    public int[][] toArray() {
        // List<int[]> => int[][]
        return paths
                .stream()
                .toArray(int[][]::new);
    }
}