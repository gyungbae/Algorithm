import java.util.*;

class Solution {
    int[] info;
    int size;
    int[][] edges;
    
    List<Integer>[] adjList;
    int answer;
    
    void search(int sheepCount, int wolfCount, List<Integer> nextNodeList) {
        if(wolfCount >= sheepCount)
                return;
        
        answer = Math.max(answer, sheepCount);
        
        for(int nodeIdx = 0; nodeIdx < nextNodeList.size(); nodeIdx++) {
            int nextNode = nextNodeList.get(nodeIdx);
            
            List<Integer> copiedNodeList = new ArrayList<>(nextNodeList); 
            copiedNodeList.remove(nodeIdx); 
            copiedNodeList.addAll(adjList[nextNode]); 
            
            if(info[nextNode] == 0) {
                search(sheepCount + 1, wolfCount, copiedNodeList);
            } else {
                search(sheepCount, wolfCount + 1, copiedNodeList);
            }
        }
        
    }
    
    public int solution(int[] info, int[][] edges) {
        this.info = info;
        size = info.length;
        this.edges = edges;
        
        adjList = new ArrayList[size];
        for(int node = 0; node < size; node++) {
            adjList[node] = new ArrayList<>();
        }
        
        for(int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            
            adjList[from].add(to);
        }
        
        search(1, 0, adjList[0]);
        
        return answer;
    }
}