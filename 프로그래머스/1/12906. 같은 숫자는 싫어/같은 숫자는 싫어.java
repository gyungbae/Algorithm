import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Stack<Integer> stack = new Stack<>();
        
        for(int num : arr) {
            if(!stack.isEmpty() && stack.peek() == num)
                continue;
            
            stack.push(num);
        }
        
        List<Integer> list = new ArrayList<>();
        while(!stack.isEmpty()) {
            list.add(stack.pop());
        }
        
        int[] answer = new int[list.size()];
        int answerIdx = 0;
        for(int idx = list.size() - 1; idx >= 0; idx--) {
            answer[answerIdx++] = list.get(idx);
        }
        
        return answer;
    }
}