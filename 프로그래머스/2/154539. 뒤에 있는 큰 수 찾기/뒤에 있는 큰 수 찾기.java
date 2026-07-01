import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        Stack<int[]> stack = new Stack<>();
        
        int[] answer = new int[numbers.length];
        
        for(int idx = 0; idx < numbers.length; idx++) {
            int num = numbers[idx];
            
            while(!stack.isEmpty() && stack.peek()[1] < num) {
                int[] info = stack.pop();
                answer[info[0]] = num;
            }
            
            stack.push(new int[]{idx, num});
        }
        
        while(!stack.isEmpty()) {
            int[] info = stack.pop();
            answer[info[0]] = -1;
        }
        
        return answer;
    }
}