import java.util.*;

class Solution {
    int n;
    int[][] wires;
    
    int answer;
    
    void search(int exceptIdx) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        
        queue.offer(1);
        visited[1] = true;
        
        int count = 0;
        while(!queue.isEmpty()) {
            int current = queue.poll();
            count++;
            
            for(int idx = 0; idx < n - 1; idx++) {
                if(idx == exceptIdx)
                    continue;
                
                int[] wire = wires[idx];
                int from = wire[0];
                int to = wire[1];
                
                if(from == current && !visited[to]) {
                    visited[to] = true;
                    queue.offer(to);
                } else if(to == current && !visited[from]) {
                    visited[from] = true;
                    queue.offer(from);
                }
            }
        }
        
        answer = Math.min(answer, Math.abs(count - (n - count)));
    }
    
    public int solution(int n, int[][] wires) {
        this.n = n;
        this.wires = wires;
        
        answer = Integer.MAX_VALUE;
        for(int idx = 0; idx < n - 1; idx++) {
            search(idx);
        }
        
        return answer;
    }
}