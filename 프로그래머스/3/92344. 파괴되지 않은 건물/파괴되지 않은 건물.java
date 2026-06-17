class Solution {
    public int solution(int[][] board, int[][] skill) {
        int rowSize = board.length;
        int colSize = board[0].length;
        
        int[][] sum = new int[rowSize + 1][colSize + 1];
        for(int[] info : skill) {
            int type = info[0];
            int fromRow = info[1];
            int fromCol = info[2];
            int toRow = info[3];
            int toCol = info[4];
            int degree = info[5];
            
            if(type == 1)
                degree *= -1;
            
            sum[fromRow][fromCol] += degree;
            sum[fromRow][toCol + 1] -= degree;
            sum[toRow + 1][fromCol] -= degree;
            sum[toRow + 1][toCol + 1] += degree;
        }
        
        for(int row = 0; row < rowSize; row++) {
            for(int col = 1; col < colSize; col++) {
                sum[row][col] += sum[row][col - 1];
            }
        }
        
        for(int col = 0; col < colSize; col++) {
            for(int row = 1; row < rowSize; row++) {
                sum[row][col] += sum[row - 1][col];
            }
        }
        
        int answer = 0;
        
        for(int row = 0; row < rowSize; row++) {
            for(int col = 0; col < colSize; col++) {
                if(board[row][col] + sum[row][col] > 0) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}