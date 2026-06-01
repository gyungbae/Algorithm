import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        if(cacheSize == 0)
            return cities.length * 5;
        
        Map<String, Integer> map = new HashMap<>();
        
        
        for(int idx = 0; idx < cities.length; idx++) {
            String city = cities[idx].toLowerCase();
            
            if(map.containsKey(city)) {
                answer++;
                map.put(city, idx);
            }
            else {
                if(map.size() == cacheSize) {
                    String minKey = "";
                    int minValue = Integer.MAX_VALUE;
                    
                    for(String key : map.keySet()) {
                        int value = map.get(key);
                        
                        if(value < minValue) {
                            minValue = value;
                            minKey = key;
                        }
                    }
                    
                    map.remove(minKey);
                } 
                    
                map.put(city, idx);
                answer += 5;
            }
                
        }
        
        return answer; 
    }
    
}

