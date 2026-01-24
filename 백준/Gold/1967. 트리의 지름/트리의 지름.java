import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static List<int[]>[] adjList;

    static boolean[] visited;
    static int[] distance;

    static void DFS(int curNode, int curDist) {
        distance[curNode] = curDist;

        visited[curNode] = true;

        for (int[] info : adjList[curNode]) {
            int nextNode = info[0];
            int weight = info[1];

            if (!visited[nextNode]) {
                distance[nextNode] = curDist + weight;
                DFS(nextNode, curDist + weight);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        adjList = new ArrayList[n + 1];
        for (int node = 0; node <= n; node++) {
            adjList[node] = new ArrayList<>();
        }

        for (int edge = 1; edge < n; edge++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[from].add(new int[]{to, weight});
            adjList[to].add(new int[]{from, weight});
        }

        visited = new boolean[n + 1];
        distance = new int[n + 1];
        DFS(1, 0);

        int maxNode = 0;
        int maxDist = Integer.MIN_VALUE;
        for (int node = 2; node <= n; node++) {
            if (distance[node] > maxDist) {
                maxNode = node;
                maxDist = distance[node];
            }
        }

        visited = new boolean[n + 1];
        distance = new int[n + 1];
        DFS(maxNode, 0);

        int answer = Integer.MIN_VALUE;
        for (int dist : distance) {
            answer = Math.max(answer, dist);
        }

        System.out.println(answer);
    }
}
