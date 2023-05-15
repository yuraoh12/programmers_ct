package org.example.p42586;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        sol1();
        sol2();
    }

    private static void sol1() {
        Solution solution = new Solution();
        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};

        int[] result = solution.solution(progresses, speeds);
        IntStream.of(result).forEach(System.out::println);
    }

    private static void sol2() {
        Solution2 solution = new Solution2();
        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};

        int[] result = solution.solution(progresses, speeds);
        IntStream.of(result).forEach(System.out::println);
    }
}

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> queue = IntStream
                .range(0, progresses.length)
                .mapToObj(i -> (int) Math.ceil((100.0 - progresses[i]) / speeds[i]))
                .collect(Collectors.toCollection(LinkedList::new));

        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int remains = queue.poll();
            int count = 1;

            while (!queue.isEmpty() && remains >= queue.peek()) {
                queue.poll();
                count++;
            }

            result.add(count);
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}

class Solution2 {
    public int[] solution(int[] progresses, int[] speeds) {
        IntStream.range(0, progresses.length)
                .forEach(i -> {
                    int day = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
                    progresses[i] = day;
                    if (i > 0) progresses[i] = Math.max(progresses[i - 1], progresses[i]);
                });

        return Arrays
                .stream(progresses)
                .boxed()
                .collect(Collectors.groupingBy(e -> e, LinkedHashMap::new, Collectors.counting()))
                .values()
                .stream()
                .mapToInt(Long::intValue)
                .toArray();
    }
}