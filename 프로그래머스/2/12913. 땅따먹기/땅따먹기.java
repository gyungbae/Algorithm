class Solution {
    int solution(int[][] land) {
        int size = land.length;
        int[][] dp = new int[size][4];

        for(int col = 0; col < 4; col++) {
            dp[0][col] = land[0][col];
        }

        for(int row = 1; row < size; row++) {
            for(int col = 0; col < 4; col++) {
                int max = 0;

                for(int prevCol = 0; prevCol < 4; prevCol++) {
                    if(col == prevCol)
                        continue;

                    max = Math.max(max, dp[row - 1][prevCol]);
                }

                dp[row][col] = max + land[row][col];
            }
        }

        int answer = 0;
        for(int col = 0; col < 4; col++) {
            answer = Math.max(answer, dp[size - 1][col]);
        }

        return answer;
    }
}