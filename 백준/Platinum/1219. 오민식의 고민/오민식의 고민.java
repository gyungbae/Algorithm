import java.io.*;
import java.util.*;

public class Main {
    static int N, start, end, M;
    static List<int[]>[] adjList;
    static int[] income;

    static long[] distance;
    static boolean[] hasCycleArr;

    static void search() {
        distance[start] = income[start];

        for (int edgeLength = 1; edgeLength <= N - 1; edgeLength++) {
            boolean updated = false;
            for (int curNode = 0; curNode < N; curNode++) {
                if (distance[curNode] == Long.MIN_VALUE)
                    continue;

                for (int[] info : adjList[curNode]) {
                    int nextNode = info[0];
                    int weight = info[1];

                    long nextDist = distance[curNode] + income[nextNode] - weight;
                    if (nextDist > distance[nextNode]) {
                        updated = true;
                        distance[nextNode] = nextDist;
                    }
                }
            }

            if (!updated) {
                break;
            }
        }

        for (int curNode = 0; curNode < N; curNode++) {
            if (distance[curNode] == Long.MIN_VALUE)
                continue;

            for (int[] info : adjList[curNode]) {
                int nextNode = info[0];
                int weight = info[1];

                long nextDist = distance[curNode] + income[nextNode] - weight;
                if (nextDist > distance[nextNode]) {
                    hasCycleArr[nextNode] = true;
                }
            }
        }
    }

    static boolean BFS() {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N];

        for (int node = 0; node < N; node++) {
            if (hasCycleArr[node]) {
                q.offer(node);
                visited[node] = true;
            }
        }

        while (!q.isEmpty()) {
            int curNode = q.poll();
            if (curNode == end) return true;

            for (int[] info : adjList[curNode]) {
                int nextNode = info[0];
                if (!visited[nextNode]) {
                    visited[nextNode] = true;
                    q.offer(nextNode);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N];
        for (int node = 0; node < N; node++) {
            adjList[node] = new ArrayList<>();
        }

        for (int edge = 0; edge < M; edge++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[from].add(new int[]{to, weight});
        }

        income = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            income[idx] = Integer.parseInt(st.nextToken());
        }

        distance = new long[N];
        Arrays.fill(distance, Long.MIN_VALUE);
        hasCycleArr = new boolean[N];
        search();

        String answer = distance[end] + "";
        if (distance[end] == Long.MIN_VALUE) {
            answer = "gg";
        } else if (BFS()) {
            answer = "Gee";
        }

        System.out.println(answer);
    }
}
