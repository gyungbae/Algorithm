import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;

        Stack<Character> stack = new Stack<>();
        
        for(int idx = 0; idx < s.length(); idx++) {
            char ch = s.charAt(idx);
            
            if(ch == '(') {
                stack.push(ch);
            } else {
                if(stack.isEmpty() || stack.peek() != '(') {
                    answer = false;
                    break;
                }
                
                stack.pop();
            }
        }
        
        if(!stack.isEmpty())
            answer = false;

        return answer;
    }
}