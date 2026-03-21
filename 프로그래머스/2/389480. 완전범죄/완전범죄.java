import java.util.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        int answer = n;
        
        int size = info.length;
        int[][] dp = new int[size][m];
        for(int idx = 0; idx < size; idx++) {
            Arrays.fill(dp[idx], n);
        }
        
        if(info[0][0] < n) 
            dp[0][0] = info[0][0];
        
        if(info[0][1] < m) 
            dp[0][info[0][1]] = 0;
        
        for(int idx = 1; idx < size; idx++) {
            int valueA = info[idx][0];
            int valueB = info[idx][1];
            
            for(int printB = 0; printB < m; printB++) {
                dp[idx][printB] = Math.min(dp[idx][printB], dp[idx - 1][printB] + valueA);
                
                int nextPrintB = printB + valueB;
                if(nextPrintB >= m)
                    continue;
                
                dp[idx][nextPrintB] = Math.min(dp[idx][nextPrintB], dp[idx - 1][printB]);
            }
        }
        
        for(int printB = 0; printB < m; printB++) {
            answer = Math.min(answer, dp[size - 1][printB]);
        }
         
        return answer == n ? -1 : answer;
    }
}
