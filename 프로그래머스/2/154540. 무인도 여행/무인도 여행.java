import java.util.*;

class Solution {
    char[][] map;
    int n, m;
    
    int island;
    int[][] record;
    int[] deltaRow = {-1, 1, 0, 0};
    int[] deltaCol = {0, 0, -1, 1};
    
    int search(int row, int col) {
        Queue<int[]> queue = new ArrayDeque<>();
        
        record[row][col] = island;
        queue.offer(new int[]{row, col});
        
        int food = 0;
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentRow = current[0];
            int currentCol = current[1];
            
            food += map[currentRow][currentCol] - '0';
            
            for(int delta = 0; delta < 4; delta++) {
                int nextRow = currentRow + deltaRow[delta];
                int nextCol = currentCol + deltaCol[delta];
                
                if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m)
                    continue;
                
                if(record[nextRow][nextCol] != 0 || map[nextRow][nextCol] == 'X')
                    continue;
                
                record[nextRow][nextCol] = island;
                queue.offer(new int[]{nextRow, nextCol});
            }
        }
        
        return food;
    }
    
    public int[] solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        map = new char[n][m];
        for(int row = 0; row < n; row++) {
            map[row] = maps[row].toCharArray();
        }
        
        island = 1;
        record = new int[n][m];
        List<Integer> result = new ArrayList<>();
        for(int row = 0; row < n; row++) {
            for(int col = 0; col < m; col++) {
                if(map[row][col] == 'X') {
                    record[row][col] = -1;
                    continue;
                }
                
                if(record[row][col] == 0) {
                    result.add(search(row, col));
                    island++;
                }
            }
        }
        
        if(result.size() == 0)
            return new int[]{-1};
        
        Collections.sort(result);
        int[] answer = new int[island - 1];
        for(int idx = 0; idx < island - 1; idx++) {
            answer[idx] = result.get(idx);
        }
        
        return answer;
    }
}