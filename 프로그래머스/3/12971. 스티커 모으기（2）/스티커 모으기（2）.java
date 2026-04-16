class Solution {
    public int solution(int sticker[]) {
        int size = sticker.length;
        
         if (size == 1) 
             return sticker[0];
        
        int[][] dp1 = new int[size][2];
        dp1[0][0] = sticker[0];
        
        for(int row = 1; row < size - 1; row++) {
            int plus = sticker[row];
            dp1[row][0] = dp1[row - 1][1] + plus;
            dp1[row][1] = Math.max(dp1[row - 1][0], dp1[row - 1][1]);
        }
        
        int max1 = Math.max(dp1[size - 2][0], dp1[size - 2][1]);
        
        int[][] dp2 = new int[size][2];
        
        for(int row = 1; row < size; row++) {
            int plus = sticker[row];
            dp2[row][0] = dp2[row - 1][1] + plus;
            dp2[row][1] = Math.max(dp2[row - 1][0], dp2[row - 1][1]);
        }
        
        int max2 = Math.max(dp2[size - 1][0], dp2[size - 1][1]);
        
        return Math.max(max1, max2);
    }
}