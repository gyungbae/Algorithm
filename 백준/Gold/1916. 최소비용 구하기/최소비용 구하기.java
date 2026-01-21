import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int from, to;

    static List<int[]>[] adjList;
    static int[] dist;

    static void BFS() {
        Queue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        dist[from] = 0;
        q.offer(new int[]{from, 0});

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int curNode = current[0];
            int curDist = current[1];

            if (curNode == to) {
                return;
            }

            for (int[] info : adjList[curNode]) {
                int nextNode = info[0];
                int nextWeight = info[1];

                int nextDist = curDist + nextWeight;

                if (nextDist < dist[nextNode]) {
                    dist[nextNode] = nextDist;
                    q.offer(new int[]{nextNode, nextDist});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adjList = new ArrayList[N + 1];
        for (int node = 1; node <= N; node++) {
            adjList[node] = new ArrayList<>();
        }

        for (int edge = 1; edge <= M; edge++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[from].add(new int[]{to, weight});
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        from = Integer.parseInt(st.nextToken());
        to = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        BFS();

        System.out.println(dist[to]);
    }
}
