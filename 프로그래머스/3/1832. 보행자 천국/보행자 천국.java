class Solution {
    int MOD = 20170805;

    public int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[m][n][2];

        dp[0][0][0] = 1;
        dp[0][0][1] = 0; // 수정됨

        for(int row = 0; row < m; row++) {
            for(int col = 0; col < n; col++) {
                if(row == 0 && col == 0)
                    continue;

                if(cityMap[row][col] == 1)
                    continue;

                if(col > 0) {
                    if(cityMap[row][col - 1] == 2) // 수정됨
                        dp[row][col][0] = dp[row][col - 1][0] % MOD;
                    else
                        dp[row][col][0] = (dp[row][col - 1][0] + dp[row][col - 1][1]) % MOD;
                }

                if(row > 0) {
                    if(cityMap[row - 1][col] == 2) // 수정됨
                        dp[row][col][1] = dp[row - 1][col][1] % MOD;
                    else
                        dp[row][col][1] = (dp[row - 1][col][0] + dp[row - 1][col][1]) % MOD;
                }
            }
        }

        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }
}