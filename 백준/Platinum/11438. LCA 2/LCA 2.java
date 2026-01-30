import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Integer>[] adjList;
    static int M;

    static int[][] parentArr;
    static int jumpLimit;
    static int[] levelArr;
    static boolean[] visited;

    static void record(int currentNode, int depth) {
        levelArr[currentNode] = depth;
        visited[currentNode] = true;

        for (int nextNode : adjList[currentNode]) {
            if (!visited[nextNode]) {
                parentArr[nextNode][0] = currentNode;
                record(nextNode, depth + 1);
            }
        }
    }

    static void fillParent() {
        for (int jump = 1; jump < jumpLimit; jump++) {
            for (int node = 1; node <= N; node++) {
                int midAncestor = parentArr[node][jump - 1];
                parentArr[node][jump] = midAncestor == 0 ? 0 : parentArr[midAncestor][jump - 1];
            }
        }
    }

    static int findLCA(int node1, int node2) {
        int[] nodeInfo = makeSameLevel(node1, node2);
        node1 = nodeInfo[0];
        node2 = nodeInfo[1];

        if (node1 == node2) {
            return node1;
        }

        for (int jump = jumpLimit - 1; jump >= 0; jump--) {
            if (parentArr[node1][jump] != parentArr[node2][jump]) {
                node1 = parentArr[node1][jump];
                node2 = parentArr[node2][jump];
            }
        }

        return parentArr[node1][0];
    }

    static int[] makeSameLevel(int node1, int node2) {
        int level1 = levelArr[node1];
        int level2 = levelArr[node2];

        int gap = Math.abs(level1 - level2);
        for (int jump = jumpLimit - 1; jump >= 0; jump--) {
            int jumpSize = (int) Math.pow(2, jump);
            if (jumpSize <= gap) {
                if (level1 > level2) {
                    node1 = parentArr[node1][jump];
                } else {
                    node2 = parentArr[node2][jump];
                }

                gap -= jumpSize;
            }
        }

        return new int[]{node1, node2};
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N + 1];
        for (int node = 1; node <= N; node++) {
            adjList[node] = new ArrayList<>();
        }

        for (int input = 1; input <= N - 1; input++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adjList[from].add(to);
            adjList[to].add(from);
        }

        jumpLimit = 0;
        while (Math.pow(2, jumpLimit) <= N) {
            jumpLimit++;
        }

        levelArr = new int[N + 1];
        parentArr = new int[N + 1][jumpLimit];
        visited = new boolean[N + 1];
        record(1, 0);
        fillParent();

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int input = 1; input <= M; input++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            sb.append(findLCA(node1, node2)).append("\n");
        }

        System.out.println(sb);
    }
}
