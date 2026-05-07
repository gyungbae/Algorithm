import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);
        
        int prev = -1;
        
        for(int[] target : targets) {
            int from = target[0];
            int to = target[1];
            
            if(from >= prev) {
                answer++;
                prev = to;
            }
        }
        
        return answer;
    }
}