import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        
        long total = 0;
        for(int work : works) {
            total += work;
            queue.offer(work);
        }
        
        if(total <= n)
            return 0;
        
        while(n > 0) {
            queue.offer(queue.poll() - 1);
            n--;
        }
        
        long answer = 0;
        while(!queue.isEmpty()) {
            int num = queue.poll();
            answer += (long) num * num;
        }
        
        return answer;
    }
}