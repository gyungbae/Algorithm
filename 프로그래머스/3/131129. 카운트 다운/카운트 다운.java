import java.util.*;

class Solution {
    public int[] solution(int target) {
        int[][] dp = new int[target + 1][2];
        
        for(int score = 1; score <= target; score++) {
            dp[score][0] = Integer.MAX_VALUE;
            dp[score][1] = -1;
        }
        
        List<int[]> scoreList = new ArrayList<>();
        for(int num = 1; num <= 20; num++) {
            scoreList.add(new int[]{num, 1});
            scoreList.add(new int[]{num * 2, 0});
            scoreList.add(new int[]{num * 3, 0});
        }
        scoreList.add(new int[]{50, 1});
        
        for(int currentScore = 1; currentScore <= target; currentScore++) {
            for(int[] info : scoreList) {
                int dart = info[0];
                int singleCount = info[1];
                
                if(dart > currentScore)
                    continue;
                
                int prevScore = currentScore - dart;
                
                int tmpCount = dp[prevScore][0] + 1;
                int tmpSingleCount = dp[prevScore][1] + singleCount;
                
                if (tmpCount < dp[currentScore][0]) {
                    dp[currentScore][0] = tmpCount;
                    dp[currentScore][1] = tmpSingleCount;
                } else if (tmpCount == dp[currentScore][0]) {
                    dp[currentScore][1] = Math.max(dp[currentScore][1], tmpSingleCount);
                }
            }
        }
        
        return dp[target];
    }
}