class Solution {
    public int solution(int[][] beginning, int[][] target) {
        int rowSize = beginning.length;
        int colSize = beginning[0].length;
        
        boolean[] flippedRows = new boolean[rowSize];
        boolean[] flippedCols = new boolean[colSize];
        
        for(int col = 0; col < colSize; col++) {
            flippedCols[col] = beginning[0][col] != target[0][col];
        }
        
        for(int row = 0; row < rowSize; row++) {
            int value = beginning[row][0];
            
            if(flippedCols[0])
                value = 1 - value;
            
            flippedRows[row] = value != target[row][0];
        }
        
        for(int row = 0; row < rowSize; row++) {
            for(int col = 0; col < colSize; col++) {
                int value = beginning[row][col];
                
                if(flippedRows[row])
                    value = 1 - value;
                
                if(flippedCols[col])
                    value = 1 - value;
                
                if(value != target[row][col])
                    return -1;
            }
        }
        
        int flipCount = 0;
        
        for (boolean flippedRow : flippedRows) {
            if (flippedRow) {
                flipCount++;
            }
        }

        for (boolean flippedCol : flippedCols) {
            if (flippedCol) {
                flipCount++;
            }
        }
        
        int reverse = rowSize + colSize - flipCount;
        
        return Math.min(flipCount, reverse);
    }
}