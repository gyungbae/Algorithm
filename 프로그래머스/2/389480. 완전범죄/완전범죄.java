import java.util.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        int answer = Integer.MAX_VALUE;
        
        int size = info.length;
        int[][] dp = new int[size][m];
        for(int idx = 0; idx < size; idx++) {
            Arrays.fill(dp[idx], Integer.MAX_VALUE);
        }
        
        dp[0][0] = info[0][0];
        if(info[0][1] < m) {
            dp[0][info[0][1]] = 0;
        }
        
        for(int idx = 1; idx < size; idx++) {
            int printA = info[idx][0];
            int printB = info[idx][1];
            
            for(int prevB = 0; prevB < m; prevB++) {
                if(dp[idx - 1][prevB] == Integer.MAX_VALUE) {
                    continue;
                }
                
                dp[idx][prevB] = Math.min(dp[idx][prevB], dp[idx - 1][prevB] + printA);
                int nextB = prevB + printB;
                if(nextB < m) {
                    dp[idx][nextB] = Math.min(dp[idx][nextB], dp[idx - 1][prevB]);
                }
            }
        }
        
        for(int printB = 0; printB < m; printB++) {
            answer = Math.min(answer, dp[size - 1][printB]);
        }
        
        return answer < n ? answer : -1;
    }
}