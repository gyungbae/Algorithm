import java.io.*;
import java.util.*;

public class Main {
    static int V, E;
    static int K;
    static List<int[]>[] adjList;

    static int[] dist;
    static void search(int startNode) {
        Queue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        dist[startNode] = 0;
        q.offer(new int[]{startNode, 0});

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int curNode = current[0];
            int curDist = current[1];

            for (int[] nextInfo : adjList[curNode]) {
                int nextNode = nextInfo[0];
                int nextWeight = nextInfo[1];

                int nextDist = curDist + nextWeight;

                if (nextDist < dist[nextNode]) {
                    dist[nextNode] = nextDist;
                    q.offer(new int[]{nextNode, nextDist});
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        adjList = new ArrayList[V + 1];
        for (int node = 1; node <= V; node++) {
            adjList[node] = new ArrayList<>();
        }

        for (int edge = 1; edge <= E; edge++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[from].add(new int[]{to, weight});
        }

        dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        search(K);

        StringBuilder sb = new StringBuilder();
        for (int idx = 1; idx <= V; idx++) {
            int val = dist[idx];

            if (val == Integer.MAX_VALUE) {
                sb.append("INF").append("\n");
                continue;
            }

            sb.append(val).append("\n");
        }

        System.out.println(sb);
    }
}
