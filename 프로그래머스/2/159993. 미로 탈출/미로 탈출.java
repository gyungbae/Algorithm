import java.util.*;

class Solution {
    char[][] map;
    int rowSize;
    int colSize;
    
    int startRow, startCol, leverRow, leverCol, endRow, endCol;
    int[][][] record;
    int[] deltaRow = {-1, 1, 0, 0};
    int[] deltaCol = {0, 0, -1, 1};
    
    void search() {
        Queue<int[]> queue = new ArrayDeque<>();
        record = new int[rowSize][colSize][2];
        
        record[startRow][startCol][0] = 1;
        queue.offer(new int[]{startRow, startCol, 0});
        
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            
            if(current[0] == endRow && current[1] == endCol && current[2] == 1)
                break;
            
            for(int delta = 0; delta < 4; delta++) {
                int nextRow = current[0] + deltaRow[delta];
                int nextCol = current[1] + deltaCol[delta];
                
                if(nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize)
                    continue;
                
                if(map[nextRow][nextCol] == 'X')
                    continue;
                
                int state = current[2];

                if (nextRow == leverRow && nextCol == leverCol)
                    state = 1;

                if (record[nextRow][nextCol][state] != 0)
                    continue;

                record[nextRow][nextCol][state] = record[current[0]][current[1]][current[2]] + 1;

                queue.offer(new int[]{nextRow, nextCol, state});
            }
        }
    }
    
    public int solution(String[] maps) {
        rowSize = maps.length;
        colSize = maps[0].length();
        map = new char[rowSize][colSize];
        for(int row = 0; row < rowSize; row++) {
            map[row] = maps[row].toCharArray();
        }
        
        for(int row = 0; row < rowSize; row++) {
            for(int col = 0; col < colSize; col++) {
                char ch = map[row][col];
                
                if(ch == 'S') {
                    startRow = row;
                    startCol = col;
                } else if(ch == 'L') {
                    leverRow = row;
                    leverCol = col;
                } else if(ch == 'E') {
                    endRow = row;
                    endCol = col;
                }
            }
        }
        
        search();
        
        int answer = record[endRow][endCol][1] - 1;
        
        return answer  == 0 ? -1 : answer;
    }
}