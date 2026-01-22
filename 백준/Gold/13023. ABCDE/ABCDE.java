import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer>[] adjList;

    static boolean[] visited;
    static boolean flag;

    static void DFS(int curNode, int depth) {
        if (flag) {
            return;
        }
        
        if (depth == 5) {
            flag = true;
            return;
        }
        
        visited[curNode] = true;
        
        for (int nextNode : adjList[curNode]) {
            if (!visited[nextNode]) {
                DFS(nextNode, depth + 1);
            }
        }
        
        visited[curNode] = false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        adjList = new ArrayList[N];
        for (int node = 0; node < N; node++) {
            adjList[node] = new ArrayList<>();
        }

        for (int edge = 1; edge <= M; edge++) {
            input = br.readLine().split(" ");
            int from = Integer.parseInt(input[0]);
            int to = Integer.parseInt(input[1]);

            adjList[from].add(to);
            adjList[to].add(from);
        }

        visited = new boolean[N];
        for (int node = 0; node < N; node++) {
            DFS(node, 1);
            if (flag) {
                break;
            }
        }

        System.out.println(flag ? 1 : 0);
    }
}
