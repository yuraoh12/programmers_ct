package org.example.p92343;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class SolutionTests {
    @Test
    @DisplayName("{0,0,1,1,1,0,1,0,1,0,1,1}, {{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}} → 5")
    void t01() {
        assertThat(
                new Solution().solution(
                        new int[]{0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1},
                        new int[][]{{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}}
                )
        ).isEqualTo(
                5
        );
    }

    @Test
    @DisplayName("getNextNodes")
    void t02() {
        PathCalculator pathCalculator = new PathCalculator(
                new int[]{0, 0, 0},
                new int[][]{{0, 1}, {0, 2}}
        );

        assertThat(
                pathCalculator.getNextNodes(0)
        )
                .isEqualTo(
                        List.of(1, 2)
                );
    }

    @Test
    @DisplayName("getNextNodes with history")
    void t03() {
        PathCalculator pathCalculator = new PathCalculator(
                new int[]{0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0},
                new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 8}, {6, 9}, {9, 10}}
        );

        assertThat(
                pathCalculator.getNextNodes(4, List.of(0, 1, 4))
        )
                .containsAll(
                        List.of(3, 8, 2)
                );
    }

    @Test
    @DisplayName("getNextNodes with history")
    void t04() {
        PathCalculator pathCalculator = new PathCalculator(
                new int[]{0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0},
                new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 8}, {6, 9}, {9, 10}}
        );

        assertThat(
                pathCalculator.getNextNodes(5, List.of(0, 2, 5))
        )
                .containsAll(
                        List.of(1, 6)
                );
    }

    @Test
    @DisplayName("getNextNodes with history")
    void t05() {
        PathCalculator pathCalculator = new PathCalculator(
                new int[]{0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0},
                new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 8}, {6, 9}, {9, 10}}
        );

        assertThat(
                pathCalculator.getNextNodes(5, List.of(0, 1, 2, 5))
        )
                .containsAll(
                        List.of(3, 4, 6)
                );
    }

    @Test
    @DisplayName("wholePath")
    void t06() {
        PathCalculator pathCalculator = new PathCalculator(
                new int[]{0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0},
                new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 8}, {6, 9}, {9, 10}}
        );

        Path path = pathCalculator.getFirstPath();

        System.out.println(path);
    }
}
