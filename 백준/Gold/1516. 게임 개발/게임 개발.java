import java.io.*;
import java.util.*;

public class Main {
    static int N;

    static List<Integer>[] adjList;
    static int[] timeArr;
    static int[] indegreeArr;
    static int[] minTimeArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N + 1];
        for (int node = 1; node <= N; node++) {
            adjList[node] = new ArrayList<>();
        }

        timeArr = new int[N + 1];
        indegreeArr = new int[N + 1];
        for (int node = 1; node <= N; node++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            timeArr[node] = time;

            int indegree = 0;
            while (st.hasMoreTokens()) {
                int before = Integer.parseInt(st.nextToken());
                if (before != -1) {
                    adjList[before].add(node);
                    indegreeArr[node]++;
                }
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();

        minTimeArr = new int[N + 1];
        Arrays.fill(minTimeArr, Integer.MIN_VALUE);
        for (int node = 1; node <= N; node++) {
            if (indegreeArr[node] == 0) {
                queue.offer(node);
            }
            minTimeArr[node] = timeArr[node];
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : adjList[current]) {
                minTimeArr[next] = Math.max(minTimeArr[next], minTimeArr[current] + timeArr[next]);

                if (--indegreeArr[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int node = 1; node <= N; node++) {
            sb.append(minTimeArr[node]).append("\n");
        }
        System.out.println(sb);
    }
}
