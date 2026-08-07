import java.util.*;

class Solution {
    int m, n;
    char[][] board;
    
    boolean isSame(int row, int col, char ch) {
        if(ch == '0')
            return false;
        
        if(row + 1 >= m || col + 1 >= n)
            return false;
        
        if(board[row + 1][col] != ch || board[row][col + 1] != ch ||
          board[row + 1][col + 1] != ch)
            return false;
        
        return true;
    }
    
    void crack(List<int[]> crackList) {
        for(int[] info : crackList) {
            int row = info[0];
            int col = info[1];
            board[row][col] = '0';
            board[row + 1][col] = '0';
            board[row][col + 1] = '0';
            board[row + 1][col + 1] = '0';
        }
        
        for(int col = 0; col < n; col++) {
            down(col);
        }
    }
    
    void down(int col) {
        int zeroRow = m - 1;
        for(int row = m - 1; row >= 0; row--) {
            if(board[row][col] == '0')
                continue;
            
            char current = board[row][col];
            board[row][col] = '0';
            board[zeroRow][col] = current;
            zeroRow--;
        }
    }
    
    public int solution(int m, int n, String[] board) {
        this.m = m;
        this.n = n;
        this.board = new char[m][n];
        
        for(int row = 0; row < m; row++) {
            this.board[row] = board[row].toCharArray();
        }
        
        while(true) {
            List<int[]> crackList = new ArrayList<>();
            
            for(int row = 0; row < m - 1; row++) {
                for(int col = 0; col < n - 1; col++) {
                    if(isSame(row, col, this.board[row][col]))
                        crackList.add(new int[]{row, col});
                }
            }
            
            if(crackList.size() == 0)
                break;
            
            crack(crackList);
        }
        
        int remainCount = 0;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (this.board[row][col] != '0')
                    remainCount++;
            }
        }

        return m * n - remainCount;
    }
}