import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static List<int[]>[] adjList;
    static int startNode, endNode;

    static long[] dist;
    static int[] record;

    static void search() {
        Queue<long[]> q = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));

        dist[startNode] = 0;
        q.offer(new long[]{startNode, 0});

        while (!q.isEmpty()) {
            long[] current = q.poll();
            int curNode = (int) current[0];
            long curDist = current[1];

            if (curDist > dist[curNode]) {
                continue;
            }

            for (int[] info : adjList[curNode]) {
                int nextNode = info[0];
                int weight = info[1];

                long nextDist = curDist + weight;
                if (nextDist < dist[nextNode]) {
                    dist[nextNode] = nextDist;
                    record[nextNode] = curNode;
                    q.offer(new long[]{nextNode, nextDist});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        adjList = new ArrayList[n + 1];
        for (int node = 1; node <= n; node++) {
            adjList[node] = new ArrayList<>();
        }

        for (int edge = 1; edge <= m; edge++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int fromNode = Integer.parseInt(st.nextToken());
            int toNode = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[fromNode].add(new int[]{toNode, weight});
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        startNode = Integer.parseInt(st.nextToken());
        endNode = Integer.parseInt(st.nextToken());

        dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        record = new int[n + 1];
        Arrays.fill(record, -1);

        search();

        List<Integer> path = new ArrayList<>();
        int curNode = endNode;
        while (curNode != 0 && curNode != -1) {
            path.add(curNode);
            if (curNode == startNode) {
                break;
            }
            curNode = record[curNode];
        }
        Collections.reverse(path);

        StringBuilder sb = new StringBuilder();
        sb.append(dist[endNode]).append("\n");
        sb.append(path.size()).append("\n");
        for (int node : path) {
            sb.append(node).append(" ");
        }

        System.out.println(sb);
    }
}
