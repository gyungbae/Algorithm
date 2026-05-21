import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int size = want.length;
        
        int windowSize = 0;
        Map<String, Integer> map = new HashMap<>();
        for(int idx = 0; idx < size; idx++) {
            String key = want[idx];
            int count = number[idx];
            
            windowSize += count;
            map.put(key, idx);
        }
        
        int answer = 0;
        int zeroCount = 0;
        for(int idx = 0; idx < windowSize; idx++) {
            String key = discount[idx];
            
            if(!map.containsKey(key))
                continue;
            
            int numberIdx = map.get(key);
            number[numberIdx]--;
            
            if(number[numberIdx] == 0)
                zeroCount++;
        }
        
        if(zeroCount == size)
            answer++;
        
        int from = 0;
        int to = windowSize - 1;
        while(true) {
            String fromKey = discount[from];
            
            if(map.containsKey(fromKey)) {
                int fromIdx = map.get(fromKey);
                number[fromIdx]++;
                
                if(number[fromIdx] == 1)
                    zeroCount--;
            }
            
            from++;
            
            
            to++;
            
            if(to == discount.length)
                break;
            
            String toKey = discount[to];
            
            if(map.containsKey(toKey)) {
                int toIdx = map.get(toKey);
                number[toIdx]--;
                
                if(number[toIdx] == 0) 
                    zeroCount++;
            }
            
            if(zeroCount == size)
                answer++;
        }
        
        return answer;
    }
}