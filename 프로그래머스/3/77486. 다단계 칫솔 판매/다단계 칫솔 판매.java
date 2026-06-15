import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, Integer> map = new HashMap<>();
        for(int idx = 0; idx < enroll.length; idx++) {
            String name = enroll[idx];
            map.put(name, idx);
        }
        
        int[] answer = new int[enroll.length];
        for(int idx = 0; idx < seller.length; idx++) {
            String name = seller[idx];
            int value = amount[idx] * 100;
            int pointer = map.get(name); 
            
            while(true) {
                int nextValue = (int) (value * 0.1);
                answer[pointer] += value - nextValue;
                
                if(nextValue == 0)
                    break;
                
                String referralName = referral[pointer];
                
                if(referralName.equals("-"))
                    break;
                
                pointer = map.get(referralName);
                value = nextValue;
            }
        }
        
        return answer;
    }
}

