import java.util.*;

class Solution {
    
    boolean isCorrect(Deque<Character> input) {
        Deque<Character> queue = new ArrayDeque<>(input);
        Stack<Character> stack = new Stack<>();
        
        while(!queue.isEmpty()) {
            char ch = queue.pollFirst();
            
            if(ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                if(stack.isEmpty())
                    return false;
                
                char open = stack.pop();
                
                if(ch == ')' && open != '(')
                    return false;
                
                if(ch == '}' && open != '{')
                    return false;
                
                if(ch == ']' && open != '[')
                    return false;
            }
        }
        
        return stack.isEmpty();
    }
    
    public int solution(String s) {
        Deque<Character> queue = new ArrayDeque<>();
        
        for(char ch : s.toCharArray()) {
            queue.offerLast(ch);
        }
        
        int answer = 0;
        
        for(int rotate = 0; rotate < s.length(); rotate++) {
            if(isCorrect(queue))
                answer++;
            
            char ch = queue.pollFirst();
            queue.offerLast(ch);
        }
        
        return answer;
    }
}