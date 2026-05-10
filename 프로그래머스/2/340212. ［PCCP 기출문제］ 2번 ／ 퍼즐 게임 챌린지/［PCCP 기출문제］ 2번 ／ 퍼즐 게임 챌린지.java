class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        int size = diffs.length;
        
        int max = 0;
        for(int diff : diffs) {
            max = Math.max(max, diff);
        }
        
        int left = 1;
        int right = max;
        while(left <= right) {
            int mid = (left + right) / 2;
            
            long sum = times[0];
            for(int idx = 1; idx < size; idx++) {
                if(mid < diffs[idx]) {
                    sum += (long) (times[idx] + times[idx - 1]) * (diffs[idx] - mid);
                }
                
                sum += times[idx];
                
                if(sum >= limit)
                    break;
            }
            
            if(sum <= limit) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
}