import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, X;
    static List<Integer>[] adjList;

    static int[] distance;

    static void BFS() {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(X);
        distance[X] = 0;

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();

            for (int nextNode : adjList[currentNode]) {
                int nextDistance = distance[currentNode] + 1;
                if (nextDistance < distance[nextNode]) {
                    distance[nextNode] = nextDistance;
                    queue.offer(nextNode);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N + 1];
        for (int node = 1; node <= N; node++) {
            adjList[node] = new ArrayList<>();
        }

        for (int edge = 1; edge <= M; edge++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adjList[from].add(to);
        }

        distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        BFS();

        StringBuilder sb = new StringBuilder();
        for (int node = 1; node <= N; node++) {
            if (distance[node] == K) {
                sb.append(node).append("\n");
            }
        }
        System.out.println(sb.length() == 0 ? -1 : sb);
    }
}
