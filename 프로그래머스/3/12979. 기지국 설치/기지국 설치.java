class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        
        int currentIdx = 1;
        int range = w * 2 + 1;
        for(int station : stations) {
            int left = station - w;
            int right = station + w;
            
            if(currentIdx < left) {
                int gap = left - currentIdx;
                
                int build = gap / range;
                
                if(gap % range != 0)
                    build++;
                
                answer += build;
            }
            
            currentIdx = right + 1;
        }
        
        if(currentIdx <= n) {
            int gap = n - currentIdx + 1;
            
            int build = gap / range;
            
            if(gap % range != 0)
                build++;
            
            answer += build;
        }

        return answer;
    }
}