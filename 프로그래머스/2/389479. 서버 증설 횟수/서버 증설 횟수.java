import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        Queue<Integer> queue = new ArrayDeque<>();
        for(int time = 0; time < 24; time++) {
            while(!queue.isEmpty() && queue.peek() <= time) {
                queue.poll();
            }
            
            int player = players[time];
            if(player < m) continue;
            
            int require = player / m;
            while(queue.size() < require) {
                queue.offer(time + k);
                answer++;
            }
        }
        
        return answer;
    }
}