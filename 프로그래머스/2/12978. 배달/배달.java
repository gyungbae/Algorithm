import java.util.*;

class Solution {
    int N, K;
    
    List<int[]>[] adjList;
    int[] distance;
    
    void search() {
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        
        queue.add(new int[]{1, 0});
        
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            
            if(current[1] > distance[current[0]])
                continue;
            
            for(int[] info : adjList[current[0]]) {
                int next = info[0];
                int nextWeight = current[1] + info[1];
                
                if(nextWeight < distance[next]) {
                    distance[next] = nextWeight;
                    queue.add(new int[]{next, nextWeight});
                }
            }
        }
    }

    public int solution(int N, int[][] road, int K) {
        this.N = N;
        this.K = K;
        
        adjList = new ArrayList[N + 1];
        for(int node = 1; node <= N; node++) {
            adjList[node] = new ArrayList<>();
        }
        
        for(int[] info : road) {
            int from = info[0];
            int to = info[1];
            int weight = info[2];
            
            adjList[from].add(new int[]{to, weight});
            adjList[to].add(new int[]{from, weight});
        }
        
        distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;
        
        search();
        
        int answer = 0;
        for(int value : distance) {
            if(value <= K)
                answer++;
        }
        
        return answer;
    }
}