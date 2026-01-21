import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<int[]>[] adjList;

    static long[] dist;

    static boolean search() {
        dist[1] = 0;

        for (int length = 1; length <= N - 1; length++) {
            boolean updated = false;

            for (int curNode = 1; curNode <= N; curNode++) {
                if (dist[curNode] == Integer.MAX_VALUE) {
                    continue;
                }

                for (int[] info : adjList[curNode]) {
                    int nextNode = info[0];
                    int nextWeight = info[1];

                    long nextDist = dist[curNode] + nextWeight;
                    if (nextDist < dist[nextNode]) {
                        dist[nextNode] = nextDist;
                        updated = true;
                    }
                }
            }

            if (!updated) {
                break;
            }
        }

        for (int curNode = 1; curNode <= N; curNode++) {
            if (dist[curNode] == Integer.MAX_VALUE) {
                continue;
            }

            for (int[] info : adjList[curNode]) {
                int nextNode = info[0];
                int nextWeight = info[1];

                long nextDist = dist[curNode] + nextWeight;
                if (nextDist < dist[nextNode]) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N + 1];
        for (int node = 1; node <= N; node++) {
            adjList[node] = new ArrayList<>();
        }

        for (int edge = 1; edge <= M; edge++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[from].add(new int[]{to, weight});
        }

        dist = new long[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        boolean hasCycle = search();

        if (hasCycle) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int node = 2; node <= N; node++) {
            if (dist[node] == Integer.MAX_VALUE) {
                sb.append(-1).append("\n");
                continue;
            }

            sb.append(dist[node]).append("\n");
        }

        System.out.println(sb);
    }
}
