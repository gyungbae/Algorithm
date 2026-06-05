import java.util.*;

class Solution {
    List<Integer>[] adjList;
    boolean[] visited;
    
    void search(int startNode) {
        Queue<Integer> queue = new ArrayDeque<>();
        
        visited[startNode] = true;
        queue.offer(startNode);
        
        while(!queue.isEmpty()) {
            int current = queue.poll();
            
            for(int next : adjList[current]) {
                if(visited[next])
                    continue;
                
                visited[next] = true;
                queue.offer(next);
            }
        }
    }
    
    public int solution(int n, int[][] computers) {
        adjList = new ArrayList[n];
        for(int node = 0; node < n; node++) {
            adjList[node] = new ArrayList<>();
        }
        
        for(int from = 0; from < n; from++) {
            int[] info = computers[from];
            
            for(int to = 0; to < n; to++) {
                if(from == to || info[to] == 0)
                    continue;
                
                adjList[from].add(to);
                adjList[to].add(from);
            }
        }
            
        int answer = 0;
        visited = new boolean[n];
        for(int node = 0; node < n; node++) {
            if(visited[node])
                continue;

            answer++;
            search(node);
        }
        
        return answer;
    }
}