package org.example.p42840;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(
                        new Solution().solution(new int[]{1, 2, 3, 4, 5})
                )
        );
        // [1]


        System.out.println(
                Arrays.toString(
                        new Solution2().solution(new int[]{1, 3, 2, 4, 2})
                )
        );
        // [1, 2, 3]
    }
}

class Solution {
    private Map<Integer, Integer> scores = new HashMap<>();

    public int[] solution(int[] answers) {
        // 최고점수 얻고
        int maxPoint = IntStream.rangeClosed(1, 3)
                .map(patterNo -> scoreOf(answers, patterNo))
                .max()
                .getAsInt();

        // 각각의 방법으로 했을 때의 점수를 얻은 후 그것이 최고점수이면 배열에 수포자 번호 담는다.
        return IntStream.rangeClosed(1, 3)
                .filter(patterNo -> maxPoint == scoreOf(answers, patterNo))
                .toArray();
    }

    private int scoreOf1(int[] answers) {
        return scoreOf(answers, new int[]{1, 2, 3, 4, 5});
    }

    private int scoreOf2(int[] answers) {
        return scoreOf(answers, new int[]{2, 1, 2, 3, 2, 4, 2, 5});
    }

    private int scoreOf3(int[] answers) {
        return scoreOf(answers, new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5});
    }

    // 순수계산함수를 호출하는 함수, 단 캐시가 있으면 순수계산함수를 호출하지 않음
    private int scoreOf(int[] answers, int patternNo) {
        System.out.println("scoreOf(캐시) 호출, 캐시 키 : patternNo = " + patternNo);
        // 일단 바로 계산을 하기 전에 cache 뒤져본다.
        if (scores.containsKey(patternNo)) return scores.get(patternNo);

        int score = _scoreOf(answers, patternNo);

        // 계산한 점수를 cache 에 저장한다.
        scores.put(patternNo, score);

        return score;
    }

    // 순수계산함수
    private int _scoreOf(int[] answers, int patternNo) {
        if (patternNo == 1) return scoreOf1(answers);
        else if (patternNo == 2) return scoreOf2(answers);

        return scoreOf3(answers);
    }

    private int scoreOf(int[] answers, int[] pattern) {
        System.out.println("순수계산함수, 호출");

        int score = 0;

        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == pattern[i % pattern.length]) {
                score++;
            }
        }

        return score;
    }
}

class Solution2 {
    public int[] solution(int[] answers) {
        Map<Integer, Long> rightAnswerCountsByStyleNo =
                IntStream.range(0, answers.length)
                        .mapToObj(i -> {
                            List<Integer> l = new ArrayList<>();

                            if (answers[i] == answerOfSt1(i)) l.add(1);
                            if (answers[i] == answerOfSt2(i)) l.add(2);
                            if (answers[i] == answerOfSt3(i)) l.add(3);

                            return l;
                        })
                        .flatMap(List::stream)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        long max = rightAnswerCountsByStyleNo
                .values()
                .stream()
                .max(Long::compareTo)
                .get();

        return rightAnswerCountsByStyleNo
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == max)
                .mapToInt(Map.Entry::getKey)
                .sorted()
                .toArray();
    }

    int answerOfSt1(int i) {
        int[] answers = new int[]{1, 2, 3, 4, 5};
        return answers[i % answers.length];
    }

    int answerOfSt2(int i) {
        int[] answers = new int[]{2, 1, 2, 3, 2, 4, 2, 5};
        return answers[i % answers.length];
    }

    int answerOfSt3(int i) {
        int[] answers = new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        return answers[i % answers.length];
    }
}