import java.util.*;

class Solution {
    public int solution(int[] topping) {
        if(topping.length == 1)
            return 0;
        
        int[] count = new int[10001];
        int totalTopping = 0;
        
        for(int num : topping) {
            if(count[num] == 0)
                totalTopping++;
            
            count[num]++;
        }
        
        int answer = 0;
        Set<Integer> leftSet = new HashSet<>();
        int rightTopping = totalTopping;
        
        for(int cutIdx = 0; cutIdx < topping.length - 1; cutIdx++) {
            int num = topping[cutIdx];
            
            leftSet.add(num);
            
            if(count[num] == 1) 
                rightTopping--;
            
            count[num]--;
            
            if(leftSet.size() == rightTopping)
                answer++;
        }
        
        return answer;
    }
}