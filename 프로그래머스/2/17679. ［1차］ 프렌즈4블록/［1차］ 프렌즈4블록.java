import java.util.*;

class Solution {
    char[][] map;
    int m, n;
    
    List<int[]> deleteList;
    
    void delete() {
        for(int[] info : deleteList) {
            int row = info[0];
            int col = info[1];
            
            map[row][col] = '.';
            map[row + 1][col] = '.';
            map[row][col + 1] = '.';
            map[row + 1][col + 1] = '.';
        }
        
        for (int col = 0; col < n; col++) {
            int emptyRow = m - 1;

            for (int row = m - 1; row >= 0; row--) {
                if (map[row][col] == '.')
                    continue;

                map[emptyRow][col] = map[row][col];

                if (emptyRow != row)
                    map[row][col] = '.';

                emptyRow--;
            }

            while (emptyRow >= 0) {
                map[emptyRow][col] = '.';
                emptyRow--;
            }
        }
    }
    
    public int solution(int m, int n, String[] board) {
        this.m = m;
        this.n = n;
        
        map = new char[m][n];
        for(int row = 0; row < m; row++) {
            for(int col = 0; col < n; col++) {
                map[row][col] = board[row].charAt(col);
            }
        }
        
        while(true) {
            deleteList = new ArrayList<>();
            
            for(int row = 0; row < m - 1; row++) {
                for(int col = 0; col < n - 1; col++) {
                    char ch = map[row][col];
                    
                    if(ch == '.')
                        continue;
                    
                    if(map[row + 1][col] == ch && map[row][col + 1] == ch && map[row + 1][col + 1] == ch)
                        deleteList.add(new int[]{row, col});
                }
            }
            
            if(deleteList.size() == 0)
                break;
            
            delete();
        }
        
        int answer = 0;
        
        for(int row = 0; row < m; row++) {
            for(int col = 0; col < n; col++) {
                if(map[row][col] == '.')
                    answer++;
            }
        }
        
        return answer;
    }
}