import java.util.*;

class Solution {
    char[][] board;
    int rowSize, colSize;
    
    int[][] distance;
    int[] deltaRow = {-1, 1, 0, 0};
    int[] deltaCol = {0, 0, -1, 1};
    
    boolean isInRange(int row, int col) {
        if(row < 0 || row >= rowSize || col < 0 || col >= colSize)
            return false;
        
        return true;
    }
    
    int bfs(int startRow, int startCol, int endRow, int endCol) {
        Queue<int[]> queue = new ArrayDeque<>();
        int[][] distance = new int[rowSize][colSize];
        for(int row = 0; row < rowSize; row++) {
            Arrays.fill(distance[row], -1);
        }
        
        distance[startRow][startCol] = 0;
        queue.offer(new int[]{startRow, startCol});
        
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentRow = current[0];
            int currentCol = current[1];
            
            if(currentRow == endRow && currentCol == endCol)
                break;
            
            for(int delta = 0; delta < 4; delta++) {
                int nextRow = currentRow;
                int nextCol = currentCol;
                
                while(true) {
                    int tmpRow = nextRow + deltaRow[delta];
                    int tmpCol = nextCol + deltaCol[delta];
                    
                    if(!isInRange(tmpRow, tmpCol))
                        break;
                    
                    if(board[tmpRow][tmpCol] == 'D')
                        break;
                    
                    nextRow = tmpRow;
                    nextCol = tmpCol;
                }
                
                if(distance[nextRow][nextCol] == -1) {
                    distance[nextRow][nextCol] = distance[currentRow][currentCol] + 1;
                    queue.offer(new int[]{nextRow, nextCol});
                }
                
            }
        }
        
        return distance[endRow][endCol];
    }
    
    public int solution(String[] board) {        
        rowSize = board.length;
        colSize = board[0].length();
        this.board = new char[rowSize][colSize];
        for(int row = 0; row < rowSize; row++) {
            this.board[row] = board[row].toCharArray();
        }
        
        int startRow = -1;
        int startCol = -1;
        int endRow = -1;
        int endCol = -1;
        for(int row = 0; row < rowSize; row++) {
            String line = board[row];
            for(int col = 0; col < colSize; col++) {
                if(this.board[row][col] == 'R') {
                    startRow = row;
                    startCol = col;
                } else if(this.board[row][col] == 'G') {
                    endRow = row;
                    endCol = col;
                }
            }
        }
        
        return bfs(startRow, startCol, endRow, endCol);
    }
}