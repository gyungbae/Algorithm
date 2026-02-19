import java.io.*;
import java.util.*;

public class Main {
    static int N, E;
    static List<int[]>[] adjList;

    static int viaNode1, viaNode2;

    static int search(int fromNode, int toNode) {
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(value -> value[1]));
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[fromNode] = 0;
        queue.offer(new int[]{fromNode, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentNode = current[0];
            int currentDistance = current[1];

            if (currentDistance > distance[currentNode]) {
                continue;
            }

            if (currentNode == toNode) {
                break;
            }

            for (int[] edge : adjList[currentNode]) {
                int nextNode = edge[0];
                int nextDistance = distance[currentNode] + edge[1];

                if (nextDistance < distance[nextNode]) {
                    distance[nextNode] = nextDistance;
                    queue.offer(new int[]{nextNode, nextDistance});
                }
            }
        }

        return distance[toNode];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N + 1];
        for (int node = 1; node <= N; node++) {
            adjList[node] = new ArrayList<>();
        }

        for (int edge = 1; edge <= E; edge++) {
            st = new StringTokenizer(br.readLine());
            int fromNode = Integer.parseInt(st.nextToken());
            int toNode = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[fromNode].add(new int[]{toNode, weight});
            adjList[toNode].add(new int[]{fromNode, weight});
        }

        st = new StringTokenizer(br.readLine());
        viaNode1 = Integer.parseInt(st.nextToken());
        viaNode2 = Integer.parseInt(st.nextToken());

        int distance1 = search(1, viaNode1);
        int distance2 = search(viaNode1, viaNode2);
        int distance3 = search(viaNode2, N);
        int distance4 = search(1, viaNode2);
        int distance5 = search(viaNode2, viaNode1);
        int distance6 = search(viaNode1, N);

        int result1 = Integer.MAX_VALUE;
        if (distance1 != Integer.MAX_VALUE && distance2 != Integer.MAX_VALUE && distance3 != Integer.MAX_VALUE) {
            result1 = distance1 + distance2 + distance3;
        }

        int result2 = Integer.MAX_VALUE;
        if (distance4 != Integer.MAX_VALUE && distance5 != Integer.MAX_VALUE && distance6 != Integer.MAX_VALUE) {
            result2 = distance4 + distance5 + distance6;
        }

        int answer = Math.min(result1, result2);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}
