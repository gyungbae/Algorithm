import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer>[] adjList;
    
    static int answer;

    static boolean[] visited;
    static void bfs(int startNode) {
        Queue<Integer> q = new LinkedList();
        q.offer(startNode);
        visited[startNode] = true;

        while(!q.isEmpty()) {
            int curNode = q.poll();

            for(int nextNode : adjList[curNode]) {
                if(visited[nextNode]) {
                    continue;
                }

                visited[nextNode] = true;
                q.offer(nextNode);
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
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

        visited = new boolean[N + 1];
        answer = 0;
        for(int node = 1; node <= N; node++) {
            if(!visited[node]) {
                bfs(node);
                answer++;
            }
        }

        System.out.println(answer);
    }
}
