import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> firstQueue = new ArrayDeque<>();
        Queue<Integer> secondQueue = new ArrayDeque<>();
        
        long sum1 = 0;
        long sum2 = 0;
        
        for (int num : queue1) {
            firstQueue.offer(num);
            sum1 += num;
        }
        
        for (int num : queue2) {
            secondQueue.offer(num);
            sum2 += num;
        }
        
        long total = sum1 + sum2;
        if (total % 2 != 0) {
            return -1;
        }
        
        long target = total / 2;
        int moveCount = 0;
        int limit = queue1.length * 4;
        
        while (moveCount <= limit) {
            if (sum1 == target) {
                return moveCount;
            }
            
            if (sum1 > target) {
                int movedNum = firstQueue.poll();
                secondQueue.offer(movedNum);
                sum1 -= movedNum;
            } else {
                int movedNum = secondQueue.poll();
                firstQueue.offer(movedNum);
                sum1 += movedNum;
            }
            
            moveCount++;
        }
        
        return -1;
    }
}