class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] distance = new int[n + 1][n + 1];
        for(int node1 = 1; node1 <= n; node1++) {
            for(int node2 = 1; node2 <= n; node2++) {
                if(node1 == node2)
                    distance[node1][node2] = 0;
                else
                    distance[node1][node2] = Integer.MAX_VALUE;
            }
        }
        
        for(int[] fare : fares) {
            int node1 = fare[0];
            int node2 = fare[1];
            int cost = fare[2];
            
            distance[node1][node2] = Math.min(distance[node1][node2], cost);
            distance[node2][node1] = Math.min(distance[node2][node1], cost);
        } 
        
        for(int mid = 1; mid <= n; mid++) {
            for(int from = 1; from <= n; from++) {
                for(int to = 1; to <= n; to++) {
                    if(distance[from][mid] == Integer.MAX_VALUE || distance[mid][to] == Integer.MAX_VALUE)
                        continue;
                    
                    int weight = distance[from][mid] + distance[mid][to];
                    
                    if(distance[from][to] > weight) 
                        distance[from][to] = weight;
                }
            }
        }
        
        int answer = distance[s][a] + distance[s][b];
        for(int mid = 1; mid <= n; mid++) {
            if(mid == s)
                continue;
            
            if(distance[s][mid] == Integer.MAX_VALUE 
               || distance[mid][a] == Integer.MAX_VALUE || distance[mid][b] == Integer.MAX_VALUE)
                continue;
            
            int cost = distance[s][mid] + distance[mid][a] + distance[mid][b];
            
            answer = Math.min(answer, cost);
        }
        
        return answer;
    }
}