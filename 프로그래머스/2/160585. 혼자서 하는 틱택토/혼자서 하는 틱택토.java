class Solution {
    char[][] board;
    
    int search(char ch) {
        int count = 0;
        
        for(int row = 0; row < 3; row++) {
            boolean same = true;
            for(int col = 0; col < 3; col++) {
                if(board[row][col] != ch) {
                    same = false;
                    break;
                }
            }
            
            if(same) count++;
        }
        
        for(int col = 0; col < 3; col++) {
            boolean same = true;
            for(int row = 0; row < 3; row++) {
                if(board[row][col] != ch) {
                    same = false;
                    break;
                }
            }
            
            if(same) count++;
        }
        
        if(board[1][1] == ch) {
            if(board[0][0] == ch && board[2][2] == ch)
                count++;
            
            if(board[0][2] == ch && board[2][0] == ch)
                count++;
        }
        
        return count;
    }
    
    public int solution(String[] board) {
        this.board = new char[3][3];
        
        int oCount = 0;
        int xCount = 0;
        for(int row = 0; row < 3; row++) {
            String line = board[row];
            for(int col = 0; col < 3; col++) {
                char ch = line.charAt(col);
                this.board[row][col] = ch;
                
                if(ch == 'O') 
                    oCount++;
                else if(ch == 'X')
                    xCount++;
            }
        }
        
        if(xCount > oCount || oCount > xCount + 1)
            return 0;
        
        int oLine = search('O');
        int xLine = search('X');
        
        if(oLine > 0 && xLine > 0)
            return 0;
        
        if(oLine > 0 && oCount != xCount + 1)
            return 0;
        
        if(xLine > 0 && oCount != xCount)
            return 0;
        
        return 1;
    }
}