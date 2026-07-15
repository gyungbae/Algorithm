class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int current = 1;
        int coverage = 2 * w + 1;
        
        for(int station : stations) {
            if(current > n)
                break;
            
            int left = station - w;
            int right = station + w;
            
            if(left <= current) {
                current = right + 1; 
                continue;
            }
            
            int gap = left - current;
            answer += gap / coverage;
            
            if(gap % coverage != 0)
                answer++;
            
            current = right + 1;
        }
        
        int gap = n - current + 1;
        
        if(gap > 0) {
            answer += gap / coverage;
            
            if(gap % coverage != 0)
                answer++;
        }
        
        return answer;
    }
}