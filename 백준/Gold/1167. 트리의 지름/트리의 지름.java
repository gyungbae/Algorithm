import java.io.*;
import java.util.*;

public class Main {
    static int V;
    static List<int[]>[] adjList;

    static boolean[] visited;
    static int[] distance;

    static void DFS(int curNode, int curDist) {
        visited[curNode] = true;
        distance[curNode] = curDist;

        for (int[] info : adjList[curNode]) {
            int nextNode = info[0];
            int weight = info[1];

            if (visited[nextNode]) {
                continue;
            }

            DFS(nextNode, curDist + weight);
        }
    }

    static void BFS(int startNode) {
        Queue<int[]> q = new ArrayDeque<>();

        visited[startNode] = true;
        q.offer(new int[]{startNode, distance[startNode]});

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int curNode = current[0];
            int curDist = current[1];

            for (int[] info : adjList[curNode]) {
                int nextNode = info[0];
                int weight = info[1];

                if (visited[nextNode]) {
                    continue;
                }

                int nextDist = curDist + weight;
                distance[nextNode] = nextDist;
                visited[nextNode] = true;
                q.offer(new int[]{nextNode, nextDist});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        V = Integer.parseInt(br.readLine());
        adjList = new ArrayList[V + 1];
        for (int node = 1; node <= V; node++) {
            adjList[node] = new ArrayList<>();
        }

        for (int edge = 1; edge <= V; edge++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1)
                    break;

                int weight = Integer.parseInt(st.nextToken());
                adjList[from].add(new int[]{to, weight});
            }
        }

        distance = new int[V + 1];
        visited = new boolean[V + 1];
        DFS(1, 0);

        int maxDist = 0;
        int maxNode = 0;
        for (int node = 1; node <= V; node++) {
            if (distance[node] > maxDist) {
                maxDist = distance[node];
                maxNode = node;
            }
        }

        distance = new int[V + 1];
        visited = new boolean[V + 1];
        BFS(maxNode);

        int answer = 0;
        for (int node = 1; node <= V; node++) {
            answer = Math.max(distance[node], answer);
        }

        System.out.println(answer);
    }
}
