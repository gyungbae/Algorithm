import java.util.*;

class Solution {
    public String solution(String number, int k) {
        Stack<Character> stack = new Stack<>(); 
        
        for(int idx = 0; idx < number.length(); idx++) {
            char currentNum = number.charAt(idx); 
            
            while(k > 0 && !stack.isEmpty() && stack.peek() < currentNum) { 
                stack.pop(); 
                k--;
            }
            
            stack.push(currentNum);
        }
        
        while(k > 0) { 
            stack.pop(); 
            k--;
        }
        
        StringBuilder answer = new StringBuilder();
        for(char num : stack) { 
            answer.append(num);
        }
        
        return answer.toString();
    }
}