class Solution {
    
    final int MOD = 10007;
    
    public int solution(int n, int[] tops) {
        int size = 2 * n + 1;
        int[] dp = new int[size + 1];
        dp[0] = 1;
        dp[1] = 1;
        
        for (int i = 2; i <= size; i++) {
            if (i % 2 == 0 && tops[(i - 1) / 2] == 1) {
                dp[i] = ((dp[i - 1] * 2) + dp[i - 2]) % MOD;
            } else {
                dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
            }
        }

        return dp[size];
    }
}