import java.io.*;
import java.util.*;

public class Main {
    static int N, M, X;
    static List<int[]>[] adjList;

    static int[] fromDistanceArr;
    static int[] toDistanceArr;
    static List<int[]>[] reversedAdjList;

    static void dijkstra(int[] distanceArr, List<int[]>[] adjList) {
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        Arrays.fill(distanceArr, Integer.MAX_VALUE);
        distanceArr[X] = 0;
        queue.offer(new int[]{X, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentNode = current[0];
            int currentDistance = current[1];

            if (currentDistance > distanceArr[currentNode]) {
                continue;
            }

            for (int[] info : adjList[currentNode]) {
                int nextNode = info[0];
                int weight = info[1];

                int nextDistance = distanceArr[currentNode] + weight;
                if(nextDistance < distanceArr[nextNode]) {
                    distanceArr[nextNode] = nextDistance;
                    queue.offer(new int[]{nextNode, nextDistance});
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N + 1];
        reversedAdjList = new ArrayList[N + 1];
        for (int node = 1; node <= N; node++) {
            adjList[node] = new ArrayList<>();
            reversedAdjList[node] = new ArrayList<>();
        }

        for (int edge = 1; edge <= M; edge++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[from].add(new int[]{to, weight});
            reversedAdjList[to].add(new int[]{from, weight});
        }

        fromDistanceArr = new int[N + 1];
        dijkstra(fromDistanceArr, adjList);
        toDistanceArr = new int[N + 1];
        dijkstra(toDistanceArr, reversedAdjList);

        int max = 0;
        for (int node = 1; node <= N; node++) {
            max = Math.max(max, fromDistanceArr[node] + toDistanceArr[node]);
        }

        System.out.println(max);
    }
}
