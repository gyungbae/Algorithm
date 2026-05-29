import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for(int value : scoville) {
            queue.offer(value);
        }

        int answer = 0;

        while(queue.peek() < K) {
            if(queue.size() < 2) {
                return -1;
            }

            int first = queue.poll();
            int second = queue.poll();

            int mixed = first + second * 2;
            queue.offer(mixed);

            answer++;
        }

        return answer;
    }
}