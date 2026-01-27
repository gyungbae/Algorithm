import java.io.*;
import java.util.*;

public class Main {
    static int N, M, X;
    static List<int[]>[] adjacentList;

    static final int INF = 1_000_000_000;

    static int[] dijkstra(int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curDist = cur[1];

            // 이미 더 좋은 값으로 처리된 적 있으면 스킵
            if (curDist != dist[curNode]) continue;

            for (int[] edge : adjacentList[curNode]) {
                int next = edge[0];
                int w = edge[1];

                int nd = curDist + w;
                if (nd < dist[next]) {
                    dist[next] = nd;
                    pq.offer(new int[]{next, nd});
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        adjacentList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) adjacentList[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjacentList[from].add(new int[]{to, w});
        }

        // 1) X -> 모든 노드
        int[] distFromX = dijkstra(X);

        // 2) i -> X (역방향 없이: i마다 다익스트라)
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            int[] distFromI = dijkstra(i);
            int go = distFromI[X];     // i -> X
            int back = distFromX[i];   // X -> i
            ans = Math.max(ans, go + back);
        }

        System.out.println(ans);
    }
}
