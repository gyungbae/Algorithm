import java.io.*;
import java.util.*;

public class Main {
    static int TC;
    static int N, M, W;
    static List<int[]>[] adjList;

    static StringBuilder sb = new StringBuilder();

    static boolean search() {
        int[] distance = new int[N + 1];

        for (int edgeCount = 1; edgeCount <= N; edgeCount++) {
            boolean updated = false;

            for (int from = 1; from <= N; from++) {
                for (int[] edge : adjList[from]) {
                    int to = edge[0];
                    int weight = edge[1];

                    int nextDistance = distance[from] + weight;
                    if (nextDistance < distance[to]) {
                        if (edgeCount == N) {
                            return true;
                        }

                        distance[to] = nextDistance;
                        updated = true;
                    }
                }
            }

            if (!updated) {
                break;
            }
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            adjList = new ArrayList[N + 1];
            for (int node = 1; node <= N; node++) {
                adjList[node] = new ArrayList<>();
            }

            for (int input = 1; input <= M; input++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                adjList[from].add(new int[]{to, weight});
                adjList[to].add(new int[]{from, weight});
            }

            for (int input = 1; input <= W; input++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                adjList[from].add(new int[]{to, -weight});
            }

            if (search()) {
                sb.append("YES").append("\n");
            } else {
                sb.append("NO").append("\n");
            }
        }

        System.out.println(sb);
    }
}
