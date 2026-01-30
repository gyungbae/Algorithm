import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] parentArr;
    static List<Integer>[] adjList;
    static int M;

    static int[] levelArr;
    static boolean[] visited;

    static void record(int currentNode, int depth) {
        levelArr[currentNode] = depth;
        visited[currentNode] = true;

        for (int nextNode : adjList[currentNode]) {
            if (!visited[nextNode]) {
                parentArr[nextNode] = currentNode;
                record(nextNode, depth + 1);
            }
        }
    }

    static int findLCA(int node1, int node2) {
        int[] nodeInfo = makeSameLevel(node1, node2);
        node1 = nodeInfo[0];
        node2 = nodeInfo[1];

        while (node1 != node2) {
            node1 = parentArr[node1];
            node2 = parentArr[node2];
        }

        return node1;
    }

    static int[] makeSameLevel(int node1, int node2) {
        int level1 = levelArr[node1];
        int level2 = levelArr[node2];
        while (level1 != level2) {
            if (levelArr[node1] > levelArr[node2]) {
                node1 = parentArr[node1];
                level1--;
            } else {
                node2 = parentArr[node2];
                level2--;
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

        levelArr = new int[N + 1];
        parentArr = new int[N + 1];
        visited = new boolean[N + 1];
        record(1, 0);

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
