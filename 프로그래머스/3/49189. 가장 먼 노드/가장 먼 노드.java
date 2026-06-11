import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        List<Integer>[] adjList = new ArrayList[n + 1];
        for(int node = 1; node <= n; node++) {
            adjList[node] = new ArrayList<>();
        }
        
        for(int[] info : edge) {
            int node1 = info[0];
            int node2 = info[1];
            
            adjList[node1].add(node2);
            adjList[node2].add(node1);
        }
        
        Queue<int[]> queue = new ArrayDeque<>(); 
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        
        distance[1] = 0;
        queue.offer(new int[]{1, 0});
        
        int maxDistance = 0;
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentNode = current[0];
            int currentDistance = current[1];
            
            maxDistance = Math.max(maxDistance, currentDistance);
            
            for(int node : adjList[currentNode]) {
                int nextNode = node;
                int nextDistance = currentDistance + 1;
                
                if(distance[nextNode] > nextDistance) {
                    distance[nextNode] = nextDistance;
                    
                    queue.offer(new int[]{nextNode, nextDistance});
                } 
            }
        }
        
        int answer = 0;
        for(int value : distance) {
            if(value == maxDistance)
                answer++;
        }
        
        return answer;
    }
}