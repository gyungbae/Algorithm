class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int mod = 1000000007;
        
        boolean[][] isPuddle = new boolean[n][m];
        for(int[] info : puddles) {
            int row = info[1] - 1;
            int col = info[0] - 1;
            isPuddle[row][col] = true;
        }
        
        int[][] dp = new int[n][m];
        dp[0][0] = 1;
        for(int row = 0; row < n; row++) {
            for(int col = 0; col < m; col++) {
                if(row == 0 && col == 0 || isPuddle[row][col])
                    continue;
                
                if(row == 0) {
                    dp[row][col] = dp[row][col - 1] % mod;
                    continue;
                }
                
                if(col == 0) {
                    dp[row][col] = dp[row - 1][col] % mod;
                    continue;
                }
                
                dp[row][col] = (dp[row - 1][col] + dp[row][col - 1]) % mod;
            }
        }
        
        return dp[n - 1][m - 1];
    }
}