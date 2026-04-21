import java.util.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        int answer = Integer.MAX_VALUE;
        
        int size = info.length;
        int[][] dp = new int[size][m];
        for(int row = 0; row < size; row++) {
            Arrays.fill(dp[row], n);
        }
        
        if(info[0][0] < n)
            dp[0][0] = info[0][0];
        
        if(info[0][1] < m)
            dp[0][info[0][1]] = 0;
        
        for(int row = 1; row < size; row++) {
            int printA = info[row][0];
            int printB = info[row][1];
            
            for(int col = 0; col < m; col++) {
                if(dp[row - 1][col] == n)
                    continue;
                
                int nextA = dp[row - 1][col] + printA;
                if(nextA < n)
                    dp[row][col] = Math.min(dp[row][col], nextA);
                
                int nextB = col + printB;
                if(nextB < m)
                    dp[row][nextB] = Math.min(dp[row][nextB], dp[row - 1][col]); 
            }
        }
        
        for(int col = 0; col < m; col++) {
            answer = Math.min(dp[size - 1][col], answer);
        }
        
        return answer == n ? -1 : answer;
    }
}