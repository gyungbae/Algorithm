import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<int[]>[] adjList;

    static long[] distance;
    static boolean hasNegCycle;

    static void search() {
        distance[1] = 0;

        for (int visitCnt = 1; visitCnt <= N - 1; visitCnt++) {
            boolean updated = false;

            for (int curNode = 1; curNode <= N; curNode++) {
                if (distance[curNode] == Long.MAX_VALUE) {
                    continue;
                }

                for (int[] info : adjList[curNode]) {
                    int nextNode = info[0];
                    int weight = info[1];

                    long nextDist = distance[curNode] + weight;
                    if (nextDist < distance[nextNode]) {
                        distance[nextNode] = nextDist;
                        updated = true;
                    }
                }
            }

            if (!updated) {
                break;
            }
        }

        for (int curNode = 1; curNode <= N; curNode++) {
            if (distance[curNode] == Long.MAX_VALUE) {
                continue;
            }

            for (int[] info : adjList[curNode]) {
                int nextNode = info[0];
                int weight = info[1];

                long nextDist = distance[curNode] + weight;
                if (nextDist < distance[nextNode]) {
                    hasNegCycle = true;
                    return;
                }
            }
        }
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

        distance = new long[N + 1];
        Arrays.fill(distance, Long.MAX_VALUE);
        hasNegCycle = false;
        search();
        if (hasNegCycle) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int node = 2; node <= N; node++) {
            long val = distance[node];
            if (val == Long.MAX_VALUE) {
                sb.append(-1).append("\n");
                continue;
            }

            sb.append(val).append("\n");
        }
        System.out.println(sb);
    }
}
