import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        Queue<Integer> queue = new ArrayDeque<>();
        
        for(int time = 0; time < 24; time++) {
            while(!queue.isEmpty() && queue.peek() < time) {
                queue.poll();
            }
            
            int player = players[time];
            
            int pool = m - 1 + m * queue.size();
            
            if(player < pool)
                continue;
            
            int require = player - pool;
            int add = 0;
            if(require % m == 0) {
                add = require / m;
            } else {
                add = require / m + 1;
            }
            
            answer += add;
            for(int i = 0; i < add; i++) {
                queue.offer(time + k - 1);
            }
        }
        
        return answer;
    }
}
