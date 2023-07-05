package org.example.p42583;


import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SolutionTests {

    @Test
    @DisplayName("100, 100, {10} → 101")
    void t01() {
        assertThat(
                new Solution().solution(100, 100, new int[]{10})
        ).isEqualTo(
                101
        );
    }

    @Test
    @DisplayName("100, 100, {10, 10} → 102")
    void t02() {
        assertThat(
                new Solution().solution(100, 100, new int[]{10, 10})
        ).isEqualTo(
                102
        );
    }

    @Test
    @DisplayName("100, 100, {10,10,10,10,10,10,10,10,10,10} → 110")
    void t03() {
        assertThat(
                new Solution().solution(100, 100, new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10})
        ).isEqualTo(
                110
        );
    }

    @Test
    @DisplayName("2, 10, {7,4,5,6} → 8")
    void t04() {
        assertThat(
                new Solution().solution(2, 10, new int[]{7, 4, 5, 6})
        ).isEqualTo(
                8
        );
    }

    @Test
    @DisplayName("Solution2 : 100, 100, {10} → 101")
    void t05_1() {
        assertThat(
                new Solution2().solution(100, 100, new int[]{10})
        ).isEqualTo(
                101
        );
    }

    @Test
    @DisplayName("Solution2 : 100, 100, {10, 10} → 102")
    void t05_2() {
        assertThat(
                new Solution2().solution(100, 100, new int[]{10, 10})
        ).isEqualTo(
                102
        );
    }

    @Test
    @DisplayName("Solution2 : 100, 100, {10,10,10,10,10,10,10,10,10,10} → 110")
    void t05_3() {
        assertThat(
                new Solution2().solution(100, 100, new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10})
        ).isEqualTo(
                110
        );
    }

    @Test
    @DisplayName("Solution2 : 2, 10, {7,4,5,6} → 8")
    void t05_4() {
        assertThat(
                new Solution2().solution(2, 10, new int[]{7, 4, 5, 6})
        ).isEqualTo(
                8
        );
    }
}
