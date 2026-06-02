class Solution {
    int n;
    
    int answer;
    boolean[][] placed;
    
    boolean isPossible(int nextRow, int nextCol) {
        int searchRow = nextRow + 1;
        
        while(searchRow < n) {
            if(placed[searchRow][nextCol])
                return false;
            
            searchRow++;
        }
        
        searchRow = nextRow + 1;
        int left = nextCol - 1;
        int right = nextCol + 1;
        while(true) {
            if(searchRow >= n || (left < 0 && right >= n))
                break;
            
            if(left >= 0 && placed[searchRow][left]) 
                return false;
            
            if(right < n && placed[searchRow][right])
                return false;
            
            searchRow++;
            left--;
            right++;
        }
        
        return true;
    }
    
    void search(int row) {
        if(row < 0) {
            answer++;
            return;
        }
        
        for(int col = 0; col < n; col++) {
            if(!isPossible(row, col))
                continue;
            
            placed[row][col] = true;
            search(row - 1);
            placed[row][col] = false;
        }
    }
    public int solution(int n) {
        this.n = n;
        
        placed = new boolean[n][n];
        for(int col = 0; col < n; col++) {
            placed[n - 1][col] = true;
            search(n - 2);
            placed[n - 1][col] = false;
        }
        
        return answer;
    }
}