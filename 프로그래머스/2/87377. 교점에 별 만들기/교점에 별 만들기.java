import java.util.*;

class Solution {
    public String[] solution(int[][] line) {
        int size = line.length;
        
        long minRow = Long.MAX_VALUE;
        long minCol = Long.MAX_VALUE;
        long maxRow = Long.MIN_VALUE;
        long maxCol = Long.MIN_VALUE;
        
        Set<String> set = new HashSet<>(); 
        
        for(int idx1 = 0; idx1 < size; idx1++) {
            for(int idx2 = idx1 + 1; idx2 < size; idx2++) {
                int[] line1 = line[idx1];
                long A = line1[0];
                long B = line1[1];
                long E = line1[2];
                
                int[] line2 = line[idx2];
                long C = line2[0];
                long D = line2[1];
                long F = line2[2];
                
                long down = A * D - B * C;
                
                if(down == 0) 
                    continue;
                
                long colTop = B * F - E * D;
                long rowTop = E * C - A * F;
                
                if(colTop % down != 0 || rowTop % down != 0)
                    continue;
                
                long col = colTop / down;
                long row = rowTop / down;
                
                minRow = Math.min(minRow, row);
                maxRow = Math.max(maxRow, row);
                minCol = Math.min(minCol, col);
                maxCol = Math.max(maxCol, col);
                
                set.add(row + "," + col);
            }
        }
        
        int rowSize = (int)(maxRow - minRow + 1);
        int colSize = (int)(maxCol - minCol + 1);
        
        char[][] board = new char[rowSize][colSize];
        
        for(int row = 0; row < rowSize; row++) {
            Arrays.fill(board[row], '.');
        }
        
        for(String point : set) {
            String[] split = point.split(",");
            
            long row = Long.parseLong(split[0]);
            long col = Long.parseLong(split[1]);
            
            int boardRow = (int)(maxRow - row);
            int boardCol = (int)(col - minCol);
            
            board[boardRow][boardCol] = '*';
        }
        
        String[] answer = new String[rowSize];
        
        for(int row = 0; row < rowSize; row++) {
            answer[row] = new String(board[row]);
        }
        
        return answer;
    }
}