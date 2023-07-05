package org.example.p92343;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
}


class Solution {
    public int solution(int[] info, int[][] edges) {

        PathCalculator pathCalculator = new PathCalculator(
                info,
                edges
        );

        return pathCalculator.getMaxSheepCount();
    }
}

class PathCalculator {
    private final int[] info;
    private final boolean[][] tree;
    private Path maxSheepCountPath;
    private Path firstPath;

    public PathCalculator(int[] info, int[][] edges) {
        this.info = info;
        tree = new boolean[info.length][info.length];

        for (int[] edge : edges) {
            tree[edge[0]][edge[1]] = true;
            tree[edge[1]][edge[0]] = true;
        }

        // 계산하기
        calc();
    }


    // 특정 노드를 기준으로, 이동할 수 있는 인접 노드들의 번호를 리턴하는 함수
    public List<Integer> getNextNodes(int currentNode) {
        List<Integer> nextNodes = new ArrayList<>();

        for (int i = 0; i < tree[currentNode].length; i++) {
            if (tree[currentNode][i]) {
                nextNodes.add(i);
            }
        }

        return nextNodes;
    }

    public List<Integer> getNextNodes(int currentNode, List<Integer> history) {
        return getNextNodes(currentNode, history, new ArrayList<>());
    }

    // 특정 노드를 기준으로, 이동할 수 있는 인접 노드들의 번호를 리턴하는 함수
    // 단 이미 방문한 곳은 지나친다.
    public List<Integer> getNextNodes(int currentNode, List<Integer> history, List<Integer> prevent) {
        // 현재 노드에 방문했다는 건, 더 이상 이 노드에서 탐색할 필요가 없다는 뜻
        // 이 작업을 안하면 같은 노드를 계속 여러번 탐색하게 된다.
        prevent.add(currentNode);

        // 최종적인 데이터가 담길 곳
        List<Integer> nextNodes = new ArrayList<>();

        // 일단 단순하게 현재 노드 기준으로 이동할 수 있는 노드들을 구한다.
        List<Integer> _nextNodes = getNextNodes(currentNode);

        for (int nextNode : _nextNodes) {
            // 이미 탐색한 곳이면 패스
            if (prevent.contains(nextNode)) {
                continue;
            }

            // 이미 방문한 곳이면
            if (history.contains(nextNode)) {
                // 그곳을 기준으로 새로 탐색
                nextNodes.addAll(getNextNodes(nextNode, history, prevent));
            } else {
                // 방문할 리스트에 넣는다.
                nextNodes.add(nextNode);
            }
        }

        return nextNodes;
    }

    private void calc() {
        // 일단 첫번째 Path 를 만든다.
        Path path = new Path(null, 0, true);

        firstPath = path;
        maxSheepCountPath = path;

        // 첫번째 패스에 인접노드들을 붙여서 childPath 로 추가한다.
        for (int nextNode : getNextNodes(0)) {
            addChildPath(path, nextNode);
        }
    }

    private void addChildPath(Path parentPath, int node) {
        Path path = parentPath.addChildPath(node, info[node] == 0);

        if (path.getSheepCount() == 0) return;

        if (maxSheepCountPath.getSheepCount() < path.getSheepCount()) {
            maxSheepCountPath = path;
        }

        for (int nextNode : getNextNodes(node, path.getHistory())) {
            addChildPath(path, nextNode);
        }
    }

    public Path getFirstPath() {
        return firstPath;
    }

    public int getMaxSheepCount() {
        return maxSheepCountPath.getSheepCount();
    }
}

class Path {
    private final int depth;
    private final int node;
    private final Path parentPath;
    private final List<Path> childPaths;
    private final int sheepCount;
    private final int wolfCount;
    private List<Integer> history;


    Path(Path parentPath, int node, boolean isSheep) {
        this.parentPath = parentPath;
        this.depth = parentPath == null ? 0 : parentPath.depth + 1;
        this.node = node;
        this.childPaths = new ArrayList<>();

        int sheepCount = (isSheep ? 1 : 0) + (parentPath == null ? 0 : parentPath.sheepCount);
        int wolfCount = (isSheep ? 0 : 1) + (parentPath == null ? 0 : parentPath.wolfCount);

        if (sheepCount <= wolfCount) {
            sheepCount = 0;
        }

        this.sheepCount = sheepCount;
        this.wolfCount = wolfCount;
    }

    public int getSheepCount() {
        return sheepCount;
    }

    public Path addChildPath(int nextNode, boolean isSheep) {
        Path path = new Path(this, nextNode, isSheep);
        childPaths.add(path);

        return path;
    }

    public List<Integer> getHistory() {
        if (history != null) return history;

        List<Integer> parentPathHistory = parentPath == null ? new ArrayList<>() : parentPath.getHistory();

        history = new ArrayList<>(parentPathHistory);
        history.add(node);

        return history;
    }

    @Override
    public String toString() {
        return " ".repeat(depth) + node + "\n" + childPaths.stream().map(Path::toString).collect(Collectors.joining("\n"));
    }
}