import java.util.*;

class Solution {
    String[] conditions;
    char[] arr = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    boolean[] selected = new boolean[8];
    char[] combination = new char[8];
    
    int answer;
    
    boolean isPossible() {
        for(String condition : conditions) {
            char ch1 = condition.charAt(0);
            char ch2 = condition.charAt(2);
            char operator = condition.charAt(3);
            int amount = condition.charAt(4) - '0';
            
            int ch1Idx = -1;
            int ch2Idx = -1;
            
            for(int idx = 0; idx < 8; idx++) {
                if(combination[idx] == ch1)
                    ch1Idx = idx;
                
                if(combination[idx] == ch2)
                    ch2Idx = idx;
            }
            
            int distance = Math.abs(ch1Idx - ch2Idx) - 1;
            
            if(operator == '=') {
                if(distance != amount)
                    return false;
            } else if(operator == '<') {
                if(distance >= amount)
                    return false;
            } else if(operator == '>') {
                if(distance <= amount)
                    return false;
            }
        }
        
        return true;
    }
    
    void search(int depth) {
        if(depth == 8) {
            if(isPossible())
                answer++;
            
            return;
        }
        
        for(int idx = 0; idx < 8; idx++) {
            if(selected[idx])
                continue;
            
            selected[idx] = true;
            combination[depth] = arr[idx];
            search(depth + 1);
            combination[depth] = '0';
            selected[idx] = false;
        }
    }
    
    public int solution(int n, String[] data) {
        answer = 0;
        conditions = data;
        
        search(0);
        
        return answer;
    }
}