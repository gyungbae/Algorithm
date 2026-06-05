import java.util.*;

class Solution {
    char[][] map;
    int rowSize, colSize;
    
    boolean[][][] visited;
    int[] deltaRow = {-1, 0, 1, 0};
    int[] deltaCol = {0, 1, 0, -1};
    
    List<Integer> list = new ArrayList<>();
    
    void search(int startRow, int startCol, int startDelta) {
        Queue<int[]> queue = new ArrayDeque<>();
        
        visited[startRow][startCol][startDelta] = true;
        queue.offer(new int[]{startRow, startCol, startDelta, 0});
        
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int delta = current[2];
            int distance = current[3];
            
            int[] next = move(row, col, delta, map[row][col]);
            int nextRow = next[0];
            int nextCol = next[1];
            int nextDelta = next[2];
            int nextDistance = distance + 1;
            
            if(nextRow == startRow && nextCol == startCol && 
                nextDelta == startDelta) {
                list.add(nextDistance);
                break;
            }
            
            if(visited[nextRow][nextCol][nextDelta])
                continue;
            
            visited[nextRow][nextCol][nextDelta] = true;
            queue.offer(new int[]{nextRow, nextCol, nextDelta, nextDistance});
        }
    }
    
    int[] move(int row, int col, int delta, char ch) { 
        if(ch == 'L')
            delta = (delta + 3) % 4;
        
        if(ch == 'R')
            delta = (delta + 1) % 4;
        
        int nextRow = row + deltaRow[delta];
        if(nextRow < 0) nextRow = rowSize - 1;
        if(nextRow >= rowSize) nextRow = 0;
        
        int nextCol = col + deltaCol[delta];
        if(nextCol < 0) nextCol = colSize - 1;
        if(nextCol >= colSize) nextCol = 0;
        
        return new int[]{nextRow, nextCol, delta};
    }
    
    public int[] solution(String[] grid) {
        rowSize = grid.length;
        colSize = grid[0].length();
        map = new char[rowSize][colSize];
        
        for(int row = 0; row < rowSize; row++) {
            map[row] = grid[row].toCharArray();
        }
        
        visited = new boolean[rowSize][colSize][4];
        for(int row = 0; row < rowSize; row++) {
            for(int col = 0; col < colSize; col++) {
                for(int delta = 0; delta < 4; delta++) {
                    if(!visited[row][col][delta])
                        search(row, col, delta);
                }
            }
        }
        
        Collections.sort(list);
        
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}