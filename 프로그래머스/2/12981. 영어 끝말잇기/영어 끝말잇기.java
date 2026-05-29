import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        Set<String> set = new HashSet<>();
        
        String prev = words[0];
        set.add(prev);
        
        if(prev.length() == 1)
            return new int[]{0, 0};
        
        for(int idx = 1; idx < words.length; idx++) {
            String current = words[idx];
            
            if(current.length() == 1 || set.contains(current) || current.charAt(0) != prev.charAt(prev.length() - 1))
                return new int[]{idx % n + 1, idx / n + 1};
            
            set.add(current);
            prev = current;
        }
        
        return new int[]{0, 0};
    }
}