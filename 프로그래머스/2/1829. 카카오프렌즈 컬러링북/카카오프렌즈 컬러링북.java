import java.util.*;

class Solution {
    int m, n;
    int[][] picture;
    
    int areaCount;
    int maxArea;
    boolean[][] visited;
    int[] deltaRow = {-1, 1, 0, 0};
    int[] deltaCol = {0, 0, -1, 1};
    
    void search(int startRow, int startCol) {
        Queue<int[]> queue = new LinkedList<>();
        int color = picture[startRow][startCol];
        
        visited[startRow][startCol] = true;
        queue.offer(new int[]{startRow, startCol});
        
        int count = 0;
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            count++;
            
            for(int delta = 0; delta < 4; delta++) {
                int nextRow = current[0] + deltaRow[delta];
                int nextCol = current[1] + deltaCol[delta];
                
                if(nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n)
                    continue;
                
                if(visited[nextRow][nextCol] || picture[nextRow][nextCol] != color)
                    continue;
                
                visited[nextRow][nextCol] = true;
                queue.offer(new int[]{nextRow, nextCol});
            }
        }
        
        maxArea = Math.max(maxArea, count);
    }
    
    public int[] solution(int m, int n, int[][] picture) {
        this.m = m;
        this.n = n;
        this.picture = picture;
        
        visited = new boolean[m][n];
        for(int row = 0; row < m; row++) {
            for(int col = 0; col < n; col++) {
                if(picture[row][col] == 0 || visited[row][col])
                    continue;
                
                areaCount++;
                search(row, col);
            }
        }
        
        return new int[]{areaCount, maxArea};
    }
}