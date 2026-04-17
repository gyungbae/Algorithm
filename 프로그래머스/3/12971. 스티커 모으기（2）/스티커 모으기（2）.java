class Solution {
    public int solution(int sticker[]) {
        int size = sticker.length;
        
        if(size == 1)
            return sticker[0];
        
        if(size == 2)
            return Math.max(sticker[0], sticker[1]);
        
        int[] dp1 = new int[size - 1];
        dp1[0] = sticker[0];
        dp1[1] = Math.max(sticker[0], sticker[1]);
        
        for(int idx = 2; idx < size - 1; idx++) {
            dp1[idx] = Math.max(dp1[idx - 1], dp1[idx - 2] + sticker[idx]);
        }
        
        int max1 = dp1[size - 2];
        
        int[] dp2 = new int[size];
        dp2[1] = sticker[1];
        for(int idx = 2; idx < size; idx++) {
            dp2[idx] = Math.max(dp2[idx - 1], dp2[idx - 2] + sticker[idx]);
        }
        
        int max2 = dp2[size - 1];

        return Math.max(max1, max2);
    }
}