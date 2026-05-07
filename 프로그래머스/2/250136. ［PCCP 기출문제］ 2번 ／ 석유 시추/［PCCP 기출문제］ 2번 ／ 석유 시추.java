import java.util.*;

class Solution {
    int[][] land;
    int rowSize, colSize;
    
    boolean[][] visited;
    int[] deltaRow = {-1, 1, 0, 0};
    int[] deltaCol = {0, 0, -1, 1};
    
    int[] search(int startRow, int startCol) {
        Queue<int[]> queue = new ArrayDeque<>();
        
        visited[startRow][startCol] = true;
        queue.offer(new int[]{startRow, startCol});
        
        int fromCol = startCol;
        int toCol = startCol;
        int amount = 0;
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            amount++;
            fromCol = Math.min(fromCol, current[1]);
            toCol = Math.max(toCol, current[1]);
            
            for(int delta = 0; delta < 4; delta++) {
                int nextRow = current[0] + deltaRow[delta];
                int nextCol = current[1] + deltaCol[delta];
                
                if(nextRow < 0 || nextRow >= rowSize || 
                   nextCol < 0 || nextCol >= colSize)
                    continue;
                
                if(land[nextRow][nextCol] != 1 || visited[nextRow][nextCol])
                    continue;
                
                visited[nextRow][nextCol] = true;
                queue.offer(new int[]{nextRow, nextCol});
            }
        }
        
        return new int[]{fromCol, toCol, amount};
    }
    
    public int solution(int[][] land) {
        int answer = 0;
        
        this.land = land;
        rowSize = land.length;
        colSize = land[0].length;
        
        visited = new boolean[rowSize][colSize];
        int[] sum = new int[colSize + 1];
        
        for(int row = 0; row < rowSize; row++) {
            for(int col = 0; col < colSize; col++) {
                if(visited[row][col] || land[row][col] == 0) continue;
                
                int[] info = search(row, col);
                int fromCol = info[0];
                int toCol = info[1];
                int amount = info[2];
                
                sum[fromCol] += amount;
                
                if(toCol + 1 < sum.length) {
                    sum[toCol + 1] -= amount;
                }
            }
        }
        
        int currentSum = 0;
        for(int idx = 0; idx < sum.length; idx++) {
            currentSum += sum[idx];
            answer = Math.max(answer, currentSum);
        }
        
        return answer;
    }
}