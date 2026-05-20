import java.util.*;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        List<Integer> list = new ArrayList<>();
    
        while(k >= 1) {
            list.add(k);
            
            if(k == 1)
                break;
            
            if(k % 2 == 0) {
                k /= 2;    
            } else {
                k *= 3;
                k += 1;
            }
        }
        
        int n = list.size();
        
        double[] sum = new double[n];
        for(int idx = 1; idx < n; idx++) {
            int prev = list.get(idx - 1);
            int current = list.get(idx);
            
            int big = Math.max(prev, current);
            int small = Math.min(prev, current);
            
            sum[idx] = sum[idx - 1] + big - (big - small) / 2.0;
        }
        
        double[] answer = new double[ranges.length];
        for(int idx = 0; idx < ranges.length; idx++) {
            int from = ranges[idx][0];
            int to = n - 1 + ranges[idx][1];
            
            if(from > to) {
                answer[idx] = -1.0;
            } else {
                answer[idx] = sum[to] - sum[from];
            }
        }
        
        return answer;
    }
}