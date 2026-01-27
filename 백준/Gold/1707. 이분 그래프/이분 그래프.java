import java.io.*;
import java.util.*;

public class Main {
    static int K;
    static int V, E;
    static List<Integer>[] adjacentList;

    static int[] visited;
    static StringBuilder sb = new StringBuilder();

    static boolean BFS(int startNode) {
        Queue<Integer> queue = new LinkedList<>();

        visited[startNode] = 1;
        queue.offer(startNode);

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();

            for (int nextNode : adjacentList[currentNode]) {
                if (visited[nextNode] == 0) {
                    visited[nextNode] = -visited[currentNode];
                    queue.offer(nextNode);
                    continue;
                }

                if (visited[nextNode] == visited[currentNode]) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());
        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            adjacentList = new ArrayList[V + 1];
            for (int node = 1; node <= V; node++) {
                adjacentList[node] = new ArrayList<>();
            }

            for (int edge = 1; edge <= E; edge++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                adjacentList[from].add(to);
                adjacentList[to].add(from);
            }

            visited = new int[V + 1];
            boolean isPossible = false;
            for (int node = 1; node <= V; node++) {
                if (visited[node] != 0) {
                    continue;
                }

                isPossible = BFS(node);
                if (!isPossible) {
                    break;
                }
            }

            if (isPossible) {
                sb.append("YES").append("\n");
            } else {
                sb.append("NO").append("\n");
            }
        }

        System.out.println(sb);
    }
}
