import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer>[] adjacentList;

    static boolean[] visited;

    static int BFS(int startNode) {
        Queue<Integer> queue = new LinkedList<>();

        visited[startNode] = true;
        queue.offer(startNode);

        int count = 0;
        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            count++;

            for (int nextNode : adjacentList[currentNode]) {
                if (!visited[nextNode]) {
                    visited[nextNode] = true;
                    queue.offer(nextNode);
                }
            }
        }

        return count;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjacentList = new ArrayList[N + 1];
        for (int node = 1; node <= N; node++) {
            adjacentList[node] = new ArrayList<>();
        }

        for (int edge = 1; edge <= M; edge++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());

            adjacentList[from].add(to);
        }

        int maxCount = 0;
        int[] record = new int[N + 1];
        for (int node = 1; node <= N; node++) {
            visited = new boolean[N + 1];
            record[node] = BFS(node);
            if (record[node] > maxCount) {
                maxCount = record[node];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int node = 1; node <= N; node++) {
            if (record[node] == maxCount) {
                sb.append(node).append(" ");
            }
        }
        System.out.println(sb);
    }
}
