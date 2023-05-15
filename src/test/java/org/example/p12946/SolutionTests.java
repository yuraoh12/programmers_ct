package org.example.p12946;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SolutionTests {
    @BeforeAll
    void beforeAll() {
        Solution.isDebug = true;
    }

    @Test
    @DisplayName("n=1 => [[1,3]]")
    void t01() {
        assertThat(
                new Solution().solution(1)
        ).isEqualTo(
                new int[][]{{1, 3}}
        );
    }

    @Test
    @DisplayName("n=2 => [[1,2], [1,3], [2,3]]")
    void t02() {
        assertThat(
                new Solution().solution(2)
        ).isEqualTo(
                new int[][]{{1, 2}, {1, 3}, {2, 3}}
        );
    }

    @Test
    @DisplayName("from=1, to=2, n=2 => [[1,3], [1,2], [3,2]]")
    void t03() {
        assertThat(
                new Hanoi(1, 2, 2).toArray()
        ).isEqualTo(
                new int[][]{{1, 3}, {1, 2}, {3, 2}}
        );
    }

    @Test
    @DisplayName("from=2, to=1, n=2 => [[2,3], [2,1], [3,1]]")
    void t04() {
        assertThat(
                new Hanoi(2, 1, 2).toArray()
        ).isEqualTo(
                new int[][]{{2, 3}, {2, 1}, {3, 1}}
        );
    }

    @Test
    @DisplayName("3 => [[1, 3], [1, 2], [3, 2], [1, 3], [2, 1], [2, 3], [1, 3]]")
    void t05() {
        assertThat(
                new Solution().solution(3)
        ).isEqualTo(new int[][]{{1, 3}, {1, 2}, {3, 2}, {1, 3}, {2, 1}, {2, 3}, {1, 3}});
    }

    @Test
    @DisplayName("4 => [[1, 2], [1, 3], [2, 3], [1, 2], [3, 1], [3, 2], [1, 2], [1, 3], [2, 3], [2, 1], [3, 1], [2, 3], [1, 2], [1, 3], [2, 3]]")
    void t06() {
        assertThat(
                new Solution().solution(4)
        ).isEqualTo(new int[][]{{1, 2}, {1, 3}, {2, 3}, {1, 2}, {3, 1}, {3, 2}, {1, 2}, {1, 3}, {2, 3}, {2, 1}, {3, 1}, {2, 3}, {1, 2}, {1, 3}, {2, 3}});
    }
}