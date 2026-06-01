import java.util.*;

class Solution {
    int[][] map;
    int rowSize, colSize;
    
    int[] deltaRow = {-1, 1, 0, 0};
    int[] deltaCol = {0, 0, -1, 1};
    
    int search() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rowSize][colSize];
        
        visited[0][0] = true;
        queue.offer(new int[]{0, 0, 1});
        
        int count = 0;
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            
            if(current[0] == rowSize - 1 && current[1] == colSize - 1)
                return current[2];
            
            for(int delta = 0; delta < 4; delta++) {
                int nextRow = current[0] + deltaRow[delta];
                int nextCol = current[1] + deltaCol[delta];
                
                if(nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize)
                    continue;
                
                if(visited[nextRow][nextCol] || map[nextRow][nextCol] == 0)
                    continue;
                
                visited[nextRow][nextCol] = true;
                queue.offer(new int[]{nextRow, nextCol, current[2] + 1});
            }
        }
        
        return -1;
    }
    
    public int solution(int[][] maps) {
        map = maps;
        rowSize = map.length;
        colSize = map[0].length;
        
        return search();
    }
}