import java.util.*;

/**
request의 길이가 1일 경우 상하좌우 탐색으로 외곽과의 연결여부 확인
※요청이 들어온 순간 접근 가능한 -> 일괄 삭제 필요
request의 길이가 2일 경우 모두 삭제
DFS 풀이
**/

class Solution {
    int rowSize;
    int colSize;
    char[][] chStorage;
    boolean[][] removed;
    
    int[] deltaRow = {-1, 1, 0, 0};
    int[] deltaCol = {0, 0, -1, 1};
    
    void request1(String request) {
        char command = request.charAt(0);
        List<int[]> toRemove = new ArrayList<>();
        
        for(int row = 0; row < rowSize; row++) {
            for(int col = 0; col < colSize; col++) {
                if(removed[row][col] || chStorage[row][col] != command) 
                    continue;
                
                if(canRemove(row, col)) {
                    toRemove.add(new int[]{row, col});
                }
            }
        }
        
        for(int[] info : toRemove) {
            removed[info[0]][info[1]] = true;
        }
    }
    
    boolean canRemove(int row, int col) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[rowSize][colSize];
        
        visited[row][col] = true;
        queue.offer(new int[]{row, col});
        
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            
            for(int delta = 0; delta < 4; delta++) {
                int nextRow = current[0] + deltaRow[delta];
                int nextCol = current[1] + deltaCol[delta];
                
                if(nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize) 
                    return true;
                
                if(visited[nextRow][nextCol] || !removed[nextRow][nextCol])
                    continue;
                
                visited[nextRow][nextCol] = true;
                queue.offer(new int[]{nextRow, nextCol});
            }
        }
        
        return false;
    }
    
    void request2(String request) {
        char command = request.charAt(0);
        
        for(int row = 0; row < rowSize; row++) {
            for(int col = 0; col < colSize; col++) {
                if(chStorage[row][col] == command) {
                    removed[row][col] = true;
                }       
            }
        }
    }
    
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        
        rowSize = storage.length;
        colSize = storage[0].length();
        chStorage = new char[rowSize][colSize];
        for(int row = 0; row < rowSize; row++) {
            String line = storage[row];
            for(int col = 0; col < colSize; col++) {
                chStorage[row][col] = line.charAt(col);
            }
        }
        
        removed = new boolean[rowSize][colSize];
        for(String request : requests) {
            if(request.length() == 1) {
                request1(request);
                continue;
            }
            
            request2(request);
        }
        
        for(int row = 0; row < rowSize; row++) {
            for(int col = 0; col < colSize; col++) {
                if(!removed[row][col])
                    answer++;
            }
        }
        
        return answer;
    }
}