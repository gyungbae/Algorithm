import java.io.*;
import java.util.*;

public class Main {
    static int nodeCnt;
    static int edgeCnt;

    static List<Integer>[] adjList;
    static boolean[] visited;
    static int answer;

    static void DFS(int curNode) {
        if (visited[curNode]) {
            return;
        }

        answer++;
        visited[curNode] = true;

        for (int nextNode : adjList[curNode]) {
            if (visited[nextNode]) {
                continue;
            }

            DFS(nextNode);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        nodeCnt = Integer.parseInt(br.readLine());
        edgeCnt = Integer.parseInt(br.readLine());
        adjList = new ArrayList[nodeCnt + 1];
        for (int node = 1; node <= nodeCnt; node++) {
            adjList[node] = new ArrayList<>();
        }

        for (int edge = 1; edge <= edgeCnt; edge++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adjList[from].add(to);
            adjList[to].add(from);
        }

        visited = new boolean[nodeCnt + 1];
        answer = -1;
        DFS(1);

        System.out.println(answer);
    }
}
