import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        
        Arrays.sort(data, (o1, o2) -> {
           if(o1[col - 1] == o2[col - 1])
               return o2[0] - o1[0];
            
            return o1[col - 1] - o2[col - 1];
        });
        
        for(int row = row_begin; row <= row_end; row++) {
            int[] tuple = data[row - 1];
            
            int sum = 0;
            for(int value : tuple) {
                sum += value % row;
            }
            
            answer ^= sum;
        }
        
        return answer;
    }
}