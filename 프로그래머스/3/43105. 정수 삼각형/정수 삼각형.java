class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        int rowSize = triangle.length;
        int colSize = triangle[rowSize - 1].length;
        
        int[][] dp = new int[rowSize][colSize];
        dp[0][0] = triangle[0][0];
        
        for(int row = 1; row < rowSize; row++) {
            for(int col = 0; col < row + 1; col++) {
                int plus = triangle[row][col];
                
                if(col == 0) 
                    dp[row][col] = dp[row - 1][col];
                else if(col == row)
                    dp[row][col] = dp[row - 1][col - 1];
                else
                    dp[row][col] = Math.max(dp[row - 1][col], dp[row - 1][col - 1]);
                
                dp[row][col] += plus;
            }
        }
        
        for(int col = 0; col < colSize; col++) {
            answer = Math.max(answer, dp[rowSize - 1][col]);
        }
        
        return answer;
    }
}
