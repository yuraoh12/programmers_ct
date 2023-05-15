package org.example.p43163;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
}

class Solution {
    public int solution(String begin, String target, String[] words) {
        Queue<State> queue = new LinkedList<>();

        queue.add(new State(begin, 0));

        boolean[] visited = new boolean[words.length];

        while (!queue.isEmpty()) {
            State state = queue.poll();

            // 오아시스인지 체크하고, 맞으면 바로 끝냄, 왜냐하면 나는 중심점으로 부터 찔끔찔끔 움직이고 있으니
            // 발견되는 그 지점은 중심점에서 가장 가까운 지점이기 때문이다.
            if (state.word.equals(target)) return state.depth;

            // 오아시스가 아닌 경우
            for (int i = 0; i < words.length; i++) {
                if (visited[i]) continue; // 이미 탐색할곳으로 등록된 경우는 무시

                // 비록 오아시스는 아니지만
                // 그 지점으로 부터 뻗어나갈 수 있는 경로가 있다면
                if (isConvertible(state.word, words[i])) {
                    // 추후 탐색지점으로 등록한다.
                    visited[i] = true; // 해당 지점은 이미 탐색되었음을 알림
                    queue.add(new State(words[i], state.depth + 1));
                }
            }
        }

        return 0;
    }

    private static class State {
        private String word;
        private int depth;

        public State(String word, int depth) {
            this.word = word;
            this.depth = depth;
        }
    }

    public boolean isConvertible(String word1, String word2) {
        int diffCount = 0;

        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i))
                diffCount++;

            if (diffCount > 1) return false;
        }

        return true;
    }
}