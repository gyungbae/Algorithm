import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Integer>[] adjList;

    static int[] parentArr;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    static void DFS(int curNode) {
        visited[curNode] = true;

        for (int nextNode : adjList[curNode]) {
            if (visited[nextNode]) {
                continue;
            }

            parentArr[nextNode] = curNode;
            DFS(nextNode);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N + 1];
        for (int node = 1; node <= N; node++) {
            adjList[node] = new ArrayList<>();
        }

        for (int input = 1; input < N; input++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adjList[from].add(to);
            adjList[to].add(from);
        }

        parentArr = new int[N + 1];
        visited = new boolean[N + 1];
        DFS(1);

        for (int node = 2; node <= N; node++) {
            sb.append(parentArr[node]).append("\n");
        }

        System.out.println(sb);
    }
}
