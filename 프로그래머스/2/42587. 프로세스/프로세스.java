import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<int[]> queue = new LinkedList<>();
        PriorityQueue<Integer> priorityQueue =
                new PriorityQueue<>(Collections.reverseOrder());

        for(int idx = 0; idx < priorities.length; idx++) {
            queue.offer(new int[]{idx, priorities[idx]});
            priorityQueue.offer(priorities[idx]);
        }

        int answer = 0;

        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            if(current[1] == priorityQueue.peek()) {
                answer++;
                priorityQueue.poll();

                if(current[0] == location)
                    return answer;
            } else {
                queue.offer(current);
            }
        }

        return answer;
    }
}