import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> set = new HashSet<>();
        for(String gem : gems) {
            set.add(gem);
        }
        
        Map<String, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int answerLeft = 0;
        int answerRight = gems.length - 1;
        int minLength = gems.length;
        while(right < gems.length) {
            String rightGem = gems[right];
            map.put(rightGem, map.getOrDefault(rightGem, 0) + 1);
            
            while(map.size() == set.size()) {
                int currentLength = right - left + 1;
                
                if(currentLength < minLength) {
                    minLength = currentLength;
                    answerLeft = left;
                    answerRight = right;
                }
                
                String leftGem = gems[left];
                map.put(leftGem, map.get(leftGem) - 1);

                if(map.get(leftGem) == 0)
                    map.remove(leftGem);
                
                left++;
            }
            
            right++;
        }
        
        return new int[]{answerLeft + 1, answerRight + 1};
    }
}