import java.io.*;
import java.util.*;

public class Main {
    static int N, M;

    static List<Integer>[] adjList;
    static int[] indegreeArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N + 1];
        for (int node = 1; node <= N; node++) {
            adjList[node] = new ArrayList<>();
        }

        indegreeArr = new int[N + 1];
        for (int input = 1; input <= M; input++) {
            st = new StringTokenizer(br.readLine());
            int taller = Integer.parseInt(st.nextToken());
            int smaller = Integer.parseInt(st.nextToken());

            indegreeArr[smaller]++;
            adjList[taller].add(smaller);
        }

        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new ArrayDeque<>();
        for (int node = 1; node <= N; node++) {
            if (indegreeArr[node] == 0) {
                queue.offer(node);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            sb.append(current).append(" ");

            for (int next : adjList[current]) {
                if (--indegreeArr[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        System.out.println(sb);
    }
}
