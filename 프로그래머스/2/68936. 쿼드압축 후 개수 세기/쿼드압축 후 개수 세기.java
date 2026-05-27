class Solution {
    int[][] arr;
    int size;
    
    int zeroCount;
    int oneCount;
    
    void search(int currentSize, int fromRow, int fromCol) {
        if(currentSize == 1) {
            if(arr[fromRow][fromCol] == 0)
                zeroCount++;
            else
                oneCount++;
            
            return;
        }
        
        int num = arr[fromRow][fromCol];
        boolean same = true;
        for(int row = fromRow; row < fromRow + currentSize; row++) {
            for(int col = fromCol; col < fromCol + currentSize; col++) {
                if(arr[row][col] != num) {
                    same = false;
                    break;
                }
            }
        }
        
        if(same) {
            if(num == 0)
                zeroCount++;
            else
                oneCount++;
            
            return;
        }
        
        int half = currentSize / 2;
        search(half, fromRow, fromCol);
        search(half, fromRow + half, fromCol);
        search(half, fromRow, fromCol + half);
        search(half, fromRow + half, fromCol + half);
    } 
    
    public int[] solution(int[][] arr) {
        this.arr = arr;
        size = arr.length;
        
        search(size, 0, 0);
        
        return new int[]{zeroCount, oneCount};
    }
}