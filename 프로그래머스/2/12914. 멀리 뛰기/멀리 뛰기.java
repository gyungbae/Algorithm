class Solution {
    public long solution(int n) {
        if(n  <= 2)
            return n;
        
        long[] dp = new long[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for(int idx = 3; idx <= n; idx++) {
            dp[idx] = (dp[idx - 1] + dp[idx - 2]) % 1234567;
        }
        
        return dp[n];
    }
}