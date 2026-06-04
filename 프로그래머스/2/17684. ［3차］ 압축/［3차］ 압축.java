import java.util.*;

class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> map = new HashMap<>();
        
        int value = 1;
        for(char ch = 'A'; ch <= 'Z'; ch++) {
            map.put(String.valueOf(ch), value++);
        }
        
        String input = "";
        List<Integer> list = new ArrayList<>();
        for(int idx = 0; idx < msg.length() - 1; idx++) {
            char current = msg.charAt(idx);
            input += current;
            
            char next = msg.charAt(idx + 1);
            String nextKey = input + next;
            
            if(!map.containsKey(nextKey)) {
                list.add(map.get(input));
                map.put(nextKey, value++);
                input = "";
            }
        }
        
        list.add(map.get(input + msg.charAt(msg.length() - 1)));
        
        int[] answer = new int[list.size()];
        for(int idx = 0; idx < list.size(); idx++) {
            answer[idx] = list.get(idx);
        }
        
        return answer;
    }
}