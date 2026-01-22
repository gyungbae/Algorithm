import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k;
    static List<int[]>[] adjList;

    static long[][] dist;
    static void search() {
        Queue<long[]> q = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));

        dist[1][1] = 0;
        q.offer(new long[]{1, 0, 1});

        while (!q.isEmpty()) {
            long[] current = q.poll();
            int curNode = (int) current[0];
            long curDist = current[1];

            for (int[] info : adjList[curNode]) {
                int nextNode = info[0];
                int weight = info[1];

                long nextDist = curDist + weight;
                for (int rank = 1; rank <= k; rank++) {
                    int insertIdx = 0;
                    if (nextDist < dist[nextNode][rank]) {
                        insertIdx = rank;
                        for (int idx = k; idx > insertIdx; idx--) {
                            dist[nextNode][idx] = dist[nextNode][idx - 1];
                        }
                        dist[nextNode][insertIdx] = nextDist;
                        q.offer(new long[]{nextNode, nextDist});
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[n + 1];
        for (int node = 1; node <= n; node++) {
            adjList[node] = new ArrayList<>();
        }

        for (int edge = 1; edge <= m; edge++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[from].add(new int[]{to, weight});
        }

        dist = new long[n + 1][k + 1];
        for (int node = 1; node <= n; node++) {
            Arrays.fill(dist[node], Long.MAX_VALUE);
        }

        search();

        StringBuilder sb = new StringBuilder();
        for (int node = 1; node <= n; node++) {
            if (dist[node][k] == Long.MAX_VALUE) {
                sb.append(-1).append("\n");
                continue;
            }

            sb.append(dist[node][k]).append("\n");
        }
        System.out.println(sb);
    }
}
