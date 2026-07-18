class Solution
{
    public int solution(int [][]board)
    {
        int rowSize = board.length;
        int colSize = board[0].length;
        
        int[][] dp = new int[rowSize][colSize];
        int answer = 0;
        
        for(int row = 0; row < rowSize; row++) {
            for(int col = 0; col < colSize; col++) {
                if(board[row][col] == 0)
                    continue;
                
                if(row == 0 || col == 0)
                    dp[row][col] = 1;
                else {
                    dp[row][col] = Math.min(Math.min(dp[row - 1][col], 
                                        dp[row][col - 1]), dp[row - 1][col - 1]) + 1;
                }
                
                answer = Math.max(answer, dp[row][col]);
            }
        }
        
        return answer * answer;
    }
}
