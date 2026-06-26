import java.util.*;

class Solution {
    int n;
    int[] nodeInfo;
    List<int[]>[] adjList;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        this.n = n;
        
        nodeInfo = new int[n + 1];
        for(int node : gates) {
            nodeInfo[node] = 1;
        }
        
        for(int node : summits) {
            nodeInfo[node] = 2;
        }
        
        adjList = new ArrayList[n + 1];
        for(int node = 1; node <= n; node++) {
            adjList[node] = new ArrayList<>();
        }
        
        for(int[] info : paths) {
            int node1 = info[0];
            int node2 = info[1];
            int weight = info[2];
            
            adjList[node1].add(new int[]{node2, weight});
            adjList[node2].add(new int[]{node1, weight});
        }
        
        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);
        
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        for(int gate : gates) {
            intensity[gate] = 0;
            queue.offer(new int[]{gate, 0});
        }
        
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentNode = current[0];
            int currentIntensity = current[1];
            
            if(currentIntensity > intensity[currentNode])
                continue;
            
            if(nodeInfo[currentNode] == 2)
                continue;
            
            for(int[] edge : adjList[currentNode]) {
                int nextNode = edge[0];
                int weight = edge[1];
                
                if(nodeInfo[nextNode] == 1)
                    continue;
                
                int nextIntensity = Math.max(weight, currentIntensity);
                
                if(intensity[nextNode] > nextIntensity) {
                    intensity[nextNode] = nextIntensity;
                    queue.offer(new int[]{nextNode, nextIntensity});
                }
            }
        }
        
        Arrays.sort(summits);
        
        int answerSummit = 0;
        int answerIntensity = Integer.MAX_VALUE;
        
        for(int summit : summits) {
            if(intensity[summit] < answerIntensity) {
                answerIntensity = intensity[summit];
                answerSummit = summit;
            }
        }
        
        return new int[]{answerSummit, answerIntensity};
    }
}