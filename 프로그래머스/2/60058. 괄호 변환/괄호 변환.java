import java.util.*;

class Solution {
    String answer;
    
    boolean isCorrect(String input) {
        Stack<Character> stack = new Stack<>();
        
        for(int idx = 0; idx < input.length(); idx++) {
            char ch = input.charAt(idx);
            
            if(ch == '(')
                stack.push(ch);
            
            if(ch == ')') {
                if(stack.isEmpty())
                    return false;
                
                stack.pop();
            }
        }
        
        return stack.isEmpty();
    }
    
    String divide(String input) {
        if(input.equals(""))
            return "";
        
        String u = "";
        String v = "";
        int open = 0;
        int close = 0;
        for(int idx = 0; idx < input.length(); idx++) {
            char ch = input.charAt(idx);
            
            if(ch == '(') {
                open++;
            } else {
                close++;
            }
            
            u += ch;
            
            if(open == close) {
                v = input.substring(idx + 1);
                break;
            }
        }
        
        if(isCorrect(u)) {
            u += divide(v);
            return u;
        } else {
            String str = "(";
            str += divide(v);
            str += ")";
            
           for (int idx = 1; idx < u.length() - 1; idx++) {
                char ch = u.charAt(idx);

                if (ch == '(') {
                    str += ")";
                } else {
                    str += "(";
                }
            }

            return str;
        }
    }
    
    public String solution(String p) {  
        return divide(p);
    }
}