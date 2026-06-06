import java.util.*;

class Solution {
    int[][] map;
    int rowSize, colSize;
    
    List<int[]>[] railArr;
    List<int[]> checkList = new ArrayList<>();
    List<int[]> rail3List = new ArrayList<>();
    boolean[][][] visited;
    int[] deltaRow = {-1, 0, 1, 0};
    int[] deltaCol = {0, 1, 0, -1};
    Set<String> set = new HashSet<>();
    int answer;
    
    void fillRailArr() {
        railArr = new ArrayList[8];
        for(int rail = 1; rail <= 7; rail++) {
            railArr[rail] = new ArrayList<>();
        }
        
        railArr[1].add(new int[]{1, 1});
        railArr[1].add(new int[]{3, 3});
        
        railArr[2].add(new int[]{0, 0});
        railArr[2].add(new int[]{2, 2});
        
        railArr[3].add(new int[]{0, 0});
        railArr[3].add(new int[]{1, 1});
        railArr[3].add(new int[]{2, 2});
        railArr[3].add(new int[]{3, 3});
        
        railArr[4].add(new int[]{1, 0});
        railArr[4].add(new int[]{2, 3});
        
        railArr[5].add(new int[]{2, 1});
        railArr[5].add(new int[]{3, 0});
        
        railArr[6].add(new int[]{0, 1});
        railArr[6].add(new int[]{3, 2});
        
        railArr[7].add(new int[]{1, 2});
        railArr[7].add(new int[]{0, 3});
    }
    
    boolean isInRange(int row, int col) {
        return !(row < 0 || row >= rowSize || col < 0 || col >= colSize);
    }
    
    boolean canEnter(int row, int col, int currentDelta) {
        int rail = map[row][col];
        
        if(rail <= 0)
            return false;
        
        for(int[] info : railArr[rail]) {
            if(info[0] == currentDelta)
                return true;
        }
        
        return false;
    }
    
    boolean connectedAll() {
        for(int[] info : rail3List) {
            int row = info[0];
            int col = info[1];
            
            if(!isInRange(row - 1, col)) return false;
            if(!isInRange(row + 1, col)) return false;
            if(!isInRange(row, col - 1)) return false;
            if(!isInRange(row, col + 1)) return false;
        
            int up = map[row - 1][col];
            if(!(up == 2 || up == 6 || up == 7 || up == 3))
                return false;

            int down = map[row + 1][col];
            if(!(down == 2 || down == 4 || down == 5 || down == 3))
                return false;

            int left = map[row][col - 1];
            if(!(left == 1 || left == 5 || left == 6 || left == 3))
                return false;

            int right = map[row][col + 1];
            if(!(right == 1 || right == 4 || right == 7 || right == 3))
                return false;
        }

        return true;
    }
    
    boolean visitedAll() {
        for(int[] info : checkList) {
            int row = info[0];
            int col = info[1];
            
            boolean visitedCheck = false;
            for(int delta = 0; delta < 4; delta++) {
                if(visited[row][col][delta]) {
                    visitedCheck = true;
                    break;
                }        
            }
            
            if(!visitedCheck)
                return false;
        }
        
        return true;
    }
    
    String makeKey() {
        StringBuilder sb = new StringBuilder();
        
        for(int row = 0; row < rowSize; row++) {
            for(int col = 0; col < colSize; col++) {
                sb.append(map[row][col]).append(',');
            }
        }
        
        return sb.toString();
    }
    
    void search(int currentRow, int currentCol, int currentDelta) {
        if(currentRow == rowSize - 1 && currentCol == colSize - 1) {
            if(!canEnter(currentRow, currentCol, currentDelta))
                return;
            
            if(visitedAll() && connectedAll()) {
                String key = makeKey();
                
                if(!set.contains(key)) {
                    set.add(key);
                    answer++;
                }
            }
            
            return;
        }
        
        for(int rail = 1; rail <= 7; rail++) {
            if(map[currentRow][currentCol] != 0 && map[currentRow][currentCol] != rail)
                continue;
            
            int nextDelta = -1;
            
            for(int[] info : railArr[rail]) {
                int from = info[0];
                int to = info[1];
                
                if(currentDelta == from) {
                    nextDelta = to;
                    break;
                }
            }
            
            if(nextDelta == -1)
                continue;
            
            int nextRow = currentRow + deltaRow[nextDelta];
            int nextCol = currentCol + deltaCol[nextDelta];
            
            if(!isInRange(nextRow, nextCol))
                continue;
            
            if(map[nextRow][nextCol] == -1)
                continue;
            
            if(visited[nextRow][nextCol][nextDelta])
                continue;
            
            int prevRail = map[currentRow][currentCol];
            boolean addedRail3 = false;
            
            map[currentRow][currentCol] = rail;
            
            if(prevRail == 0 && rail == 3) {
                rail3List.add(new int[]{currentRow, currentCol});
                addedRail3 = true;
            }
            
            visited[nextRow][nextCol][nextDelta] = true;
            
            search(nextRow, nextCol, nextDelta);
            
            visited[nextRow][nextCol][nextDelta] = false;
            
            if(addedRail3) {
                rail3List.remove(rail3List.size() - 1);
            }
            
            map[currentRow][currentCol] = prevRail;
        }
    }
    
    public int solution(int[][] grid) {
        rowSize = grid.length;
        colSize = grid[0].length;
        map = new int[rowSize][colSize];
        
        for(int row = 0; row < rowSize; row++) {
            for(int col = 0; col < colSize; col++) {
                map[row][col] = grid[row][col];
                
                if(1 <= map[row][col] && map[row][col] <= 7) {
                    checkList.add(new int[]{row, col});
                }
                
                if(map[row][col] == 3) {
                    rail3List.add(new int[]{row, col});
                }
            }
        }
        
        fillRailArr();
        
        visited = new boolean[rowSize][colSize][4];
        visited[0][0][1] = true;
        
        search(0, 0, 1);
        
        return answer;
    }
}