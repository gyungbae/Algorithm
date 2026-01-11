import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, V;
    static List<Integer>[] adjList;

    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    
    static void DFS(int currentNode) {
        if(visited[currentNode]) {
            return;
        }

        visited[currentNode] = true;
        sb.append(currentNode).append(" ");

        for(int nextNode : adjList[currentNode]) {
            if(visited[nextNode]) {
                continue;
            }

            DFS(nextNode);
        }
    }

    static void BFS(int startNode) {
        Queue<Integer> q = new ArrayDeque<>();
        
        q.offer(startNode);
        visited[startNode] = true;

        while(!q.isEmpty()) {
            int currentNode = q.poll();
            sb.append(currentNode).append(" ");

            for(int nextNode : adjList[currentNode]) {
                if(visited[nextNode]) {
                    continue;
                }

                visited[nextNode] = true;
                q.offer(nextNode);
            }
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N + 1];
        for(int node = 1; node <= N; node++) {
            adjList[node] = new ArrayList<>();
        }

        for(int edge = 1; edge <= M; edge++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adjList[from].add(to);
            adjList[to].add(from);    
        }

        for(int node = 1; node <= N; node++) {
            Collections.sort(adjList[node]);
        }

        visited = new boolean[N + 1];
        DFS(V);
        sb.append("\n");
        visited = new boolean[N + 1];
        BFS(V);

        System.out.println(sb);
    }
}
