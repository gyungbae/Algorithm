import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        Queue<Integer> queue = new ArrayDeque<>();
        
        for(int idx = 0; idx < 24; idx++) {
            while(!queue.isEmpty() && queue.peek() <= idx) {
                queue.poll();
            }
            
            int player = players[idx];
            
            if(player < m)
                continue;
            
            while(queue.size() < player / m) {
                queue.offer(idx + k);
                answer++;
            }
        }
        
        return answer;
    }
}