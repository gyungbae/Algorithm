import java.util.*;

class Solution {
    int rowSize, colSize;
    char[][] map;
    
    boolean[][] outside;
    boolean[][] removed;
    int[] deltaRow = {-1, 1, 0, 0};
    int[] deltaCol = {0, 0, -1, 1};
    
    void checkOutside() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[rowSize + 2][colSize + 2];
        outside = new boolean[rowSize + 2][colSize + 2];
        
        visited[0][0] = true;
        queue.offer(new int[]{0, 0});
        
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            
            for(int delta = 0; delta < 4; delta++) {
                int nextRow = current[0] + deltaRow[delta];
                int nextCol = current[1] + deltaCol[delta];
                
                if(nextRow < 0 || nextRow > rowSize + 1 || nextCol < 0 || nextCol > colSize + 1)
                    continue;
                
                if(visited[nextRow][nextCol])
                    continue;
                
                if(1 <= nextRow && nextRow <= rowSize && 1 <= nextCol && nextCol <= colSize){
                    if(!removed[nextRow][nextCol]) {
                        outside[nextRow][nextCol] = true;
                    } else {
                        visited[nextRow][nextCol] = true;
                        queue.offer(new int[]{nextRow, nextCol});
                    }
                } else {
                    visited[nextRow][nextCol] = true;
                    queue.offer(new int[]{nextRow, nextCol});
                }
            }
        } 
    }
    
    int getRemain() {
        int count = 0;
        for(int row = 1; row <= rowSize; row++) {
            for(int col = 1; col <= colSize; col++) {
                if(!removed[row][col])
                    count++;
            }
        }
        
        return count;
    }
    
    void remove1(char target) {
        for(int row = 1; row <= rowSize; row++) {
            for(int col = 1; col <= colSize; col++) {
                if(removed[row][col])
                    continue;
                
                if(map[row][col] == target && outside[row][col])
                    removed[row][col] = true;
            }
        }
    }
    
    void remove2(char target) {
        for(int row = 1; row <= rowSize; row++) {
            for(int col = 1; col <= colSize; col++) {
                if(removed[row][col])
                    continue;
                
                if(map[row][col] == target)
                    removed[row][col] = true;
            }
        }
    }
    
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        
        rowSize = storage.length;
        colSize = storage[0].length();
        
        map = new char[rowSize + 1][colSize + 1];
        for(int row = 1; row <= rowSize; row++) {
            char[] chArr = storage[row - 1].toCharArray();
            for(int col = 1; col <= colSize; col++) {
                map[row][col] = chArr[col - 1];
            }
        }
        
        outside = new boolean[rowSize + 1][colSize + 1];
        removed = new boolean[rowSize + 1][colSize + 1];
        for(String request : requests) {
            checkOutside();
            
            if(request.length() == 1) {
                remove1(request.charAt(0));
            } else {
              remove2(request.charAt(0));  
            }
        }
        
        return getRemain();
    }
}