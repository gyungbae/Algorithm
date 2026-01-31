import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<int[]>[] adjList;

    static int search() {
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        boolean[] visited = new boolean[N + 1];

        queue.offer(new int[]{1, 0});

        int sum = 0;
        int count = 0;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentNode = current[0];
            int currentDistance = current[1];

            if (visited[currentNode]) {
                continue;
            }

            visited[currentNode] = true;
            count++;
            sum += currentDistance;

            for (int[] info : adjList[currentNode]) {
                int nextNode = info[0];
                int weight = info[1];

                if (!visited[nextNode]) {
                    queue.offer(new int[]{nextNode, weight});
                }
            }
        }


        return count == N ? sum : -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        adjList = new ArrayList[N + 1];
        for (int node = 1; node <= N; node++) {
            adjList[node] = new ArrayList<>();
        }

        int totalLength = 0;
        for (int from = 1; from <= N; from++) {
            String input = br.readLine();
            for (int to = 1; to <= N; to++) {
                char ch = input.charAt(to - 1);
                int length = 0;
                if (ch == '0') {
                    continue;
                } else if ('a' <= ch && ch <= 'z') {
                    length = ch - 'a' + 1;
                } else {
                    length = ch - 'A' + 27;
                }

                adjList[from].add(new int[]{to, length});
                adjList[to].add(new int[]{from, length});
                totalLength += length;
            }
        }

        int minLength = search();

        if (minLength == -1) {
            System.out.println(-1);
        } else {
            System.out.println(totalLength - minLength);
        }
    }
}
