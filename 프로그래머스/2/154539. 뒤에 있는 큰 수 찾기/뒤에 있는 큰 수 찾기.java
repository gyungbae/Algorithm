import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int size = numbers.length;
        
        int[] answer = new int[size];
        Arrays.fill(answer, -1);
        
        Stack<Integer> stack = new Stack<>();
        
        for(int idx = 0; idx < size; idx++) {
            while(!stack.isEmpty() && numbers[stack.peek()] < numbers[idx]) {
                int prevIdx = stack.pop();
                answer[prevIdx] = numbers[idx];
            }
            
            stack.push(idx);
        }
        
        answer[size - 1] = -1;
        
        return answer;
    }
}