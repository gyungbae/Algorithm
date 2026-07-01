import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Map<Integer, Set<String>> map = new TreeMap<>();
        for(int key = 1; key <= 20; key++) {
            map.put(key, new HashSet<>());
        }
        
        Arrays.sort(phone_book, (o1, o2) -> o1.length() - o2.length());
        
        for(String num : phone_book) {
            int length = num.length();
            
            for(int key = 1; key < length; key++) {
                String substring = num.substring(0, key);
                
                if(map.get(key).contains(substring))
                    return false;
            }
            
            map.get(length).add(num);
        }
        
        return true;
    }
}