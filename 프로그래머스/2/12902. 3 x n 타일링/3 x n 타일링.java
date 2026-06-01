class Solution {
    public int solution(int n) {
        if(n % 2 == 1)
            return 0;
        
        if(n == 2)
            return 3;
        
        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[2] = 3;
        
        for(int idx = 4; idx <= n; idx += 2) {
            dp[idx] = dp[idx - 2] * 3;
            
            for(int prevIdx = idx - 4; prevIdx >= 0; prevIdx -= 2) {
                dp[idx] += dp[prevIdx] * 2;
            }
            
            dp[idx] %= 1000000007;
        }
        
        return (int) dp[n];
    }
}