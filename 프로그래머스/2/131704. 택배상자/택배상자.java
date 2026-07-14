import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        int boxNum = 1;
        Stack<Integer> stack = new Stack<>();
        
        for(int num : order) {
            if(boxNum == num) {
                answer++;
                boxNum++;
                continue;
            }
            
            if(!stack.isEmpty() && stack.peek() == num) {
                answer++;
                stack.pop();
                continue;
            }
            
            if(boxNum > num)
                break;
            
            while(boxNum < num) {
                stack.push(boxNum++);
            }
            
            answer++;
            boxNum++;
        }
        
        return answer;
    }
}