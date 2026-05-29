import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int size = progresses.length;
        int day = 0;
        
        Queue<Integer> queue = new ArrayDeque<>();
        for(int idx = 0; idx < size; idx++) {
            int remain = 100 - progresses[idx];
            int speed = speeds[idx];
            
            if(remain % speed == 0)
                queue.offer(remain / speed);
            else
                queue.offer(remain / speed + 1);
        }
        
        List<Integer> list = new ArrayList<>();
        while(!queue.isEmpty()) {
            day++;
            
            int count = 0;
            while(!queue.isEmpty() && queue.peek() <= day) {
                queue.poll();
                count++;
            }
            
            if(count > 0)
                list.add(count);
        }
        
        int[] answer = new int[list.size()];
        for(int idx = 0; idx < list.size(); idx++) {
            answer[idx] = list.get(idx);
        }
        
        return answer;
    }
}