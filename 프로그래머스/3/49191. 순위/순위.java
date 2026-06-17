class Solution {
    public int solution(int n, int[][] results) {
        boolean[][] win = new boolean[n + 1][n + 1];
        
        for(int[] result : results) {
            int winner = result[0];
            int loser = result[1];
            
            win[winner][loser] = true;
        }
        
        for(int mid = 1; mid <= n; mid++) {
            for(int from = 1; from <= n; from++) {
                for(int to = 1; to <= n; to++) {
                    if(win[from][mid] && win[mid][to])
                        win[from][to] = true;
                }
            }
        }
        
        int answer = 0;
        
        for(int idx1 = 1; idx1 <= n; idx1++) {
            int count = 0;
            
            for(int idx2 = 1; idx2 <= n; idx2++) {
                if(idx1 == idx2)
                    continue;
                
                if(win[idx1][idx2] || win[idx2][idx1])
                    count++;
            }
            
            if(count == n - 1)
                answer++;
        }
        
        return answer;
    }
}