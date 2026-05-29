import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, List<String>> map = new HashMap<>();
        for(String[] cloth : clothes) {
            String name = cloth[0];
            String type = cloth[1];
            
            if(map.containsKey(type)) {
                map.get(type).add(name);
            } else {
                map.put(type, new ArrayList<>());
                map.get(type).add(name);
            }
        }
        
        int answer = 1;
        for(List<String> list : map.values()) {
            answer *= (list.size() + 1);
        }
        
        return answer - 1;
    }
}