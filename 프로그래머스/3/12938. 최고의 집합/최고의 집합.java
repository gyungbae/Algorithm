import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        if(s < n)
            return new int[]{-1};
        
        int quotient = s / n;
        int remain = s % n;
        
        int[] answer = new int[n];
        Arrays.fill(answer, quotient);
        
        while(remain > 0) {
            for(int idx = n - 1; idx >= 0; idx--) {
                answer[idx]++;
                remain--;
                
                if(remain == 0)
                    break;
            }
        }
        
        return answer;
    }
}