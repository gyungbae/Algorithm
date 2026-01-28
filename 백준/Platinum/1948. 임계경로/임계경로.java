import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static List<int[]>[] adjList;
    static List<int[]>[] reverseAdjList;
    static int start, end;

    static int[] indegreeArr;
    static long[] distanceArr;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        adjList = new ArrayList[n + 1];
        reverseAdjList = new ArrayList[n + 1];
        for (int node = 1; node <= n; node++) {
            adjList[node] = new ArrayList<>();
            reverseAdjList[node] = new ArrayList<>();
        }

        indegreeArr = new int[n + 1];
        for (int edge = 1; edge <= m; edge++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[from].add(new int[]{to, weight});
            reverseAdjList[to].add(new int[]{from, weight});
            indegreeArr[to]++;
        }

        String[] input = br.readLine().split(" ");
        start = Integer.parseInt(input[0]);
        end = Integer.parseInt(input[1]);

        distanceArr = new long[n + 1];
        Arrays.fill(distanceArr, Long.MIN_VALUE);
        Queue<Integer> queue = new ArrayDeque<>();

        distanceArr[start] = 0;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int[] info : adjList[current]) {
                int next = info[0];
                int weight = info[1];

                distanceArr[next] = Math.max(distanceArr[next], distanceArr[current] + weight);

                if (--indegreeArr[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        int edgeCount = 0;
        visited = new boolean[n + 1];

        queue.offer(end);
        visited[end] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int[] info : reverseAdjList[current]) {
                int next = info[0];
                int weight = info[1];

                if (distanceArr[current] == distanceArr[next] + weight) {
                    edgeCount++;

                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
        }

        System.out.println(distanceArr[end]);
        System.out.println(edgeCount);
    }
}
