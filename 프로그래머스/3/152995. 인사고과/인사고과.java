import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int rank = 0;
        int[] stdInfo = scores[0];
        int stdScore1 = stdInfo[0];
        int stdScore2 = stdInfo[1];
        int stdSum = stdScore1 + stdScore2;
        
        Arrays.sort(scores, (o1, o2) -> {
           if(o1[0] == o2[0])
               return o1[1] - o2[1];
            
            return o2[0] - o1[0];
        });
        
        int rank1 = 0;
        int maxScore2 = 0;
        
        for(int[] info : scores) {
            int score1 = info[0];
            int score2 = info[1];
            int sum = score1 + score2;
            
            if(score2 < maxScore2) {
                if(score1 == stdScore1 && score2 == stdScore2) {
                    return -1;
                }
                
                continue;
            }
            
            maxScore2 = Math.max(maxScore2, score2);
            
            if(sum > stdSum) {
                rank++;
            }
        }
        
        return rank + 1;
    }
}