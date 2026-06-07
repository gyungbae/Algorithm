import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        
        Deque<Integer> queue = new ArrayDeque<>();
        for(int num : B) {
            queue.offer(num);
        }
        
        int answer = 0;
        int aIdx = A.length - 1;
        while(!queue.isEmpty()) {
            if(queue.peekLast() > A[aIdx]) {
                queue.pollLast();
                answer++;
            } else{
                queue.poll();
            }
            
            aIdx--;
        }
        
        return answer;
    }
}