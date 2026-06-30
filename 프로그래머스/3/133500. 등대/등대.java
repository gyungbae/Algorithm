import java.util.*;

class Solution {
    int n;
    
    List<Integer>[] adjList;
    int[][] dp;
    boolean[] visited;
    
    void search(int currentNode) {
        visited[currentNode] = true;
        
        dp[currentNode][0] = 0;
        dp[currentNode][1] = 1;
        
        for(int nextNode : adjList[currentNode]) {
            if(visited[nextNode])
                continue;
            
            search(nextNode);
            
            dp[currentNode][0] += dp[nextNode][1];
            dp[currentNode][1] += Math.min(dp[nextNode][0], dp[nextNode][1]);
        }
    }
    
    public int solution(int n, int[][] lighthouse) {
        this.n = n;
        
        adjList = new ArrayList[n + 1];
        for(int node = 1; node <= n; node++) {
            adjList[node] = new ArrayList<>();
        }
        
        for(int[] info : lighthouse) {
            int node1 = info[0];
            int node2 = info[1];
            
            adjList[node1].add(node2);
            adjList[node2].add(node1);
        }
        
        dp = new int[n + 1][2];
        visited = new boolean[n + 1];
        
        search(1);
        
        return Math.min(dp[1][0], dp[1][1]);
    }
}