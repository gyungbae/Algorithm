import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<Integer>[] adjList = new ArrayList[n + 1];
        for(int node = 1; node <= n; node++) {
            adjList[node] = new ArrayList<>();
        }
        
        for(int[] info : roads) {
            int from = info[0];
            int to = info[1];
            
            adjList[from].add(to);
            adjList[to].add(from);
        }
        
        Queue<int[]> queue = new ArrayDeque<>();
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        
        distance[destination] = 0;
        queue.offer(new int[]{destination, 0});
        
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentNode = current[0];
            int currentDistance = current[1];
            
            for(int nextNode : adjList[currentNode]) {
                int nextDistance = currentDistance + 1;
                
                if(nextDistance < distance[nextNode]) {
                    distance[nextNode] = nextDistance;
                    queue.offer(new int[]{nextNode, nextDistance});
                }
            }
        }
        
        int[] answer = new int[sources.length];
        for(int idx = 0; idx < sources.length; idx++) {
            int node = sources[idx];
            
            if(distance[node] == Integer.MAX_VALUE)
                answer[idx] = -1;
            else
                answer[idx] = distance[sources[idx]];
        }
        
        return answer;
    }
}
