import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] inputArr;

    static List<int[]> nodeList = new ArrayList<>();
    static int nodeCount;
    static List<int[]> edgeList = new ArrayList<>();
    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};
    static boolean[][] visited;
    static int[] parentArr;

    static void saveNode(int startRow, int startCol) {
        Queue<int[]> queue = new ArrayDeque<>();

        visited[startRow][startCol] = true;
        queue.offer(new int[]{startRow, startCol});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            inputArr[current[0]][current[1]] = nodeList.size() + 1;

            for (int delta = 0; delta < 4; delta++) {
                int nextRow = current[0] + deltaRow[delta];
                int nextCol = current[1] + deltaCol[delta];

                if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) {
                    continue;
                }

                if (inputArr[nextRow][nextCol] == 0 || visited[nextRow][nextCol]) {
                    continue;
                }

                visited[nextRow][nextCol] = true;

                queue.offer(new int[]{nextRow, nextCol});
            }
        }

        nodeList.add(new int[]{startRow, startCol});
    }

    static void saveEdge() {
        int[][] distanceArr = new int[nodeCount + 1][nodeCount + 1];
        for (int from = 1; from <= nodeCount; from++) {
            for (int to = 1; to <= nodeCount; to++) {
                distanceArr[from][to] = Integer.MAX_VALUE;
            }
        }

        for (int[] nodeInfo : nodeList) {
            int row = nodeInfo[0];
            int col = nodeInfo[1];
            int fromNode = inputArr[row][col];

            Queue<int[]> queue = new ArrayDeque<>();
            visited = new boolean[N][M];

            visited[row][col] = true;
            queue.offer(new int[]{row, col});

            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                for (int[] bridge : search(fromNode, current[0], current[1])) {
                    int from = bridge[0];
                    int to = bridge[1];
                    int weight = bridge[2];
                    if (distanceArr[from][to] > weight) {
                        distanceArr[from][to] = weight;
                        edgeList.add(new int[]{bridge[0], bridge[1], bridge[2]});
                    }
                }

                for (int delta = 0; delta < 4; delta++) {
                    int nextRow = current[0] + deltaRow[delta];
                    int nextCol = current[1] + deltaCol[delta];

                    if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) {
                        continue;
                    }

                    if (inputArr[nextRow][nextCol] != fromNode || visited[nextRow][nextCol]) {
                        continue;
                    }

                    visited[nextRow][nextCol] = true;
                    queue.offer(new int[]{nextRow, nextCol});
                }
            }

        }
    }

    static List<int[]> search(int fromNode, int row, int col) {
        List<int[]> bridgeList = new ArrayList<>();

        for (int delta = 0; delta < 4; delta++) {
            int searchRow = row;
            int searchCol = col;
            int toNode = -1;
            int distance = 0;
            while (true) {
                searchRow += deltaRow[delta];
                searchCol += deltaCol[delta];

                if (searchRow < 0 || searchRow >= N || searchCol < 0 || searchCol >= M) {
                    break;
                }

                if (inputArr[searchRow][searchCol] == 0) {
                    distance++;
                    continue;
                }

                if (inputArr[searchRow][searchCol] == fromNode) {
                    break;
                }

                if (inputArr[searchRow][searchCol] != fromNode) {
                    toNode = inputArr[searchRow][searchCol];
                    break;
                }
            }

            if (toNode != -1 && distance > 1) {
                bridgeList.add(new int[]{fromNode, toNode, distance});
            }
        }

        return bridgeList;
    }

    static int link() {
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        for (int[] edge : edgeList) {
            queue.offer(edge);
        }

        int sum = 0;
        int count = 0;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int from = current[0];
            int to = current[1];
            int weight = current[2];

            if (find(from) != find(to)) {
                union(from, to);
                sum += weight;
                count++;
            }
        }

        if (count != nodeList.size() - 1) {
            return -1;
        } else {
            return sum;
        }
    }

    static void union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);

        if (root1 != root2) {
            parentArr[root2] = root1;
        }
    }

    static int find(int node) {
        if (parentArr[node] == node) {
            return node;
        }

        return parentArr[node] = find(parentArr[node]);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        inputArr = new int[N][M];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                inputArr[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (inputArr[row][col] != 0 && !visited[row][col]) {
                    saveNode(row, col);
                }
            }
        }

        nodeCount = nodeList.size();
        saveEdge();

        parentArr = new int[nodeList.size() + 1];
        for (int node = 1; node <= nodeList.size(); node++) {
            parentArr[node] = node;
        }

        System.out.println(link());
    }
}
