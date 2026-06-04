class Solution {
    public int[] solution(int n) {
        int[][] arr = new int[n][n];
        
        int[] deltaRow = {1, 0, -1};
        int[] deltaCol = {0, 1, -1};
        
        int num = 1;
        int step = n;
        int count = 0;
        int delta = 0;
        int row = 0;
        int col = 0;
        
        while(step > 0) {
            arr[row][col] = num++;
            count++;
            
            if(count == step) {
                count = 0;
                step--;
                delta = (delta + 1) % 3;
            }
            
            row += deltaRow[delta];
            col += deltaCol[delta];
        }
        
        int[] answer = new int[num - 1];
        int idx = 0;
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                if(arr[r][c] == 0)
                    break;
                
                answer[idx++] = arr[r][c];
            }
        }
        
        return answer;
    }
}