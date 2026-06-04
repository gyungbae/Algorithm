import java.util.*;

class Solution {
    
    char[][] priorityArr = {
        {'*', '+', '-'},
        {'*', '-', '+'},
        {'+', '*', '-'},
        {'+', '-', '*'},
        {'-', '*', '+'},
        {'-', '+', '*'}
    };
    
    List<Long> numList = new ArrayList<>();
    List<Character> operatorList = new ArrayList<>();
    
    public long solution(String expression) {
        long answer = 0;
        
        String current = "";
        for(int idx = 0; idx < expression.length(); idx++) {
            char ch = expression.charAt(idx);
            
            if(!('0' <= ch && ch <= '9')) {
                numList.add(Long.parseLong(current));
                current = "";
                operatorList.add(ch);
            } else {
                current += ch;
            }
        }
        
        numList.add(Long.parseLong(current));
        
        for(int priorityIdx = 0; priorityIdx < 6; priorityIdx++) {
            char[] priority = priorityArr[priorityIdx];
            List<Long> tmpNumList = new ArrayList<>(numList);
            List<Character> tmpOperatorList = new ArrayList<>(operatorList);
            
            for(char operator : priority) {
                int operatorIdx = 0;
                
                while(operatorIdx < tmpOperatorList.size()) {
                    if(operator != tmpOperatorList.get(operatorIdx)) {
                        operatorIdx++;
                        continue;
                    }
                    
                    long result = tmpNumList.get(operatorIdx);
                    long rightNum = tmpNumList.get(operatorIdx + 1);

                    if(operator == '+')
                        result += rightNum;
                    else if(operator == '-')
                        result -= rightNum;
                    else
                        result *= rightNum;

                    tmpNumList.remove(operatorIdx + 1);
                    tmpNumList.remove(operatorIdx);
                    tmpNumList.add(operatorIdx, result);

                    tmpOperatorList.remove(operatorIdx);
                }  
            }
                      
            answer = Math.max(answer, Math.abs(tmpNumList.get(0)));
        }
                      
        return answer;
    }
}