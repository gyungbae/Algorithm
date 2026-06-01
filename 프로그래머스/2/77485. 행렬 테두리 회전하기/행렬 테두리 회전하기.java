class Solution {
    int[][] map;
    int rows, columns;
    
    int rotate(int[] query) {
        int fromRow = query[0];
        int fromCol = query[1];
        int toRow = query[2];
        int toCol = query[3];
        
        int tmp = map[fromRow][fromCol];
        int min = tmp;
        
        for(int row = fromRow; row < toRow; row++) {
            int next = map[row + 1][fromCol];
            min = Math.min(next, min);
            map[row][fromCol] = next;
        }
        
        for(int col = fromCol; col < toCol; col++) {
            int next = map[toRow][col + 1];
            min = Math.min(min, next);
            map[toRow][col] = next;
        }
        
        for(int row = toRow; row > fromRow; row--) {
            int next = map[row - 1][toCol];
            min = Math.min(next, min);
            map[row][toCol] = next;
        }
        
        for(int col = toCol; col > fromCol; col--) {
            int next = map[fromRow][col - 1];
            min = Math.min(next, min);
            map[fromRow][col] = next;
        }
        
        map[fromRow][fromCol + 1] = tmp;
        
        return min;
    }
    
    public int[] solution(int rows, int columns, int[][] queries) {
        map = new int[rows + 1][columns + 1];
        this.rows = rows;
        this.columns = columns;
        
        int num = 1;
        for(int row = 1; row <= rows; row++) {
            for(int col = 1; col <= columns; col++) {
                map[row][col] = num++;
            }
        }
        
        int[] answer = new int[queries.length];
        for(int idx = 0; idx < queries.length; idx++) {
            answer[idx] = rotate(queries[idx]);
        }
        
        return answer;
    }
}