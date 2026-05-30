class Solution {
    public int solution(int n) {
        if(n <= 2)
            return n;
        
        int[] dp = new int[n + 1];
        
        dp[1] = 1;
        dp[2] = 2;
        for(int idx = 3; idx <= n; idx++) {
            dp[idx] = (dp[idx - 1] + dp[idx - 2]) % 1000000007;
        }
        
        return dp[n];
    }
}

