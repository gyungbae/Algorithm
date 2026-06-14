import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        int size = times.length;
        
        long left = 1;
        long right = (long) times[size - 1] * n;
        long answer = right;
        
        while(left <= right) {
            long mid = left + (right - left) / 2;
            
            long personCount = 0;
            for(int time : times) {
                personCount += mid / time;
                
                if(personCount >= n)
                    break;
            }
            
            if(personCount >= n) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        
        return answer;
    }
}