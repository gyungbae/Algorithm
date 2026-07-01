import java.util.*;

class Solution {
    public int solution(String dirs) {
        int row = 0;
        int col = 0;
        
        Set<String> set = new HashSet<>();
        
        for(int idx = 0; idx < dirs.length(); idx++) {
            char command = dirs.charAt(idx);
            
            int nextRow = row;
            int nextCol = col;
            if(command == 'U') {
                nextRow++;
            } else if(command == 'D') {
                nextRow--;
            } else if(command == 'R') {
                nextCol++;
            } else {
                nextCol--;
            }
            
            if(nextRow < -5 || nextRow > 5 || nextCol < -5 || nextCol > 5)
                continue;
            
            set.add(row + "," + col + "," + nextRow + "," + nextCol);
            set.add(nextRow + "," + nextCol + "," + row + "," + col);
            
            row = nextRow;
            col = nextCol;
        }
        
        return set.size() / 2;
    }
}