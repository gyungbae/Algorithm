class Solution {
    int n;
    int[][] cost, hint;
    int answer;
    
    int[] hintCount;
    
    void DFS(int depth, int sum) {
        if(sum >= answer) return;
        
        if(depth == n) {
            answer = Math.min(answer, sum);
            return;
        }
        
        int stageHint = Math.min(hintCount[depth], n - 1);
        int stageCost = cost[depth][stageHint];
        
        //구매
        if(depth < n - 1) {
            int[] info = hint[depth];
            int bundleCost = info[0];
            for(int idx = 1; idx < info.length; idx++) {
                int stage = info[idx] - 1;
                hintCount[stage]++;
            }

            DFS(depth + 1, sum + stageCost + bundleCost);

            for(int idx = 1; idx < info.length; idx++) {
                int stage = info[idx] - 1;
                hintCount[stage]--;
            }
        }
        
        //구매 X
        DFS(depth + 1, sum + stageCost);
    }
    
    public int solution(int[][] cost, int[][] hint) {
        n = cost.length;
        this.cost = cost;
        this.hint = hint;
        
        answer = Integer.MAX_VALUE;
        hintCount = new int[n];
        DFS(0, 0);
        
        return answer;
    }
}