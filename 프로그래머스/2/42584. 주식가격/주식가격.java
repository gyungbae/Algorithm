import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        Stack<int[]> stack = new Stack<>();
        for(int idx = 0; idx < prices.length; idx++) {
            int price = prices[idx];
            
            if(stack.isEmpty()) {
                stack.push(new int[]{idx, price});
                continue;
            }
            
            while(!stack.isEmpty() && stack.peek()[1] > price) {
                int[] info = stack.pop();
                answer[info[0]] = idx - info[0];
            }
            
            stack.push(new int[]{idx, price});
        }
        
        while(!stack.isEmpty()) {
            int[] info = stack.pop();
            answer[info[0]] = prices.length - 1 - info[0];
        }
        
        return answer;
    }
}